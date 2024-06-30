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
								<p class="title">공지사항</p>
							</div>
							<div class="cont_box">
								<div class="cate_box mob_hidden">
									<div class="sub_box">
										<div style="padding: 3px;"></div>
										<ul>
											<li class="sub_title"><a class="cpage" href="/list.notice">공지사항</a></li>
										</ul>
										<div style="padding: 5px;"></div>
										<ul>
											<li class="sub_title not_link">게시글</li>
											<li><a href="/list.board" id="board_all">전체 게시글</a></li>
											<li><a
												href="/list.board?api=/list.board&page=1&gameId=1"
												id="game1">Flappy Cirby</a></li>
											<li><a
												href="/list.board?api=/list.board&page=1&gameId=2"
												id="game2">4096</a></li>
											<li><a
												href="/list.board?api=/list.board&page=1&gameId=3"
												id="game3">shooting walnut</a></li>
											<li><a
												href="/list.board?api=/list.board&page=1&gameId=4"
												id="game4">블랙잭</a></li>
											<li><a
												href="/list.board?api=/list.board&page=1&gameId=5"
												id="game5">Doodle Jump</a></li>
									</div>
								</div>
								<select name="sub_menu" id="sub_menu" class="sub_menu">
									<option value="notice">공지사항</option>
									<option value="board_all">전체 게시글</option>
									<option value="game1">Flappy Cirby</option>
									<option value="game2">4096</option>
									<option value="game3">shooting walnut</option>
									<option value="game4">블랙잭</option>
									<option value="game5">Doodle Jump</option>
								</select>
								<div class="list_box">
									<div class="choi_box">
										<div class="btn_box">
											<button class="write_btn choi_btn" id="notice_recent_btn">최신순</button>
										</div>
										<div class="btn_box betw_btn">
											<button class="write_btn choi_btn" id="notice_views_btn">조회순</button>
										</div>
										<div class="flex_space mob_hidden"></div>
									</div>
									<!-- 추후 ajax로 데이터 받아와서 반복문으로 데이터 입력할 부분 -->
									<div class="list_table notice_table"></div>
									<div class="bottom_box">
										<div class="navi_box" id="pagination_notice"></div>
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
		$(document).ready(function () {
			fetchAndRenderDataNotice('notice.jsp',1);
			noticeLoad();
		})
		// 테이블 담을 컨테이너 변수
		let listNoticeContainer = $('.notice_table');
		// 페이지네이션 변수 설정
		let currentPage, totalRecordCount, recordsPerPage = 10, navPagesPerPage = 5;

		// 초기 페이지네이션 변수 설정
		function initPaginationVariables(currentPageParam, totalRecordCountParam) {
			currentPage = currentPageParam;
			totalRecordCount = totalRecordCountParam;
		}

		// 최신순 버튼 클릭 시
		$("#notice_recent_btn").on("click", function() {
			let apiUrl = "/list.notice";
			updateUrlAndFetchDataNotice(apiUrl, 1);
			setActiveButton("#notice_recent_btn");
		});

		// 조회수 버튼 클릭 시
		$("#notice_views_btn").on("click", function() {
			let apiUrl = "/view.notice";
			updateUrlAndFetchDataNotice(apiUrl, 1);
			setActiveButton("#notice_views_btn");
		});
		$('#sub_menu').on('change', function() {
		    let selectedValue = $(this).val();
		    if (selectedValue === 'board_all') {
		        window.location.href = '/list.board';
		    } else if (selectedValue === 'notice') {
		        window.location.href = '/list.notice';
		    } else if (selectedValue === 'game1') {
		        window.location.href = '/list.board?api=/list.board&page=1&gameId=1';
		    } else if (selectedValue === 'game2') {
		        window.location.href = '/list.board?api=/list.board&page=1&gameId=2';
		    } else if (selectedValue === 'game3') {
		        window.location.href = '/list.board?api=/list.board&page=1&gameId=3';
		    } else if (selectedValue === 'game4') {
		        window.location.href = '/list.board?api=/list.board&page=1&gameId=4';
		    } else if (selectedValue === 'game5') {
		        window.location.href = '/list.board?api=/list.board&page=1&gameId=5';
		    } else {
		        window.location.href = '/list.board';
		    }
		});

		// 버튼 active 클래스 설정
		function setActiveButton(buttonId) {
			$("#notice_recent_btn, #notice_views_btn").removeClass("active");
			$(buttonId).addClass("active");
		}

		// 데이터 가져오고 테이블 렌더링 함수
		function fetchAndRenderDataNotice(apiUrl, page) {
			$.ajax({
				url: apiUrl,
				method: "GET",
				dataType: "json",
				data: { cpage: page, recordCountPerPage: recordsPerPage }, // 페이지 번호를 쿼리 파라미터로 전달
			}).done(function(resp) {
				// 페이지네이션 변수 초기화
				initPaginationVariables(page, resp.record_total_count);

				// 기존의 목록 컨테이너를 비웁니다.
				listNoticeContainer.empty();
				console.log("Container emptied:", listNoticeContainer.html());
				// 테이블의 헤더 부분 생성
				let headerRow = $("<div>").addClass("table_row table_header");
				let headerCol1 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("번호"));
				let headerCol2 = $("<div>").addClass("table_col").append($("<span>").text("제목"));
				let headerCol3 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("작성자"));
				let headerCol4 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("작성일"));
				let headerCol5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text("조회수"));

				headerRow.append(headerCol1, headerCol2, headerCol3, headerCol4, headerCol5);
				listNoticeContainer.append(headerRow);

				// 데이터 반복 처리
				if (Array.isArray(resp.data)) {
					let startNumber = (page - 1) * recordsPerPage + 1; // 시작 번호 계산

					for (let i = 0; i < resp.data.length; i++) {
						let dto = resp.data[i];
						let row = $("<div>").addClass("table_row");
						let link = $("<a>").attr("href", "/user/detail.notice?notice_seq=" + dto.notice_seq);
						let col1 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(startNumber + i));
						let col2 = $("<div>").addClass("table_col").append($("<span>").text(dto.title));
						let col3 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(dto.nickname));
						let col4 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(dto.write_date));
						let col5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text(dto.view_count));

						link.append(col1, col2, col3, col4, col5);
						row.append(link);
						listNoticeContainer.append(row);
					}
				} else {
					console.error("데이터 형식이 올바르지 않습니다. 데이터가 배열이 아닙니다.");
				}

				// 페이지네이션 생성
				renderPaginationNotice(apiUrl, page);

				// URL 업데이트
				updateUrlNotice(apiUrl, page);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				// AJAX 호출이 실패했을 경우 처리할 내용
				console.error("AJAX 호출 실패: ", textStatus, errorThrown);
			});
		}

		// 페이지네이션 생성 함수
		function renderPaginationNotice(apiUrl, currentPage) {
			// 페이지네이션 초기 설정
			let pageTotalCount = Math.ceil(totalRecordCount / recordsPerPage);
			let startNavi = Math.floor((currentPage - 1) / navPagesPerPage) * navPagesPerPage + 1;
			let endNavi = startNavi + navPagesPerPage - 1;

			if (endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}

			let needNext = endNavi < pageTotalCount;
			let needPrev = startNavi > 1;

			// 페이지네이션 HTML 생성
			let pageNation = $("#pagination_notice");
			pageNation.empty();

			pageNation.append("<a class='page_navi arr_navi start_arr" + (needPrev ? "" : " disabled") + "' href='" + (needPrev ? "#" : "#") + "' data-page='" + (needPrev ? (startNavi - 1) : "#") + "'><img class='navi_icon start_navi' src='../../image/icon/pagination.png' alt='start navi 로고'></a>");

			for (let i = startNavi; i <= endNavi; i++) {
				if (currentPage === i) {
					pageNation.append("<a class='page_navi active' href='#' data-page='" + i + "'>" + i + "</a> ");
				} else {
					pageNation.append("<a class='page_navi' href='#' data-page='" + i + "'>" + i + "</a> ");
				}
			}

			pageNation.append("<a class='page_navi arr_navi end_arr" + (needNext ? "" : " disabled") + "' href='" + (needNext ? "#" : "#") + "' data-page='" + (needNext ? (endNavi + 1) : "#") + "'><img class='navi_icon end_navi' src='../../image/icon/pagination.png' alt='end navi 로고'></a>");
		}



		// 현재 선택된 API 경로 반환 함수
		function getCurrentApiUrlNotice() {
			if ($("#notice_recent_btn").hasClass("active")) {
				return "/list.notice";
			} else if ($("#notice_views_btn").hasClass("active")) {
				return "/view.notice";
			}
			// 기본적으로 최신순으로 설정
			return "/list.notice";
		}

		// URL 업데이트 및 데이터 가져오는 함수
		function updateUrlAndFetchDataNotice(apiUrl, page) {
			updateUrlNotice(apiUrl, page);
			fetchAndRenderDataNotice(apiUrl, page);
		}

		// URL 업데이트 함수
		function updateUrlNotice(apiUrl, page) {
			history.pushState(null, null, "?api=" + apiUrl + "&page=" + page);
		}

		// 초기 페이지 로드 시 실행
		function noticeLoad() {
			// 초기에 URL 파라미터에 따라 데이터 불러오기
			let urlParams = new URLSearchParams(window.location.search);
			let apiUrl = urlParams.has('api') ? urlParams.get('api') : '/list.notice';
			let page = urlParams.has('page') ? parseInt(urlParams.get('page')) : 1;

			fetchAndRenderDataNotice(apiUrl, page); // 초기에는 최신순 데이터를 가져오도록 설정

			// 버튼의 active 클래스 설정
			setActiveButton(apiUrl === "/list.notice" ? "#notice_recent_btn" : "#notice_views_btn");
			// 페이지네이션 클릭 이벤트 처리
		};

		// 게시글의 seq 값을 반환하는 메서드
		function get_board_seq_notice() {
			return $("#board_seq").text().replace('# ', '');
		}
		$(document).on("click", ".page_navi:not(.disabled)", function() {
			let nextPage = $(this).data("page");
			let currentApiUrl = getCurcrentApiUrlNotice();
			updateUrlAndFetchDataNotice(currentApiUrl, nextPage);
		});
	
	</script>
</body>
</html>