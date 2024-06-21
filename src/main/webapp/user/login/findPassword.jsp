<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="../../css/login.css">
<script defer src="../../js/login.js"></script>
<script defer src="../../js/findPassword.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
 <div class="login_wrap">
        <div class="container">
            <div class="find_Account_box">
                <div class="site_Name">
                    UniBunny<br>
                    <div class="find_change">
                        <a href="findAccount.jsp" id="find_id">아이디 찾기</a>
                        <a href="findPassword.jsp" id="find_password">
                            비밀번호 찾기
                            <hr>
                        </a>
                    </div>
                </div>

                <div class="find_input_id">
                    <input type="text" placeholder="아이디">
                </div>
                <div class="find_input_reg">
                    <input type="text" placeholder="생년월일">
                </div>
                <div class="find_input_email">
                    <input type="text" placeholder="이메일">
                </div>
                <div class="find_email">
                    <button>이메일 전송</button>
                </div>

                <div class="link_Change">
                    <div class="signup_Link">
                        <a href="/member/signup.jsp" id="signup_btn">회원가입</a>

                    </div>
                    <div class="login_Link">
                        <a href="/member/login.jsp" id="login_btn">로그인</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>