<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/js/admin/categoryManagement.js"></script>
<link rel="stylesheet" href="/static/css/admin/categoryManagement.css" />

<div class="categoryManagement_btnArea">
	<button type="button" class="btn btn-success" id="categoryManagement_categoryAddBtn">카테고리등록</button>
	<button type="button" class="btn btn-danger" id="categoryManagement_categoryDelBtn">카테고리삭제</button>
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
			<th scope="col">카테고리번호</th>
			<th scope="col">카테고리아이디</th>
			<th scope="col">카테고리명</th>
			<th scope="col">메인표시영역</th>
			<th scope="col">등록자아이디</th>
			<th scope="col">등록일시</th>
		</tr>
	</thead>
	<tbody id="categoryManagement_list">
	</tbody>
</table>
<div>
	<ul class="pagination" id="pagination">
	</ul>
</div>

<!-- categoryAdd dialog -->
<div id="categoryManagement-categoryAdd-dialog" title="카테고리">
	<form id="categoryManagementCategoryAddForm">
		<input type="hidden" id="categoryAddDialogCategoryOpenMode"/>
		<input type="hidden" id="categoryAddDialogCategorySeq"/>
		<div class="form-group">
			<label for="categoryAddDialogCategoryId">카테고리아이디</label>
			<input type="text" class="form-control" name="categoryAddDialogCategoryId" id="categoryAddDialogCategoryId" placeholder="카테고리아이디 입력">
			<small class="form-text text-muted">중복되는 아이디는 사용할수없습니다.</small>
		</div>
		<div class="form-group">
			<label for="categoryAddDialogCategoryName">카테고리명</label>
			<input type="text" class="form-control" name="categoryAddDialogCategoryName" id="categoryAddDialogCategoryName" placeholder="카테고리명 입력">
		</div>
		<div class="form-group">
			<label for="categoryAddDialogMainViewArea">메인표시영역</label>
			<select id="categoryAddDialogMainViewArea" name="categoryAddDialogMainViewArea" class="form-control">
				<option value="">표시안함</option>
				<option value="BODY">BODY</option>
				<option value="SIDEBAR">SIDEBAR</option>
			</select>
		</div>
	</form>
	<button type="button" class="btn btn-success" id="categoryManagementCategoryModify" style="display:none">수정</button>
	<button type="button" class="btn btn-success" id="categoryManagementCategoryAdd" style="display:none">추가</button>
	<button type="button" class="btn btn-danger" id="categoryManagementCategoryAddCancel">취소</button>
</div>

<script id="categoryManagement_list_template" type="text/x-jquery-tmpl">
{{each(i,item) list}}
	<tr class="table-secondary" data-categorySeq="\${item.categorySeq}">
		<th>
			<div class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input" id="customCheck\${i}">
				<label class="custom-control-label" for="customCheck\${i}"></label>
			</div>
		</th>
		<td>\${item.categorySeq}</td>
		<td>\${item.categoryId}</td>
		<td>\${item.categoryName}</td>
		<td>\${item.mainViewArea}</td>
		<td>\${item.instId}</td>
		<td>\${item.instDate}</td>
	</tr>
{{/each}}
</script>