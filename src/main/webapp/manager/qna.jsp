<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<link rel="stylesheet" href="../../css/manager_layout.css">
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/manager.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<!-- 전체 틀 영역 -->
	<div class="wrapper">
		<!-- 헤더 영역 -->
		<div class="snb_area">
			<ul class="snb_cont">
				<li class="snb_cate"><a href="javascript:;" title="메인으로 가기">
						<h1 class="logo">
							<img src="../image/logo.png" alt="">
						</h1>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>대시보드</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>배너
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>게임
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>회원
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>게시판
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>공지사항
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="javascript:;"> <span>고객센터</span>
				</a></li>
			</ul>
		</div>
		<div class="main_area">
			<div class="header_area">
				<div class="header">
					<div class="wrap">
						<div class="header_con">
							<div class="titlebox">
								<span>고객센터</span>
							</div>
							<div class="header_my">
								<ul>
									<li><a href="javascript:;"> <span class="img_box">
												<img src="../image/icon/mypageW.png" alt="">
										</span>
									</a></li>
									<li><a href="javascript:;"> Logout </a></li>
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
							<div class="con qa_con">
								<div class="select_subject">
									<ul>
										<li><a href="/list.faq?userType=manager">FAQ</a></li>
										<li><a href="/list.qna">Q&A</a></li>
									</ul>
								</div>
								<div style="padding: 30px;"></div>
								<div class="qa list_table">
									<div class="qa table_row table_header">
										<div class="table_col">
											<span>NO</span>
										</div>
										<div class="table_col">
											<span>ID</span>
										</div>
										<div class="table_col">
											<span>제목</span>
										</div>
										<div class="table_col">
											<span>작성일시</span>
										</div>
										<div class="table_col">
											<span>답변여부</span>
										</div>
									</div>
									<c:forEach var="qna" items="${qnaList}">
										<div class="table_row">
											<div class="table_col">
												<span>${qna.question_seq}</span>
											</div>
											<div class="table_col">
												<span>${qna.id}</span>
											</div>
											<div class="table_col">
												<a href="/detail.qna?question_seq=${qna.question_seq}"><span>${qna.question_title}</span></a>
											</div>
											<div class="table_col">
												<span><fmt:formatDate value="${qna.write_date}"
														pattern="yy.MM.dd" /></span>
											</div>
											<div class="table_col">
												<span>${qna.answer_yn}</span>
											</div>
										</div>
									</c:forEach>
								</div>

								<div class="search_box">
									<div class="search_bar">
										<div class="search_input">
											<input type="text" class="input_tag" id="search_input">
										</div>
										<div class="search_img">
											<a href="javascript:;"><img
												src="../image/icon/ico_search.png" alt="검색 로고"></a>
										</div>
									</div>
								</div>
								<div class="pagination">
									<a href="javascript:;" class="btn_prev btn_disabled"></a> <a
										href="javascript:;" class="active_page">1</a> <a
										href="javascript:;">2</a> <a href="javascript:;">3</a> <a
										href="javascript:;">4</a> <a href="javascript:;">5</a> <a
										href="javascript:;" class="btn_next"></a>
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
							<li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호
							</li>
							<li>Tel : 010-5482-9107</li>
						</ul>
					</div>
					<div class="footer_service">
						<strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
						<ul class="copy_desc">
							<li class="footer_copy">Copyright Team HoduGwaja. All Right
								Reserved</li>
						</ul>
					</div>
				</div>
			</div>
</body>
</html>
