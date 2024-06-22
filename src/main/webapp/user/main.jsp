<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unibunny</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/sub.css">
<link rel="stylesheet" href="../../css/layout.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script defer src="../../js/common.js"></script>
</head>
<body>
<!-- 전체 틀 영역 -->
  <div class="wrapper">
    <!-- 헤더 영역 -->
    <div class="header_area">
      <div class="header">
        <!-- 헤더 PC -->
        <div class="wrap mob_hidden">
          <h1 class="logo"><a href="javascript:;" title="메인으로 가기">
              <img src="../image/logo.png" alt="">
            </a>
          </h1>
          <div class="header_con">
            <ul class="header_gnb">
              <li>
                <a href="/list.board" class="gnb_comu"><span>커뮤니티</span></a>
              </li>
              <li>
                <a href="rank/rank.jsp" class="gnb_rank"><span>랭킹</span></a>
              </li>
              <li>
                <a href="ourpage/ourpage.jsp" class="gnb_our"><span>OUR PAGE</span></a>
              </li>
              <li>
                <a href="crud/faq.jsp" class="gnb_cs"><span>고객센터</span></a>
              </li>
            </ul>
            <ul class="header_my">
              <li class="my_01 "><a href="#" onclick="location.href='/mypage.member'" class="btn_mypage"><img src="../image/icon/mypageW.png"
                    alt="마이페이지 로고"></a></li>
              <li class="my_02"><a href="javascript:;" class="btn_login"><img src="../image/icon/login.png"
                    alt="로그인 로고"></a></li>
            </ul>
          </div>
        </div>
        <!-- 헤더 Mobile-->
        <div class="mob_wrap">

          <h1 class="mob_logo"><a href="javascript:;" title="메인으로 가기">
              <img src="../image/logo.png" alt="">
            </a>
          </h1>
          <div class="mob_ham"></div>
          <div onclick="history.back();" class="mob_page_cover"></div>
          <div class="mob_menu">
            <ul class="mob_list">
              <li>
                <strong><a href="/list.board">커뮤니티</a></strong>
              </li>
              <li>
                <strong><a href="javascript:;">랭킹</a></strong>
              </li>
              <li>
                <strong><a href="javascript:;">OUR PAGE</a></strong>
              </li>
              <li>
                <strong><a href="javascript:;">고객센터</a></strong>
              </li>

            </ul>
            <div class="mob_my">
              <ul>
                <li><a href="javascript:;" class="mob_mypage"><img src="../image/icon/mypage.png" alt="마이페이지 로고"></a>
                </li>
                <li><a href="javascript:;" class="mob_login"><img src="../image/icon/login_b.png" alt="로그인 로고"></a></li>
                <li>
                  <div onclick="history.back();" class="mob_close"></div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="banner_cont">
      <div class="swiper mainSwiper">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <a href="javascript:;" class="swiper_bg">
              <img src="../image/main/main_banner1.png" alt="">
            </a>
          </div>
          <div class="swiper-slide">
            <a href="javascript:;" class="swiper_bg">
              <img src="../image/main/main_banner.png" alt="">
            </a>
          </div>
          <div class="swiper-slide">
            <a href="javascript:;" class="swiper_bg">
              <img src="../image/main/Asset6.png" alt="">
            </a>
          </div>
          <div class="swiper-slide">
            <a href="javascript:;" class="swiper_bg">
              <img src="../image/main/Asset7.png" alt="">
            </a>
          </div>
        </div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-pagination"></div>
      </div>
    </div>
    <!-- 메인 영역 -->
    <div class="body_area">
      <div class="body for_pc">
        <div class="wrap">
          <!-- 메인 콘텐츠 영역 -->
          <div class="con_wrap main_con">
            <!-- 게임 콘텐츠 영역 -->
            <div class="game_con">
              <!-- 타이틀 박스 공통 -->
              <div class="title_box">
                <p class="title">인기게임</p>
              </div>
              <!-- 게임 스와이퍼 버튼 -->
              <div class="swiper_button_box">
                <div class="swiper_game_next">
                  <div class="swiper_btn">
                    <img src="../image/icon/ico_pgnext_arr.png" alt="">
                  </div>
                </div>
                <div class="swiper_game_prev">
                  <div class="swiper_btn swiper_btn_prev">
                    <img src="../image/icon/ico_pgnext_arr.png" alt="">
                  </div>
                </div>
              </div>
              <!-- 게임 스와이퍼 -->
              <div class="swiper_cont">
                <div class="swiper game_swiper">
                  <div class="swiper-wrapper">
                    <div class="swiper-slide">
					  <a href="javascript:;" onclick="window.open('../game/flappyCirby/index.jsp', 'GameWindow', 'width=288,height=505')" class="game_box">
					    <img src="../image/main/gameimg1.png" alt="">
					  </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="game_box">
                        <img src="../image/main/dummy.png" alt="">
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--  -->
            <div class="dual_con">
              <!-- 랭킹 콘텐츠 영역 -->
              <div class="rank_con">
                <!-- 타이틀 박스 공통 -->
                <div class="title_box">
                  <p class="title">랭킹</p>
                  <a href="javascript:;"></a>
                </div>
                <div class="rank_cont">
                  <div class="second_place">
                    <a href="javascript:;">
                      <img src="../image/main/문경원 증명사진.jpg" alt="">
                    </a>
                  </div>
                  <div class="first_place">
                    <a href="javascript:;">
                      <img src="../image/main/문경원 증명사진.jpg" alt="">
                    </a>
                  </div>
                  <div class="third_place">
                    <a href="javascript:;">
                      <img src="../image/main/문경원 증명사진.jpg" alt="">
                    </a>
                  </div>
                </div>
              </div>
              <!-- 게시판 콘텐츠 영역 -->
              <div class="noti_con">
                <!-- 타이틀 박스 공통 -->
                <div class="title_box">
                  <p class="title">인기글</p>
                  <a href="/list.board?api=/like.board&page=1"></a>
                </div>
                <div class="list_table">
                  <div class="table_row table_header">
                    <div class="table_col">
                      <span>제목</span>
                    </div>
                    <div class="table_col">
                      <span>추천수</span>
                    </div>
                  </div>
                  <div class="table_row">
                    <div class="table_col">
                      <a href="javascript:;">데이터 1 </a>
                    </div>
                    <div class="table_col">
                      <span>데이터 2</span>
                    </div>
                  </div>
                  <div class="table_row">
                    <div class="table_col">
                      <a href="javascript:;">데이터 1 </a>
                    </div>
                    <div class="table_col">
                      <span>데이터 2</span>
                    </div>
                  </div>
                  <div class="table_row">
                    <div class="table_col">
                      <a href="javascript:;">데이터 1 </a>
                    </div>
                    <div class="table_col">
                      <span>데이터 2</span>
                    </div>
                  </div>
                  <div class="table_row">
                    <div class="table_col">
                      <a href="javascript:;">데이터 1 </a>
                    </div>
                    <div class="table_col">
                      <span>데이터 2</span>
                    </div>
                  </div>
                  <div class="table_row">
                    <div class="table_col">
                      <a href="javascript:;">데이터 1 </a>
                    </div>
                    <div class="table_col">
                      <span>데이터 2</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 푸터 영역 -->
    <div class="footer_area">
      <div class="footer">
        <div class="wrap">
          <div class="footer_info">
            <ul class="footer_link">
              <li class="personal"><a href="javascript:;">개인정보처리방침</a></li>
              <li><a href="javascript:;">이용약관</a></li>
            </ul>
            <ul class="footer_address">
              <li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호</li>
              <li>Tel : 010-5482-9107</li>
            </ul>
          </div>
          <div class="footer_service">
            <strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
            <ul class="copy_desc">
              <li class="footer_copy">Copyright Team HoduGwaja. All Right Reserved</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script>
$(document).ready(function () {
    // 리스트 테이블을 담을 변수
    let listContainer = $(".list_table");

    // 초기 리스트 테이블 비우기
    listContainer.empty();

    // AJAX로 데이터 받아오기
    $.ajax({
        url: "/like.board",
        method: "GET",
        dataType: "json",
        data: { cpage: 1 }, // 페이지 번호를 쿼리 파라미터로 전달
    }).done(function (resp) {
        console.log(resp); // 받은 데이터 확인

        // 테이블의 헤더 부분 생성
        let headerRow = $("<div>").addClass("table_row table_header");
        let headerCol1 = $("<div>").addClass("table_col").append($("<span>").text("제목"));
        let headerCol2 = $("<div>").addClass("table_col").append($("<span>").text("추천수"));
        headerRow.append(headerCol1, headerCol2);
        listContainer.append(headerRow);

        // 데이터 반복 처리 (최대 5개까지 출력)
        let maxItems = Math.min(resp.data.length, 5); // 최대 5개 항목만 출력
        for (let i = 0; i < maxItems; i++) {
            let data = resp.data[i];
            let row = $("<div>").addClass("table_row");
            let link = $("<a>").attr("href", "/user/detail.board?board_seq=" + data.board_seq).text(data.title);
            let col1 = $("<div>").addClass("table_col").append(link);
            let col2 = $("<div>").addClass("table_col").append($("<span>").text(data.thumbs_up));
            row.append(col1, col2);
            listContainer.append(row);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // AJAX 호출이 실패했을 경우 처리할 내용
        console.error("AJAX 호출 실패: ", textStatus, errorThrown);
    });
});

</script>
</html>