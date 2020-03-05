<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Dokyu's blog</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/static/css/main/base.css" />
		<script src="/static/component/basic/jquery.min.js"></script>
		<script src="/static/component/jquery-ui-1.12.1/jquery-ui.min.js"></script>
		<script src="/static/component/jquery-validation-1.19.1/dist/jquery.validate.min.js"></script>
		<script src="/static/component/jquery-tmpl-master/jquery.tmpl.min.js"></script>
		<link rel="stylesheet" href="/static/component/jquery-ui-1.12.1/jquery-ui.css">
		
		<script type="text/javascript">
			var userInfo = {
					userSeq : '${userSeq}',
					userId : '${userId}',
					userName : '${userName}',
					email : '${email}',
					userAuth : '${userAuth}',
					useYn : '${useYn}'
			}
		</script>
	</head>
	<body class="is-preload">
		<div id="wrapper">
			<div id="main">
				<tiles:insertAttribute name="body"/>
			</div>
			<div id="sidebar">
				<tiles:insertAttribute name="sidebar"/>
			</div>
		</div>
		
		<script src="/static/component/basic/browser.min.js"></script>
		<script src="/static/component/basic/breakpoints.min.js"></script>
		<script src="/static/component/basic/util.js"></script>
		<script src="/static/component/basic/main.js"></script>
	</body>
</html>