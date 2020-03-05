$(function(){
	
	fn.modalCreate();
	
	fn.categoryLoad();
	
	fn.onValidation();
	
	$("#categoryManagement_list").on("click","td",function(){
		var categorySeq = $(this).closest('tr').attr('data-categorySeq');
		fn.openCategoryModifyDialog(categorySeq);
	});
	
	$("#categoryManagement_categoryAddBtn").click(function(){
		fn.openCategoryAddDialog();
	});
	
	$("#categoryManagement_categoryDelBtn").click(function(){
		fn.categoryDelete();
	});
	
	$("#categoryManagementCategoryAddCancel").click(function(){
		$("#categoryManagement-categoryAdd-dialog").dialog('close');
	});
	
	$("#categoryManagementCategoryAdd").on("click",function(){
		$("#categoryManagementCategoryAddForm").submit();
	});
	
	$("#categoryManagementCategoryModify").on("click",function(){
		$("#categoryManagementCategoryAddForm").submit();
	});
	
	$("#customCheckAll").click(function(){
		fn.checkboxAllCheck();
	});
	
});

var fn = {
	
	categoryLoad : function(nowPageNumber){
		
		$.ajax({
			url : '/admin/categoryList.do',
			type : 'post',
			dataType : 'json',
			data : {
				nowPageNumber : nowPageNumber
			},
			success : function(res){
				$("#categoryManagement_list").empty();
				$('#categoryManagement_list_template').tmpl(res).appendTo('#categoryManagement_list');
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
		$("#categoryManagement-categoryAdd-dialog").dialog({
			autoOpen: false,
			width: 450,
			resizable: true,
			modal: true,
			close: function() {
				$("#categoryManagement-categoryAdd-dialog").dialog("close");
			}
		});
	},
	
	openCategoryAddDialog : function(){
		$("#categoryManagement-categoryAdd-dialog form")[0].reset();
		$("#categoryManagementCategoryModify").hide();
		$("#categoryManagementCategoryAdd").show();
		$("#categoryAddDialogCategoryOpenMode").val('N');
		$("#categoryAddDialogCategoryId").attr('disabled',false);
		$("#categoryManagement-categoryAdd-dialog").dialog('open');
	},
	
	onValidation : function(){
		$("#categoryManagementCategoryAddForm").validate({
			rules: {
				categoryAddDialogCategoryId : {
					required : true,
					minlength : 3,
					maxlength : 39
				},
				categoryAddDialogCategoryName : {
					required : true,
					minlength : 2,
					maxlength : 99
				}
			},
			messages: {
				categoryAddDialogCategoryId : {
					required : "카테고리아이디를 입력해주세요",
					minlength : "카테고리아이디는 3~39자리로 입력하세요",
					maxlength : "카테고리아이디는 3~39자리로 입력하세요"
				},
				categoryAddDialogCategoryName : {
					required : "카테고리명을 입력해주세요",
					minlength : "카테고리명은 2~99자리로 입력하세요",
					maxlength : "카테고리명은 2~99자리로 입력하세요"
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
				var mode = $("#categoryAddDialogCategoryOpenMode").val();
				if(mode == 'N'){
					fn.categoryAdd();
				}else{
					fn.categoryModify();
				}
			}
		});
	},
	
	categoryAdd : function(){
		
		var param = {
			categoryId : $("#categoryAddDialogCategoryId").val(),
			categoryName : $("#categoryAddDialogCategoryName").val(),
			mainViewArea : $("#categoryAddDialogMainViewArea").val()
		}
		
		$.ajax({
			url : '/admin/categoryAdd.do',
			type : 'post',
			dataType : 'json',
			data : param,
			success : function(res){
				if(res.state == 'success'){
					fn.categoryLoad();
					$("#categoryManagement-categoryAdd-dialog").dialog("close");
				}else{
					alert('사용중인 카테고리아이디입니다');
					return false;
				}
			}
		});
	},
	
	openCategoryModifyDialog : function(categorySeq){
		
		$.ajax({
			url : '/admin/loadCategoryInfo.do',
			type : 'post',
			data : {
				categorySeq : categorySeq
			},
			dataType : 'json',
			success : function(res){
				$("#categoryAddDialogCategorySeq").val(categorySeq);
				$("#categoryAddDialogCategoryId").val(res.categoryId);
				$("#categoryAddDialogCategoryName").val(res.categoryName);
				$("#categoryAddDialogMainViewArea").val(res.mainViewArea);
				$("#categoryManagementCategoryModify").show();
				$("#categoryManagementCategoryAdd").hide();
				$("#categoryAddDialogCategoryOpenMode").val('U');
				$("#categoryAddDialogCategoryId").attr('disabled',true);
				
				$("#categoryManagement-categoryAdd-dialog").dialog('open');
			}
		});
		
	},
	
	categoryModify : function(){
		
		$("#categoryAddDialogCategoryId").attr('disabled',false);
		
		var param = {
				categorySeq : $("#categoryAddDialogCategorySeq").val(),
				categoryId : $("#categoryAddDialogCategoryId").val(),
				categoryName : $("#categoryAddDialogCategoryName").val(),
				mainViewArea : $("#categoryAddDialogMainViewArea").val()
		}
		
		$.ajax({
			url : '/admin/categoryModify.do',
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(res){
				if(res.state == 'success'){
					fn.categoryLoad();
					$("#categoryManagement-categoryAdd-dialog").dialog("close");
				}else{
					alert('데이터베이스 에러입니다. 관리자에게 문의하세요');
					return false;
				}
			}
		});
	},
	
	checkboxAllCheck : function(){
		if($("#customCheckAll").is(":checked")){
			$("#categoryManagement_list").find('input:checkbox').each(function(){
				$(this).attr('checked',true);
			});
		}else{
			$("#categoryManagement_list").find('input:checkbox').each(function(){
				$(this).attr('checked',false);
			});
		}
	},
	
	categoryDelete : function(){
		var categorySeqList = new Array();
		$("#categoryManagement_list").find('input:checkbox').each(function(){
			if($(this).is(':checked')){
				var categorySeq = $(this).closest('tr').attr('data-categorySeq');
				categorySeqList.push(categorySeq);
			}
		});
		
		if(categorySeqList == ''){
			alert('카테고리를 선택해주세요');
			return false;
		}else{
			
			if(confirm('선택하신 카테고리를 삭제하시겠습니까?')){
				$.ajax({
					url : '/admin/categoryDelete.do',
					type : 'post',
					dataType : 'json',
					data : {
						categorySeqList : categorySeqList
					},
					success : function(res){
						if(res.state == 'success'){
							fn.categoryLoad();
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