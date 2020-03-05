$(function(){
	
	fn.categoryLoad();
	
	fn.createEditor();
	
	fn.writeModChange();
	
	$("#boardWriteView_fileAddBtn").on("click",function(){
		fn.fileInputAdd();
	});
	
	$("#boardWriteView_boardAddBtn").on("click",function(){
		fn.boardWrite($("#boardWriteView_writeMod").val());
	});
	
	$("#boardWriteView_boardModifyBtn").on("click",function(){
		fn.boardWrite($("#boardWriteView_writeMod").val());
	});
	
	$("#boardWriteView_file_div").on("click","button",function(){
		$(this).closest('div').remove();
	});
	
	$("#boardWriteView_boardCancelBtn").on("click",function(){
		document.location.href = "/admin/boardManagement.do";
	});
	
	$("#boardWriteView_thumbnailFileInfo").on("click","span",function(){
		var fileSeq = $(this).attr('data-fileSeq');
		fn.thumbnailDel(fileSeq);
	});
	
	$("#boardWriteView_fileInfo").on("click","span",function(){
		var fileSeq = $(this).attr('data-fileSeq');
		fn.fileDel(fileSeq , $(this));
	});
	
	$("#boardWriteView_boardDelBtn").on("click",function(){
		fn.boardDel($("#boardWriteView_boardSeq").val());
	});
	
});

var fn = {
		
	boardDel : function(boardSeq){
		
		if(confirm('모든 첨부파일 및 썸네일이 삭제됩니다 삭제하시겠습니까?')){
			
			$.ajax({
				url : '/admin/boardDel.do',
				type : 'post',
				dataType : 'json',
				data : {
					boardSeq : boardSeq
				},
				success : function(res){
					if(res.state == 'success'){
						document.location.href = "/admin/boardManagement.do";
					}else{
						alert('데이터베이스 에러입니다 관리자에게 문의해주세요');
						return false;
					}
				}
			});
			
		}
		
	},
		
	fileDel : function(fileSeq , me){
		
		$.ajax({
			url : '/admin/fileDel.do',
			type : 'post',
			dataType : 'json',
			data : {
				fileSeq : fileSeq
			},
			success : function(res){
				if(res.state == 'success'){
					alert('삭제되었습니다');
					me.closest('div').remove();
				}else{
					alert('데이터베이스 에러입니다. 관리자에게 문의해주세요');
					return false;
				}
			}
		});
		
	},
	
	thumbnailDel : function(fileSeq){
		
		$.ajax({
			url : '/admin/fileDel.do',
			type : 'post',
			dataType : 'json',
			data : {
				fileSeq : fileSeq
			},
			success : function(res){
				if(res.state == 'success'){
					alert('삭제되었습니다');
					$("#boardWriteView_thumbnailFileInfo").hide();
					$("#boardWriteView_thumbnailFileDiv").show();
				}else{
					alert('데이터베이스 에러입니다. 관리자에게 문의해주세요');
					return false;
				}
			}
		});
		
	},
		
	writeModChange : function(){
		
		var writeMod = $("#boardWriteView_writeMod").val();
		if(writeMod == 'N'){
			$("#boardWriteView_boardAddBtn").show();
		}else{
			$("#boardWriteView_boardModifyBtn").show();
			$("#boardWriteView_boardDelBtn").show();
			CKEDITOR.on('instanceReady', function(){
				fn.loadBoardInfo();
			});
		}
		
	},
	
	loadBoardInfo : function(){
		
		var boardSeq = $("#boardWriteView_boardSeq").val();
		
		$.ajax({
			url : '/admin/loadBoardInfo.do',
			type : 'post',
			data : {
				boardSeq : boardSeq
			},
			dataType : 'json',
			success : function(res){
				
				var boardInfo = res.boardInfo;
				var thumbnailFileInfo = res.thumbnailFileInfo;
				var fileInfoList = res.fileInfoList;
				
				$("#boardWriteView_categoryId").val(boardInfo.categoryId);
				$("#boardWriteView_mainTopView").val(boardInfo.mainTopView);
				$("#boardWriteView_title").val(boardInfo.title);
				$("#boardWriteView_explanation").val(boardInfo.explanation);
				CKEDITOR.instances.boardWriteView_content.setData(boardInfo.content);
				
				if(thumbnailFileInfo != null){
					$("#boardWriteView_thumbnailFileDiv").hide();
					var html = [];
					html.push('<div class="col-sm-12">');
					html.push('<span class="badge badge-secondary">THUMBNAIL</span>');
					html.push('<p class="text-muted" style="display:inline;padding-left:20px;">'+thumbnailFileInfo.originFileName+'</p>');
					html.push('<span class="badge badge-pill badge-danger" style="cursor:pointer; margin-left:20px;" data-fileSeq='+thumbnailFileInfo.fileSeq+'>삭제</span>');
					html.push('</div>');
					$("#boardWriteView_thumbnailFileInfo").append(html.join(''));
				}
				
				if(fileInfoList != null){
					var html = [];
					$.each(fileInfoList,function(i,item){
						html.push('<div class="col-sm-12">');
						html.push('<span class="badge badge-secondary">FILE</span>');
						html.push('<p class="text-muted" style="display:inline;padding-left:20px;">'+item.originFileName+'</p>');
						html.push('<span class="badge badge-pill badge-danger" style="cursor:pointer; margin-left:20px;" data-fileSeq='+item.fileSeq+'>삭제</span>');
						html.push('</div>');
					});
					$("#boardWriteView_fileInfo").append(html.join(''));
				}
			}
		});
		
		
	},
		
	boardWrite : function(writeMod){
		
		var categoryId = $("#boardWriteView_categoryId").val();
		if(categoryId == '' || categoryId == null){
			alert('카테고리를 선택하세요');
			return false;
		}
		
		var thumbnail = $("#boardWriteView_thumbnail").val();
		if(thumbnail != ''){
			var extName = thumbnail.substring(thumbnail.lastIndexOf(".")+1).toUpperCase();
			if(extName != 'JPG' && extName != 'PNG' && extName != 'GIF'){
				alert('썸네일 확장자는 jpg , png , gif 만 등록할수있습니다');
				return false;
			}
			if($("#boardWriteView_thumbnail")[0].files[0].size > 500000){
				alert('썸네일은 500KB 이상 업로드할수없습니다');
				return false;
			}
		}
		
		var title = $("#boardWriteView_title").val();
		if(title == ''){
			alert('제목을 입력하세요');
			return false;
		}
		if(title.length > 299){
			alert('제목은 300자 이하로 입력하세요');
			return false;
		}
		
		var explanation = $("#boardWriteView_explanation").val();
		if(explanation.length > 999){
			alert('글설명은 1000자 이하로 입력하세요');
			return false;
		}
		
		var fileInputs = $("input:file[name=boardWriteView_file]");
		var fileRes = true;
		if(fileInputs.length > 0){
			$.each(fileInputs,function(i , item){
				if(fileInputs[i].files[0] != null){
					if(fileInputs[i].files[0].size > 500000){
						alert(fileInputs[i].files[0].name + ' 의 파일크기가 500KB를 넘어 업로드할수없습니다.');
						fileRes = false;
					}
				}
			});
		}
		if(!fileRes){
			return false;
		}
		
		
		var formData = new FormData();
		if(writeMod == 'U'){
			formData.append("boardSeq", $("#boardWriteView_boardSeq").val());
		}
		formData.append("categoryId", $("#boardWriteView_categoryId").val());
		formData.append("mainTopView", $("#boardWriteView_mainTopView").val());
		
		if($("#boardWriteView_thumbnail")[0].files[0] != null){
			formData.append("thumbnail", $("#boardWriteView_thumbnail")[0].files[0]);
		}
		
		formData.append("title", $("#boardWriteView_title").val());
		formData.append('explanation', $("#boardWriteView_explanation").val());
		formData.append("content", CKEDITOR.instances.boardWriteView_content.getData());
		
		var fileInputs = $("input:file[name=boardWriteView_file]");
		if(fileInputs.length > 0){
			$.each(fileInputs,function(i , item){
				if(fileInputs[i].files[0] != null){
					formData.append("files", fileInputs[i].files[0]);
				}
			});
		}
		if(writeMod == 'N'){
			if(confirm('입력하신 내용으로 글을 등록하시겠습니까?')){
				$.ajax({
					url : '/admin/boardWrite.do',
					data : formData,
					dataType : 'json',
					type : 'post',
					enctype : 'multipart/form-data',
					processData : false, 
					contentType : false,
					success : function(res){
						if(res.state == 'success'){
							alert('정상 등록되었습니다.');
							document.location.href = "/admin/boardManagement.do";
						}else{
							alert('등록에 실패하였습니다. 관리자에게 문의해주세요');
							return false;
						}
					}
				});
			}
		}else if(writeMod == 'U'){
			if(confirm('입력하신 내용으로 수정하시겠습니까?')){
				$.ajax({
					url : '/admin/boardModify.do',
					data : formData,
					dataType : 'json',
					type : 'post',
					enctype : 'multipart/form-data',
					processData : false, 
					contentType : false,
					success : function(res){
						if(res.state == 'success'){
							alert('정상 수정되었습니다.');
							document.location.href = "/admin/boardManagement.do";
						}else{
							alert('수정에 실패하였습니다. 관리자에게 문의해주세요');
							return false;
						}
					}
				});
			}
		}
		
	},
		
	fileInputAdd : function(){
		var html = [];
		html.push('<div>');
		html.push('<input type="file" name="boardWriteView_file"/>');
		html.push('<button type="button" class="btn btn-outline-danger">삭제</button>');
		html.push('</div>');
		$("#boardWriteView_file_div").append(html.join(''));
	},
		
	categoryLoad : function(){
		$.ajax({
			url : '/admin/categoryListForSelectBox.do',
			type : 'post',
			dataType : 'json',
			success : function(res){
				var html = [];
				$.each(res,function(i , item){
					html.push('<option value='+item.categoryId+'>'+item.categoryName+'</option>');
				});
				$("#boardWriteView_categoryId").append(html.join(''));
			}
		});
	},
	
	createEditor : function(){
		
		CKEDITOR.replace('boardWriteView_content',{
			filebrowserUploadUrl : '/admin/boardImageUpload.do'
		});
		
		CKEDITOR.on('dialogDefinition', function(ev){
			var dialogName = ev.data.name;
			var dialogDefinition = ev.data.definition;
			
			switch (dialogName) {
				case 'image':
					dialogDefinition.removeContents('Link');
					dialogDefinition.removeContents('advanced');
					break;
			}
		});
		
	}
	
}