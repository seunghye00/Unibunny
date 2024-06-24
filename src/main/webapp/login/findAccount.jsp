<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Find Account</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/login.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script defer src="../../js/login.js"></script>
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
                <div >
                <div class="find_input_reg">
                    <input type="text" placeholder="생년월일" name="find_input_reg">
                </div>
                <div class="find_input_email">
                    <input type="text" placeholder="이메일" name="find_input_email">
                </div>	
                <div class="find_input_phone">
                    <input type="text" placeholder="핸드폰 번호" name="find_input_phone">
                </div>
                <div class="find_ID">
                    <button onClick="findId()">아이디 찾기</button>
                    </form>
                </div>
                <div class="link_Change">
                    <div class="signup_Link">
                     <a href="signup.jsp" id="signup_btn"> 회원가입</a>
                    </div>
                    <div class="login_Link">
                    <a href="login.jsp" id="login_btn"> 로그인</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>