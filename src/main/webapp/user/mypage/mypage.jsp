<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<link rel="stylesheet" href="../../../css/common.css">
<link rel="stylesheet" href="../../../css/sub.css">
<link rel="stylesheet" href="../../../css/layout.css">
<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.4.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
<script defer src="../../../js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
/* 페이지네이션 스타일 */
.profile_wrapper .bottom_box {
  text-align: right;
  display: flex;
  align-items: center;
  margin-top: 30px;
  position: relative;
  min-height: 45px;
}

.profile_wrapper .navi_box {
  width: 100%;
  font-size: 18px;
  height: 45px;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.profile_wrapper .navi_box a {
  padding: 0 20px;
}

.profile_wrapper .navi_box .arr_navi {
  padding: 5px 10px;
  border-radius: 5px;
  position: relative;
}

.profile_wrapper .navi_box .arr_navi::after {
  background-color: var(--color-white);
  content: "";
  display: block;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 5px;
}

.profile_wrapper .navi_box .cpage {
  color: var(--color-yellow);
}

.profile_wrapper .navi_box .page_navi .navi_icon {
  width: 20px;
  height: 20px;
  top: 4px;
  z-index: 1;
}

.profile_wrapper .navi_box .page_navi .start_navi {
  rotate: 180deg;
}

.profile_wrapper .navi_box .start_arr.disabled,
.profile_wrapper .navi_box .end_arr.disabled {
  opacity: 0.6;
  pointer-events: none;
}

.profile_wrapper .navi_box a.active {
  color: var(--color-yellow);
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
            <div class="profile_container">
              <div class="profile_aside">
                <div class="tab_container">
                  <ul role="menubar">
                    <li class="tab_item" role="none">
                      <button id="btnMyProfile"><i class="fi fi-rr-user"></i> 내 프로필</button>
                    </li>
                  </ul>
                  <div class="tab_wrapper">
                    <strong class="tab_title">MY</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button id="btnMyAccount">계정관리</button></li>
                      <li class="tab_item" role="none"><button id="btnMyRecord">게임 기록 확인</button></li>
                    </ul>
                  </div>
                  <div class="tab_wrapper">
                    <strong class="tab_title">커뮤니티</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button id="btnMyPosts">작성한 글</button></li>
                      <li class="tab_item" role="none"><button id="btnComments">작성한 댓글</button></li>
                      <li class="tab_item" role="none"><button id="btnBookmarks">북마크</button></li>
                    </ul>
                  </div>
                  <div class="tab_wrapper">
                    <strong class="tab_title">고객센터</strong>
                    <ul role="menubar">
                      <li class="tab_item" role="none"><button id="btnmyQNA">1:1문의</button></li>
                    </ul>
                  </div>
                  <ul role="menubar">
                    <li class="tab_item" role="none">
                      <button type="button" id="logout">로그아웃</button>
                    </li>
                  </ul>
                </div>
              </div>

              <!-- 프로필 상세내용(모바일 마이페이지 메인화면) -->
              <div class="profile_wrapper">
                <div class="profile_contents_wrap default_contents">
                  <div class="profile_title">내 프로필</div>
                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>

                  <div class="profile_contents">
                    <form id="profileForm" action="/uploadProfile.member" method="post" enctype="multipart/form-data">
                      <div class="my_profile">
                        <div class="profile_left">
                          <div class="profile_image_container">
                            <img id="profileImage" src="${my_info.profile_img}" alt="Profile Image">
                          </div>
                          <span>${my_info.nickname}</span>
                        </div>
                        <div class="button_container">
                          <button type="button" class="profile_edit_btn">프로필 편집</button>
                          <input type="file" name="profileImage" class="real_upload" accept="image/*" style="display: none;">
                          <button type="button" class="apply_changes_btn" style="display: none;">적용</button>
                          <button type="button" class="cancel_changes_btn" style="display: none;">취소</button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="profile_contents">
                    <div class="my_history">
                      <ul>
                        <li>가입날짜 : ${formattedJoinDate}</li>
                        <li>게시물 수 : ${board_count}</li>
                        <li>댓글 단 수 : ${reply_count}</li>
                      </ul>
                    </div>
                  </div>
                </div>

                <!-- 계정 관리 -->
                <form action="/edit.member" method="post">
                  <div class="profile_contents_wrap" id="myAccount">
                    <div class="profile_title">계정관리</div>

                    <select class="tab-dropdown dropdown">
                      <option value="">카테고리 선택.</option>
                      <option value="내 프로필">내 프로필</option>
                      <option value="계정관리">계정관리</option>
                      <option value="게임 기록 확인">게임 기록 확인</option>
                      <option value="작성한 글">작성한 글</option>
                      <option value="작성한 댓글">작성한 댓글</option>
                      <option value="북마크">북마크</option>
                      <option value="1:1문의">1:1문의</option>
                    </select>
                    <div class="profile_contents">
                      <div class="my_account">
                        <label for="id">아이디</label>
                        <input type="text" id="id" readonly value="${my_info.userid}">

                        <label for="password" style="display: none">비밀번호</label>
                        <input type="password" name="pw" id="password" readonly placeholder="변경할 비밀번호를 입력하세요" style="display: none">

                        <label for="nickname">닉네임</label>
                        <input type="text" name="nickname" id="nickname" readonly value="${my_info.nickname}">
                        <label for="phone">전화번호</label>
                        <input type="text" name="phone" id="phone" readonly value="${my_info.phone}">
                        <label for="email">이메일</label>
                        <input type="text" name="email" id="email" readonly value="${my_info.email}">
                        <button class="postcode_btn" style="display: none" type="button">우편번호</button>
                        <label for="address1">주소</label>
                        <input type="text" name="address1" id="address1" readonly value="${my_info.address1}">
                        <label for="address2">상세주소</label>
                        <input type="text" name="address2" id="address2" readonly value="${my_info.address2}">
                        <label for="postcode">우편번호</label>	
                        <input type="text" name="postcode" id="postcode" readonly value="${my_info.postcode}">
                      </div>
                    </div>

                    <div class="profile_contents">
                      <div class="edit_account">
                        <ul>
                          <li><label>계정 정보 수정</label></li>
                        </ul>
                        <button type="button" id="edit_button">수정</button>
                        <div id="edit_actions" style="display: none;">
                          <button type="submit" id="apply_button">적용</button>
                          <button type="button" id="cancel_button">취소</button>
                        </div>
                      </div>
                    </div>

                    <div class="profile_contents">
                      <div class="delete_account">
                        <ul>
                          <li><label>계정 삭제하기</label></li>
                        </ul>
                        <button type="button" id="memberout">회원 탈퇴</button>
                      </div>
                    </div>
                  </div>
                </form>

                <!-- 게임 기록 확인 -->
<div class="profile_contents_wrap" id="myScore">
  <div class="profile_title">게임 기록 확인</div>

  <select class="tab-dropdown dropdown">
    <option value="">카테고리 선택.</option>
    <option value="내 프로필">내 프로필</option>
    <option value="계정관리">계정관리</option>
    <option value="게임 기록 확인">게임 기록 확인</option>
    <option value="작성한 글">작성한 글</option>
    <option value="작성한 댓글">작성한 댓글</option>
    <option value="북마크">북마크</option>
    <option value="1:1문의">1:1문의</option>
  </select>

  <div class="profile_contents">
    <div class="my_score">
      <div class="dropdown-container">
        <select name="gameid" id="gameSelect" required>
          <option value="all">전체 게임</option>
        </select>
      </div>

      <div class="list_table" id="scoreTable">
        <div class="table_row table_header">
          <div class="table_col"><span>번호</span></div>
          <div class="table_col"><span>게임명</span></div>
          <div class="table_col"><span>점수</span></div>
          <div class="table_col"><span>플레이 날짜</span></div>
        </div>
        <!-- 점수 목록이 여기에 동적으로 추가됩니다 -->
      </div>
    </div>
  </div>
</div>

                <!-- 작성한 글 -->
                <div class="profile_contents_wrap" id="myPosts">
                  <div class="profile_title">작성한 글</div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>

                  <div class="profile_contents">
                    <div class="my_post">
                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>

                        <c:forEach var="dto" items="${mylist}">
                          <div class="table_row">
                            <div class="table_col"><span>${dto.board_seq}</span></div>
                            <div class="table_col"><a href="/user/detail.board?board_seq=${dto.board_seq}">${dto.title}</a></div>
                            <div class="table_col"><span><fmt:formatDate value="${dto.write_date}" pattern="yyyy.MM.dd" /></span></div>
                            <div class="table_col"><span>${dto.view_count}</span></div>
                          </div>
                        </c:forEach>
                      </div>
                      <div class="bottom_box">
                        <div class="navi_box" id="pagination"></div>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- 작성한 댓글 -->
                <div class="profile_contents_wrap" id="comments">
                  <div class="profile_title">작성한 댓글</div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>

                  <div class="profile_contents">
                    <div class="my_comment">
                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>
                        <c:forEach var="dto" items="${myreplylist}">
                          <div class="table_row">
                            <div class="table_col"><span>${dto.board_seq}</span></div>
                            <div class="table_col"><a href="/user/detail.board?board_seq=${dto.board_seq}">${dto.title}</a></div>
                            <div class="table_col"><span><fmt:formatDate value="${dto.write_date}" pattern="yyyy.MM.dd" /></span></div>
                            <div class="table_col"><span>${dto.view_count}</span></div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- 북마크 -->
                <div class="profile_contents_wrap" id="bookmarks">
                  <div class="profile_title">북마크</div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>

                  <div class="profile_contents">
                    <div class="my_bookmark">
                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>글 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>조회수</span></div>
                        </div>
                        <c:forEach var="dto" items="${mybookmark}">
                          <div class="table_row">
                            <div class="table_col"><span>${dto.board_seq}</span></div>
                            <div class="table_col"><a href="/user/detail.board?board_seq=${dto.board_seq}">${dto.title}</a></div>
                            <div class="table_col"><span><fmt:formatDate value="${dto.write_date}" pattern="yyyy.MM.dd" /></span></div>
                            <div class="table_col"><span>${dto.view_count}</span></div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- 1:1문의 -->
                <div class="profile_contents_wrap" id="questions">
                  <div class="profile_title">1:1문의</div>

                  <select class="tab-dropdown dropdown">
                    <option value="">카테고리 선택.</option>
                    <option value="내 프로필">내 프로필</option>
                    <option value="계정관리">계정관리</option>
                    <option value="게임 기록 확인">게임 기록 확인</option>
                    <option value="작성한 글">작성한 글</option>
                    <option value="작성한 댓글">작성한 댓글</option>
                    <option value="북마크">북마크</option>
                    <option value="1:1문의">1:1문의</option>
                  </select>

                  <div class="profile_contents">
                    <div class="my_question">
                      <div class="list_table">
                        <div class="table_row table_header">
                          <div class="table_col"><span>번호</span></div>
                          <div class="table_col"><span>문의 제목</span></div>
                          <div class="table_col"><span>작성일</span></div>
                          <div class="table_col"><span>답변 여부</span></div>
                        </div>
                        <c:forEach var="dto" items="${myqna}">
                          <div class="table_row">
                            <div class="table_col"><span>${dto.question_seq}</span></div>
                            <div class="table_col"><a href="/user/detail.qna?question_seq=${dto.question_seq}">${dto.question_title}</a></div>
                            <div class="table_col"><span><fmt:formatDate value="${dto.write_date}" pattern="yyyy.MM.dd" /></span></div>
                            <div class="table_col"><span>${dto.answer_yn}</span></div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                </div>

              </div>
            </div>

            <script>
            
            // 프로필 편집


            $(document).ready(function () {
    var originalImageSrc = $('#profileImage').attr('src');

    // 프로필 편집 버튼 클릭 시 파일 선택 창 열기
    $('.profile_edit_btn').on('click', function (event) {
        event.stopPropagation();
        console.log('프로필 편집 버튼 클릭됨');
        $(this).siblings('.real_upload').click();
    });

    // 파일 입력 변경 시 미리보기 및 재업로드 기능
    $('.real_upload').on('change', function () {
        console.log('파일 선택됨');
        var input = this;
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                console.log('파일 읽기 완료');
                var imageContainer = $(input).closest('.my_profile').find('.profile_image_container');
                // 기존 이미지 삭제
                imageContainer.find('img').remove();
                imageContainer.append('<img id="profileImage" src="' + e.target.result + '" alt="Profile Image">');
                // 버튼 표시
                $('.profile_edit_btn').hide();
                $('.apply_changes_btn').show();
                $('.cancel_changes_btn').show();
                console.log('이미지 미리보기 설정됨');
            };
            reader.readAsDataURL(input.files[0]);
        }
    });

    // 변경사항 적용 버튼 클릭 시
    $('.apply_changes_btn').on('click', function () {
        console.log('적용 버튼 클릭됨');
        // 폼 제출
        $('#profileForm').submit();
    });

    // 취소 버튼 클릭 시
    $('.cancel_changes_btn').on('click', function () {
        console.log('취소 버튼 클릭됨');
        var imageContainer = $('.profile_image_container');
        // 기존 이미지로 되돌리기
        imageContainer.find('img').remove();
        imageContainer.append('<img src="' + originalImageSrc + '" alt="Profile Image">');
        // 버튼 숨기기
        $('.apply_changes_btn').hide();
        $('.cancel_changes_btn').hide();	
        $('.profile_edit_btn').show();
    });
    
    
    $.ajax({
        url: '/getgames.game',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            var gameSelect = $('#gameSelect');
            gameSelect.empty();
            gameSelect.append('<option value="all">전체 게임</option>'); // 전체 게임 옵션 추가
            $.each(data, function (index, game) {
                gameSelect.append('<option value="' + game.game_id + '">' + game.game_name + '</option>');
            });
        },
        error: function (xhr, status, error) {
            console.error('게임 데이터를 가져오는데 실패했습니다:', status, error);
        }
    });
    
    
});

            
            
         // 마이페이지 계정관리

            document.getElementById('edit_button').addEventListener('click', function () {
  // 모든 input 요소를 선택
  const inputs = document.querySelectorAll('.my_account input');

  // 각 input 요소의 readonly 속성을 제거하고 border 스타일을 추가
  inputs.forEach((input) => {
    // id가 'id'인 input 요소는 제외
    if (input.id === 'id' || input.id === 'address1' || input.id === 'postcode') {
      return;
    }

    // 원래 값을 data-original-value 속성에 저장
    if (!input.hasAttribute('data-original-value')) {
      input.setAttribute('data-original-value', input.value);
    }
    input.removeAttribute('readonly');
    input.style.border = '1px solid #ffffff';
  });

  // 비밀번호 관련 라벨과 input을 표시
  const passwordLabel = document.querySelector('label[for="password"]');
  const passwordInput = document.getElementById('password');
  const postcodeButton = document.querySelector('.postcode_btn');

  if (passwordLabel && passwordInput) {
    passwordLabel.style.display = 'block';
    passwordInput.style.display = 'block';
    passwordInput.style.border = '1px solid #ffffff'; // 비밀번호 input에도 border 스타일 추가
  }

  if (postcodeButton) { 
      postcodeButton.style.display = 'block';
  }
  
  // 버튼 표시/숨기기
  document.getElementById('edit_button').style.display = 'none';
  document.getElementById('edit_actions').style.display = 'flex';
});


            document.getElementById('cancel_button').addEventListener('click', function () {
                // 모든 input 요소를 선택
                const inputs = document.querySelectorAll('.my_account input');

                // 각 input 요소의 readonly 속성을 다시 추가하고 border 스타일 제거
                inputs.forEach((input) => {
                  // id가 'id'인 input 요소는 제외
                  if (input.id === 'id') {
                    return;
                  } 

                  // data-original-value 속성에 저장된 원래 값으로 복원
                  const originalValue = input.getAttribute('data-original-value');
                  if (originalValue !== null) {
                    input.value = originalValue;
                  }
                  input.setAttribute('readonly', true);
                  input.style.border = 'none';
                });

                // 비밀번호 관련 라벨과 input을 숨기기
                const passwordLabel = document.querySelector('label[for="password"]');
                const passwordInput = document.getElementById('password');
                const postcodeButton = document.querySelector('.postcode_btn'); 

                if (passwordLabel && passwordInput) {
                  passwordLabel.style.display = 'none';
                  passwordInput.style.display = 'none';
                }

                if (postcodeButton) { 
                    postcodeButton.style.display = 'none';
                }
                
                
                // 버튼 표시/숨기기
                document.getElementById('edit_button').style.display = 'inline';
                document.getElementById('edit_actions').style.display = 'none';
              }); 
         
         
         
            document.getElementById('apply_button').addEventListener('click', function (event) {
            	  // 모든 input 요소를 선택
            	  const inputs = document.querySelectorAll('.my_account input');

            	  // 유효성 검사 플래그
            	  let isValid = true;

            	  // 각 input 요소를 검사
            	  inputs.forEach((input) => {
            	    // id가 'address2'인 input 요소는 제외
            	    if (input.id === 'address2' || input.id === 'id') {
            	      return;
            	    }

            	    // input 값이 null이거나 빈 문자열이면 유효하지 않음
            	    if (input.value === null || input.value.trim() === '') {
            	      isValid = false;
            	      input.style.border = '1px solid red'; // 경고를 위해 빨간 테두리 추가
            	    } else if (input.id === 'postcode' && input.value.length > 10) {
            	      isValid = false;
            	      alert('우편번호는 10자 이하로 입력해 주십시오');
            	      input.style.border = '1px solid red'; // 경고를 위해 빨간 테두리 추가
            	    } else {
            	      input.style.border = '1px solid #ffffff'; // 유효한 경우 흰색 테두리로 변경
            	    }
            	  });

            	  // 유효하지 않은 경우 경고 메시지 표시
            	  if (!isValid) {
            	    alert('올바른 정보를 입력해 주십시오');
            	    event.preventDefault(); // 폼 제출을 막음
            	  }
            	});
            
            
            
            $("#logout").on("click", function() {
              location.href = "/logout.member";
            });

            $("#memberout").on("click", function() {
              if (confirm("정말 탈퇴 하시겠습니까?")) {
                location.href = "/memberout.member";
              }
            });

            document.addEventListener("DOMContentLoaded", function() {
              // select 요소와 tab item 연동
              document.querySelectorAll('.tab-dropdown').forEach(dropdown => {
                dropdown.addEventListener('change', function() {
                  const value = this.value;
                  if (value) {
                    const tabId = {
                      "내 프로필": "btnMyProfile",
                      "계정관리": "btnMyAccount",
                      "게임 기록 확인": "btnMyRecord",
                      "작성한 글": "btnMyPosts",
                      "작성한 댓글": "btnComments",
                      "북마크": "btnBookmarks",
                      "1:1문의": "btnmyQNA"
                    }[value];
                    if (tabId) document.getElementById(tabId).click();
                  }
                });
              });

              document.getElementById("btnMyProfile").addEventListener("click", function() {
                window.location.href = "/mypage.member";
              });
              document.getElementById("btnMyAccount").addEventListener("click", function() {
                window.location.href = "/account.member";
              });
              document.getElementById("btnMyRecord").addEventListener("click", function() {
                  window.location.href = "/mylist.score";
                });
              document.getElementById("btnMyPosts").addEventListener("click", function() {
                window.location.href = "/myboard.board";
              });
              document.getElementById("btnComments").addEventListener("click", function() {
                window.location.href = "/myreply.board";
              });
              document.getElementById("btnBookmarks").addEventListener("click", function() {
                window.location.href = "/mybookmark.board";
              });
              document.getElementById("btnmyQNA").addEventListener("click", function() {
                window.location.href = "/myqna.qna";
              });

              // 서버에서 전달된 activeTab 값을 읽어 해당 탭을 활성화
              const activeTab = "${activeTab}";
              if (activeTab) {
                document.querySelector(".default_contents").style.display = "none";
                document.getElementById(activeTab).style.display = "block";
              } else {
                // 기본적으로 내 프로필 탭을 활성화
                document.querySelector(".default_contents").style.display = "block";
              }
            });
            
            // 다음 우편번호 서비스 사용
            $(".postcode_btn").click(function() {
               new daum.Postcode({
                  oncomplete: function(data) {
                     $("#postcode").val(data.zonecode);
                     $("#address1").val(data.jibunAddress);
                     $("#address2").val("");
                  },
               }).open();
            });
            
            
            </script>
          </div>
        </div>
      </div>
    </div>
    <!-- 푸터 영역 -->
  	<jsp:include page="../../common/footer.jsp" />
    <script>
    
    
    
      // 페이지네이션 스크립트
      let pageNation = $("#pagination");
      let cpage = ${cpage};
      let record_total_count = ${record_total_count};
      let record_count_per_page = ${record_count_per_page};
      let navi_count_per_page = ${navi_count_per_page};
      let pageTotalCount = Math.ceil(record_total_count / record_count_per_page);

      // 네비게이터의 시작 값
      let startNavi = Math.floor((cpage - 1) / navi_count_per_page) * navi_count_per_page + 1;
      // 네비게이터의 끝 값 
      let endNavi = startNavi + navi_count_per_page - 1;

      if (endNavi > pageTotalCount) {
        endNavi = pageTotalCount;
      }

      let needNext = endNavi < pageTotalCount;
      let needPrev = startNavi > 1;

      pageNation.append("<a class='page_navi arr_navi start_arr" + (needPrev ? "" : " disabled") + "' href='/myboard.board?cpage=" + (needPrev ? startNavi - 1 : "#") + "'><img class='navi_icon start_navi' src='../../image/icon/pagination.png' alt='start navi 로고'></a>");

      for (let i = startNavi; i <= endNavi; i++) {
        if (cpage == i) {
          pageNation.append("<a class='page_navi active' href='/myboard.board?cpage=" + i + "'>" + i + "</a> ");
        } else {
          pageNation.append("<a class='page_navi' href='/myboard.board?cpage=" + i + "'>" + i + "</a> ");
        }
      }

      pageNation.append("<a class='page_navi arr_navi end_arr" + (needNext ? "" : " disabled") + "' href='/myboard.board?cpage=" + (needNext ? endNavi + 1 : "#") + "'><img class='navi_icon' src='../../image/icon/pagination.png' alt='end navi 로고'></a>");
    </script>
</div>
</body>
</html>
