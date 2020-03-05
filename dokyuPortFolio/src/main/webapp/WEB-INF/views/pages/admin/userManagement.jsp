<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/js/admin/userManagement.js"></script>
<link rel="stylesheet" href="/static/css/admin/userManagement.css" />

<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">사용자번호</th>
			<th scope="col">사용자아이디</th>
			<th scope="col">사용자명</th>
			<th scope="col">사용자이메일</th>
			<th scope="col">사용자권한</th>
			<th scope="col">사용여부</th>
			<th scope="col">가입일시</th>
		</tr>
	</thead>
	<tbody id="userManagement_list">
	</tbody>
</table>
<div>
	<ul class="pagination" id="pagination">
	</ul>
</div>

<!-- userModify dialog -->
<div id="userManagement-userModify-dialog" title="사용자">
	<form id="userManagementUserModifyForm">
		<input type="hidden" id="userModifyDialogUserSeq"/>
		<div class="form-group">
			<label for="userModifyDialogUserId">사용자아이디</label>
			<input type="text" class="form-control" name="userModifyDialogUserId" id="userModifyDialogUserId" placeholder="사용자아이디 입력">
		</div>
		<div class="form-group">
			<label for="userModifyDialogUserName">사용자명</label>
			<input type="text" class="form-control" name="userModifyDialogUserName" id="userModifyDialogUserName" placeholder="사용자명 입력">
		</div>
		<div class="form-group">
			<label for="userModifyDialogEmail">이메일</label>
			<input type="text" class="form-control" name="userModifyDialogEmail" id="userModifyDialogEmail" placeholder="이메일 입력">
		</div>
		<div class="form-group">
			<label for="userModifyDialogUserAuth">유저권한</label>
			<select class="form-control" name="userModifyDialogUserAuth" id="userModifyDialogUserAuth">
				<option value="1">일반유저</option>
				<option value="0">관리자</option>
			</select>
		</div>
		<div class="form-group">
			<label for="userModifyDialogUseYn">사용여부</label>
			<select class="form-control" name="userModifyDialogUseYn" id="userModifyDialogUseYn">
				<option value="Y">사용</option>
				<option value="N">삭제</option>
			</select>
		</div>
	</form>
	<button type="button" class="btn btn-success" id="userManagementUserModify">수정</button>
	<button type="button" class="btn btn-danger" id="userManagementUserModifyCancel">취소</button>
</div>

<script id="userManagement_list_template" type="text/x-jquery-tmpl">
{{each(i,item) list}}
	<tr class="table-secondary" data-userSeq="\${item.userSeq}">
		<td>\${item.userSeq}</td>
		<td>\${item.userId}</td>
		<td>\${item.userName}</td>
		<td>\${item.email}</td>
		<td>\${item.userAuth}</td>
		<td>\${item.useYn}</td>
		<td>\${item.regDate}</td>
	</tr>
{{/each}}
</script>