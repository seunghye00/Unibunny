<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
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
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="header_area">
			<!-- 생략된 헤더 코드 -->
		</div>
		<div class="body_area">
			<div class="body for_pc">
				<div class="wrap">
					<div class="con_wrap">
						<div class="con write_con">
							<div class="title_box">
								<p class="title">Q&A 작성하기</p>
							</div>
							<form id="qnaForm" action="/write.qna" method="post">
								<div class="list_table">
									<div class="table_row table_header">
										<span>제목</span>
										<div style="padding: 5px;"></div>
										<input type="text" name="question_title">
									</div>
									<div style="padding: 10px;"></div>
									<input type="hidden" name="userId" value="user1234">
									<div style="padding: 10px;"></div>
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
		<div class="footer_area">
			<!-- 생략된 푸터 코드 -->
		</div>
		<script>
			$(document).ready(function() {
				$('#summernote').summernote({
					height: 500,
					minHeight: null,
					maxHeight: null,
					focus: true
				});

				$('#file').on('click', function() {
					var fileInputWrapper = $('<div class="file-input-wrapper">' +
						'<input type="file" name="file">' +
						'<button type="button" class="removeFileInput">-</button>' +
						'</div>');
					$('#filebox').append(fileInputWrapper);
				});

				$('#filebox').on('click', '.removeFileInput', function() {
					$(this).closest('.file-input-wrapper').remove();
				});

				$('#qnaForm').on('submit', function(event) {
					event.preventDefault();
					var form = $(this);
					$.ajax({
						url: form.attr('action'),
						method: 'POST',
						data: form.serialize(),
						success: function(response) {
							alert('Q&A 작성 완료');
							// Q&A 작성이 완료되면 파일 업로드 처리
							var question_seq = response.question_seq; // 서버에서 반환된 question_seq 사용
							var fileInputs = $('#filebox').find('input[type="file"]');
							if (fileInputs.length > 0) {
								var formData = new FormData();
								fileInputs.each(function(index, fileInput) {
									formData.append('file', fileInput.files[0]);
								});
								formData.append('question_seq', question_seq);
								$.ajax({
									url: '/upload.qnafile',
									method: 'POST',
									data: formData,
									processData: false,
									contentType: false,
									success: function(fileResponse) {
										window.location.href = '/list.faq';
									},
									error: function() {
										alert('파일 업로드 중 오류 발생');
									}
								});
							} else {
								window.location.href = '/list.faq';
							}
						},
						error: function() {
							alert('Q&A 작성 중 오류 발생');
						}
					});
				});
			});
		</script>
	</div>
</body>
</html>
