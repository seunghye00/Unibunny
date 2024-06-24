<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<!-- 주민번호 미해결 -->

<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="../css/common.css">
	<link rel="stylesheet" href="../css/sub.css">
	<link rel="stylesheet" href="../css/layout.css">
	<link rel="stylesheet" href="../css/login.css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="../js/login.js"></script>
</head>

<body>
	<div class="signup_wrap">
		<div class="signup_container">
			<div class="signupBox">
				<div class="siteName">
					Unibunny
					<h2>회원가입</h2>
				</div>
				<form method="post" action="/signup.member">
					<!-- 아이디 -->
					<div class="signup">
						<input type="text" placeholder="아이디" id="input_id" maxlength="12" name="userid">
						<h2>＊영문 대소문자와 숫자로 이루어진 길이가 6에서 12 사이의 문자열을 입력하세요.*</h2>
						<div class="message" id="id_message"></div>
					</div>
					<!-- 닉네임 -->
					<div class="signup">
						<input type="text" placeholder="닉네임" id="input_nickname" maxlength="10" name="nickname">
						<h2>＊영어 대소문자, 숫자, 한글을 포함하여 3~10글자 사이를 입력하세요.*</h2>
						<div class="message" id="username_message"></div>
					</div>

					<!-- 비밀번호 -->
					<div class="signup">
						<input type="password" placeholder="비밀번호" id="input_pw" maxlength="15" name="pw">
						<h2>＊대소문자나 숫자를 적어도 하나 포함해야 하며, 특수문자 (!@#$%^&*()_)를 반드시 포함해야
							합니다. 길이는 8에서 15글자 사이여야 합니다.*</h2>
						<div class="message" id="password_message"></div>
					</div>
					<!-- 비밀번호 확인 -->
					<div class="signup">
						<input type="password" placeholder="비밀번호 확인" id="confirm_pw" maxlength="15">
						<div class="message" id="confirm_message"></div>
					</div>
					<!-- 전화번호 -->
					<div class="signup">
						<input type="text" placeholder="전화번호" id="input_phone" maxlength="11" name="phone">
						<h2>＊숫자만 입력가능하고 -생략하며 모든 번호의 시작은 01입니다.*</h2>
						<div class="message" id="phone_message"></div>
					</div>
					<!-- 주민번호 -->
					<div class="signup">
						<div class="reg_box">
							<input type="text" placeholder="주민번호 앞6자리" id="input_first" maxlength="6" name="reg_num_first"> <span>-</span>
							<div class="input_identify2">
								<input type="text" id="input_identify" placeholder="●" maxlength="1" name="reg_num_second"> <em>●●●●●●</em>
							</div>
							<div class="message" id="ssn_message"></div>
						</div>
						<h2>＊숫자만 입력가능합니다.*</h2>
					</div>
					<!-- 이메일 -->
					<div class="signup">
						<input type="text" placeholder="이메일" id="input_email" name="email">
						<div class="message" id="email_message"></div>
					</div>
					<!-- 우편번호 -->
					<div class="signup">
						<div class="post_box">
							<input type="text" placeholder="우편번호" id="input_postCode" name="postcode">
							<button class="postcode_btn" type="button">우편번호</button>
						</div>
					</div>
					<!-- 주소 -->
					<div class="signup">
						<input type="text" placeholder="주소1" id="input_address1" name="address1">
					</div>
					<!-- 상세 주소 -->
					<div class="signup">
						<input type="text" placeholder="주소2" id="input_address2" name="address2">
					</div>
					<button type="submit">가입하기</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>