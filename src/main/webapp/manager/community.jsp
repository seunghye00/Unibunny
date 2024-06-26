<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<link rel="stylesheet" href="../../css/manager_layout.css">
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/manager.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <style>
    .hidden {
      display: none;
    }

    .active {
      display: block;
    }
  </style>
</head>
<body>
<!-- 전체 틀 영역 -->
  <div class="wrapper">
    <!-- 헤더 영역 -->
    <div class="snb_area">
      <ul class="snb_cont">
        <li class="snb_cate">
          <a href="javascript:;" title="메인으로 가기">
            <h1 class="logo">
              <img src="../image/logo.png" alt="">
            </h1>ㅊ
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>대시보드</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>배너 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>게임 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>회원 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javescript:;">
            <span>게시판 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>고객센터</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="main_area">
      <div class="header_area">
        <div class="header">
          <div class="wrap">
            <div class="header_con">
              <div class="titlebox">
                <span>게시물 관리</span>
              </div>
              <div class="header_my">
                <ul>
                  <li>
                    <a href="javascript:;">
                      <span class="img_box">
                        <img src="../image/icon/mypageW.png" alt="">
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="javascript:;">
                      Logout
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="body_area">
        <div class="body">
          <div class="wrap">
            <!-- 메인 콘텐츠 영역 -->
            <div class="con_wrap">
              <div class="con post_con comu_con">
                <div class="select_subject">
                  <ul>
                    <li>
                      <a href="javascript:;" class="toggle-btn" data-target="all-posts">전체 게시글</a>
                    </li>
                    <li>
                      <a href="javascript:;" class="toggle-btn" data-target="draft-posts">임시 보관 게시글</a>
                    </li>
                    <li>
                      <a href="javascript:;" class="toggle-btn" data-target="draft-comments">임시 보관 댓글</a>
                    </li>
                  </ul>
                </div>
                <div class="search_box">
                  <div class="flex_space"></div>
                  <div class="search_bar">
                    <div class="search_input">
                      <input type="text" class="input_tag" id="search_input">
                    </div>
                    <div class="search_img">
                      <a href="javascript:;"><img src="../image/icon/ico_search.png" alt="검색 로고"></a>
                    </div>
                  </div>
                </div>

                <!-- 전체 게시글 리스트 -->

                <div class="all-posts list_table">
                  <ul>
                    <li class="post">
                      <button class="post-toggle"></button>
                      <div class="post-header">
                        <span class="post-title">게시글의 제목이 들어오는 곳 입니다.</span>
                        <button class="right-button">게시물 삭제</button>
                      </div>
                      <div class="comment-container">
                        <span class="post-comments">댓글1</span>
                        <button class="delete_comments_btn">댓글 삭제</button>
                      </div>
                      <div class="comment-container">
                        <span class="post-comments">댓글2</span>
                        <button class="delete_comments_btn">댓글 삭제</button>
                      </div>
                    </li>
                    <!-- 다른 게시글 항목도 동일하게 추가 -->
                  </ul>
                </div>
                


                <!-- 임시 보관 게시글 리스트-->
                <div class="list_table draft-posts hidden">
                  <div class="table_row table_header">
                    <div class="table_col"><span>번호</span></div>
                    <div class="table_col"><span>제목</span></div>
                    <div class="table_col"><span>작성자</span></div>
                    <div class="table_col"><span>작성일</span></div>
                    <div class="table_col"><span>되돌리기</span></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>1</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 글이에오</a></div>
                    <div class="table_col"><span>유저 1</span></div>
                    <div class="table_col"><span>삭제 날 1</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>2</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 글은 이 곳으로</a></div>
                    <div class="table_col"><span>비매너유저</span></div>
                    <div class="table_col"><span>삭제 날 2</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>3</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 글을 완전히 삭제 할 수 있어오</a></div>
                    <div class="table_col"><span>비매너유저</span></div>
                    <div class="table_col"><span>삭제 날 3</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>

                </div>

                <!-- 임시 보관 댓글 리스트 -->

                <div class="list_table draft-comments hidden">
                  <div class="table_row table_header">
                    <div class="table_col"><span>번호</span></div>
                    <div class="table_col"><span>제목</span></div>
                    <div class="table_col"><span>작성자</span></div>
                    <div class="table_col"><span>작성일</span></div>
                    <div class="table_col"><span>되돌리기</span></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>1</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 댓글이에오</a></div>
                    <div class="table_col"><span>유저 1</span></div>
                    <div class="table_col"><span>삭제 날 1</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>2</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 댓글은 이 곳으로</a></div>
                    <div class="table_col"><span>비매너유저</span></div>
                    <div class="table_col"><span>삭제 날 2</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>
                  <div class="table_row">
                    <div class="table_col"><span>3</span></div>
                    <div class="table_col"><a href="javascript:;">임시 삭제된 댓글을 완전히 삭제 할 수 있어오</a></div>
                    <div class="table_col"><span>비매너유저</span></div>
                    <div class="table_col"><span>삭제 날 3</span></div>
                    <div class="table_col"><button class="restore_btn">복구</button></div>
                  </div>

                </div>



                <div class="pagination">
                  <a href="javascript:;" class="btn_prev btn_disabled"></a>
                  <a href="javascript:;" class="active_page">1</a>
                  <a href="javascript:;">2</a>
                  <a href="javascript:;">3</a>
                  <a href="javascript:;">4</a>
                  <a href="javascript:;">5</a>
                  <a href="javascript:;" class="btn_next"></a>
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
    <script>
      $(document).ready(function () {
        $('.toggle-btn').on('click', function () {
          var target = $(this).data('target');
          $('.list_table').removeClass('active').addClass('hidden');
          $('.' + target).removeClass('hidden').addClass('active');
        });
      });


      // 토글


      $(document).ready(function () {
        $('.toggle-btn').on('click', function () {
          var target = $(this).data('target');
          $('.list_table').removeClass('active').addClass('hidden');
          $('.' + target).removeClass('hidden').addClass('active');
        });

        // 게시물 토글 스크립트
        $('.post-toggle').on('click', function () {
          $(this).closest('.post').toggleClass('active');
        });
      });





    </script>
</body>
</html>