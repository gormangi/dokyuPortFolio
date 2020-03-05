$(function(){
	
	sidebarFn.loadMenuList();
	
	sidebarFn.loadSidebarView();
	
	sidebarFn.searchList();
	
	sidebarFn.searchEnterEvent();
	
	$("#board_search_text").on("click",function(e){
		e.stopPropagation();
	});
	
	
});

var sidebarFn = {
		
	loadSidebarView : function(){
		
		$.ajax({
			url : '/board/loadView.do',
			type : 'post',
			dataType : 'json',
			data : {
				mainViewArea : 'SIDEBAR'
			},
			success : function(res){
				$.each(res.list,function(i , item){
					item.view.content = item.view.content.replace(/(<([^>]+)>)/gi, "");
				});
				
				$("#sidebar_viewArea").empty();
				$('#sidebar_viewArea_template').tmpl(res).appendTo('#sidebar_viewArea');
			}
		});
		
	},
	
	loadMenuList : function(){
		
		$.ajax({
			url : '/main/loadMenuList.do',
			type : 'post',
			dataType : 'json',
			success : function(res){
				$("#sidebar_menuList").empty();
				var html = [];
				$.each(res,function(i , item){
					html.push('<li><a href='+item.menuUrl+'>'+item.menuName+'</a></li>');
				});
				$("#sidebar_menuList").html(html.join(''));
			}
		});
		
	},
	
	searchList : function(){
		
		$("#sidebar_searchForm").on("click",function(){
			var board_search_text = $("#board_search_text").val();
			
			if(board_search_text == null||board_search_text == ""){
				alert("검색어를 입력해주세요");
				return false;
			}
			
			document.location.href = "/board/boardSearch.do?board_search_text="+board_search_text;
		});
		
	},
	
	searchEnterEvent : function(){
		
		$("#board_search_text").keyup(function(e){
			e.preventDefault();
			if(e.keyCode == 13){
				var board_search_text = $("#board_search_text").val();
				
				if(board_search_text == null||board_search_text == ""){
					alert("검색어를 입력해주세요");
					return false;
				}
				
				document.location.href = "/board/boardSearch.do?board_search_text="+board_search_text;
			}
		});

	}
	
}