<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/static/css/main/sidebar.css" />
<script type="text/javascript" src="/static/js/main/sidebar.js"></script>

<div class="inner">
	<section id="search" class="alt">
		<form id="sidebar_searchForm" class="sidebar_searchForm" onsubmit="return false">
			<input type="text" name="board_search_text" id="board_search_text" placeholder="Search" />
		</form>
	</section>
	<nav id="menu">
		<header class="major">
			<h2>Menu</h2>
		</header>
		<ul id="sidebar_menuList">
			<li><a href="index.html">Homepage</a></li>
			<li><a href="generic.html">Generic</a></li>
			<li><a href="elements.html">Elements</a></li>
			<li>
				<span class="opener">Submenu</span>
				<ul>
					<li><a href="#">Lorem Dolor</a></li>
					<li><a href="#">Ipsum Adipiscing</a></li>
					<li><a href="#">Tempus Magna</a></li>
					<li><a href="#">Feugiat Veroeros</a></li>
				</ul>
			</li>
			<li><a href="#">Etiam Dolore</a></li>
			<li><a href="#">Adipiscing</a></li>
			<li>
				<span class="opener">Another Submenu</span>
				<ul>
					<li><a href="#">Lorem Dolor</a></li>
					<li><a href="#">Ipsum Adipiscing</a></li>
					<li><a href="#">Tempus Magna</a></li>
					<li><a href="#">Feugiat Veroeros</a></li>
				</ul>
			</li>
			<li><a href="#">Maximus Erat</a></li>
			<li><a href="#">Sapien Mauris</a></li>
			<li><a href="#">Amet Lacinia</a></li>
		</ul>
	</nav>
	
	<section id="sidebar_viewArea">
		<header class="major">
			<h2>Ante interdum</h2>
		</header>
		<div class="mini-posts">
			<article>
				<a href="#" class="image"><img src="/static/img/pic07.jpg" alt="" /></a>
				<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
			</article>
			<article>
				<a href="#" class="image"><img src="/static/img/pic08.jpg" alt="" /></a>
				<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
			</article>
			<article>
				<a href="#" class="image"><img src="/static/img/pic09.jpg" alt="" /></a>
				<p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
			</article>
		</div>
		<ul class="actions">
			<li><a href="#" class="button">More</a></li>
		</ul>
	</section>

	<section>
		<header class="major">
			<h2>Get in touch</h2>
		</header>
		<p></p>
		<ul class="contact">
			<li class="icon solid fa-envelope"><a href="#">didehrb@naver.com</a></li>
			<li class="icon solid fa-phone">(010) 5906-7812</li>
			<li class="icon solid fa-home">대한민국 인천 서구 탁옥로 76<br/>한국아파트, 202-114</li>
		</ul>
	</section>

	<footer id="footer">
		<p class="copyright">&copy; Copyright. Dokyus blog 2020 <a href="#">Dokyu YANG</a>. Design: <a href="#">Dokyu YANG</a>.</p>
	</footer>
</div>

<script id="sidebar_viewArea_template" type="text/x-jquery-tmpl">
	<header class="major">
		<h2>\${subject.categoryName}</h2>
	</header>
	<div class="mini-posts">
		{{each(i,item) list}}
			<article>
				<a href='javascript:document.location.href="/board/boardDetail.do?bs=\${item.view.boardSeq}"' class="image">
					{{if item.viewThumbnail != null}}
						<img src="\${item.viewThumbnail.fileUrl}" alt="" />
					{{else}}
						<img src="/static/img/pic07.jpg" alt="" />
					{{/if}}
				</a>
				<p>\${item.view.title}</p>
			</article>
		{{/each}}
	</div>
</script>