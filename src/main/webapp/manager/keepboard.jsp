<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <span>공지사항</span>
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
                                        <li><a href="../manager/notice.jsp">공지사항</a></li>
                                        <li><a href="../manager/community.jsp">전체 게시물</a></li>
                                        <li><a href="../manager/keepboard.jsp">임시보관 게시물</a></li>
                                        <li><a href="../manager/keepreply.jsp">임시보관 댓글</a></li>
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
                                        <div class="table_row">
                                            <div class="table_col"><span>1</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>2</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>3</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>4</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>5</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>6</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
                                            <div class="table_col"><button class="restore_btn">복구</button></div>
                                        </div>
                                        <div class="table_row">
                                            <div class="table_col"><span>7</span></div>
                                            <div class="table_col"><span>임시 게시물</span></div>
                                            <div class="table_col"><span>관리자</span></div>
                                            <div class="table_col"><span>24.06.24</span></div>
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