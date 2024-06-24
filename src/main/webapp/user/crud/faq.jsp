<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/sub.css">
<link rel="stylesheet" href="../../css/layout.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script defer src="../../../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="header_area">
			<div class="header">
				<div class="wrap mob_hidden">
					<h1 class="logo">
						<a href="javascript:;" title="메인으로 가기"> <img
							src="../../image/logo.png" alt="">
						</a>
					</h1>
					<div class="header_con">
						<ul class="header_gnb">
							<li><a href="javascript:;" class="gnb_comu"><span>커뮤니티</span></a>
							</li>
							<li><a href="javascript:;" class="gnb_rank"><span>랭킹</span></a>
							</li>
							<li><a href="javascript:;" class="gnb_our"><span>OUR
										PAGE</span></a></li>
							<li><a href="javascript:;" class="gnb_cs"><span>고객센터</span></a>
							</li>
						</ul>
						<ul class="header_my">
							<li class="my_01 "><a href="javascript:;" class="btn_mypage"><img
									src="../../image/icon/mypageW.png" alt="마이페이지 로고"></a></li>
							<li class="my_02"><a href="javascript:;" class="btn_login"><img
									src="../../image/icon/login.png" alt="로그인 로고"></a></li>
						</ul>
					</div>
				</div>
				<div class="mob_wrap">
					<h1 class="mob_logo">
						<a href="javascript:;" title="메인으로 가기"> <img
							src="../../../image/logo.png" alt="">
						</a>
					</h1>
					<div class="mob_ham"></div>
					<div onclick="history.back();" class="mob_page_cover"></div>
					<div class="mob_menu">
						<ul class="mob_list">
							<li><strong><a href="javascript:;">커뮤니티</a></strong></li>
							<li><strong><a href="javascript:;">랭킹</a></strong></li>
							<li><strong><a href="javascript:;">OUR PAGE</a></strong></li>
							<li><strong><a href="javascript:;">고객센터</a></strong></li>
						</ul>
						<div class="mob_my">
							<ul>
								<li><a href="javascript:;" class="mob_mypage"><img
										src="../../image/icon/mypage.png" alt="마이페이지 로고"></a></li>
								<li><a href="javascript:;" class="mob_login"><img
										src="../../image/icon/login.png" alt="로그인 로고"></a></li>
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
						<div class="con_faq_con">
							<div class="title_box">
								<p class="title">고객센터</p>
							</div>
							<div class="agent_list">
								<li><a href="/list.faq">FAQ</a></li>
								<li><a href="../crud/qna_write.jsp">Q&A</a></li>
							</div>
							<div style="padding: 20px;"></div>
							<div class="list_table faq_table">
								<ul id="faqList">
									<c:forEach var="faq" items="${faqList}">
										<li class="faq"><span class="faq-title">${faq.title}</span>
											<span class="faq-text">${faq.content}</span>
											<button class="faq-toggle">
												<i class="fas fa-chevron-down"></i> <i class="fas fa-times"></i>
											</button></li>
									</c:forEach>
								</ul>
							</div>
							<div></div>
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
							<li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호
							</li>
							<li>Tel : 010-5482-9107</li>
						</ul>
					</div>
					<div class="footer_service">
						<strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
						<ul class="copy_desc">
							<li class="footer_copy">Copyright Team HoduSnack. All Right
								Reserved</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('.faq-toggle').on('click', function() {
				var $faq = $(this).closest('.faq');
				$faq.toggleClass('active');
			});
		});
	</script>

</body>
</html>
