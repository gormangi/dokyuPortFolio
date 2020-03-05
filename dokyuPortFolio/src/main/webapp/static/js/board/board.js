$(function(){
	
	fn.loadBoard();
	
	
	
	$("#board_list").on("click","tr",function(){
		var boardSeq = $(this).attr('data-boardSeq');
		if(boardSeq != null){
			document.location.href = "/board/boardDetail.do?bs="+boardSeq;
		}
	});
	
});

var fn = {
		
	blockPostCnt : '8',
		
	loadBoard : function(nowPageNumber){
		
		var categoryId = $("#board_categoryId").val();
		
		$.ajax({
			url : '/board/loadBoard.do',
			type : 'post',
			dataType : 'json',
			data : {
				nowPageNumber : nowPageNumber,
				blockPostCnt : fn.blockPostCnt,
				categoryId : categoryId
			},
			success : function(res){
				$("#board_categoryName").text(res.category.categoryName);
				
				$("#board_list").empty();
				$('#board_list_template').tmpl(res).appendTo('#board_list');
				fn.incPaging(res.paging);
			}
		});
		
	},
	
	incPaging : function(paging){
		
		var html = [];
		if(paging.nowPageNumber != 1){
			html.push('<li><a href="javascript:fn.loadBoard(1)" class="button">Prev</a></li>');
		}else{
			html.push('<li><a href="javascript:fn.loadBoard(1)" class="button disabled">Prev</a></li>');
		}
		
		for(var i=paging.startPageNumber; i<=paging.endPageNumber; i++){
			if(i == paging.nowPageNumber){
				html.push('<li><a href="javascript:fn.loadBoard('+i+')" class="page active">'+i+'</a></li>');
			}else{
				html.push('<li><a href="javascript:fn.loadBoard('+i+')" class="page">'+i+'</a></li>');
			}
		}
		
		if(paging.nowPageNumber != paging.totPageNumber){
			html.push('<li><a href="javascript:fn.loadBoard('+paging.totPageNumber+')" class="button">Next</a></li>');
		}else{
			html.push('<li><a href="javascript:fn.loadBoard('+paging.totPageNumber+')" class="button disabled">Next</a></li>');
		}		
		$("#board_pagination").html(html.join(''));
	}
	
	
	
}