<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>findPassword</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/login.css">
    <!-- <link rel="stylesheet" href="../../css/find_password.css"> -->
    <script defer src="../../js/login.js"></script>

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
                    <input type="text" placeholder="아이디" name="find_input_id">
                </div>
                <div class="find_input_reg">
                    <input type="text" placeholder="생년월일" name="find_input_reg">
                </div>
                <div class="find_input_email">
                    <input type="text" placeholder="이메일" name="find_input_email">
                </div>
                <div class="find_email">
                    <button id="find_email_form">이메일 전송</button>
                </div>

                <div class="link_Change">
                    <div class="signup_Link">
                        <a href="/login/signup.jsp" id="signup_btn">회원가입</a>

                    </div>
                    <div class="login_Link">
                        <a href="/login/login.jsp" id="login_btn">로그인</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>


</html>