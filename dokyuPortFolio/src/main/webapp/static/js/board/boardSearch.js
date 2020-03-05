$(function(){
	
	fn.SearchBoardList();
	
	$("#board_searchlist").on("click","tr",function(){
		var boardSeq = $(this).attr('data-boardSeq');
		if(boardSeq != null){
			document.location.href = "/board/boardDetail.do?bs="+boardSeq;
		}
	});
	
});

var fn = {
		
		blockPostCnt : '8',
		
		SearchBoardList : function(nowPageNumber){
			
			var title = $("#search_text").val();
			
			$.ajax({
				url : '/loadBoardSearchlist.do',
				type : 'post',
				dataType : 'json',
				data : {
					nowPageNumber : nowPageNumber,
					title : title,
					blockPostCnt :fn.blockPostCnt
				},
				success : function(res){
					$("#board_searchlist").empty();
					$('#board_searchilst_template').tmpl(res).appendTo('#board_searchlist');
					fn.incPaging(res.paging);
				}
			});
			
		},
		
		incPaging : function(paging){
			
			var html = [];
			if(paging.nowPageNumber != 1){
				html.push('<li><a href="javascript:fn.SearchBoardList(1)" class="button">Prev</a></li>');
			}else{
				html.push('<li><a href="javascript:fn.SearchBoardList(1)" class="button disabled">Prev</a></li>');
			}
			
			for(var i=paging.startPageNumber; i<=paging.endPageNumber; i++){
				if(i == paging.nowPageNumber){
					html.push('<li><a href="javascript:fn.SearchBoardList('+i+')" class="page active">'+i+'</a></li>');
				}else{
					html.push('<li><a href="javascript:fn.SearchBoardList('+i+')" class="page">'+i+'</a></li>');
				}
			}
			
			if(paging.nowPageNumber != paging.totPageNumber){
				html.push('<li><a href="javascript:fn.SearchBoardList('+paging.totPageNumber+')" class="button">Next</a></li>');
			}else{
				html.push('<li><a href="javascript:fn.SearchBoardList('+paging.totPageNumber+')" class="button disabled">Next</a></li>');
			}		
			$("#search_board_pagination").html(html.join(''));
		}
}