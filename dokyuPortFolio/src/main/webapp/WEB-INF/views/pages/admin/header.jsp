<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/js/admin/header.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="#">관리자</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="/admin/menuManagement.do" id="menuManagementBtn">메뉴관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/admin/userManagement.do" id="userManagementBtn">사용자관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/admin/boardManagement.do" id="boardManagementBtn">게시판관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/admin/categoryManagement.do" id="categoryManagementBtn">카테고리관리</a>
			</li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" id="mainReturnBtn" type="button">메인으로</button>
		</form>
	</div>
</nav>