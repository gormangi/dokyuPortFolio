<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/static/css/board/board.css" />
<script type="text/javascript" src="/static/js/board/board.js"></script>

<input type="hidden" id="board_categoryId" value="${categoryId }"/>

<div class="inner">
	<header id="header">
		<a href="javascript:void(0);" class="logo"><strong id="board_categoryName"></strong></a>
	</header>
	<section class="board_section">
		<div class="table-wrapper">
			<table>
				<thead>
					<tr>
						<th>Title</th>
						<th>Date</th>
						<th>Writer</th>
					</tr>
				</thead>
				<tbody id="board_list">
				</tbody>
			</table>
			
			<ul class="pagination" id="board_pagination" style="text-align:center;">
			</ul>
			
		</div>
	</section>
</div>

<script id="board_list_template" type="text/x-jquery-tmpl">
{{if list.length > 0}}
	{{each(i,item) list}}
		<tr data-boardSeq="\${item.boardSeq}">
			<td>\${item.title}</td>
			<td>\${item.instDate}</td>
			<td>\${item.instId}</td>
		</tr>
	{{/each}}
{{else}}
	<tr style="text-align:center">
		<td colspan="3">NO DATA</td>
	</tr>
{{/if}}
</script>