<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/game.css">
<script src="../../js/phaser.min.js"></script>
<script src="js/main.js"></script>
<script src="../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
canvas {
	display: block;
	margin: auto;
}
</style>
</head>

<body>
	<div id="background">
		<img id="rocket" src="../../image/game/roket.png" alt="Rocket">
	</div>
</body>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		setTimeout(function() {
			document.getElementById('background').style.display = 'none';
			document.getElementById('background').style.zIndex = '-1';
		}, 2000); // 5초 후에 배경 화면을 숨기고 본 콘텐츠를 표시
	});
</script>
</html>