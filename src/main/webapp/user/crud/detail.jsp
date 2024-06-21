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
            <div class="con">
              <div class="title_box">
                <p class="title">#00</p>
              </div>
              <div class="cont_box">
                <div class="cont_body">
                  <form action="/index/crud/detail.html" id="board_form">
                    <div class="board_head">
                      <div class="title">
                        <div id="board_title">오늘 나 머리자르러 간다 ~!~!</div>
                        <div class="btn_box">
                          <button class="option_btn mark_option" type="button">
                            <i class="fa-regular fa-bookmark option_icon"></i>
                            <i class="fa-solid fa-bookmark option_icon"></i>
                          </button>
                        </div>
                      </div>
                      <div class="board_info">
                        <div class="writer">작성자 : realjongho</div>
                        <div class="write_date">작성날짜 : 2024.06.08</div>
                        <div class="views">조회수 : 234</div>
                        <div class="likes">스크랩 수 : 23</div>
                        <div class="edit_box">
                          <div class="btn_box">
                            <button class="write_btn" id="edit_btn" type="button">수정</button>
                          </div>
                          <div class="btn_box">
                            <button class="write_btn" id="del_btn" type="button">삭제</button>
                          </div>
                          <div class="btn_box">
                            <button class="write_btn back_btn" id="back_btn" type="button">목록</button>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="board_body">
                      <div class="board_cont" id="board_cont" contenteditable="false">내용</div>
                    </div>
                  </form>
                  <div class="option_box">
                    <div class="btn_box">
                      <button class="option_btn likes_option" type="button">
                        <i class="fa-regular fa-thumbs-up option_icon"></i>
                        <i class="fa-solid fa-thumbs-up option_icon"></i>
                        <span>2</span>
                      </button>
                    </div>
                  </div>
                  <form action="/index/crud/detail.html" id="comm_form">
                    <div class="comm_box">
                      <div class="write_comm">
                        <div class="input_comm">
                          <div class="input_box" contenteditable="true"></div>
                        </div>
                        <div class="btn_box">
                          <button class="write_btn">작성</button>
                        </div>
                      </div>
                     </div>
                   </form>
                   <form action="/update.reply">
                      <div style="padding: 10px;"></div>
                      <div class="choi_box">
                        <div class="btn_box">
                          <button class="write_btn choi_btn" type="button" id="comm_recent_btn">최신순</button>
                        </div>
                        <div class="btn_box">
                          <button class="write_btn choi_btn" type="button" id="comm_likes_btn">추천순</button>
                        </div>
                        <div class="flex_space mob_hidden"></div>
                      </div>
                      <div class="comm_list">
                        <div class="comm">
                          <div class="comm_info">
                            <div class="comm_writer">작성자 : jonghoya</div>
                            <div class="comm_date">작성일시 : 2024.06.18</div>
                          </div>
                          <div class="comm_cont">댓글 내용</div>
                          <div class="edit_box">

                            <div class="btn_box">
                              <button class="option_btn likes_option" type="button">
                                <i class="fa-regular fa-thumbs-up fa-xs option_icon"></i>
                                <i class="fa-solid fa-thumbs-up fa-xs option_icon"></i>
                                <span>2</span>
                              </button>
                            </div>
                          </div>
                        </div>
                        <div class="comm">
                          <div class="comm_info">
                            <div class="comm_writer">작성자 : jonghoya</div>
                            <div class="comm_date">작성일시 : 2024.06.18</div>
                          </div>
                          <div class="comm_cont">댓글 내용</div>
                          <div class="edit_box">
                            <div class="btn_box">
                              <button class="write_btn" id="edit_btn" type="button">수정</button>
                              <button type="submit" class="write_btn edit_btn" id="submit_btn">완료</button>
                            </div>
                            <div class="btn_box">
                              <button class="write_btn" id="del_btn" type="button">삭제</button>
                              <button type="button" class="write_btn edit_btn" id="cancle_btn">취소</button>
                            </div>
                            <div class="btn_box">
                              <button class="option_btn likes_option" type="button">
                                <i class="fa-regular fa-thumbs-up fa-xs option_icon"></i>
                                <i class="fa-solid fa-thumbs-up fa-xs option_icon"></i>
                                <span>2</span>
                              </button>
                            </div>
                          </div>
                        </div>
                        <div class="comm">
                          <div class="comm_info">
                            <div class="comm_writer">작성자 : jonghoya</div>
                            <div class="comm_date">작성일시 : 2024.06.18</div>
                          </div>
                          <div class="comm_cont">댓글 내용</div>
                          <div class="edit_box">
                            <div class="btn_box">
                              <button class="write_btn" id="edit_btn" type="button">수정</button>
                              <button type="submit" class="write_btn edit_btn" id="submit_btn">완료</button>
                            </div>
                            <div class="btn_box">
                              <button class="write_btn" id="del_btn" type="button">삭제</button>
                              <button type="button" class="write_btn edit_btn" id="cancle_btn">취소</button>
                            </div>
                            <div class="btn_box">
                              <button class="option_btn likes_option" type="button">
                                <i class="fa-regular fa-thumbs-up fa-xs option_icon"></i>
                                <i class="fa-solid fa-thumbs-up fa-xs option_icon"></i>
                                <span>2</span>
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                     </form>
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