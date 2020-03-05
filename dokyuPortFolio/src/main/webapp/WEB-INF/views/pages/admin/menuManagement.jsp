<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/js/admin/menuManagement.js"></script>
<link rel="stylesheet" href="/static/css/admin/menuManagement.css" />

<div class="menuManagement_btnArea">
	<button type="button" class="btn btn-success" id="menuManagement_menuAddBtn">메뉴등록</button>
	<button type="button" class="btn btn-danger" id="menuManagement_menuDelBtn">메뉴삭제</button>
</div>
<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">
				<div class="custom-control custom-checkbox">
					<input type="checkbox" class="custom-control-input" id="customCheckAll">
					<label class="custom-control-label" for="customCheckAll"></label>
				</div>
			</th>
			<th scope="col">메뉴번호</th>
			<th scope="col">메뉴아이디</th>
			<th scope="col">메뉴명</th>
			<th scope="col">URL</th>
			<th scope="col">부모메뉴번호</th>
			<th scope="col">등록자아이디</th>
			<th scope="col">등록일시</th>
		</tr>
	</thead>
	<tbody id="menuManagement_list">
	</tbody>
</table>
<div>
	<ul class="pagination" id="pagination">
	</ul>
</div>

<!-- menuAdd dialog -->
<div id="menuManagement-menuAdd-dialog" title="메뉴">
	<form id="menuManagementMenuAddForm">
		<input type="hidden" id="menuAddDialogMenuOpenMode"/>
		<input type="hidden" id="menuAddDialogMenuSeq"/>
		<div class="form-group">
			<label for="menuAddDialogMenuId">메뉴아이디</label>
			<input type="text" class="form-control" name="menuAddDialogMenuId" id="menuAddDialogMenuId" placeholder="메뉴아이디 입력">
			<small class="form-text text-muted">중복되는 아이디는 사용할수없습니다.</small>
		</div>
		<div class="form-group">
			<label for="menuAddDialogMenuName">메뉴명</label>
			<input type="text" class="form-control" name="menuAddDialogMenuName" id="menuAddDialogMenuName" placeholder="메뉴명 입력">
		</div>
		<div class="form-group">
			<label for="menuAddDialogMenuUrl">메뉴 URL</label>
			<input type="text" class="form-control" name="menuAddDialogMenuUrl" id="menuAddDialogMenuUrl" placeholder="메뉴 URL 입력">
		</div>
		<div class="form-group">
			<label for="menuAddDialogParentMenuSeq">상위 메뉴아이디</label>
			<input type="text" class="form-control" name="menuAddDialogParentMenuSeq" id="menuAddDialogParentMenuSeq" placeholder="상위 메뉴아이디 입력">
		</div>
	</form>
	<button type="button" class="btn btn-success" id="menuManagementMenuModify" style="display:none">수정</button>
	<button type="button" class="btn btn-success" id="menuManagementMenuAdd" style="display:none">추가</button>
	<button type="button" class="btn btn-danger" id="menuManagementMenuAddCancel">취소</button>
</div>

<script id="menuManagement_list_template" type="text/x-jquery-tmpl">
{{each(i,item) list}}
	<tr class="table-secondary" data-menuSeq="\${item.menuSeq}">
		<th>
			<div class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input" id="customCheck\${i}">
				<label class="custom-control-label" for="customCheck\${i}"></label>
			</div>
		</th>
		<td>\${item.menuSeq}</td>
		<td>\${item.menuId}</td>
		<td>\${item.menuName}</td>
		<td>\${item.menuUrl}</td>
		<td>\${item.parentMenuSeq}</td>
		<td>\${item.instId}</td>
		<td>\${item.instDate}</td>
	</tr>
{{/each}}
</script>