<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/js/admin/boardManagement.js"></script>
<link rel="stylesheet" href="/static/css/admin/boardManagement.css" />

<div class="boardManagement_categoryArea">
	<select class="form-control" id="boardManagement_categoryId" style="width:120px; display:inline;">
		<option value="">전체</option>
	</select>
</div>
<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col" width="10%;">게시물번호</th>
			<th scope="col" width="24%;">제목</th>
			<th scope="col" width="15%;">글 설명</th>
			<th scope="col" width="8%;">메인최상위글 여부</th>
			<th scope="col" width="7%;">카테고리명</th>
			<th scope="col" width="7%;">등록자아이디</th>
			<th scope="col" width="10%;">등록일시</th>
			<th scope="col" width="7%;">수정자아이디</th>
			<th scope="col" width="10%;">수정일시</th>
		</tr>
	</thead>
	<tbody id="boardManagement_list">
		
	</tbody>
</table>

<div class="boardManagement_btnArea">
	<button type="button" class="btn btn-success" id="boardManagement_write">글 등록</button>
</div>

<div>
	<ul class="pagination" id="pagination">
	</ul>
</div>

<script id="boardManagement_list_template" type="text/x-jquery-tmpl">
{{each(i,item) list}}
	<tr class="table-secondary" data-boardSeq="\${item.boardSeq}">
		<td>\${item.boardSeq}</td>
		<td>\${item.title}</td>
		<td>\${item.explanation}</td>
		<td>\${item.mainTopView}</td>
		<td>\${item.categoryName}</td>
		<td>\${item.instId}</td>
		<td>\${item.instDate}</td>
		<td>\${item.updtId}</td>
		<td>\${item.updtDate}</td>
	</tr>
{{/each}}
</script>