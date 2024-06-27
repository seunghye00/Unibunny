<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Our page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script defer src="../../../js/common.js"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="../../common/header.jsp" />
    <div class="body_area">
      <div class="body for_pc">
        <div class="wrap">
          <div class="con_wrap our_wrap">
            <div class="our_con">
              <div class="title_box">
                <p class="title">OUR PAGE</p>
                <p class="subtxt">크리에이티브 한 개발자들의 이야기</p>
              </div>
              <div class="swiper_button_box">
                <div class="swiper_ourpage_next">
                  <div class="swiper_btn">
                    <img src="../../image/icon/ico_pgnext_arr.png" alt="">
                  </div>
                </div>
                <div class="swiper_ourpage_prev">
                  <div class="swiper_btn swiper_btn_prev">
                    <img src="../../image/icon/ico_pgnext_arr.png" alt="">
                  </div>
                </div>
              </div>
              <!-- 게임 스와이퍼 -->
              <div class="swiper_cont ourswiper_cont">
                <div class="swiper ourpage_swiper">
                  <div class="swiper-wrapper">
                    <div class="swiper-slide">
                      <a href="javascript:;" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/main/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">문경원</span>
                            <span class="our_insta">moontari_96</span>
                            <span class="our_phone">010-5482-9107</span>
                            <span class="our_txt">안녕하세요 풀스택 개발자를 꿈꾸는 알파메일 문경원입니다.</span>
                            <span class="our_stack">JS, JAVA, React, SQL</span>
                            <span class="our_role">GIT 관리 및 UI 관리</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/main/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">문경원</span>
                            <span class="our_insta">moontari_96</span>
                            <span class="our_phone"></span>
                            <span class="our_txt">안녕하세요 풀스택 개발자를 꿈꾸는 알파메일 문경원입니다.</span>
                            <span class="our_stack">JS, JAVA, React, SQL</span>
                            <span class="our_role">GIT 관리 및 UI 관리</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/main/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">문경원</span>
                            <span class="our_insta">moontari_96</span>
                            <span class="our_phone"></span>
                            <span class="our_txt">안녕하세요 풀스택 개발자를 꿈꾸는 알파메일 문경원입니다.</span>
                            <span class="our_stack">JS, JAVA, React, SQL</span>
                            <span class="our_role">GIT 관리 및 UI 관리</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/main/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">문경원</span>
                            <span class="our_insta">moontari_96</span>
                            <span class="our_phone"></span>
                            <span class="our_txt">안녕하세요 풀스택 개발자를 꿈꾸는 알파메일 문경원입니다.</span>
                            <span class="our_stack">JS, JAVA, React, SQL</span>
                            <span class="our_role">GIT 관리 및 UI 관리</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="javascript:;" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/main/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">손민서</span>
                            <span class="our_insta">minseo._.10.17</span>
                            <span class="our_phone"></span>
                            <span class="our_txt">안녕하세요 풀스택 개발자를 꿈꾸는 손민서입니다.</span>
                            <span class="our_stack">JS, JAVA, SQL</span>
                            <span class="our_role">GIT 관리 및 UI 관리</span>
                          </div>
                        </div>
                      </a>
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
</body>
<script>
/* function initializeOurSwiper() {
	  if (ourpage_swiper) {
	    ourpage_swiper.destroy(true, true); // 기존 Swiper 인스턴스를 파괴
	  }

	  let slidesPerView;

	  if ($(window).width() < 650) {
	    slidesPerView = 1;
	  } else if ($(window).width() < 750) {
	    slidesPerView = 2;
	  } else if ($(window).width() < 950) {
	    slidesPerView = 3;
	  } else if ($(window).width() < 1200) {
	    slidesPerView = 4;
	  } else {
	    slidesPerView = 5;
	  }

	  ourpage_swiper = new Swiper('.ourpage_swiper', {
	    slidesPerView: slidesPerView,
	    spaceBetween: 30,
	    navigation: {
	      nextEl: '.swiper_ourpage_next',
	      prevEl: '.swiper_ourpage_prev',
	    },
	  });
	} */
$(document).ready(function () {
	initializeOurSwiper(); // ourpage 스와이퍼 재설정
    
	$(window).resize(function () {
		initializeOurSwiper(); // ourpage 스와이퍼 재설정
	});
});
</script>
</html>