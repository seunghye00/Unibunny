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
    <div class="header_area">
      <div class="header">
        <div class="wrap mob_hidden">
          <h1 class="logo"><a href="javascript:;" title="메인으로 가기">
              <img src="../../image/logo.png" alt="">
            </a>
          </h1>
          <div class="header_con">
            <ul class="header_gnb">
              <li>
                <a href="javascript:;" class="gnb_comu"><span>커뮤니티</span></a>
              </li>
              <li>
                <a href="javascript:;" class="gnb_rank"><span>랭킹</span></a>
              </li>
              <li>
                <a href="javascript:;" class="gnb_our"><span>OUR PAGE</span></a>
              </li>
              <li>
                <a href="javascript:;" class="gnb_cs"><span>고객센터</span></a>
              </li>
            </ul>
            <ul class="header_my">
              <li class="my_01 "><a href="javascript:;" class="btn_mypage"><img src="../../image/icon/mypageW.png"
                    alt="마이페이지 로고"></a></li>
              <li class="my_02"><a href="javascript:;" class="btn_login"><img src="../../image/icon/login.png"
                    alt="로그인 로고"></a></li>
            </ul>
          </div>
        </div>
        <div class="mob_wrap">
          <h1 class="mob_logo"><a href="javascript:;" title="메인으로 가기">
              <img src="../../image/logo.png" alt="">
            </a>
          </h1>
          <div class="mob_ham"></div>
          <div onclick="history.back();" class="mob_page_cover"></div>
          <div class="mob_menu">
            <ul class="mob_list">
              <li>
                <strong><a href="javascript:;">커뮤니티</a></strong>
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
                <li><a href="javascript:;" class="mob_mypage"><img src="../../image/icon/mypage.png" alt="마이페이지 로고"></a>
                </li>
                <li><a href="javascript:;" class="mob_login"><img src="../../image/icon/login_b.png" alt="로그인 로고"></a>
                </li>
                <li>
                  <div onclick="history.back();" class="mob_close"></div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
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
</html>