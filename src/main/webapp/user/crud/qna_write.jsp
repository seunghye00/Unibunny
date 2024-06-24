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
		<jsp:include page="../../common/header.jsp" />
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
		<jsp:include page="../../common/footer.jsp" />
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
