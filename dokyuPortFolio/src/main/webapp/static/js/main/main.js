$(function(){
	
	fn.loadUserInfo();
	
	fn.modalCreate();
	
	fn.onValidation();
	
	fn.loadMainTopView();
	
	fn.loadMainView();
	
	$("#main_loginBtn").on("click",function(){
		$("#dialog-login-form form")[0].reset();
		$("#dialog-login-form").dialog('open');
	});
	
	$("#main_signBtn").on("click",function(){
		$("#dialog-sign-form form")[0].reset();
		$("#dialog-sign-form").dialog('open');
	});
	
	$("#loginForm_userPwd").keydown(function(key){
		if(key.keyCode == 13){
			fn.userLogin();
		}
	});
	
	$("#logoutBtn").on("click",function(){
		fn.userLogout();
	});
	
	$("#AdminPageBtn").on("click",function(){
		document.location.href = "/admin/menuManagement.do";
	});
});

var fn = {
		
		loadMainView : function(){
			
			$.ajax({
				url : '/board/loadView.do',
				type : 'post',
				dataType : 'json',
				data : {
					mainViewArea : 'BODY'
				},
				success : function(res){
					$.each(res.list,function(i , item){
						item.view.content = item.view.content.replace(/(<([^>]+)>)/gi, "");
					});
					
					$("#main_bodyViewArea").empty();
					$('#main_bodyViewArea_template').tmpl(res).appendTo('#main_bodyViewArea');
				}
			});
			
		},
		
		loadMainTopView : function(){
			
			$.ajax({
				url : '/board/loadMainTopView.do',
				type : 'post',
				dataType : 'json',
				success : function(res){
					res.mainTopView.content = res.mainTopView.content.replace(/(<([^>]+)>)/gi, "");
					
					$("#banner").empty();
					$('#banner_template').tmpl(res).appendTo('#banner');
				}
			});
			
		},
		
		loadUserInfo : function(){
			var html = [];
			if(userInfo.userSeq == ''){
				html.push('<li class="main_loginBtn" id="main_loginBtn"><a href="#"><h4>LOGIN</h4></a></li>');
				html.push('<li class="main_signBtn" id="main_signBtn"><a href="#"><h4>SIGN</h4></a></li>');
				$("#login_Area").html(html.join(''));
			}else{
				html.push('<li><p>'+userInfo.userName+' 님 <a href="javascript:void(0);" class="button primary small" id="logoutBtn">logout</a></p></li>');
				if(userInfo.userAuth == 0){
					html.push('<li><a href="javascript:void(0);" class="button primary small" id="AdminPageBtn">Admin</a></li>');
				}
				$("#login_Area").html(html.join(''));
			}
		},
		
		modalCreate : function(){
			
			$("#dialog-login-form").dialog({
				autoOpen: false,
				width: 450,
				resizable: true,
				modal: true,
				buttons: {
					"로그인": fn.userLoginValidation,
					"취소": function() {
						$("#dialog-login-form").dialog("close");
					}
				},
				close: function() {
					$("#dialog-login-form").dialog("close");
				}
			});
			
			$("#dialog-sign-form").dialog({
				autoOpen: false,
				width: 450,
				resizable: true,
				modal: true,
				buttons: {
					"가입": fn.userSignValidation,
					"취소": function() {
						$("#dialog-sign-form").dialog("close");
					}
				},
				close: function() {
					$("#dialog-sign-form").dialog("close");
				}
			});
			
		},
		
		userLoginValidation : function(){
			$("#dialogLoginForm").submit();
		},
		
		userSignValidation : function(){
			$("#dialogSignForm").submit();
		},
		
		onValidation : function(){
			
			$.validator.addMethod("userIdCheck",function(value,element){
				return this.optional(element) || /^[a-z]+[a-z0-9]$/.test(value);
			});
			
			$.validator.addMethod("userNameCheck",function(value,element){
				return this.optional(element) || /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/.test(value);
			});
			
			$.validator.addMethod("passwordCheck",function(value,element){
				return this.optional(element) || /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value);
			});
			
			$("#dialogLoginForm").validate({
				rules: {
					loginForm_userId : {
						required : true,
						userIdCheck : true,
						minlength : 5,
						maxlength : 19
					},
					loginForm_userPwd : {
						required : true,
						passwordCheck : true
					}
				},
				messages: {
					loginForm_userId : {
						required : "아이디를 입력해주세요",
						userIdCheck : "아이디는 영문자로 시작하는 영문자 또는 숫자 조합으로 입력하세요",
						minlength : "아이디는 5~19자리로 입력하세요",
						maxlength : "아이디는 5~19자리로 입력하세요"
					},
					loginForm_userPwd : {
						required : "비밀번호를 입력해주세요",
						passwordCheck : "비밀번호는 특수문자 / 문자 / 숫자 포함 형태의 8~15자리로 입력해주세요"
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
					fn.userLogin();
				}
			});
			
			$("#dialogSignForm").validate({
				rules: {
					signForm_userId : {
						required : true,
						userIdCheck : true,
						minlength : 5,
						maxlength : 19,
						remote : {url :"/main/userSignUserIdDupCheck.do"}
					},
					signForm_userName : {
						required : true,
						userNameCheck : true,
						remote : {url :"/main/userSignUserNameDupCheck.do"}
					},
					signForm_email : {
						required : true,
						minlength : 12,
						maxlength : 50,
						email : true,
						remote : {url :"/main/userSignEmailDupCheck.do"}
					},
					signForm_userPwd : {
						required : true,
						passwordCheck : true
					},
					signForm_userPwdConfirm : {
						required : true,
						equalTo : '#signForm_userPwd'
					}
				},
				messages: {
					signForm_userId : {
						required : "아이디를 입력해주세요",
						userIdCheck : "아이디는 영문자로 시작하는 영문자 또는 숫자 조합으로 입력하세요",
						minlength : "아이디는 5~19자리로 입력하세요",
						maxlength : "아이디는 5~19자리로 입력하세요",
						remote : '사용중인 아이디입니다'
					},
					signForm_userName : {
						required : "닉네임을 입력해주세요",
						userNameCheck : "닉네임은 2~20자 내로 입력하세요",
						remote : '사용중인 닉네임입니다'
					},
					signForm_email : {
						required : "이메일을 입력해주세요",
						minlength : "이메일은 {0}자 이상 입력해주세요",
						maxlength : "이메일은 {0}자 이하로 입력해주세요",
						email : "이메일 형식에 맞게 입력해주세요",
						remote : '사용중인 이메일입니다'
					},
					signForm_userPwd : {
						required : "비밀번호를 입력해주세요",
						passwordCheck : "비밀번호는 특수문자 / 문자 / 숫자 포함 형태의 8~15자리로 입력해주세요"
					},
					signForm_userPwdConfirm : {
						required : "비밀번호 확인란을 입력해주세요",
						equalTo : "비밀번호가 일치하지않습니다"
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
					if(confirm("입력하신 정보로 가입하시겠습니까?")){
						fn.userSign();
					}
				}
			});
		},
		
		userLogin : function(){
			var param = {
				userId : $("#loginForm_userId").val(),
				userPwd : $("#loginForm_userPwd").val()
			}
			
			$.ajax({
				url : '/main/userLogin.do',
				data : param,
				dataType : 'json',
				type : 'post',
				success : function(res){
					if(res.result){
						document.location.href = "/main/dokyuPortFolio.do";
					}else{
						alert('아이디 또는 비밀번호를 다시 확인해주세요');
						return false;
					}
				}
			});
		},
		
		userLogout : function(){
			$.ajax({
				url : '/main/userLogout.do',
				dataType : 'json',
				type : 'post',
				success : function(res){
					if(res){
						alert('정상적으로 로그아웃되었습니다.');
						document.location.href = "/main/dokyuPortFolio.do";
					}
				}
			});
		},
		
		userSign : function(){
			
			var param = {
				userId : $("#signForm_userId").val(),
				userName : $("#signForm_userName").val(),
				email : $("#signForm_email").val(),
				userPwd : $("#signForm_userPwd").val()
			}
			
			$.ajax({
				url : '/main/userSign.do',
				data : param,
				dataType : 'json',
				type : 'post',
				success : function(res){
					if(res){
						document.location.href = "/main/dokyuPortFolio.do";
					}else{
						alert('데이터베이스 에러입니다 관리자에게 문의하세요');
						$("#dialog-sign-form form")[0].reset();
						$("#dialog-sign-form").dialog('close');
						return false;
					}
				}
			});
		}
}