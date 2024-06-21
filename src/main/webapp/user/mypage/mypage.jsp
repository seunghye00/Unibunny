<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
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

            <!--  -->

            <!-- profile -->
            <div class="profile_container">

              <!-- 프로필 카테고리탭을 담고있음(모바일에서는 사이드바로 대체) -->
              <div class="profile_aside">
                <div class="tab_container">
                  <ul role="menubar">
                    <li class="tab_item" role="none">

                      <button>
                        <i class="fi fi-rr-user"></i>
                        내 프로필
                      </button>
                    </li>
                  </ul>
                  <div class="tab_wrapper">
                    <strong class="tab_title">MY</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button>계정관리</button></li>
                      <li class="tab_item" role="none"><button>게임 기록 확인</button></li>
                    </ul>
                  </div>
                  <div class="tab_wrapper">
                    <strong class="tab_title">커뮤니티</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button>작성한 글</button></li>
                      <li class="tab_item" role="none"><button>작성한 댓글</button></li>
                      <li class="tab_item" role="none"><button>북마크</button></li>
                    </ul>
                  </div>
                  <div class="tab_wrapper">
                    <strong class="tab_title">고객센터</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button>1:1문의</button></li>
                    </ul>
                  </div>
                  <ul role="menubar">
                    <li class="tab_item" role="none">

                      <button>
                        로그아웃
                      </button>
                    </li>
                  </ul>


                </div>
              </div>

              <!-- 프로필 상세내용(모바일 마이페이지 메인화면) -->
              <div class="profile_wrapper">


                <div class="profile_contents_wrap default_contents">
                  <div class="profile_title">
                    내 프로필
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_profile">
                        <div class="profile_left">
                          <div class="profile_image_container">
                            <img id="profileImage" src="../../image/mypage_image/profile_icon.png" alt="Profile Image">
                          </div>
                            <span>회원의 이름이 들어가는 곳 이에오</span>
                        </div>
                        
                        <div class="button_container">
                        <button class="profile_edit_btn">프로필 편집</button>
                        <input type="file" class="real_upload" accept="image/*" style="display: none;">
                        <button class="apply_changes_btn" style="display: none;">적용</button>
                        <button class="cancel_changes_btn" style="display: none;">취소</button>
                      </div>
                    </div>
                </div>
                  <div class="profile_contents">
                    <div class="my_history">
                      <ul>
                        <li>가입날짜 : 1972년</li>
                        <li>게시물 수 : 1972</li>
                        <li>댓글 단 수 : 1972</li>
                      </ul>
                    </div>
                  </div>
                </div>


                <!-- 계정 관리  -->

                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    계정관리
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_account">
                        <label for="id">아이디</label>
                        <input type="text" id="id" readonly value="회원의 아이디">
                        
                        <label for="password" style="display: none">비밀번호</label>
                        <input type="password" id="password" readonly value="password" style="display: none">
                        
                        <label for="nickname">닉네임</label>
                        <input type="text" id="nickname" readonly value="회원의 닉네임">
                        <label for="phone">전화번호</label>
                        <input type="text" id="phone" readonly value="회원의 전화번호">
                        <label for="email">이메일</label>
                        <input type="text" id="email" readonly value="a01000000000@gmail.com">
                        
                        <label for="address1">주소</label>
                        <input type="text" id="address1" readonly value="회원의 주소">
                        <label for="address2">상세주소</label>
                        <input type="text" id="address2" readonly value="회원의 상세 주소">
                        <label for="postcode">우편번호</label>
                        <input type="text" id="postcode" readonly value="우편번호">
                    </div>
                </div>
                
                <div class="profile_contents">
                    <div class="edit_account">
                        <ul>
                            <li><label>계정 정보 수정</label></li>
                        </ul>
                        <button id="edit_button">수정</button>
                        <div id="edit_actions" style="display: none;">
                            <button id="apply_button">적용</button>
                            <button id="cancel_button">취소</button>
                        </div>
                    </div>
                </div>
                
                <div class="profile_contents">
                  <div class="delete_account">
                      <ul>
                          <li><label>계정 삭제하기</label></li>
                      </ul>
                          <button>회원 탈퇴</button>
                  </div>
              </div>
              
                
                

                  

                </div>


                <!-- 게임 기록 확인 -->

                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    게임 기록 확인
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_score">
                      <div class="dropdown-container">
                        <!-- <label for="dropdown">GAME</label> -->
                        <select id="dropdown" class="dropdown">
                          <option value="">게임을 선택하세요.</option>
                          <option value="option1">Game 1</option>
                          <option value="option2">Game 2</option>
                          <option value="option3">Game 3</option>
                          <option value="option4">Game 4</option>
                          <option value="option5">Game 5</option>
                        </select>
                      </div>




                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>게임명</span></div>
                          <div class="table_col"><span>점수</span></div>
                          <div class="table_col"><span>플레이 날짜</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>1</span></div>
                          <div class="table_col"><span>게임1</span></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>작성일</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>2</span></div>
                          <div class="table_col"><span>게임2</span></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>작성일</span></div>
                        </div>
                      </div>
                    </div>
                  </div>



                </div>


                <!-- 작성한 글 -->

                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    작성한 글
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_post">


                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>1</span></div>
                          <div class="table_col"><a href="#">첫번째 작성 글</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>2</span></div>
                          <div class="table_col"><a href="#">두번째 작성 글</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>


                <!-- 작성한 댓글 -->

                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    작성한 댓글
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_comment">

                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>1</span></div>
                          <div class="table_col"><a href="#">댓글단 글1</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>2</span></div>
                          <div class="table_col"><a href="#">댓글단 글2</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>



                <!-- 북마크 -->

                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    북마크
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_bookmark">


                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>1</span></div>
                          <div class="table_col"><a href="#">북마크한 글1</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>2</span></div>
                          <div class="table_col"><a href="#">북마크한 글2</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>0</span></div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>


                <!-- 1:1문의 -->
                <div class="profile_contents_wrap">
                  <div class="profile_title">
                    1:1문의
                  </div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>


                  <div class="profile_contents">
                    <div class="my_question">


                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>문의 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>답변 여부</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>1</span></div>
                          <div class="table_col"><a href="#">문의 글1</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>N</span></div>
                        </div>
                        <div class="table_row">
                          <div class="table_col"><span>2</span></div>
                          <div class="table_col"><a href="#">문의 글2</a></div>
                          <div class="table_col"><span>1234</span></div>
                          <div class="table_col"><span>N</span></div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>

              </div>

            </div>


            <script>



            </script>

            <!--  -->

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