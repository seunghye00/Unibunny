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
                      <a href="https://www.instagram.com/moontari_96/" target="_brank" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/ourpage/문경원 증명사진.jpg" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">문경원</span>
                            <span class="our_insta"><i class="fa-brands fa-instagram" style="color: #ffffff;"></i> moontari_96</span>
                            <span class="our_phone"><i class="fa-brands fa-whatsapp" style="color: #ffffff;"></i> 010-5482-9107</span>
                            <span class="our_stack"><i class="fa-brands fa-dev" style="color: #ffffff;"></i> JS, JAVA, React, SQL</span>
                            <span class="our_role">GIT, UI, AWS 관리</span>
                            <span class="our_txt">유연한 사고와 책임감이 있는 개발자 알파메일 문경원(차은우)입니다.</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="https://www.instagram.com/win.h_yes/" target="_brank" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/ourpage/제리.png" alt="">
                          </div>
                          <div class="our_cont">
                            <span class="our_name">한승혜</span>
                            <span class="our_insta"><i class="fa-brands fa-instagram" style="color: #ffffff;"></i> win.h_yes</span>
                            <span class="our_phone"><i class="fa-brands fa-whatsapp" style="color: #ffffff;"></i> 010-5778-9854</span>
                            <span class="our_stack"><i class="fa-brands fa-dev" style="color: #ffffff;"></i> JS, JAVA, SQL</span>
                            <span class="our_role">PM, 코드 리뷰 ,AWS 관리</span>
                            <span class="our_txt">사용자에게 가치를 더하는 소프트 웨어를 만드는 개발자 입니다.</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="https://www.instagram.com/jongho__00/" target="_brank" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/ourpage/호두.png" alt="">
                          </div>
                           <div class="our_cont">
                            <span class="our_name">박종호</span>
                            <span class="our_insta"><i class="fa-brands fa-instagram" style="color: #ffffff;"></i> jongco_00</span>
                            <span class="our_phone"><i class="fa-brands fa-whatsapp" style="color: #ffffff;"></i> 010-2370-1713</span>
                            <span class="our_stack"><i class="fa-brands fa-dev" style="color: #ffffff;"></i> JS, JAVA, SQL</span>
                            <span class="our_role">팀장, 회원 관리</span>
                            <span class="our_txt">유연한 사고와 책임감이 있는 개발자<br>박종호 입니다.</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="https://github.com/changgi8678" target="_brank" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/ourpage/창기.png" alt="">
                          </div>
                           <div class="our_cont">
                            <span class="our_name">조창기</span>
                            <span class="our_insta"><i class="fa-brands fa-instagram" style="color: #ffffff;"></i> changgi8678</span>
                            <span class="our_phone"><i class="fa-brands fa-whatsapp" style="color: #ffffff;"></i> 010-8641-8679</span>
                            <span class="our_stack"><i class="fa-brands fa-dev" style="color: #ffffff;"></i> JS, JAVA, SQL, C#, PHP</span>
                            <span class="our_role">DB 관리 및 마이페이지</span>
                            <span class="our_txt">프로그램 개발 직무에 필요한 역량을 쌓아나가고있는 개발자 조창기입니다.</span>
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="swiper-slide">
                      <a href="https://www.instagram.com/minseo10.17/" target="_brank" class="our_box">
                        <div class="our_tit">
                          <div class="img_box">
                            <img src="../../image/ourpage/푸바오.jpg" alt="">
                          </div>
                           <div class="our_cont">
                            <span class="our_name">손민서</span>
                            <span class="our_insta"><i class="fa-brands fa-instagram" style="color: #ffffff;"></i> minseo._.10.17</span>
                            <span class="our_phone"><i class="fa-brands fa-whatsapp" style="color: #ffffff;"></i> 010-3824-6685</span>
                            <span class="our_stack"><i class="fa-brands fa-dev" style="color: #ffffff;"></i> JS, JAVA, SQL</span>
                            <span class="our_role">테스트 관리 및 QA관리</span>
                            <span class="our_txt">유연한 사고와 책임감이 있는 개발자<br>손민서 입니다.</span>
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