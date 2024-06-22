// 모바일 햄버거 기능
$(document).ready(function () {
  $('.mob_ham').click(function () {
    $('.mob_menu,.page_cover,html').addClass('mob_open');
    window.location.hash = '#open';
  });

  window.onhashchange = function () {
    if (location.hash != '#open') {
      $('.mob_menu,.page_cover,html').removeClass('mob_open');
    }
  };

  $('.mob_menu ul.sub_mobile').hide();
  $('.mob_menu ul.nav li').click(function () {
    $('ul', this).slideToggle('fast');
  });
  // 메인 스와이퍼 백그라운드 이미지
  let main_slide = [];
  $('.banner_cont .swiper_bg').each(function (i) {
    // 현재 요소(this)에서 img의 src 속성 값을 가져와서 main_slide 배열에 추가
    let imgSrc = $(this).find('img').attr('src');
    //console.log(imgSrc);
    main_slide.push(imgSrc);

    // 해당 src 값을 url 형태로 콘솔에 출력
    //console.log('url(' + imgSrc + ')');

    // 현재 요소(this)의 배경을 해당 src 값으로 설정
    $(this).css('background', 'url(' + imgSrc + ')');
  });
});

// 메인 영역 스와이퍼 기능
let mainSwiper;
let game_swiper;
let ourpage_swiper;

function initializeMainSwiper() {
  if (mainSwiper) {
    mainSwiper.destroy(true, true); // 기존 Swiper 인스턴스를 파괴
  }

  mainSwiper = new Swiper('.mainSwiper', {
    spaceBetween: 30,
    centeredSlides: true,
    loop: true, // loop를 true로 설정
    autoplay: {
      delay: 2500,
      disableOnInteraction: false,
    },
    pagination: {
      el: '.swiper-pagination',
      clickable: true,
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  });
}
// Game 콘텐츠 스와이퍼
// Swiper를 초기화하는 함수
function initializeGameSwiper() {
  if (game_swiper) {
    game_swiper.destroy(true, true); // 기존 Swiper 인스턴스를 파괴
  }

  let slidesPerView;

  if ($(window).width() < 750) {
    slidesPerView = 2;
  } else {
    slidesPerView = 5;
  }

  game_swiper = new Swiper('.game_swiper', {
    slidesPerView: slidesPerView,
    spaceBetween: 30,
    navigation: {
      nextEl: '.swiper_game_next',
      prevEl: '.swiper_game_prev',
    },
  });
}
// 초기화 및 리사이즈 이벤트 핸들러 등록
$(document).ready(function () {
  initializeGameSwiper(); // 초기 Swiper 설정

  $(window).resize(function () {
    initializeGameSwiper(); // 창 크기 변경 시 Swiper 재설정
  });
});

// ourpage 스와이퍼
// Swiper를 초기화하는 함수
// ourpage 스와이퍼 초기화
function initializeOurSwiper() {
  if (ourpage_swiper) {
    ourpage_swiper.destroy(true, true); // 기존 Swiper 인스턴스를 파괴
  }

  let slidesPerView;

  if ($(window).width() < 650) {
    slidesPerView = 1;
  } else if ($(window).width() < 750) {
    slidesPerView = 2;
  } else if ($(window).width() < 950) {
    slidesPerView = 3;
  } else if ($(window).width() < 1200) {
    slidesPerView = 4;
  } else {
    slidesPerView = 5;
  }

  ourpage_swiper = new Swiper('.ourpage_swiper', {
    slidesPerView: slidesPerView,
    spaceBetween: 30,
    navigation: {
      nextEl: '.swiper_ourpage_next',
      prevEl: '.swiper_ourpage_prev',
    },
  });
}

// 초기화 및 리사이즈 이벤트 핸들러 등록
$(document).ready(function () {
  initializeOurSwiper(); // ourpage 스와이퍼 초기화
  initializeMainSwiper(); // main 스와이퍼 초기화

  $(window).resize(function () {
    initializeOurSwiper(); // ourpage 스와이퍼 재설정
    initializeMainSwiper(); // main 스와이퍼 재설정
  });
});

// 마이페이지 js
$(document).ready(function () {
  // 모든 탭 버튼 가져오기
  const tabButtons = $('.tab_item button');

  // 각 탭 버튼에 클릭 이벤트 추가
  tabButtons.on('click', function () {
    // 모든 섹션 숨기기
    const allContents = $('.profile_contents_wrap');
    allContents.hide();
    // 클릭한 버튼에 해당하는 섹션 보이기
    const sectionTitle = $(this).text().trim();
    const targetContent = allContents.filter(function () {
      return $(this).find('.profile_title').text().trim() === sectionTitle;
    });

    if (targetContent.length) {
      targetContent.show();
    }
  });

  // 프로필 편집

  $(document).ready(function () {
    var originalImageSrc = $('#profileImage').attr('src');

    // 프로필 편집 버튼 클릭 시 파일 선택 창 열기
    $(document).on('click', '.profile_edit_btn', function () {
      $(this).siblings('.real_upload').click();
    });

    // 파일 입력 변경 시 미리보기 및 재업로드 기능
    $(document).on('change', '.real_upload', function () {
      var input = this;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
          var imageContainer = $(input)
            .closest('.my_profile')
            .find('.profile_image_container');
          // 기존 이미지 삭제
          imageContainer.find('img').remove();
          imageContainer.append(
            '<img src="' + e.target.result + '" alt="Profile Image">'
          );
          // 버튼 표시
          $('.profile_edit_btn').hide();
          $('.apply_changes_btn').show();
          $('.cancel_changes_btn').show();
        };
        reader.readAsDataURL(input.files[0]);
      }
    });

    // 변경사항 적용 버튼 클릭 시
    $(document).on('click', '.apply_changes_btn', function () {
      // 원래 이미지 src 업데이트
      originalImageSrc = $('#profileImage').attr('src');
      // 버튼 숨기기
      $('.apply_changes_btn').hide();
      $('.cancel_changes_btn').hide();
      $('.profile_edit_btn').show();
    });

    // 취소 버튼 클릭 시
    $(document).on('click', '.cancel_changes_btn', function () {
      var imageContainer = $('.profile_image_container');
      // 기존 이미지로 되돌리기
      imageContainer.find('img').remove();
      imageContainer.append(
        '<img src="' + originalImageSrc + '" alt="Profile Image">'
      );
      // 버튼 숨기기
      $('.apply_changes_btn').hide();
      $('.cancel_changes_btn').hide();
      $('.profile_edit_btn').show();
    });
  });

  // 마이페이지 계정관리

  document.getElementById('edit_button').addEventListener('click', function () {
    // 모든 input 요소를 선택
    const inputs = document.querySelectorAll('.my_account input');

    // 각 input 요소의 readonly 속성을 제거하고 border 스타일을 추가
    inputs.forEach((input) => {
      input.removeAttribute('readonly');
      input.style.border = '1px solid #ffffff';
    });

    // 비밀번호 관련 라벨과 input을 표시
    const passwordLabel = document.querySelector('label[for="password"]');
    const passwordInput = document.getElementById('password');

    if (passwordLabel && passwordInput) {
      passwordLabel.style.display = 'block';
      passwordInput.style.display = 'block';
      passwordInput.style.border = '1px solid #ffffff'; // 비밀번호 input에도 border 스타일 추가
    }

    // 버튼 표시/숨기기
    document.getElementById('edit_button').style.display = 'none';
    document.getElementById('edit_actions').style.display = 'flex';
  });

  document
    .getElementById('cancel_button')
    .addEventListener('click', function () {
      // 모든 input 요소를 선택
      const inputs = document.querySelectorAll('.my_account input');

      // 각 input 요소의 readonly 속성을 다시 추가하고 border 스타일 제거
      inputs.forEach((input) => {
        input.setAttribute('readonly', true);
        input.style.border = 'none';
      });

      // 비밀번호 관련 라벨과 input을 숨기기
      const passwordLabel = document.querySelector('label[for="password"]');
      const passwordInput = document.getElementById('password');

      if (passwordLabel && passwordInput) {
        passwordLabel.style.display = 'none';
        passwordInput.style.display = 'none';
      }

      // 버튼 표시/숨기기
      document.getElementById('edit_button').style.display = 'inline';
      document.getElementById('edit_actions').style.display = 'none';
    });

  // 드롭다운 메뉴
  $('.tab-dropdown').on('change', function () {
    // 선택된 값을 변수에 저장
    const sectionTitle = $(this).val();

    // '카테고리 선택'이 선택된 경우 아무 동작도 하지 않음
    if (sectionTitle === '') {
      return;
    }

    // 드롭다운 값을 초기 텍스트로 되돌림
    $(this).val('');

    // 모든 섹션 숨기기
    const allContents = $('.profile_contents_wrap');
    allContents.hide();

    // 선택한 옵션에 해당하는 섹션 보이기
    const targetContent = allContents.filter(function () {
      return $(this).find('.profile_title').text().trim() === sectionTitle;
    });

    if (targetContent.length) {
      targetContent.show();
    }
  });
});


// 최신순 버튼 클릭 시
$('#recent_btn').on('click', function () {
  // 최신순 버튼 색 변경 및 최신 게시글 노출
  $(this).css('background-color', 'var(--color-space-click)');
  $('.recent_list').show();

  // 조회순 및 추천순 버튼 색 변경 & 해당 리스트 숨김
  $('#views_btn,#likes_btn').css('background-color', 'var(--color-space');
  $('.views_list,.likes_list').hide();
});

// 조회순 버튼 클릭 시
$('#views_btn').on('click', function () {
  // 조회순 버튼 색 변경 및 조회순 게시글 노출
  $(this).css('background-color', 'var(--color-space-click)');
  $('.views_list').show();

  // 추천순 및 최신순 버튼 색 변경 & 해당 리스트 숨김
  $('#likes_btn, #recent_btn').css('background-color', 'var(--color-space');
  $('.likes_list, .recent_list').hide();
});

// 추천순 버튼 클릭시
$('#likes_btn').on('click', function () {
  // 추천순 버튼 색 변경 및 추천순 게시글 노출
  $(this).css('background-color', 'var(--color-space-click)');
  $('.likes_list').show();

  // 조회순, 최신순 색 변경 및 인기 게시글 숨김
  $('#views_btn, #recent_btn').css('background-color', 'var(--color-space');
  $('.views_list, .recent_list').hide();
});

// 댓글 최신순 버튼 클릭 시
$('#comm_recent_btn').on('click', function () {
  console.log(this);

  // 최신순 버튼 색 변경
  $(this).css('background-color', 'var(--color-space-click)');

  // 추천순 버튼 색 변경
  $('#comm_likes_btn').css('background-color', 'var(--color-space');
});

// 댓글 추천순 버튼 클릭시
$('#comm_likes_btn').on('click', function () {
  console.log(this);

  // 추천순 버튼 색 변경
  $(this).css('background-color', 'var(--color-space-click)');

  // 최신순 버튼 색 변경
  $('#comm_recent_btn').css('background-color', 'var(--color-space');
});

// 아이콘 클릭시
$('.option_btn').on('click hover', function () {
  if ($(this).children('.fa-regular').css('display') == 'none') {
    $(this).children('.fa-regular').show();
    $(this).children('.fa-solid').hide();
  } else {
    $(this).children('.fa-regular').hide();
    $(this).children('.fa-solid').show();
  }
});

// 수정 버튼 클릭 시
$('#edit_btn').on('click', function () {
  location.href = '/index/crud/edit_board.html';
});

// 완료 버튼 클릭 시
$('#update_board').on('click', function () {
  location.href = '/index/crud/detail.html';
});

// 취소 버튼 클릭 시
$('#cancel_btn').on('click', function () {
  location.href = '/index/crud/detail.html';
});

// 목록 버튼 클릭 시
$('#back_btn').on('click', function () {
  location.href = '/index/crud/list.html';
});

// 작성 버튼 클릭 시
$('#write_btn').on('click', function () {
  location.href = '/index/crud/write.html';
});

$(document).ready(function () {
  // 아이콘 숨기기
  $('.fa-solid').hide();

  // $('#summernote').summernote({
  //     placeholder: '내용을 입력해주세요',
  //     tabsize: 2,
  //     minHeight: 400,
  //     toolbar: [
  //       ['style', ['style']],
  //       ['font', ['bold', 'underline', 'clear']],
  //       ['color', ['color']],
  //       ['para', ['ul', 'ol', 'paragraph']],
  //       ['table', ['table']],
  //       ['insert', ['link', 'picture', 'video']],
  //       ['view', ['fullscreen', 'codeview', 'help']]
  //     ],
  //     lang: 'ko-KR'
  // });

  // 모바일에서 select로 메뉴 선택 시
  $('#sub_menu').change(function () {
    // 선택된 옵션의 value 값 가져오기
    let selectedValue = $(this).val();
    // console.log(selectedValue);

    // Ajax 요청 보내기
    $.ajax({
      url: '#',
      method: 'GET',
      data: { option: selectedValue },
      success: function (resp) {
        // 성공적으로 데이터를 받았을 때 실행할 코드
        console.log(resp);
      },
      error: function (xhr, status, error) {
        // 오류가 발생했을 때 실행할 코드
        console.error(error); // 오류 메시지 콘솔에 출력
      },
    });
  });

  	//page navi 만드는 코드

    // servlet에서 받아올 데이터 (현재 페이지, 페이지 당 노출할 게시글 수, navi 수, 전체 글 수)
    /*let cpage = ${ cpage };   
    let record_count_per_page = ${record_count_per_page};
	let navi_count_per_page = ${navi_count_per_page};		
    let record_total_count = ${record_total_count}; 

    // 필요한 Page navigator의 수
    let pageTotalCount = 0;

    if (record_total_count % record_count_per_page > 0) {
        pageTotalCount = record_total_count / record_count_per_page + 1;
    } else {
        pageTotalCount = record_total_count / record_count_per_page;
    }

    // 현재 페이지의 Page Navigator들 중 시작 번호
    let startNavi = Math.floor((cpage - 1) / navi_count_per_page) * navi_count_per_page + 1; 
    // 현재 페이지의 Page Navigator들 중 끝 번호
    let endNavi = startNavi + navi_count_per_page - 1; 

    if (endNavi > pageTotalCount) {
        endNavi = pageTotalCount;
    }

    let needNext = true;
    let needPrev = true;

    if (startNavi == 1) { needPrev = false; }
    if (endNavi == pageTotalCount) { needNext = false; }
    
    if (needPrev) {
        $(".navi_box").append("<a class='page_navi arr_navi' href='/list.board?cpage=" + (startNavi - 1) + "'><img class="navi_icon start_navi not_navi" src="../../image/icon/navi.png" alt="start navi 로고"></a>");
    } else{
         $(".navi_box").append("<a class='page_navi arr_navi start_arr' href='/list.board?cpage=" + (startNavi - 1) + "'><img class="navi_icon start_navi not_navi" src="../../image/icon/navi.png" alt="start navi 로고"></a>");
    }
    for (let i = startNavi; i <= endNavi; i++) {
        if (cpage == i) {
            $(".navi_box").append("<a class='page_navi cpage' href='/list.board?cpage=" + i + "' style='color:red'>" + i + "</a> ");
        } else {
            $(".navi_box").append("<a class='page_navi' href='/list.board?cpage=" + i + "'>" + i + "</a> ");
        }
    }
    if (needNext) {
        $(".navi_box").append("<a class='page_navi arr_navi' href='/list.board?cpage=" + (endNavi + 1) + "'><img class="navi_icon" src="../../image/icon/navi.png" alt="end navi 로고"></a>");
    }else{
        $(".navi_box").append("<a class='page_navi arr_navi end_arr' href='/list.board?cpage=" + (endNavi + 1) + "'><img class="navi_icon" src="../../image/icon/navi.png" alt="end navi 로고"></a>");
    }*/	
    
});
