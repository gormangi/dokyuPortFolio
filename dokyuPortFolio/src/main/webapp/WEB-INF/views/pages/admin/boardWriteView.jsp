<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/component/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/static/js/admin/boardWriteView.js"></script>
<link rel="stylesheet" href="/static/css/admin/boardWriteView.css" />

<input type="hidden" id="boardWriteView_writeMod" value="${writeMod }"/>
<input type="hidden" id="boardWriteView_boardSeq" value="${boardSeq }"/>

<fieldset>
	<div class="form-group row">
		<div class="col-sm-6">
			<label for="boardWriteView_categoryId">카테고리</label>
			<select class="form-control" name="boardWriteView_categoryId" id="boardWriteView_categoryId">
			</select>
		</div>
		<div class="col-sm-6">
			<label for="boardWriteView_mainTopView">메인최상위글 여부</label>
			<select class="form-control" name="boardWriteView_mainTopView" id="boardWriteView_mainTopView">
				<option value="N">일반글</option>
				<option value="Y">메인최상위글</option>
			</select>
		</div>
	</div>
	<div class="form-group" id="boardWriteView_thumbnailFileDiv">
		<label for="boardWriteView_thumbnail">썸네일</label>
		<input type="file" class="form-control-file" id="boardWriteView_thumbnail" name="boardWriteView_thumbnail">
		<small class="form-text text-muted">확장자  jpg,gif,png</small>
	</div>
	<div class="form-group row" id="boardWriteView_thumbnailFileInfo">
	</div>
	<div class="form-group">
		<label for="boardWriteView_title">제목</label>
		<input type="text" class="form-control" id="boardWriteView_title" name="boardWriteView_title" placeholder="제목을 입력하세요">
	</div>
	<div class="form-group">
		<label for="boardWriteView_explanation">글 설명</label>
		<input type="text" class="form-control" id="boardWriteView_explanation" name="boardWriteView_explanation" placeholder="글 설명을 입력하세요">
	</div>
	<div class="form-group">
		<label for="exampleTextarea">본문</label>
		<textarea class="form-control" id="boardWriteView_content" name="boardWriteView_content"></textarea>
	</div>
	<div class="form-group row" id="boardWriteView_fileInfo">
	</div>
	<div class="form-group">
		<label for="boardWriteView_categoryThumbnail">첨부파일</label>
		<div id="boardWriteView_file_div">
		</div>
		<span class="badge badge-pill badge-danger" style="cursor:pointer;" id="boardWriteView_fileAddBtn">파일 추가</span>
	</div>
	
	<div class="boardWriteView_btnArea">
		<button type="button" class="btn btn-success" id="boardWriteView_boardAddBtn" style="display:none;">글 등록</button>
		<button type="button" class="btn btn-warning" id="boardWriteView_boardModifyBtn" style="display:none;">글 수정</button>
		<button type="button" class="btn btn-secondary" id="boardWriteView_boardDelBtn" style="display:none;">글 삭제</button>
		<button type="button" class="btn btn-danger" id="boardWriteView_boardCancelBtn">취소</button>
	</div>
</fieldset>