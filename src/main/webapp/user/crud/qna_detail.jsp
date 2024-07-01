<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/manager.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script defer src="../../../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="../../common/header.jsp" />
    <div class="body_area">
      <div class="body for_pc">
        <div class="wrap">
          <div class="con_wrap qa_detail_con">
            <div class="con">
              <div class="qa_title_box">
                <p class="title">문의사항</p>
              </div>
              <div class="list_table">
                <div class="table_row table_header">
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
                    <span>작성일자</span>
                  </div>
                </div>
                <div class="table_row">
                  <div class="table_col">
                    <span>1</span>
                  </div>
                  <div class="table_col">
                    <span>작성자 : ${qna.userid}</span>
                  </div>
                  <div class="table_col">
                    <span>${qna.question_title }</span>
                  </div>
                  <div class="table_col">
                    <span><fmt:formatDate value="${qna.write_date}"
                                            pattern="yyyy.MM.dd HH:mm" /></span>
                  </div>
                </div>
                <div class="table_row">
                  <div class="table_col">
                    <span>첨부파일 : </span>
                  </div>
                  <div class="table_col">
                     <c:if test="${file != null}">
                                            <a href="/download.qnafile?fileName=${file.sysname}">${file.oriname}</a>
                                        </c:if>
                  </div>
                </div>
              </div>
              <div>
                <!-- <p> 태그를 제거한 내용을 출력 -->
                <textarea id="summernote"><c:out value="${cleanedContent}" escapeXml="false"/></textarea>
              </div>
              <div class="answer_section">
                <h3>답변</h3>
                <textarea readonly>${qna.answer_content}</textarea>
              </div>
              <div class="btns">
                <a href="/myqna.qna" class="list_btn">
                  <button>돌아가기</button>
              </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <jsp:include page="../../common/footer.jsp" />
    </div>
  </div>
  <script>
    $(document).ready(function () {
      // Summernote 초기화
      $('#summernote').summernote({
        height: 250,                 // 에디터 높이 설정
        minHeight: null,             // 최소 높이 설정
        maxHeight: null,             // 최대 높이 설정
        focus: true,                 // 초기 포커스 설정
        toolbar: false               // 툴바 숨김
      });

      $('#summernote').summernote('disable');  // 읽기 전용 모드 설정
    });
  </script>
</body>
</html>
