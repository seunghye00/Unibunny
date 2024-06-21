<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<link rel="stylesheet" href="../../css/manager_layout.css">
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/manager.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="../../js/common.js"></script>
</head>
<body>
  <!-- 전체 틀 영역 -->
  <div class="wrapper">
    <!-- 헤더 영역 -->
    <div class="snb_area">
      <ul class="snb_cont">
        <li class="snb_cate">
          <a href="javascript:;" title="메인으로 가기">
            <h1 class="logo">
              <img src="../image/logo.png" alt="">
            </h1>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>대시보드</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>배너 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>게임 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>회원 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>게시물 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>댓글 관리</span>
          </a>
        </li>
        <li class="snb_cate">
          <a href="javascript:;">
            <span>고객센터</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="main_area">
      <div class="header_area">
        <div class="header">
          <div class="wrap">
            <div class="header_con">
              <div class="titlebox">
                <span>고객센터</span>
              </div>
              <div class="header_my">
                <ul>
                  <li>
                    <a href="Javascript:;">
                      <span class="img_box">
                        <img src="../image/icon/mypageW.png" alt="">
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="Javascript:;">
                      Logout
                    </a>
                  </li>
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
              <div class="con">
                <div class="select_subject">
                  <ul>
                    <li>
                      <a href="../manager/faq_component.html">FAQ</a>
                    </li>
                    <li>
                      <a href="../manager/qa.html">Q&A</a>
                    </li>
                  </ul>
                </div>
                <div style="padding: 20px;"></div>
                <div class="faq list_table faq_table">
                  <ul id="faqList">
                    <li class="faq">
                      <span class="faq-title">자주 묻는 질문 1</span>
                      <span class="faq-text">답변 : 집 가고 싶다!
                        <button class="faq-delete">삭제</button>
                      </span>
                      <button class="faq-toggle">
                        <i class="fas fa-chevron-down"></i>
                        <i class="fas fa-times"></i>
                      </button>
                    </li>
                    <li class="faq">
                      <span class="faq-title">자주 묻는 질문 2</span>
                      <span class="faq-text">답변 : 집 가고 싶다!
                        <button class="faq-delete">삭제</button>
                      </span>
                      <button class="faq-toggle">
                        <i class="fas fa-chevron-down"></i>
                        <i class="fas fa-times"></i>
                      </button>
                    </li>
                  </ul>
                </div>
				<form action="/write.faq" method="post">
                <div class="faq">
                  <div class="faq btns">
                    <button type="button" class="faq_btn">추가</button>
                    <button type="submit" class="save_btn" style="display:none;">등록</button>
                    <button type="button" class="cancel_btn" style="display:none;">취소</button>
                  </div>
                </div>
               </form>
                <div style="padding: 20px;"></div>
                <div class="pagination">
                  <a href="javascript:;" class="btn_prev btn_disabled"></a>
                  <a href="javascript:;" class="active_page">1</a>
                  <a href="javascript:;">2</a>
                  <a href="javascript:;">3</a>
                  <a href="javascript:;">4</a>
                  <a href="javascript:;">5</a>
                  <a href="javascript:;" class="btn_next"></a>
                </div>
              </div>
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
              <li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호</li>
              <li>Tel : 010-5482-9107</li>
            </ul>
          </div>
          <div class="footer_service">
            <strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
            <ul class="copy_desc">
              <li class="footer_copy">Copyright Team HoduGwaja. All Right Reserved</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
<script>
$(document).ready(function() {
    // FAQ 항목 토글 버튼 클릭 이벤트 핸들러 (FAQ 항목 동적 생성 시)
    $(document).on('click', '.faq-toggle', function() {
        var $faq = $(this).closest('.faq');
        $faq.toggleClass('active');
    });

    // 새로운 FAQ 항목 추가!
    function addFaqItem() {
        var newFaq = `<li class="faq">
            <span class="faq-title editable" contenteditable="true">제목</span>
            <span class="faq-text editable" contenteditable="true">답변 : 
              <button class="faq-delete" style="display:none;">삭제</button>
            </span>
            <button class="faq-toggle">
              <i class="fas fa-chevron-down"></i>
              <i class="fas fa-times"></i>
            </button>
          </li>`;
        $('#faqList').append(newFaq);
        $('.faq_btn').hide();
        $('.save_btn, .cancel_btn').show();
    }

    // 추가 버튼 클릭 이벤트 핸들러
    $('.faq_btn').on('click', function() {
        addFaqItem();
    });

    // 등록 버튼 클릭 이벤트 핸들러
    $('.save_btn').on('click', function() {
        alert('새로운 FAQ 항목이 등록되었습니다.');
        $('#faqList li:last-child .faq-delete').show();
        $('.save_btn, .cancel_btn').hide();
        $('.faq_btn').show();
    });

    // 취소 버튼 클릭 이벤트 핸들러
    $('.cancel_btn').on('click', function() {
        $('#faqList li:last-child').remove();
        $('.save_btn, .cancel_btn').hide();
        $('.faq_btn').show();
    });

    // FAQ 항목 더블 클릭 이벤트 핸들러 (수정 가능하게 만들기)
    $(document).on('dblclick', '.editable', function() {
        $(this).attr('contenteditable', 'true').focus();
    });
    
    // 포커스를 잃었을 때 contenteditable 비활성화
    // 새로운 faq 등록후 답변 부분이 삭제버튼의 영역을 넘어버려서 추가한 코드!
    $(document).on('blur', '.editable', function() {
        $(this).attr('contenteditable', 'false');
    });

});

</script>




</body>
</html>
