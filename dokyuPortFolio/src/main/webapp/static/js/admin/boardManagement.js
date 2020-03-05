$(function(){
	
	fn.categoryLoad();
	
	fn.boardLoad();
	
	$("#boardManagement_write").on("click",function(){
		document.location.href = "/admin/boardWriteView.do?wMod=N";
	});
	
	$("#boardManagement_list").on("click","tr",function(){
		var boardSeq = $(this).attr('data-boardSeq');
		document.location.href = "/admin/boardWriteView.do?wMod=U&bs="+boardSeq;
	});
	
	$("#boardManagement_categoryId").on("change",function(){
		fn.categoryId = $(this).val();
		fn.boardLoad();
	});
	
});

var fn = {
		
	categoryId : '',
	
	categoryLoad : function(){
		
		$.ajax({
			url : '/admin/categoryListForSelectBox.do',
			type : 'post',
			dataType : 'json',
			success : function(res){
				var html = [];
				$.each(res,function(i , item){
					html.push('<option value='+item.categoryId+'>'+item.categoryName+'</option>');
				});
				$("#boardManagement_categoryId").append(html.join(''));
			}
		});
		
	},
	
	boardLoad : function(nowPageNumber){
		
		$.ajax({
			url : '/admin/boardList.do',
			type : 'post',
			dataType : 'json',
			data : {
				nowPageNumber : nowPageNumber,
				categoryId : fn.categoryId
			},
			success : function(res){
				$("#boardManagement_list").empty();
				$('#boardManagement_list_template').tmpl(res).appendTo('#boardManagement_list');
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
		html.push('<a class="page-link" href="javascript:fn.boardLoad(1)">&laquo;</a>');
		html.push('</li>');
		
		for(var i=paging.startPageNumber; i<=paging.endPageNumber; i++){
			if(i == paging.nowPageNumber){
				html.push('<li class="page-item active">');
			}else{
				html.push('<li class="page-item">');
			}
			html.push('<a class="page-link" href="javascript:fn.boardLoad('+i+')">'+i+'</a>');
			html.push('</li>');
		}
		
		if(paging.nowPageNumber != paging.totPageNumber){
			html.push('<li class="page-item">');
		}else{
			html.push('<li class="page-item disabled">');
		}
		html.push('<a class="page-link" href="javascript:fn.boardLoad('+paging.totPageNumber+')">&raquo;</a>');
		html.push('</li>');
		
		$("#pagination").html(html.join(''));
	}
	
}