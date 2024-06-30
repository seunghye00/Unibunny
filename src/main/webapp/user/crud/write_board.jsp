<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../../css/common.css">
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
          <div class="con_wrap">
            <div class="con write_con board_write">
              <div class="title_box">
                <p class="title">게시글 작성하기</p>
                <select name="choi_menu" id="choi_menu" class="choi_menu">
                	<option value="">-------- 카테고리 --------</option>
					<option value="1">게임1</option>
					<option value="2">게임2</option>
					<option value="3">게임3</option>
					<option value="4">게임4</option>
					<option value="5">게임5</option>
				</select>
              </div>
              <form action="/upload.boardfile"  method="post" enctype="multipart/form-data" id="board_write_form">
              <div class="list_table">
                <div class="table_row table_header">
                  <span>제목</span>
                  <div style="padding: 5px;"></div>
                  <input type="text" id="insert_title">
                  <input type="hidden" id="board_seq" name="board_seq">
                </div>
                <div style="padding: 10px;"></div>
                <div id="addfile">
                  <span>파일첨부</span>
                  <div style="padding: 5px;"></div>
                  <button type="button" id="file">+</button>
                </div>
                <div id="filebox"></div>
                <div style="padding: 10px;"></div>
                <div class="write">
                  <textarea id="summernote"></textarea>
                </div>
              </div>
              <div class="btns">
                <button class="write_btn" type="button" id="write_board">작성하기</button>
                <button class="list_btn" onclick="location.href='/user/crud/list.jsp'" type="button">돌아가기</button>
              </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <jsp:include page="../../common/footer.jsp" />
    </div>
    </div>
    <script>
      $(document).ready(function () {
       	editer_setting();
      });
    </script>
</body>
</html>