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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script defer src="../js/manager.js"></script>

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
				<li class="snb_cate"><a href="/manager/member.jsp"> <span>회원
							관리</span>
				</a></li>
				<li class="snb_cate"><a href="/manager/board.jsp"> <span class="cpage">게시판
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
								<span>임시 보관 댓글</span>
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
							<div class="con ntc_con_reply">
								<div class="ntc select_subject">
									<ul>
										<li><a href="/manager/notice.jsp">공지사항</a></li>
										<li><a href="/manager/community.jsp">전체
												게시물</a></li>
										<li><a href="/manager/keepboard.jsp">임시보관 게시물</a></li>
										<li><a href="/manager/keepreply.jsp" class="cpage">임시보관 댓글</a></li>
									</ul>
								</div>

								<div style="padding: 10px;"></div>
								<div class="keep list_table">
									<div class="table_row table_header">
										<div class="table_col">
											<span>게시물 번호</span>
										</div>
										<div class="table_col">
											<span>댓글 번호</span>
										</div>
										<div class="table_col">
											<span>댓글 내용</span>
										</div>
										<div class="table_col">
											<span>작성자</span>
										</div>
										<div class="table_col">
											<span>복구</span>
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
		$(document).ready(function () {
			get_community_list("reply" , 1 ,"Y");
		});
	</script>
</body>
	
</html>