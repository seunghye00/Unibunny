<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세</title>
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/sub.css">
    <link rel="stylesheet" href="../../css/layout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <script defer src="../../js/common.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="../../common/header.jsp" />
        <div class="body_area">
            <div class="body for_pc">
                <div class="wrap">
                    <div class="con_wrap">
                        <div class="con">
                            <div class="title_box">
                                <p class="title">${notice.title}</p>
                            </div>
                            <div class="cont_box">
                                <div class="cont_body notice_cont">
                                    <div class="board_box">
                                        <div class="board_head">
                                            <div class="title">
                                                <div id="board_title">${notice.title}</div>
                                                <div class="btn_box">
                                                    <button class="option_btn file_option" type="button">
                                                        <i class="fa-regular fa-folder option_icon"></i>
                                                        <i class="fa-solid fa-folder option_icon"></i>
                                                    </button>
                                                    <div class="file_list"></div>
                                                </div>
                                            </div>
                                            <div class="board_info">
                                                <div class="writer">작성자 : ${notice.nickname}</div>
                                                <div class="write_date">
                                                    작성날짜 :
                                                    <fmt:formatDate value="${notice.write_date}" pattern="yyyy.MM.dd HH:mm" />
                                                </div>
                                                <div class="views">조회수 : ${notice.view_count}</div>
                                                <div class="edit_box">
                                                    <div class="btn_box">
                                                        <button class="write_btn back_btn" id="back_btn" type="button"
                                                        onclick="location.href='/../manager/notice.jsp'">목록</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="board_body">
                                            <div class="board_cont" id="board_cont">${notice.content}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../../common/footer.jsp" />
    </div>
</body>
</html>
