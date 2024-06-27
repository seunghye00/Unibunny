<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
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
<body class="notice-edit">
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
								<p class="title">공지사항 수정하기</p>
							</div>
							<form id="ntcForm" action="/modify.notice" method="post" enctype="multipart/form-data">
    <input type="hidden" name="notice_seq" value="${notice.notice_seq}">
    <input type="hidden" id="deletedFiles" name="deletedFiles" value="">
    <div class="nct list_table">
        <div class="table_row table_header">
            <span>제목</span>
            <div style="padding: 5px;"></div>
            <input type="text" name="notice_title" value="${notice.title}">
        </div>
        <div style="padding: 10px;"></div>
        <div id="addfile">
            <span>파일첨부</span>
            <div style="padding: 5px;"></div>
            <button type="button" id="file">+</button>
        </div>
        <div id="filebox">
            <ul>
                <c:forEach var="file" items="${files}">
                    <li class="file-list-wrapper" id="file_${file.ntc_files_seq}">
                        <span>${file.oriname}</span>
                        <button type="button" class="write_btn" onclick="deleteFile(${file.ntc_files_seq})">삭제</button>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="newFileInputs">
            <!-- 새로운 파일 입력 추가를 위한 div -->
        </div>
        <div style="padding: 10px;"></div>
        <div class="write">
            <textarea id="summernote" name="notice_content">${notice.content}</textarea>
        </div>
    </div>
    <div class="btns">
        <button class="write_btn" type="button" onclick="submitForm()">수정하기</button>
        <button class="list_btn" type="button" onclick="location.href='/admin_list.notice'">돌아가기</button>
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
    var deletedFiles = [];

    $('#summernote').summernote({
        height: 500,
        minHeight: null,
        maxHeight: null,
        focus: true
    });

    $('#file').on('click', function() {
        var fileInputWrapper = $('<div class="file-input-wrapper">'
                + '<input type="file" name="files">'
                + '<button type="button" class="removeFileInput">-</button>'
                + '</div>');
        $('#newFileInputs').append(fileInputWrapper);
    });

    $('#newFileInputs').on('click', '.removeFileInput', function() {
        $(this).closest('.file-input-wrapper').remove();
    });

    window.deleteFile = function(fileSeq) {
        if (confirm("정말 삭제하시겠습니까?")) {
            $.ajax({
                url: '/deleteFile.noticefile',
                method: 'POST',
                data: { file_seq: fileSeq },
                success: function(response) {
                    if(response.trim() === "success") {
                        $('#file_' + fileSeq).remove();
                        deletedFiles.push(fileSeq);
                    } else {
                        alert("파일 삭제에 실패했습니다.");
                    }
                },
                error: function() {
                    alert("파일 삭제 중 오류가 발생했습니다.");
                }
            });
        }
    };
});

function submitForm() {
    var formData = new FormData($('#ntcForm')[0]);

    $.ajax({
        url: '/modify.notice',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            // 공지사항 수정 후 파일 업로드 처리
            console.log('공지사항 수정 완료');
            uploadFiles();
        },
        error: function() {
            alert('공지사항 수정 중 오류가 발생했습니다.');
        }
    });
}

function uploadFiles() {
    var files = $('input[name="files"]');
    var noticeSeq = $('input[name="notice_seq"]').val();

    if (files.length === 0) {
        location.href = '/admin_list.notice';
        return;
    }

    var formData = new FormData();
    formData.append("notice_seq", noticeSeq);

    files.each(function(index, fileInput) {
        if (fileInput.files.length > 0) {
            formData.append("files", fileInput.files[0]);
        }
    });

    $.ajax({
        url: '/upload.noticefile',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            console.log('파일 업로드 완료');
            location.href = '/admin_list.notice';
        },
        error: function() {
            alert('파일 업로드 중 오류가 발생했습니다.');
        }
    });
}
</script>
	</div>
</body>
</html>


