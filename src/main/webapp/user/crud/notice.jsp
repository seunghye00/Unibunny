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
											<li><a href="/list.board" id ="board_all">전체 게시글</a></li>
											<li><a href="javascript:;" id ="game1">게임1</a></li>
											<li><a href="javascript:;" id ="game2">게임2</a></li>
											<li><a href="javascript:;" id ="game3">게임3</a></li>
											<li><a href="javascript:;" id ="game4">게임4</a></li>
											<li><a href="javascript:;" id ="game5">게임5</a></li>
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
									<div class="list_table notice_table"></div>
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
			
		</div>
		<jsp:include page="../../common/footer.jsp" />
	</div>
	<script>
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
	<script type="text/javascript">
	// 테이블 담을 컨테이너 변수
	let listContainer = $(".notice_table");

	// 페이지네이션 변수 설정
	let cpage, record_total_count, record_count_per_page = 10, navi_count_per_page = 5;

	// 초기 페이지네이션 변수 설정
	function initPaginationVariables(cpageParam, recordTotalCount) {
	    cpage = cpageParam;
	    record_total_count = recordTotalCount;
	}

	// 최신순 버튼 클릭 시
	$("#recent_btn").on("click", function() {
	    let apiUrl = "/list.notice";
	    updateUrlAndFetchData(apiUrl, 1);
	});

	// 조회수 버튼 클릭 시
	$("#views_btn").on("click", function() {
	    let apiUrl = "/view.notice";
	    updateUrlAndFetchData(apiUrl, 1);
	});

	// 데이터 가져오고 테이블 렌더링 함수
	function fetchAndRenderData(apiUrl, page) {
	    $.ajax({
	        url: apiUrl,
	        method: "GET",
	        dataType: "json",
	        data: { cpage: page, recordCountPerPage: record_count_per_page }, // 페이지 번호를 쿼리 파라미터로 전달
	    }).done(function(resp) {
	        console.log(resp); // 받은 데이터 확인

	        // 페이지네이션 변수 초기화
	        initPaginationVariables(page, resp.record_total_count);

	        // 기존의 목록 컨테이너를 비웁니다.
	        listContainer.empty();

	        // 테이블의 헤더 부분 생성
	        let headerRow = $("<div>").addClass("table_row table_header");
	        let headerCol1 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("번호"));
	        let headerCol2 = $("<div>").addClass("table_col").append($("<span>").text("제목"));
	        let headerCol3 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("작성자"));
	        let headerCol4 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text("작성일"));
	        let headerCol5 = null;

	        // 버튼에 따라 추가되는 열 처리
	        if (apiUrl === "/list.notice") {
	            headerCol5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text("조회수"));
	        } else if (apiUrl === "/view.notice") {
	            headerCol5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text("조회수"));
	        }

	        headerRow.append(headerCol1, headerCol2, headerCol3, headerCol4, headerCol5);
	        listContainer.append(headerRow);

	        // 데이터 반복 처리
	        if (Array.isArray(resp.data)) {
	            let startNumber = (page - 1) * record_count_per_page + 1; // 시작 번호 계산

	            for (let i = 0; i < resp.data.length; i++) {
	                let dto = resp.data[i];
	                let row = $("<div>").addClass("table_row");
	                let link = $("<a>").attr("href", "/user/detail.notice?notice_seq=" + dto.notice_seq);
	                let col1 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(startNumber + i));
	                let col2 = $("<div>").addClass("table_col").append($("<span>").text(dto.title));
	                let col3 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(dto.nickname));
	                let col4 = $("<div>").addClass("table_col mob_hidden").append($("<span>").text(dto.write_date));
	                let col5 = null;

	                // 버튼에 따라 추가되는 열 처리
	                if (apiUrl === "/list.notice") {
	                    col5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text(dto.view_count));
	                } else if (apiUrl === "/view.notice") {
	                    col5 = $("<div>").addClass("table_col mob_hidden views_column").append($("<span>").text(dto.view_count));
	                }

	                link.append(col1, col2, col3, col4, col5);
	                row.append(link);
	                listContainer.append(row);
	            }
	        } else {
	            console.error("데이터 형식이 올바르지 않습니다. 데이터가 배열이 아닙니다.");
	        }

	        // 페이지네이션 생성
	        renderPagination(apiUrl, page);

	        // URL 업데이트
	        updateUrl(apiUrl, page);

	        // 버튼의 active 클래스 설정
	        $("#recent_btn, #views_btn").removeClass("active"); // 모든 버튼의 active 클래스 제거
	        if (apiUrl === "/list.notice") {
	            $("#recent_btn").addClass("active");
	        } else if (apiUrl === "/view.notice") {
	            $("#views_btn").addClass("active");
	        }

	    }).fail(function(jqXHR, textStatus, errorThrown) {
	        // AJAX 호출이 실패했을 경우 처리할 내용
	        console.error("AJAX 호출 실패: ", textStatus, errorThrown);
	    });
	}

	// 페이지네이션 생성 함수
	function renderPagination(apiUrl, currentPage) {
	    // 페이지네이션 초기 설정
	    let pageTotalCount = Math.ceil(record_total_count / record_count_per_page);
	    let startNavi = Math.floor((currentPage - 1) / navi_count_per_page) * navi_count_per_page + 1;
	    let endNavi = startNavi + navi_count_per_page - 1;

	    if (endNavi > pageTotalCount) {
	        endNavi = pageTotalCount;
	    }

	    let needNext = endNavi < pageTotalCount;
	    let needPrev = startNavi > 1;

	    // 페이지네이션 HTML 생성
	    let pageNation = $("#pagination");
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

	// 페이지네이션 클릭 이벤트 처리
	$(document).on("click", ".page_navi:not(.disabled)", function() {
	    let nextPage = $(this).data("page");
	    let currentApiUrl = getCurrentApiUrl();
	    updateUrlAndFetchData(currentApiUrl, nextPage);
	});

	// 현재 선택된 API 경로 반환 함수
	function getCurrentApiUrl() {
	    if ($("#recent_btn").hasClass("active")) {
	        return "/list.notice";
	    } else if ($("#views_btn").hasClass("active")) {
	        return "/view.notice";
	    }
	    // 기본적으로 최신순으로 설정
	    return "/list.board";
	}

	// URL 업데이트 및 데이터 가져오는 함수
	function updateUrlAndFetchData(apiUrl, page) {
	    updateUrl(apiUrl, page);
	    fetchAndRenderData(apiUrl, page);
	}

	// URL 업데이트 함수
	function updateUrl(apiUrl, page) {
	    history.pushState(null, null, "?api=" + apiUrl + "&page=" + page);
	}

	// 초기 페이지 로드 시 실행
	$(document).ready(function() {
	    // 초기에 URL 파라미터에 따라 데이터 불러오기
	    let urlParams = new URLSearchParams(window.location.search);
	    let apiUrl = urlParams.has('api') ? urlParams.get('api') : '/list.notice';
	    let page = urlParams.has('page') ? parseInt(urlParams.get('page')) : 1;

	    fetchAndRenderData(apiUrl, page); // 초기에는 최신순 데이터를 가져오도록 설정

	    // 버튼의 active 클래스 설정
	    $("#recent_btn, #views_btn").removeClass("active"); // 모든 버튼의 active 클래스 제거
	    if (apiUrl === "/list.notice") {
	        $("#recent_btn").addClass("active");
	    } else if (apiUrl === "/view.notice") {
	        $("#views_btn").addClass("active");
	    }
	});

	// 조회순 버튼 클릭 시
	$('#views_btn').on('click', function() {
	    // 조회순 버튼 색 변경 및 조회순 게시글 노출
	    $(this).css('background-color', 'var(--color-space-click)');
	    $('.views_list').show();

	    // 최신순 버튼 색 변경 & 해당 리스트 숨김
	    $('#recent_btn').css('background-color', 'var(--color-space');
	    $('.recent_list').hide();
	});

	// 게시글 상세 페이지에서 수정 버튼 클릭 시
	$('#edit_btn').on('click', function() {
	    location.href = '/tryUpdate.board?board_seq=' + get_board_seq();
	});

	// 게시글 수정 페이지에서 완료 버튼 클릭 시
	$('#update_btn').on('click', function(e) {
	    e.preventDefault();

	    let trimmedHtml = $("#summernote").html().trim();
	    $("#summernote").html(trimmedHtml);
	    console.log($("#summernote").text());
	    $('#update_board').submit();
	});

	// 게시글 수정 페이지에서 취소 버튼 클릭 시
	$('#cancel_btn').on('click', function() {
	    if (confirm("수정하신 내용은 저장되지 않습니다.")) {
	        location.href = '/user/detail.board?board_seq=' + get_board_seq();
	    }
	});

	// 게시글 상세 페이지에서 삭제 버튼 클릭 시
	$('#del_btn').on('click', function() {
	    if (confirm("정말로 삭제하시겠습니까?")) {
	        location.href = '/delete.board?board_seq=' + get_board_seq();
	    }
	});

	// 게시글의 seq 값을 반환하는 메서드
	function get_board_seq() {
	    return $("#board_seq").text().replace('# ', '');
	}
	</script>
</body>
</html>