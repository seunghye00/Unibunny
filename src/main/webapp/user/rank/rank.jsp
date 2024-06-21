<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>랭크</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
  <link rel="stylesheet" href="../../css/common.css">
  <link rel="stylesheet" href="../../css/sub.css">
  <link rel="stylesheet" href="../../css/layout.css">
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
  <script defer src="../../js/common.js"></script>
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
              <li class="my_01"><a href="javascript:;" class="btn_mypage"><img src="../../image/icon/mypageW.png"
                    alt="마이페이지 로고"></a></li>
              <li class="my_02"><a href="javascript:;" class="btn_login"><img src="../../image/icon/login.png"
                    alt="로그인 로고"></a></li>
            </ul>
          </div>
        </div>
        <div class="mob_wrap">
          <h1 class="mob_logo"><a href="javascript:;" title="메인으로 가기">
              <img src="../../../image/logo.png" alt="">
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
          <div class="con_wrap">
            <div class="con rank">
              <div class="title_box">
                <p class="title">랭킹</p>
              </div>
              <div class="dropdown-btn">Game</div>
              <div class="game_list">
                <a href="" title="게임1로 이동">호두러너</a>
                <a href="" title="게임2로 이동">게임2</a>
                <a href="" title="게임3로 이동">게임3</a>
                <a href="" title="게임4로 이동">게임4</a>
                <a href="" title="게임5로 이동">게임5</a>
              </div>
              <div class="top_rank">
                매월 첫째 주 월요일 업데이트 !!!
                <div class="podium">
                  <div class="second">
                    <div class="second-2">
                      <p>500점</p>
                      <P>손민서</P>
                    </div>
                    <img src="../../image/rank/silver.png" alt="로고">
                  </div>
                  <div class="first">
                    <div class="first-2">
                      <p>1000점</p>
                      <P>손민서</P>
                    </div>
                    <img src="../../image/rank/gold.png" alt="로고">
                  </div>
                  <div class="third">
                    <div class="third-2">
                      <p>500점</p>
                      <P>손민서</P>
                    </div>
                    <img src="../../image/rank/bronze.png" alt="로고">
                  </div>
                  <img src="../../image/rank/podium.png" alt="단상">
                </div>
              </div>
              <div class="rank list_table">
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
                <div class="table_row">
                  <div class="table_col"><span>4</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>70점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>5</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>60점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>6</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>50점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>7</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>40점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>8</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>30점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>9</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>20점</span></div>
                </div>
                <div class="table_row">
                  <div class="table_col"><span>10</span></div>
                  <div class="table_col"><img src="../../image/rank/gold.png" class="img"></div>
                  <div class="table_col"><span>데이터</span></div>
                  <div class="table_col"><span>10점</span></div>
                </div>
              </div>
              <div>
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
              <li class="footer_copy">Copyright Team HoduSnack. All Right Reserved</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const dropdownBtn = document.querySelector('.dropdown-btn');
      const gameList = document.querySelector('.game_list');

      dropdownBtn.addEventListener('click', function () {
        gameList.classList.toggle('show');
      });
    });

  </script>
</body>

</html>