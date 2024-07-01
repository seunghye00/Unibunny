<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<body>
<div class="header_area">
    <div class="header">
        <div class="wrap mob_hidden">
            <h1 class="logo">
                <a href="/user/main.jsp" title="메인으로 가기">
                    <img src="../../image/logo.png" alt="">
                </a>
            </h1>
            <div class="header_con">
                <ul class="header_gnb">
                    <li><a href="/list.board" class="gnb_comu"><span>커뮤니티</span></a>
                    </li>
                    <li><a href="/user/rank/rank.jsp" class="gnb_rank"><span>랭킹</span></a>
                    </li>
                    <li><a href="/user/ourpage/ourpage.jsp" class="gnb_our"><span>OUR
                      PAGE</span></a></li>
                    <li><a href="/list.faq" class="gnb_cs"><span>고객센터</span></a>
                    </li>
                </ul>
                <ul class="header_my">

                    <%
                        String profileImg = (String) session.getAttribute("profileImg");
                        String nickName = (String) session.getAttribute("nickName");
                    %>

                    <% if (profileImg != null && nickName != null) { %>
	                <li class="my_01 "><a href="javascript:;" onclick="location.href='/mypage.member'" class="btn_mypage"><img src="<%=profileImg%>" alt=""></a></li>
	                <li class="my_01 "><a href="jacascript::"><%=nickName%>님</a></li>
	                <li class="my_02"><a href="/logout.member" class="btn_login"><img src="../../image/icon/logout.png"
	                      alt="로그아웃 로고"></a></li>
                    <% } else { %>
                    <li class="my_01">
                        <a href="javascript:;" class="btn_mypage">
                            <img src="../../image/icon/mypageW.png" alt="마이페이지 로고">
                        </a>
                    </li>
                    <li class="my_02">
                        <a href="/login/login.jsp" class="btn_login">
                            <img src="../../image/icon/login.png" alt="로그인 로고">
                        </a>
                    </li>
                    
                    <% } %>
                </ul>
            </div>
        </div>
        <div class="mob_wrap">
            <h1 class="mob_logo">
                <a href="/user/main.jsp" title="메인으로 가기">
                    <img src="../../image/logo.png" alt="">
                </a>
            </h1>
            <div class="mob_ham"></div>
            <div onclick="history.back();" class="mob_page_cover"></div>
            <div class="mob_menu">
                <ul class="mob_list">
                    <li><strong><a href="/list.board">커뮤니티</a></strong></li>
                    <li><strong><a href="/user/rank/rank.jsp">랭킹</a></strong></li>
                    <li><strong><a href="/user/ourpage/ourpage.jsp">OUR PAGE</a></strong></li>
                    <li><strong><a href="/list.faq">고객센터</a></strong></li>
                </ul>
                <div class="mob_my">
                    <ul>
							<% if (profileImg != null && nickName != null) { %>
	                <li class="my_01 "><a href="javascript:;" onclick="location.href='/mypage.member'" class="btn_mypage"><img src="<%=profileImg%>" alt=""></a></li>
	                

	                <li class="my_02"><a href="/logout.member" class="btn_login"><img src="../../image/icon/mob_logout.png"
	                      alt="로그아웃 로고"></a></li>
	                      <li>
                            <div onclick="history.back();" class="mob_close"></div>
                        </li>
                    <% } else { %>                    
                        <li><a href="javascript:;" class="mob_mypage"><img src="../../image/icon/mypage.png"
                                                                           alt="마이페이지 로고"></a>
                        </li>
                        <li><a href="/login/login.jsp" class="mob_login"><img src="../../image/icon/login_b.png" alt="로그인 로고"></a>
                        </li>
                        <li>
                            <div onclick="history.back();" class="mob_close"></div>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>