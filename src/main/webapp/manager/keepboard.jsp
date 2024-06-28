<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" href="../css/manager_layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/manager.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
                        </h1>
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
                    <a href="javascript:;">
                        <span>게시판 관리</span>
                    </a>
                </li>
                <li class="snb_cate">
                    <a href="javascript:;">
                        <span>공지사항 관리</span>
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
                                <span>임시 보관 게시물</span>
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
                            <div class="con">
                                <div class="ntc select_subject">
                                    <ul>
                                        <li><a href="/admin_list.notice">공지사항</a></li>
                                        <li><a href="/admin_list.board">전체 게시물</a></li>
                                        <li><a href="/deletedboard.board" class="cpage">임시보관 게시물</a></li>
                                        <li><a href="/deletedreply.reply">임시보관 댓글</a></li>
                                    </ul>
                                </div>

                                <div style="padding: 10px;"></div>
                                <div class="ntc list_table">
                                    <div class="table_row table_header">
                                        <div class="table_col">
                                            <span>게시물 번호</span>
                                        </div>
                                        <div class="table_col">
                                            <span>게시물 제목</span>
                                        </div>
                                        <div class="table_col">
                                            <span>작성자</span>
                                        </div>
                                        <div class="table_col">
                                            <span>보관날짜</span>
                                        </div>
                                        <div class="table_col">
                                            <span>되돌리기</span>
                                        </div>
                                    </div>
                                    
                                    <c:forEach var="deletedboard" items="${deletedboard}">
                                        <div class="table_row">
                                            <div class="table_col"><span>${deletedboard.board_seq }</span></div>
                                            <div class="table_col"><span>${deletedboard.title }</span></div>
                                            <div class="table_col"><span>${deletedboard.nickname }</span></div>
                                            <div class="table_col"><span><fmt:formatDate value="${deletedboard.delete_date}" pattern="yyyy.MM.dd" /></span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                    </c:forEach>
                                        
                                </div>
                                <div class="pagination">
									<a href="javascript:;" class="btn_prev btn_disabled"></a>
									<c:forEach var="i" begin="1"
										end="${record_total_count / record_count_per_page + 1}">
										<a href="/deletedboard?cpage=${i}"
											class="<c:if test="${i == cpage}">active_page</c:if>">${i}</a>
									</c:forEach>
									<a href="javascript:;" class="btn_next"></a>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 푸터 영역 -->
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
</body>

</html>