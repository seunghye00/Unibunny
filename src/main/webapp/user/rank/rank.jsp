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
								<a href="javascript:;" title="게임1로 이동" gamelistNum=1>FlappyCirby</a>
								<a href="javascript:;" title="게임2로 이동" gamelistNum=2>호두러너</a> <a
									href="javascript:;" title="게임3로 이동" gamelistNum=3>게임3</a> <a
									href="javascript:;" title="게임4로 이동" gamelistNum=4>게임4</a> <a
									href="javascript:;" title="게임5로 이동" gamelistNum=5>게임5</a>
							</div>
							<div class="top_rank">
								매월 첫째 주 월요일 업데이트 !!!
								<div class="podium">
									<div class="second">
										<div class="second-2">
											<img src="../../image/rank/silver.png" alt="로고">
										</div>
									</div>
									<div class="first">
										<div class="first-2">
											<img src="../../image/rank/gold.png" alt="로고">
										</div>
									</div>
									<div class="third">
										<div class="third-2">
											<img src="../../image/rank/bronze.png" alt="로고">
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
		<div class="footer_area">
			<div class="footer">
				<div class="wrap">
					<div class="footer_info">
						<ul class="footer_link">
							<li class="personal"><a href="javascript:;">개인정보처리방침</a></li>
							<li><a href="javascript:;">이용약관</a></li>
						</ul>
						<ul class="footer_address">
							<li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호
							</li>
							<li>Tel : 010-5482-9107</li>
						</ul>
					</div>
					<div class="footer_service">
						<strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
						<ul class="copy_desc">
							<li class="footer_copy">Copyright Team HoduSnack. All Right
								Reserved</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
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
        	gamelistNum = this.getAttribute('gamelistNum');
        	loadTableData();
            if (gamelistNum) {
                console.log('Clicked game ID:', gamelistNum);
                // 여기서 gameId를 사용하여 추가 작업을 수행할 수 있습니다.
            } else {
                console.log('No gameId attribute found for this link');
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
            $first2.append($score).append($nickname);
        } else if (i === 1) {
            // 두 번째 데이터를 .second-2에 추가
            var $second2 = $('.second-2');
            $second2.empty(); // 기존 내용 지우기
            var $score = $('<p>').text(topData.score + '점');
            var $nickname = $('<p>').text(topData.nickname);
            $second2.append($score).append($nickname);
        } else if (i === 2) {
            // 세 번째 데이터를 .third-2에 추가
            var $third2 = $('.third-2');
            $third2.empty(); // 기존 내용 지우기
            var $score = $('<p>').text(topData.score + '점');
            var $nickname = $('<p>').text(topData.nickname);
            $third2.append($score).append($nickname);
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