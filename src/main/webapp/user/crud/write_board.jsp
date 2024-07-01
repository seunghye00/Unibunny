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
<style>
	select {
  color: black; /* 텍스트 색상 */
  background-color: white; /* 배경 색상 */
}

select option {
  color: black; /* 옵션 텍스트 색상 */
  background-color: white; /* 옵션 배경 색상 */
}

select option:hover {
  background-color: lightgray; /* 마우스를 올렸을 때 배경 색상 */
}
	
</style>
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
                <select name="gameid" id="gameSelect" required></select>
	
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
                </div>
                </form>
                <div style="padding: 10px;"></div>
                <div class="write">
                  <textarea id="summernote"></textarea>
                </div>  
              <div class="btns">
                <button class="write_btn" type="button" id="write_board">작성하기</button>
                <button class="list_btn" onclick="location.href='/user/crud/list.jsp'" type="button">돌아가기</button>
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
    </div>
    <script>
      $(document).ready(function () {
       	editer_setting();
       	
     // AJAX 요청을 통해 게임 데이터를 가져와 select 옵션 추가
        $.ajax({
            url: '/getgames.game',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                var gameSelect = $('#gameSelect');
                gameSelect.empty();
                $.each(data, function (index, game) {
                    gameSelect.append('<option value="' + game.game_id + '">' + game.game_name + '</option>');
                });
            },
            error: function (xhr, status, error) {
                console.error('게임 데이터를 가져오는데 실패했습니다:', status, error);
            }
        });
       	
       	
       	
      });
    </script>
</body>
</html>