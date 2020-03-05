<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/static/css/board/boardDetail.css" />
<script type="text/javascript" src="/static/js/board/boardDetail.js"></script>

<input type="hidden" id="boardDetail_boardSeq" value="${boardSeq }"/>
<input type="hidden" id="userId" value="${userId }"/>

<div class="inner" id="boardDetail_inner">
	<header id="header">
		<a href="index.html" class="logo"><strong id="boardDetail_categoryName"></strong></a>
		<ul class="icons">
			<li><span class="label" id="boardDetail_inst"></span></li>
		</ul>
	</header>
	<div id="boardDetail_attatchFileDiv">
		<a href="javascript:void(0);" id="boardDetail_attachFileBtn" class="button icon solid fa-download boardDetail_attachFileBtn">ATTATCH FILE</a>
		<div class="box" style="display:none;">
			<ul class="alt">
			</ul>
		</div>
	</div>
	<section id="boardDetail_article" style="max-width:50em;">
	</section>
	<section id="boardDetail_reple" style="max-width:50em;">
	</section>
	<section id="boardDetail_repleWrite" style="max-width:50em;">
		<h3>COMMENT</h3>
		<form id="boardDetail_writeform">
			<div class="row gtr-uniform" id="boardDetail_comment_write">
				<div class="col-6 col-12-xsmall">
					<input type="text" placeholder="Title" id="boardDetail_comment_title" name="boardDetail_comment_title"/>
				</div>
				<div class="col-12">
					<textarea name="demo_message" id="demo_message" placeholder="Enter your message" rows="6"></textarea>
				</div>
				<div class="col-12">
					<ul class="actions">
						<li><input type="submit" value="Write Comment" class="primary" id="boardDetail_commentwrite" /></li>
					</ul>
				</div>
			</div>
		</form>
	</section>
</div>