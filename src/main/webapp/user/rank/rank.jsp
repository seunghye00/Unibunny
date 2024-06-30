<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>랭크</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/sub.css">
<link rel="stylesheet" href="../../css/layout.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script defer src="../../js/common.js"></script>
</head>

<body>
	<div class="wrapper">
		<jsp:include page="../../common/header.jsp" />
		<div class="body_area">
			<div class="body for_pc">
				<div class="wrap">
					<div class="con_wrap">
						<div class="con rank">
							<div class="title_box">
								<p class="title">랭킹</p>
							</div>
							<div class="dropdown-btn">Game</div>
							<div class="game_list">
								<a href="javascript:;" class="cpage" title="게임1로 이동" gamelistNum=1>FlappyCirby</a>
								<a href="javascript:;" title="게임2로 이동" gamelistNum=2>4096</a> <a
									href="javascript:;" title="게임3로 이동" gamelistNum=3>shooting walnut</a> <a
									href="javascript:;" title="게임4로 이동" gamelistNum=4>블랙잭</a> <a
									href="javascript:;" title="게임5로 이동" gamelistNum=5>Doodle Jump</a>
							</div>
							<div class="top_rank">
								<div class="podium">
									<div class="second">
										<div class="second-2">
										</div>
									</div>
									<div class="first">
										<div class="first-2">
										</div>
									</div>
									<div class="third">
										<div class="third-2">
										</div>
									</div>
									<img src="../../image/rank/podium.png" alt="단상">
								</div>
							</div>
							<div class="rank list_table" id="table_container">
								<div class="table_row table_header">
									<div class="table_col">
										<span>순위</span>
									</div>
									<div class="table_col">
										<span>프로필</span>
									</div>
									<div class="table_col">
										<span>닉네임</span>
									</div>
									<div class="table_col">
										<span>점수</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../../common/footer.jsp" />
	</div>
	<script>
	// select로 변경 될 예정
    document.addEventListener("DOMContentLoaded", function () {
      const dropdownBtn = document.querySelector('.dropdown-btn');
      const gameList = document.querySelector('.game_list');

      dropdownBtn.addEventListener('click', function () {
        gameList.classList.toggle('show');
      });
    });
	//
	$(document).ready(function () {
    	loadTableData();
	});
	
	let gameLinks = document.querySelectorAll('.game_list a');
	let gamelistNum = 1;

	gameLinks.forEach(function(link) {
	    link.addEventListener('click', function(event) {
	        // Prevent the default link behavior if needed
	        event.preventDefault();
	        
	        // Update gamelistNum
	        gamelistNum = this.getAttribute('gamelistNum');
	        
	        // Load the table data
	        loadTableData();
	        
	        if (gamelistNum) {
	            // Remove 'cpage' class from all game links
	            gameLinks.forEach(function(link) {
	                link.classList.remove('cpage');
	            });
	            
	            // Add 'cpage' class to the clicked link
	            this.classList.add('cpage');
	            
	            // Additional actions using gamelistNum if needed
	        } else {
	            // Handle cases where gamelistNum is not set
	        }
	    });
	});
    // AJAX 요청을 통해 데이터를 가져오는 함수
    function loadTableData() {
        $.ajax({
            url: '/list.score', // 서버 API URL로 변경 필요
            method: 'GET',
            data: {
            	gamelistNum: gamelistNum,
             },
            dataType: 'json', // 데이터 타입을 JSON으로 지정
        }).done(function(data) {
            // 데이터가 성공적으로 반환된 경우 테이블에 추가
            console.log("데이터 받아오기 성공:", data);
            appendTableData(data);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            // 오류 처리
            console.error('데이터 받아오기 실패:', textStatus, errorThrown);
        });
    }

    // 데이터를 테이블에 추가하는 함수
    function appendTableData(data) {
    // 데이터가 추가될 컨테이너 선택
    var $tableContainer = $('#table_container');

    // 기존 데이터 제거
    $('.table_row').not('.table_header').remove();

 	// 최상위 3개 데이터를 각각 추가
    for (var i = 0; i < Math.min(data.length, 3); i++) {
        var topData = data[i];

        if (i === 0) {
            // 첫 번째 데이터를 .first-2에 추가
            var $first2 = $('.first-2');
            $first2.empty(); // 기존 내용 지우기
            var $score = $('<p>').text(topData.score + '점');
            var $nickname = $('<p>').text(topData.nickname);
            var $rankimg= $('<img src="../../image/rank/gold.png" alt="로고">')
            $first2.append($score).append($nickname).append($rankimg);
        } else if (i === 1) {
            // 두 번째 데이터를 .second-2에 추가
            var $second2 = $('.second-2');
            $second2.empty(); // 기존 내용 지우기
            var $score = $('<p>').text(topData.score + '점');
            var $nickname = $('<p>').text(topData.nickname);
            var $rankimg= $('<img src="../../image/rank/silver.png" alt="로고">')
            $second2.append($score).append($nickname).append($rankimg);
        } else if (i === 2) {
            // 세 번째 데이터를 .third-2에 추가
            var $third2 = $('.third-2');
            $third2.empty(); // 기존 내용 지우기
            var $score = $('<p>').text(topData.score + '점');
            var $nickname = $('<p>').text(topData.nickname);
            var $rankimg= $('<img src="../../image/rank/bronze.png" alt="로고">')
            $third2.append($score).append($nickname).append($rankimg);
        }
    }

    // 데이터 반복 처리 (최대 7개까지 출력)
    var maxRows = Math.min(data.length, 10);
    for (var i = 3; i < maxRows; i++) {
        var item = data[i];
        var $row = $('<div>', { class: 'table_row' });

        $('<div>', { class: 'table_col' }).append($('<span>').text(i + 1)).appendTo($row); // 순위
        $('<div>', { class: 'table_col' }).append($('<img>', { src: item.profile_img, class: 'img' })).appendTo($row); // 프로필 이미지
        $('<div>', { class: 'table_col' }).append($('<span>').text(item.nickname)).appendTo($row); // 닉네임
        $('<div>', { class: 'table_col' }).append($('<span>').text(item.score)).appendTo($row); // 점수

        $tableContainer.append($row);
    }
}

  </script>
</body>

</html>