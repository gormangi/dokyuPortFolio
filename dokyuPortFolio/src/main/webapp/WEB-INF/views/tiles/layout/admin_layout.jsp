<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin</title>
	<script src="/static/component/basic/jquery.min.js"></script>
	<script src="/static/component/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
	<script src="/static/component/jquery-tmpl-master/jquery.tmpl.min.js"></script>
	<script src="/static/component/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script src="/static/component/jquery-validation-1.19.1/dist/jquery.validate.min.js"></script>
	<link rel="stylesheet" href="/static/component/jquery-ui-1.12.1/jquery-ui.css">
	<link rel="stylesheet" href="/static/css/admin/bootstrap.min.css">
	
	<script type="text/javascript">
		var userInfo = {
				userSeq : '${userSeq}',
				userId : '${userId}',
				userName : '${userName}',
				email : '${email}',
				userAuth : '${userAuth}'
		}
		
		if(userInfo.userSeq == ''){
			alert('로그인해야합니다.');
			document.location.href = "/main/dokyuPortFolio.do";
		}else{
			if(userInfo.userAuth != 0){
				alert('관리자만 접근할수있습니다');
				document.location.href = "/main/dokyuPortFolio.do";
			}
		}
		
		var adminMenuName = '${adminMenuName}';
	</script>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header"/>
	</header>
	<section>
		<tiles:insertAttribute name="section"/>
	</section>
</body>
</html>