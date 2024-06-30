 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>Member</title>
        <link rel="stylesheet" href="../css/manager_layout.css">
        <link rel="stylesheet" href="../css/common.css">
        <link rel="stylesheet" href="../css/manager.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script defer src="../js/manager.js"></script>
      </head>

      <body>
        <div class="wrapper">
          <div class="snb_area">
            <ul class="snb_cont">
              <li class="snb_cate">
                <a href="javascript:;" title="메인으로 가기">
                  <h1 class="logo">
                    <img src="../image/logo.png" alt="">
                  </h1>
                </a>
              </li>
				<li class="snb_cate"><a href="/admin/member.jsp"> <span class="cpage"
						>회원 관리</span>
				</a></li>
				<li class="snb_cate"><a href="/admin/community.jsp"> <span>게시판
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="/list.faq?userType=admin"> <span>고객센터</span>
				</a></li>
            </ul>
          </div>
          <div class="main_area">
            <div class="header_area">
              <div class="header">
                <div class="wrap">
                  <div class="header_con">
                    <div class="titlebox">
                      <span>회원 관리</span>
                    </div>
                    <div class="header_my">
                      <ul>
                        <li>
                          <a href="Javascript:;">
                            <span class="img_box">
                              <img src="../image/icon/mypageW.png" alt="">
                            </span>
                          </a>
                        </li>
                        <li>
                          <a href="Javascript:;">
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
              <div class="body manager_body">
                <div class="wrap">
                  <!-- 메인 콘텐츠 영역 -->
                  <div class="con_wrap">
                    <div class="con member_con">
                      <div class="select_subject">
                        <ul>
                          <li>
                            <a href="javascript:change_grade('회원')" class="cpage grade user">일반 회원</a>
                          </li>
                          <li>
                            <a href="javascript:change_grade('정지된 회원')" class="grade blacklist">블랙리스트</a>
                          </li>
                        </ul>
                      </div>
                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>아이디</span></div>
                          <div class="table_col"><span>닉네임</span></div>
                          <div class="table_col"><span>가입일</span></div>
                          <div class="table_col"><span>등록</span></div>
                        </div>
                      </div>
                      <div class="search_box">
                        <div class="search_bar">
                          <div class="search_input">
                            <input type="text" class="input_tag" id="search_input" placeholder="아이디 혹은 닉네임">
                          </div>
                          <div class="search_img">
                            <a href="javascript:search_user(1,'first')"><img src="../image/icon/ico_search.png" alt="검색 로고"></a>
                          </div>
                        </div>
                      </div>
                      <div class="pagination"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <jsp:include page="../common/footer.jsp" />
        </div>
        <script>
          $(document).ready(function() {
            get_member_list();
          });
        </script>
      </body>

      </html>