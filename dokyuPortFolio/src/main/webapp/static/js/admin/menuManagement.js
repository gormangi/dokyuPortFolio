$(function(){
	
	fn.menuLoad();
	
	fn.modalCreate();
	
	fn.onValidation();
	
	$("#menuManagement_list").on("click","td",function(){
		var menuSeq = $(this).closest('tr').attr('data-menuSeq');
		fn.openMenuModifyDialog(menuSeq);
	});
	
	$("#menuManagement_menuAddBtn").click(function(){
		fn.openMenuAddDialog();
	});
	
	$("#menuManagement_menuDelBtn").click(function(){
		fn.menuDelete();
	});
	
	$("#menuManagementMenuAdd").click(function(){
		$("#menuManagementMenuAddForm").submit();
	});
	
	$("#menuManagementMenuModify").click(function(){
		$("#menuManagementMenuAddForm").submit();
	});
	
	$("#menuManagementMenuAddCancel").click(function(){
		$("#menuManagement-menuAdd-dialog").dialog('close');
	});
	
	$("#customCheckAll").click(function(){
		fn.checkboxAllCheck();
	});
});

var fn = {
	
	menuLoad : function(nowPageNumber){
		
		$.ajax({
			url : '/admin/menuList.do',
			data : {
				nowPageNumber : nowPageNumber
			},
			dataType : 'json',
			type : 'post',
			success : function(res){
				$("#menuManagement_list").empty();
				$('#menuManagement_list_template').tmpl(res).appendTo('#menuManagement_list');
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
		
		$("#menuManagement-menuAdd-dialog").dialog({
			autoOpen: false,
			width: 450,
			resizable: true,
			modal: true,
			close: function() {
				$("#menuManagement-menuAdd-dialog").dialog("close");
			}
		});
		
	},
	
	onValidation : function(){
		$("#menuManagementMenuAddForm").validate({
			rules: {
				menuAddDialogMenuId : {
					required : true,
					minlength : 3,
					maxlength : 19
				},
				menuAddDialogMenuName : {
					required : true,
					minlength : 2,
					maxlength : 29
				},
				menuAddDialogMenuUrl : {
					required : true,
					minlength : 1,
					maxlength : 99
				},
				menuAddDialogParentMenuSeq : {
					required : true,
					minlength : 3,
					maxlength : 19
				}
			},
			messages: {
				menuAddDialogMenuId : {
					required : "메뉴아이디를 입력해주세요",
					minlength : "메뉴아이디는 3~19자리로 입력하세요",
					maxlength : "메뉴아이디는 3~19자리로 입력하세요"
				},
				menuAddDialogMenuName : {
					required : "메뉴명을 입력해주세요",
					minlength : "메뉴명은 2~29자리로 입력하세요",
					maxlength : "메뉴명은 2~29자리로 입력하세요"
				},
				menuAddDialogMenuUrl : {
					required : "메뉴URL을 입력해주세요",
					minlength : "메뉴URL은 1~99자리로 입력하세요",
					maxlength : "메뉴URL은 1~99자리로 입력하세요"
				},
				menuAddDialogParentMenuSeq : {
					required : "상위메뉴아이디를 입력해주세요",
					minlength : "상위메뉴아이디는 3~19자리로 입력하세요",
					maxlength : "상위메뉴아이디는 3~19자리로 입력하세요"
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
				var mode = $("#menuAddDialogMenuOpenMode").val();
				if(mode == 'N'){
					fn.menuAdd();
				}else{
					fn.menuModify();
				}
			}
		});
	},
	
	menuAdd : function(){
		
		var param = {
				menuId : $("#menuAddDialogMenuId").val(),
				menuName : $("#menuAddDialogMenuName").val(),
				menuUrl : $("#menuAddDialogMenuUrl").val(),
				parentMenuSeq : $("#menuAddDialogParentMenuSeq").val()
		}
		
		$.ajax({
			url : '/admin/menuAdd.do',
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(res){
				if(res.state == 'success'){
					fn.menuLoad();
					$("#menuManagement-menuAdd-dialog").dialog("close");
				}else{
					alert('사용중인 메뉴아이디입니다');
					return false;
				}
			}
		});
		
	},
	
	menuModify : function(){
		
		$("#menuAddDialogMenuId").attr('disabled',false);
		
		var param = {
				menuSeq : $("#menuAddDialogMenuSeq").val(),
				menuId : $("#menuAddDialogMenuId").val(),
				menuName : $("#menuAddDialogMenuName").val(),
				menuUrl : $("#menuAddDialogMenuUrl").val(),
				parentMenuSeq : $("#menuAddDialogParentMenuSeq").val()
		}
		
		$.ajax({
			url : '/admin/menuModify.do',
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(res){
				if(res.state == 'success'){
					fn.menuLoad();
					$("#menuManagement-menuAdd-dialog").dialog("close");
				}else{
					alert('데이터베이스 에러입니다. 관리자에게 문의하세요');
					return false;
				}
			}
		});
		
	},
	
	openMenuAddDialog : function(){
		$("#menuManagement-menuAdd-dialog form")[0].reset();
		$("#menuManagementMenuModify").hide();
		$("#menuManagementMenuAdd").show();
		$("#menuAddDialogMenuOpenMode").val('N');
		$("#menuAddDialogMenuId").attr('disabled',false);
		$("#menuManagement-menuAdd-dialog").dialog('open');
	},
	
	openMenuModifyDialog : function(menuSeq){
		
		$.ajax({
			url : '/admin/loadMenuInfo.do',
			type : 'post',
			data : {
				menuSeq : menuSeq
			},
			dataType : 'json',
			success : function(res){
				$("#menuAddDialogMenuSeq").val(menuSeq);
				$("#menuAddDialogMenuId").val(res.menuId);
				$("#menuAddDialogMenuName").val(res.menuName);
				$("#menuAddDialogMenuUrl").val(res.menuUrl);
				$("#menuAddDialogParentMenuSeq").val(res.parentMenuSeq);
				$("#menuManagementMenuModify").show();
				$("#menuManagementMenuAdd").hide();
				$("#menuAddDialogMenuOpenMode").val('U');
				$("#menuAddDialogMenuId").attr('disabled',true);
				
				$("#menuManagement-menuAdd-dialog").dialog('open');
			}
		});
		
	},
	
	checkboxAllCheck : function(){
		
		if($("#customCheckAll").is(":checked")){
			$("#menuManagement_list").find('input:checkbox').each(function(){
				$(this).attr('checked',true);
			});
		}else{
			$("#menuManagement_list").find('input:checkbox').each(function(){
				$(this).attr('checked',false);
			});
		}
	},
	
	menuDelete : function(){
		var menuSeqList = new Array();
		$("#menuManagement_list").find('input:checkbox').each(function(){
			if($(this).is(':checked')){
				var menuSeq = $(this).closest('tr').attr('data-menuSeq');
				menuSeqList.push(menuSeq);
			}
		});
		
		if(menuSeqList == ''){
			alert('메뉴를 선택해주세요');
			return false;
		}else{
			
			if(confirm('선택하신 메뉴를 삭제하시겠습니까?')){
				$.ajax({
					url : '/admin/menuDelete.do',
					type : 'post',
					dataType : 'json',
					data : {
						menuSeqList : menuSeqList
					},
					success : function(res){
						if(res.state == 'success'){
							fn.menuLoad();
						}else{
							alert('데이터베이스 에러입니다. 관리자에게 문의하세요');
							return false;
						}
					}
				});
			}
			
		}
	}
	
}