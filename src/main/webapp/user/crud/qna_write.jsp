<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 작성하기</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script defer src="../../../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<!-- 헤더 영역 -->
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
							<li><a href="javascript:;" class="gnb_comu"><span>커뮤니티</span></a></li>
							<li><a href="javascript:;" class="gnb_rank"><span>랭킹</span></a></li>
							<li><a href="javascript:;" class="gnb_our"><span>OUR
										PAGE</span></a></li>
							<li><a href="javascript:;" class="gnb_cs"><span>고객센터</span></a></li>
						</ul>
						<ul class="header_my">
							<li class="my_01"><a href="javascript:;" class="btn_mypage"><img
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
										src="../../image/icon/login_b.png" alt="로그인 로고"></a></li>
								<li><div onclick="history.back();" class="mob_close"></div></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 본문 영역 -->
		<div class="body_area">
			<div class="body for_pc">
				<div class="wrap">
					<div class="con_wrap">
						<div class="con qa_write_con">
							<div class="title_box">
								<p class="title">Q&A 작성하기</p>
							</div>
							<form id="qnaForm" action="/write.qna" method="post"
								enctype="multipart/form-data">
								<div class="list_table">
									<div class="table_row table_header">
										<span>제목</span>
										<div style="padding: 5px;"></div>
										<input type="text" name="question_title">
									</div>
									<div style="padding: 10px;"></div>
									<input type="hidden" name="userId" value="user1234">
									<div id="addfile">
										<span>파일첨부</span>
										<div style="padding: 5px;"></div>
										<button type="button" id="file" name="files[]">+</button>
									</div>
									<div id="filebox"></div>
									<div style="padding: 10px;"></div>
									<div class="write">
										<textarea id="summernote" name="question_content"></textarea>
									</div>
								</div>
								<div class="btns">
									<button class="write_btn" type="submit">작성하기</button>
									<button class="list_btn" type="button"
										onclick="location.href='/list.faq'">돌아가기</button>
								</div>
							</form>
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
		$(document)
				.ready(
						function() {
							$('#summernote').summernote({
								height : 500, // 에디터 높이 설정
								minHeight : null, // 최소 높이 설정
								maxHeight : null, // 최대 높이 설정
								focus : true
							// 초기 포커스 설정
							});

							$('#file')
									.on(
											'click',
											function() {
												var fileInputWrapper = $('<div class="file-input-wrapper">'
														+ '<input type="file" name="files[]" multiple>'
														+ '<button type="button" class="removeFileInput">-</button>'
														+ '</div>');
												$('#filebox').append(
														fileInputWrapper);
											});

							$('#filebox').on(
									'click',
									'.removeFileInput',
									function() {
										$(this).closest('.file-input-wrapper')
												.remove();
									});

							$('#qnaForm').on(
									'submit',
									function(event) {
										var title = $(
												'input[name="question_title"]')
												.val().trim();
										if (!title) {
											alert('제목을 입력해주세요.');
											event.preventDefault();
										}
									});
						});
</script>
</body>
</html>
