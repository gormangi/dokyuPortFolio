$(function(){
	$("#mainReturnBtn").click(function(){
		location.href = "/main/dokyuPortFolio.do";
	});
	
	headerFn.menuHighlight();
});

var headerFn = {
	
	menuHighlight : function(){
		$("#navbarColor01 li").removeClass('active');
		if(adminMenuName == 'menuManagement'){
			$("#menuManagementBtn").parent().addClass('active');
		}
		if(adminMenuName == 'userManagement'){
			$("#userManagementBtn").parent().addClass('active');
		}
		if(adminMenuName == 'boardManagement'){
			$("#boardManagementBtn").parent().addClass('active');
		}
		if(adminMenuName == 'categoryManagement'){
			$("#categoryManagementBtn").parent().addClass('active');
		}
	}
	
}