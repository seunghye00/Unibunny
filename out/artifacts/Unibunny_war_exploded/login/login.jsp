<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String profileImg = (String) session.getAttribute("profileImg");
	String nickName = (String) session.getAttribute("nickName");
	if (profileImg != null && nickName != null) {
		response.sendRedirect("/user/main.jsp");
	}
%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<link rel="stylesheet" href="../css/login.css">
	<script defer src="../js/login.js"></script>

</head>

<body>
	<div class="login_wrap">

		<div class="container">
			<div class="login_Box">
				<div class="site_Name">UniBunny</div>
				<form method="post" action="/login.member">
				<div class="login_Input">
					<input type="text" placeholder="Id" name="userid">
				</div>
				<div class="pw_Input">
					<input type="password" placeholder="Password" name="pw">
				</div>
				<div class="login_Btn">
					<button id="login_form_btn" type="submit">Login</button>
				</div>
				</form>
				<div class="api_Login">
					<button class="naver_Login">
						<span>Naver</span>
					</button>
					<button class="kakao_Login">
						<span>Kakao</span>
					</button>
				</div>
				<div class="link_Change">
					<div class="signup_Link">
						<a href="/login/signup.jsp" id="signup_btn">회원가입</a>
					</div>

					<div class="find_Account_Link">
						<a href="/login/findAccount.jsp" id="find_btn">계정찾기</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>