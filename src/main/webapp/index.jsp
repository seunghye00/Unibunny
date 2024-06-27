<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../css/index.css">
<script defer src="../js/index.js"></script>
</head>
<body>
  <div class="index_wrapper">
    <div class="index_wrap">
      <div class="img_box">
        <a href="#" onclick="location.href='/user/main.jsp';">
          <img src="../image/index/index_main.png" alt="">
        </a>
      </div>
      <div class="img_box">
        <a href="/list.board">
          <img src="../image/index/index_commu.png" alt="">
        </a>
      </div>
      <div class="img_box">
         <a href="#" onclick="location.href='/login/login.jsp';">
          <img src="../image/index/index_login.png" alt="">
        </a>
      </div>
      <div class="img_box">
        <a href="user/ourpage/ourpage.jsp" >
          <img src="../image/index/index_ourpage.png" alt="">
        </a>
      </div>
    </div>
  </div>
  <canvas class="canvas"></canvas>
</body>
</html>