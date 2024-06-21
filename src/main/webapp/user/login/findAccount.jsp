<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="../../css/login.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script defer src="../../js/fineAccount.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <div class="login_wrap">
        <div class="container">
            <div class="find_Account_box">
                <div class="site_Name">
                    UniBunny
                    <div class="find_change">
                        <a href="findAccount.jsp" id="find_id">아이디 찾기
                            <hr>
                        </a>
                        <a href="findPassword.jsp" id="find_password">비밀번호 찾기</a>
                    </div>
                </div>
                <div class="find_input_reg">
                    <input type="text" placeholder="생년월일">
                </div>
                <div class="find_input_email">
                    <input type="text" placeholder="이메일">
                </div>
                <div class="find_input_phone">
                    <input type="text" placeholder="핸드폰 번호">
                </div>
                <div class="find_ID">
                    <button>아이디 찾기</button>
                </div>
                <div class="link_Change">
                    <div class="signup_Link">
                        <button type="button" id="signup_btn">회원가입</button>
                    </div>
                    <div class="login_Link">
                        <button id="login_btn">로그인</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>