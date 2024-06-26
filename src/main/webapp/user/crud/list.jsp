<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script defer src="../../../js/common.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../../common/header.jsp" />
		<div class="body_area">
			<div class="body for_pc">
				<div class="wrap">
					<div class="con_wrap">
						<div class="con">
							<div class="title_box">
								<p class="title">게시판</p>
							</div>
							<div class="cont_box">
								<div class="cate_box mob_hidden">
									<div class="sub_box">
										<div style="padding: 3px;"></div>
										<ul>
											<li class="sub_title"><a href="/list.notice">공지사항</a></li>
										</ul>
										<div style="padding: 5px;"></div>
										<ul>
											<li class="sub_title not_link">게시글</li>
											<li><a href="javascript:;" id ="board_all">전체 게시글</a></li>
											<li><a href="javascript:;" id ="game1">게임1</a></li>
											<li><a href="javascript:;" id ="game2">게임2</a></li>
											<li><a href="javascript:;" id ="game3">게임3</a></li>
											<li><a href="javascript:;" id ="game4">게임4</a></li>
											<li><a href="javascript:;" id ="game5">게임5</a></li>
										</ul>
									</div>
								</div>
								<select name="sub_menu" id="sub_menu" class="sub_menu">
									<option value="notice">공지사항</option>
									<option value="board_all">전체 게시글</option>
									<option value="game1">게임1</option>
									<option value="game2">게임2</option>
									<option value="game3">게임3</option>
									<option value="game4">게임4</option>
									<option value="game5">게임5</option>
								</select>
								<div class="list_box">
									<div class="choi_box">
										<div class="btn_box">
											<button class="write_btn choi_btn" id="recent_btn">최신순</button>
										</div>
										<div class="btn_box betw_btn">
											<button class="write_btn choi_btn" id="views_btn">조회순</button>
										</div>
										<div class="btn_box">
											<button class="write_btn choi_btn" id="likes_btn">추천순</button>
										</div>

										<div class="flex_space mob_hidden"></div>
										<div class="search_bar">
											<div class="search_input">
												<input type="text" class="input_tag" id="search_input">
											</div>
											<div class="search_img">
												<a href="javascript:;"><img
													src="../../image/icon/ico_search.png" alt="검색 로고"></a>
											</div>
										</div>
									</div>
									<!-- 추후 ajax로 데이터 받아와서 반복문으로 데이터 입력할 부분 -->
									<div class="list_table crud_table"></div>
									<div class="bottom_box">
										<div class="navi_box" id="pagination"></div>
										<div class="btn_box">
											<button class="write_btn" id="write_btn" type="button">작성하기</button>
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
	</div>
	<script>
	$(document).ready(function () {
		fetchAndRenderData('list.jsp',1,"default");
		 listLoad();
	});
	// select 요소의 변경 이벤트 처리
	document.getElementById('sub_menu').addEventListener('change', function() {
		// 선택된 옵션의 값 가져오기
		let selectedValue = this.value;

		// 선택된 옵션 값에 따라 분기 처리
		if (selectedValue === 'board_all') {
			// 전체 게시글을 선택한 경우, 원하는 동작 수행 (예: 기본 페이지로 이동)
			window.location.href = '/list.board';
		} else if (selectedValue === 'notice') {
			// 다른 옵션을 선택한 경우, 해당 페이지로 이동
			window.location.href = '/list.notice';
		} else {
			window.location.href = '/list.board';
		} 
	});
	</script>
</body>
</html>