$(function(){
	
	fn.userLoad();
	
	fn.modalCreate();
	
	fn.onValidation();
	
	$("#userManagement_list").on("click","tr",function(){
		var userSeq = $(this).attr('data-userSeq');
		fn.openUserModifyDialog(userSeq);
	});
	
	$("#userManagementUserModifyCancel").on("click",function(){
		$("#userManagement-userModify-dialog").dialog('close');
	});
	
	$("#userManagementUserModify").on("click",function(){
		$("#userManagementUserModifyForm").submit();
	});
});

var fn = {
	
	userLoad : function(nowPageNumber){
		
		$.ajax({
			url : '/admin/userList.do',
			type : 'post',
			dataType : 'json',
			data : {
				nowPageNumber : nowPageNumber
			},
			success : function(res){
				$("#userManagement_list").empty();
				$('#userManagement_list_template').tmpl(res).appendTo('#userManagement_list');
				fn.incPaging(res.paging);
			}
		});
		
	},
	
	incPaging : function(paging){
		var html = [];
		if(paging.nowPageNumber != 1){
			html.push('<li class="page-item">');
		}else{
			html.push('<li class="page-item disabled">');
		}
		html.push('<a class="page-link" href="javascript:fn.menuLoad(1)">&laquo;</a>');
		html.push('</li>');
		
		for(var i=paging.startPageNumber; i<=paging.endPageNumber; i++){
			if(i == paging.nowPageNumber){
				html.push('<li class="page-item active">');
			}else{
				html.push('<li class="page-item">');
			}
			html.push('<a class="page-link" href="javascript:fn.menuLoad('+i+')">'+i+'</a>');
			html.push('</li>');
		}
		
		if(paging.nowPageNumber != paging.totPageNumber){
			html.push('<li class="page-item">');
		}else{
			html.push('<li class="page-item disabled">');
		}
		html.push('<a class="page-link" href="javascript:fn.menuLoad('+paging.totPageNumber+')">&raquo;</a>');
		html.push('</li>');
		
		$("#pagination").html(html.join(''));
	},
	
	modalCreate : function(){
		$("#userManagement-userModify-dialog").dialog({
			autoOpen: false,
			width: 450,
			resizable: true,
			modal: true,
			close: function() {
				$("#userManagement-userModify-dialog").dialog("close");
			}
		});
	},
	
	openUserModifyDialog : function(userSeq){
		
		$.ajax({
			url : '/admin/loadUserInfo.do',
			type : 'post',
			data : {
				userSeq : userSeq
			},
			dataType : 'json',
			success : function(res){
			
				$("#userModifyDialogUserSeq").val(res.userSeq);
				$("#userModifyDialogUserId").val(res.userId);
				$("#userModifyDialogUserName").val(res.userName);
				$("#userModifyDialogEmail").val(res.email);
				$("#userModifyDialogUserAuth").val(res.userAuth);
				$("#userModifyDialogUseYn").val(res.useYn);
				$("#userModifyDialogUserId").attr('disabled',true);
				$("#userManagement-userModify-dialog").dialog('open');
			}
		});
		
	},
	
	onValidation : function(){
		
		$.validator.addMethod("userNameCheck",function(value,element){
			return this.optional(element) || /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/.test(value);
		});
		
		$("#userManagementUserModifyForm").validate({
			rules: {
				userModifyDialogUserName : {
					userNameCheck : true
				},
				userModifyDialogEmail : {
					minlength : 12,
					maxlength : 50,
					email : true
				}
			},
			messages: {
				userModifyDialogUserName : {
					userNameCheck : "닉네임은 2~20자 내로 입력하세요"
				},
				userModifyDialogEmail : {
					minlength : "이메일은 {0}자 이상 입력해주세요",
					maxlength : "이메일은 {0}자 이하로 입력해주세요",
					email : "이메일 형식에 맞게 입력해주세요"
				}
			},
			onkeyup : false,
			onclick : false,
			onfocusout : false,
			showErrors : function(errorMap, errorList){
				if(errorList.length){
					alert(errorList[0].message);
				}
			},
			submitHandler : function(){
				fn.userModify();
			}
		});
	},
	
	userModify : function(){
		
		if(confirm('입력하신 내용으로 수정하시겠습니까?')){
			$("#userModifyDialogUserId").attr('disabled',false);
			
			var param = {
				userSeq : $("#userModifyDialogUserSeq").val(),
				userId : $("#userModifyDialogUserId").val(),
				userName : $("#userModifyDialogUserName").val(),
				email : $("#userModifyDialogEmail").val(),
				userAuth : $("#userModifyDialogUserAuth").val(),
				useYn : $("#userModifyDialogUseYn").val()
			}
			
			$.ajax({
				url : '/admin/userModify.do',
				type : 'post',
				data : param,
				dataType : 'json',
				success : function(res){
					
					if(res.state == 'success'){
						fn.userLoad();
						$("#userManagement-userModify-dialog").dialog('close');
					}else{
						alert('데이터베이스 에러입니다. 관리자에게 문의해주세요');
						return false;
					}
					
				}
			});
		}
	}
	
}