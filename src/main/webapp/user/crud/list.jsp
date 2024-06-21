<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script defer src="../../../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
                      <li class="sub_title"><a href="javascript:;">공지사항</a></li>
                    </ul>
                    <div style="padding: 5px;"></div>
                    <ul>
                      <li class="sub_title not_link">게시글</li>
                      <li><a href="javascript:;">전체 게시글</a></li>
                      <li><a href="javascript:;">게임1</a></li>
                      <li><a href="javascript:;">게임2</a></li>
                      <li><a href="javascript:;">게임3</a></li>
                      <li><a href="javascript:;">게임4</a></li>
                      <li><a href="javascript:;">게임5</a></li>
                    </ul>
                  </div>
                </div>
                <select name="sub_menu" id="sub_menu" class="sub_menu">
                  <option value="notice">공지사항</option>
                  <option value="board_all" selected>전체 게시글</option>
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
                        <a href="javascript:;"><img src="../../image/icon/ico_search.png" alt="검색 로고"></a>
                      </div>
                    </div>
                  </div>
                  <!-- 추후 ajax로 데이터 받아와서 반복문으로 데이터 입력할 부분 -->
                  <div class="list_table recent_list">
                    <div class="table_row table_header">
                      <div class="table_col mob_hidden"><span>번호</span></div>
                      <div class="table_col"><span>제목</span></div>
                      <div class="table_col mob_hidden"><span>작성자</span></div>
                      <div class="table_col mob_hidden"><span>작성일</span></div>
                      <div class="table_col mob_hidden"><span>조회수</span></div>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>1</span></div>
                        <div class="table_col"><span>집에 너무 가고 싶다</span></div>
                        <div class="table_col"><span>I'm jongho</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>58</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>2</span></div>
                        <div class="table_col"><span>흐ㅓ어어어엉어어ㅓ어ㅜㅡㅜㅠ</span></div>
                        <div class="table_col"><span>jonghoHi</span></div>
                        <div class="table_col"><span>2024.05.18</span></div>
                        <div class="table_col"><span>5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>3</span></div>
                        <div class="table_col"><span>어쩌라고요</span></div>
                        <div class="table_col"><span>jonghobabo</span></div>
                        <div class="table_col"><span>2024.04.01</span></div>
                        <div class="table_col"><span>2456</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>4</span></div>
                        <div class="table_col"><span>VVS</span></div>
                        <div class="table_col"><span>jonghoV</span></div>
                        <div class="table_col"><span>2024.01.09</span></div>
                        <div class="table_col"><span>16786</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                  </div>
                  <div class="list_table views_list">
                    <div class="table_row table_header">
                      <div class="table_col mob_hidden"><span>번호</span></div>
                      <div class="table_col"><span>제목</span></div>
                      <div class="table_col mob_hidden"><span>작성자</span></div>
                      <div class="table_col mob_hidden"><span>작성일</span></div>
                      <div class="table_col mob_hidden"><span>조회수</span></div>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>1</span></div>
                        <div class="table_col"><span>집에 너무 가고 싶다</span></div>
                        <div class="table_col"><span>I'm jongho</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>58</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>2</span></div>
                        <div class="table_col"><span>흐ㅓ어어어엉어어ㅓ어ㅜㅡㅜㅠ</span></div>
                        <div class="table_col"><span>jonghoHi</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>3</span></div>
                        <div class="table_col"><span>어쩌라고요</span></div>
                        <div class="table_col"><span>jonghobabo</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>2</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>4</span></div>
                        <div class="table_col"><span>VVS</span></div>
                        <div class="table_col"><span>jonghoV</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>1</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                  </div>
                  <div class="list_table likes_list">
                    <div class="table_row table_header">
                      <div class="table_col mob_hidden"><span>번호</span></div>
                      <div class="table_col"><span>제목</span></div>
                      <div class="table_col mob_hidden"><span>작성자</span></div>
                      <div class="table_col mob_hidden"><span>작성일</span></div>
                      <div class="table_col mob_hidden"><span>추천수</span></div>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>1</span></div>
                        <div class="table_col"><span>집에 너무 가고 싶다</span></div>
                        <div class="table_col"><span>I'm jongho</span></div>
                        <div class="table_col"><span>2024.06.18</span></div>
                        <div class="table_col"><span>58</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>2</span></div>
                        <div class="table_col"><span>흐ㅓ어어어엉어어ㅓ어ㅜㅡㅜㅠ</span></div>
                        <div class="table_col"><span>jonghoHi</span></div>
                        <div class="table_col"><span>2024.05.18</span></div>
                        <div class="table_col"><span>50</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>3</span></div>
                        <div class="table_col"><span>어쩌라고요</span></div>
                        <div class="table_col"><span>jonghobabo</span></div>
                        <div class="table_col"><span>2024.04.01</span></div>
                        <div class="table_col"><span>35</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="/index/crud/detail.html">
                        <div class="table_col mob_hidden"><span>4</span></div>
                        <div class="table_col"><span>VVS</span></div>
                        <div class="table_col"><span>jonghoV</span></div>
                        <div class="table_col"><span>2024.01.09</span></div>
                        <div class="table_col"><span>1</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                    <div class="table_row">
                      <a href="javascript:;">
                        <div class="table_col mob_hidden"><span>데이터 1</span></div>
                        <div class="table_col"><span>데이터 2</span></div>
                        <div class="table_col"><span>데이터 3</span></div>
                        <div class="table_col"><span>데이터 4</span></div>
                        <div class="table_col"><span>데이터 5</span></div>
                      </a>
                    </div>
                  </div>
                  <div class="bottom_box">
                    <div class="navi_box">
                      <a href="#" class="page_navi arr_navi start_arr">
                        <img class="navi_icon start_navi" src="../../image/icon/pagination.png" alt="start navi 로고">
                      </a>
                      <a href="#" class="page_navi cpage">1</a>
                      <a href="#" class="page_navi">2</a>
                      <a href="#" class="page_navi">3</a>
                      <a href="#" class="page_navi">4</a>
                      <a href="#" class="page_navi arr_navi">
                        <img class="navi_icon" src="../../image/icon/pagination.png" alt="end navi 로고">
                      </a>
                    </div>
                    <div class="btn_box">
                      <button class="write_btn" id="write_btn">작성하기</button>
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
                <li class="footer_copy">Copyright Team HoduSnack. All Right Reserved</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>