<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/static/css/main/main.css" />
<script type="text/javascript" src="/static/js/main/main.js"></script>

<div class="inner">
	<header id="header">
		<a href="javascript:void(0);" class="logo"><strong>Dokyu</strong>'s blog</a>
		<ul class="icons" id="login_Area"></ul>
	</header>
	<section id="banner">
	</section>
	<section id="main_bodyViewArea">
	</section>
</div>

<!-- login dialog -->
<div id="dialog-login-form" class="dialog-login-form" title="로그인">
	<form id="dialogLoginForm">
		<div class="loginForm_userId_div">
			<label for="loginForm_userId">아이디 :</label>
			<input type="text" name="loginForm_userId" id="loginForm_userId" placeholder="아이디 입력">
		</div>
		<div class="loginForm_userPwd_div">
			<label for="loginForm_userPwd">비밀번호 :</label>
			<input type="password" name="loginForm_userPwd" id="loginForm_userPwd" placeholder="비밀번호 입력">
		</div>
	</form>
</div>

<!-- sign dialog -->
<div id="dialog-sign-form" class="dialog-sign-form" title="회원가입">
	<form id="dialogSignForm">
		<div class="signForm_userId_div">
			<label for="signForm_userId">아이디 :</label>
			<input type="text" name="signForm_userId" id="signForm_userId" placeholder="아이디 입력">
		</div>
		<div class="signForm_userName_div">
			<label for="signForm_userName">닉네임 :</label>
			<input type="text" name="signForm_userName" id="signForm_userName" placeholder="닉네임 입력">
		</div>
		<div class="signForm_email_div">
			<label for="signForm_email">이메일 :</label>
			<input type="text" name="signForm_email" id="signForm_email" placeholder="이메일 입력">
		</div>
		<div class="signForm_userPwd_div">
			<label for="signForm_userPwd">비밀번호 :</label>
			<input type="password" name="signForm_userPwd" id="signForm_userPwd" placeholder="비밀번호 입력">
		</div>
		<div class="signForm_userPwdConfirm_div">
			<label for="signForm_userPwdConfirm">비밀번호 확인 :</label>
			<input type="password" name="signForm_userPwdConfirm" id="signForm_userPwdConfirm" placeholder="비밀번호 확인 입력">
		</div>
	</form>
</div>

<script id="banner_template" type="text/x-jquery-tmpl">
	<div class="content">
		<header>
			<h2>\${mainTopView.title}</h2>
		</header>
		<p>\${mainTopView.content}</p>
		<ul class="actions">
			<li><a href='javascript:document.location.href="/board/boardDetail.do?bs=\${mainTopView.boardSeq}"' class="button big">자세히</a></li>
		</ul>
	</div>
	<span class="image object">
		{{if mainTopViewThumbnail != null}}
			<img src="\${mainTopViewThumbnail.fileUrl}" alt="" />
		{{else}}
			<img src="/static/img/pic10.jpg" alt="" />
		{{/if}}
	</span>
</script>

<script id="main_bodyViewArea_template" type="text/x-jquery-tmpl">
	<header class="major">
		<h2>\${subject.categoryName}</h2>
	</header>
	<div class="posts">
		{{each(i,item) list}}
			<article>
				<a href='javascript:document.location.href="/board/boardDetail.do?bs=\${item.view.boardSeq}"' class="image">
					{{if item.viewThumbnail != null}}
						<img src="\${item.viewThumbnail.fileUrl}" alt="" />
					{{else}}
						<img src="/static/img/pic01.jpg" alt="" />
					{{/if}}
				</a>
				<h3>\${item.view.title}</h3>
				<p>\${item.view.content}</p>
				<ul class="actions">
					<li><a href='javascript:document.location.href="/board/boardDetail.do?bs=\${item.view.boardSeq}"' class="button">자세히</a></li>
				</ul>
			</article>
		{{/each}}
	</div>
</script>