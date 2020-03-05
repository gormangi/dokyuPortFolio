$(function(){
	
	fn.loadBoardDetail();
	
	fn.loadFileList();
	
	fn.onValidation();
	
	fn.deleteconfirm();
	
	$("#boardDetail_attatchFileDiv").on("click","#boardDetail_attachFileBtn",function(){
		$("#boardDetail_attatchFileDiv .box").toggle('fast');
	});
	
	$("#boardDetail_attatchFileDiv").on("click","li",function(){
		document.location.href = "/board/fileDownload.do?fileSeq="+$(this).find('a').attr('data-fileSeq');
	});
	
});



var fn = {
		
	loadFileList : function(){
		
		var boardSeq = $("#boardDetail_boardSeq").val();
		
		$.ajax({
			url : '/board/loadFileList.do',
			type : 'post',
			dataType : 'json',
			data : {
				boardSeq : boardSeq
			},
			success : function(res){
				
				if(res.length > 0){
					
					var html = [];
					$.each(res,function(i,item){
						html.push('<li><a href="javascript:void(0);" data-fileSeq='+item.fileSeq+'>'+item.originFileName+'</a></li>');
					});
					
					$("#boardDetail_attatchFileDiv").find('ul').html(html.join(''));
					
				}else{
					$("#boardDetail_attatchFileDiv").hide();
				}
				
			}
		});
		
	},
	
	loadBoardDetail : function(){
		
		var boardSeq = $("#boardDetail_boardSeq").val();
		var userId = $("#userId").val();
	
		if(userId==''){
			$("#boardDetail_repleWrite").hide();
		}
		
		$.ajax({
			url : '/board/loadBoardDetail.do',
			type : 'post',
			dataType : 'json',
			data : {
				boardSeq : boardSeq
			},
			success : function(res){
				
				$("#boardDetail_categoryName").text(res.category.categoryName);
				$("#boardDetail_inst").text(res.boardInfo.instDate + ' / ' + res.boardInfo.instId);
				
				var articleHtml = [];
				articleHtml.push('<header class="main">');
				articleHtml.push('<h1>'+res.boardInfo.title+'</h1>');
				articleHtml.push('</header>');
				articleHtml.push(res.boardInfo.content);
				
				$("#boardDetail_article").html(articleHtml.join(''));
				
				var repleHtml = [];
				$.each(res.commentList , function(i,item){
					repleHtml.push('<div>');
					repleHtml.push('<h4 style="display:inline;">'+item.tbCommentTitle+'</h4>');
					if(item.tbCommentRegId == userInfo.userId){
						repleHtml.push('<a style="margin-left:15px;" href="javascript:void(0);" data-tbCommentNum='+item.tbCommentNum+' id="boardDetail_commentDelBtn" class="button small">삭제</a>');
					}
					repleHtml.push('<blockquote>'+item.tbCommentComment+'</blockquote>');
					repleHtml.push('</div>');
				});
				
				$("#boardDetail_reple").html(repleHtml.join(''));

				if(res.commentList.length == 0){
					$("#boardDetail_reple").hide();
				}else{
					$("#boardDetail_reple").show();
				}
			}
		});
		
	},
	
	boardDetail_commentwrite :function(){
		var tbCommentSeq = $("#boardDetail_boardSeq").val();
		var tbCommentTitle = $("#boardDetail_comment_title").val();
		var tbCommentComment = $("#demo_message").val();
		var tbCommentRegId = $("#userId").val();
		
		
		
		$.ajax({
			url : '/board/loadBoardDetailCommentwrite.do',
			type : 'post',
			dataType : 'json',
			data : {
				tbCommentSeq : tbCommentSeq,
				tbCommentTitle : tbCommentTitle,
				tbCommentComment : tbCommentComment,
				tbCommentRegId : tbCommentRegId
			},
			success : function(res){
				fn.loadBoardDetail();
			}
		});
		
	},
	deleteComment : function(tbCommentNum){
		
		$.ajax({
			url : '/board/loadBoardDetailCommentdelete.do',
			type : 'post',
			dataType : 'json',
			data : {
				tbCommentNum : tbCommentNum
			},
			success : function(res){
				fn.loadBoardDetail();
			}
		});
		
	},
	
	onValidation : function(){
		
		$("#boardDetail_writeform").validate({
			rules: {
				boardDetail_comment_title : {
					required : true,
					minlength : 3,
					maxlength : 40
				},
				demo_message : {
					required : true,
					minlength : 3,
					maxlength : 400
				}
			},
			messages: {
				boardDetail_comment_title : {
					required : "제목을 입력해주세요",
					minlength: "제목을 3글자 이상 써주세요",
					maxlength: "제목은 40자 이내로 써주세요"
				},
				demo_message : {
					required : "내용을 입력해주세요",
					minlength: "내용을 3글자 이상 써주세요",
					maxlength: "제목은 400자 이내로 써주세요"
				}
			},
			onkeyup : false,
			onclick : false,
			onfocusout : false,
			showErrors : function(errorMap, errorList){
				if(errorList.length){
					alert(errorList[0].message);
				}
			},
			submitHandler : function(){
				fn.boardDetail_commentwrite();
			}
		
		});
	},
	
	deleteconfirm : function(){
		
		$("#boardDetail_reple").on("click","#boardDetail_commentDelBtn",function(){
			var deleteConfirm = confirm("삭제 하시겠습니까");
			if(deleteConfirm == true){
				var tbCommentNum = $(this).attr('data-tbCommentNum');
				fn.deleteComment(tbCommentNum);
			}
			else if(deleteConfirm == false){
			 return false;
			}
		});
		
	}
	
}