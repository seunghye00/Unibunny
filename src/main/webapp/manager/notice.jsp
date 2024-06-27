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
				<li class="snb_cate"><a href="/admin_list.notice"> <span>공지사항
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
								<span>공지사항</span>
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
							<div class="con ntc_con">
								<div class="ntc select_subject">
									<ul>
										<li><a href="/admin_list.notice" class="cpage">공지사항</a></li>
										<li><a href="../manager/community.jsp">전체 게시물</a></li>
										<li><a href="../manager/keepboard.jsp">임시보관 게시물</a></li>
										<li><a href="../manager/keepreply.jsp">임시보관 댓글</a></li>
									</ul>
								</div>

								<div style="padding: 10px;"></div>
								<div class="ntc list_table">
									<div class="ntc table_row table_header">
										<div class="table_col">
											<span>번호</span>
										</div>
										<div class="table_col">
											<span>제목</span>
										</div>
										<div class="table_col">
											<span>작성자</span>
										</div>
										<div class="table_col">
											<span>작성일</span>
										</div>
										<div class="table_col">
											<span>조회수</span>
										</div>
									</div>
									<c:forEach var="notice" items="${noticelist}">
										<div class="table_row">
											<div class="table_col">
												<span>${notice.notice_seq}</span>
											</div>
											<div class="table_col">
												<a
													href="admin_detail.notice?notice_seq=${notice.notice_seq}">${notice.title}</a>
											</div>

											<div class="table_col">
												<span>${notice.nickname}</span>
											</div>
											<div class="table_col">
												<span><fmt:formatDate value="${notice.write_date}"
														pattern="yyyy-MM-dd" /></span>
											</div>
											<div class="table_col">
												<span>${notice.view_count}</span>
											</div>
										</div>
									</c:forEach>
								</div>
								<div class="ntc">
									<div class="ntc btn">
										<button class="ntc write_btn"
											onclick="location.href='ntc_write.jsp'">작성하기</button>
									</div>
								</div>
								<div class="pagination">
									<a href="javascript:;" class="btn_prev btn_disabled"></a>
									<c:forEach var="i" begin="1"
										end="${record_total_count / record_count_per_page + 1}">
										<a href="/admin_list.notice?cpage=${i}"
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
			<script>
    document.addEventListener('DOMContentLoaded', function() {
        let rows = document.querySelectorAll('.table_row:not(.table_header)');
        let totalRows = rows.length;

        // 행들을 배열로 변환하고 최신순으로 정렬
        let sortedRows = Array.from(rows).sort((a, b) => {
            let dateA = new Date(a.querySelector('.table_col:nth-child(4) span').textContent);
            let dateB = new Date(b.querySelector('.table_col:nth-child(4) span').textContent);
            return dateB - dateA; // 최신순으로 정렬
        });

        // 정렬된 순서대로 번호 재지정 및 화면에 다시 배치
        sortedRows.forEach((row, index) => {
            let numberCell = row.querySelector('.table_col:nth-child(1) span');
            numberCell.textContent = index + 1;
            row.parentNode.appendChild(row); // 다시 배치
        });
    });
</script>
</body>

</html>
