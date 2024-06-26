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

// 커뮤니티 게시판 테이블 조회 스크립트
// 테이블 담을 컨테이너 변수
let listContainer = $('.crud_table');
let listNoticeContainer = $('.notice_table');
// 초기 값 설정
let currentGameId = 'game_id';
// 페이지네이션 변수 설정
let cpage,
  record_total_count,
  record_count_per_page = 10,
  navi_count_per_page = 5;

// 초기 페이지네이션 변수 설정
function initPaginationVariables(cpageParam, recordTotalCount) {
  cpage = cpageParam;
  record_total_count = recordTotalCount;
}

// 최신순 버튼 클릭 시
$('#recent_btn').on('click', function () {
  let apiUrl = '/list.board';
  updateUrlAndFetchData(apiUrl, 1, currentGameId);
  $('#recent_btn, #likes_btn, #views_btn').removeClass('active');
  $(this).addClass('active');
});

// 좋아요 버튼 클릭 시
$('#likes_btn').on('click', function () {
  let apiUrl = '/like.board';
  updateUrlAndFetchData(apiUrl, 1, currentGameId);
  $('#recent_btn, #likes_btn, #views_btn').removeClass('active');
  $(this).addClass('active');
});

// 조회수 버튼 클릭 시
$('#views_btn').on('click', function () {
  let apiUrl = '/view.board';
  updateUrlAndFetchData(apiUrl, 1, currentGameId);
  $('#recent_btn, #likes_btn, #views_btn').removeClass('active');
  $(this).addClass('active');
});

// 게임 선택 및 전체 보기 버튼 클릭 시 이벤트 처리
$('#board_all').click(function () {
  currentGameId = 'game_id';
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});
$('#game1').click(function () {
  currentGameId = 1; // 현재 게임 ID를 1로 설정
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});
$('#game2').click(function () {
  currentGameId = 2; // 현재 게임 ID를 2로 설정
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});
$('#game3').click(function () {
  currentGameId = 3; // 현재 게임 ID를 3로 설정
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});
$('#game4').click(function () {
  currentGameId = 4; // 현재 게임 ID를 4로 설정
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});
$('#game5').click(function () {
  currentGameId = 5; // 현재 게임 ID를 5로 설정
  updateUrlAndFetchData('/list.board', 1, currentGameId); // 최신순 목록 불러오기
});

$('#sub_menu').on('change', function () {
  // 선택된 옵션의 값 가져오기
  let selectedValue = $(this).val();

  // 선택된 옵션 값에 따라 분기 처리
  if (selectedValue === 'board_all') {
    currentGameId = 'game_id';
    updateUrlAndFetchData('/list.board', 1, currentGameId);
  } else if (selectedValue === 'notice') {
    // 공지사항을 선택한 경우, 해당 페이지로 이동
    window.location.href = '/list.notice';
  } else if (selectedValue.startsWith('game')) {
    // 게임을 선택한 경우, 해당 페이지로 이동
    let gameId = selectedValue.replace('game', '');
    currentGameId = gameId;
    updateUrlAndFetchData('/list.board', 1, currentGameId);
  } else {
    // 기타 경우, 기본 페이지로 이동
    window.location.href = '/list.board';
  }
});

// 데이터 가져오고 테이블 렌더링 함수
function fetchAndRenderData(apiUrl, page, gameId) {
  if (gameId == 'default') {
    // 정렬 기준이 dafault면 최신순으로 설정
    gameId = 'gameId';
  }
  $.ajax({
    url: apiUrl,
    method: 'POST',
    dataType: 'json',
    data: {
      cpage: page,
      recordCountPerPage: record_count_per_page,
      gameId: gameId,
    }, // 페이지 번호를 쿼리 파라미터로 전달
  })
    .done(function (resp) {
      console.log(resp); // 받은 데이터 확인

      // 페이지네이션 변수 초기화
      initPaginationVariables(page, resp.record_total_count);

      // 기존의 목록 컨테이너를 비웁니다.
      listContainer.empty();

      // 테이블의 헤더 부분 생성
      let headerRow = $('<div>').addClass('table_row table_header');
      let headerCol1 = $('<div>')
        .addClass('table_col mob_hidden')
        .append($('<span>').text('번호'));
      let headerCol2 = $('<div>')
        .addClass('table_col')
        .append($('<span>').text('제목'));
      let headerCol3 = $('<div>')
        .addClass('table_col mob_hidden')
        .append($('<span>').text('작성자'));
      let headerCol4 = $('<div>')
        .addClass('table_col mob_hidden')
        .append($('<span>').text('작성일'));
      let headerCol5 = null;
      let headerCol6 = null;

      // 버튼에 따라 추가되는 열 처리
      if (apiUrl === '/list.board') {
        headerCol5 = $('<div>')
          .addClass('table_col mob_hidden views_column')
          .append($('<span>').text('조회수'));
      } else if (apiUrl === '/like.board') {
        headerCol5 = $('<div>')
          .addClass('table_col mob_hidden likes_column')
          .append($('<span>').text('추천수'));
      } else if (apiUrl === '/view.board') {
        headerCol5 = $('<div>')
          .addClass('table_col mob_hidden views_column')
          .append($('<span>').text('조회수'));
      }

      headerRow.append(
        headerCol1,
        headerCol2,
        headerCol3,
        headerCol4,
        headerCol5,
        headerCol6
      );
      listContainer.append(headerRow);

      // 데이터 반복 처리
      if (Array.isArray(resp.data)) {
        let startNumber = (page - 1) * record_count_per_page + 1; // 시작 번호 계산

        for (let i = 0; i < resp.data.length; i++) {
          let dto = resp.data[i];
          let row = $('<div>').addClass('table_row');
          let link = $('<a>').attr(
            'href',
            '/user/detail.board?board_seq=' + dto.board_seq
          );
          let col1 = $('<div>')
            .addClass('table_col mob_hidden')
            .append($('<span>').text(startNumber + i));
          let col2 = $('<div>')
            .addClass('table_col')
            .append($('<span>').text(dto.title));
          let col3 = $('<div>')
            .addClass('table_col')
            .append($('<span>').text(dto.nickname));
          let col4 = $('<div>')
            .addClass('table_col')
            .append($('<span>').text(dto.write_date));
          let col5 = null;
          let col6 = null;

          // 버튼에 따라 추가되는 열 처리
          if (apiUrl === '/list.board') {
            col5 = $('<div>')
              .addClass('table_col mob_hidden views_column')
              .append($('<span>').text(dto.view_count));
          } else if (apiUrl === '/like.board') {
            col5 = $('<div>')
              .addClass('table_col mob_hidden likes_column')
              .append($('<span>').text(dto.thumbs_up));
          } else if (apiUrl === '/view.board') {
            col5 = $('<div>')
              .addClass('table_col mob_hidden views_column')
              .append($('<span>').text(dto.view_count));
          }

          link.append(col1, col2, col3, col4, col5, col6);
          row.append(link);
          listContainer.append(row);
        }
      } else {
        console.error(
          '데이터 형식이 올바르지 않습니다. 데이터가 배열이 아닙니다.'
        );
      }

      // 페이지네이션 생성
      renderPagination(apiUrl, page);

      // URL 업데이트
      updateUrl(apiUrl, page);

      // 버튼의 active 클래스 설정
      $('#recent_btn, #likes_btn, #views_btn').removeClass('active'); // 모든 버튼의 active 클래스 제거
      if (apiUrl === '/list.board') {
        $('#recent_btn').addClass('active');
      } else if (apiUrl === '/like.board') {
        $('#likes_btn').addClass('active');
      } else if (apiUrl === '/view.board') {
        $('#views_btn').addClass('active');
      }
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      // AJAX 호출이 실패했을 경우 처리할 내용
      console.error('AJAX 호출 실패: ', textStatus, errorThrown);
    });
}

// 페이지네이션 생성 함수
function renderPagination(apiUrl, currentPage) {
  // 페이지네이션 초기 설정
  let pageTotalCount = Math.ceil(record_total_count / record_count_per_page);
  let startNavi =
    Math.floor((currentPage - 1) / navi_count_per_page) * navi_count_per_page +
    1;
  let endNavi = startNavi + navi_count_per_page - 1;

  if (endNavi > pageTotalCount) {
    endNavi = pageTotalCount;
  }

  let needNext = endNavi < pageTotalCount;
  let needPrev = startNavi > 1;

  // 페이지네이션 HTML 생성
  let pageNation = $('#pagination');
  pageNation.empty();

  // '첫 페이지로' 버튼
  pageNation.append(
    "<a class='page_navi arr_navi start_arr" +
      (needPrev ? '' : ' disabled') +
      "' href='" +
      (needPrev ? '#' : 'javascript:void(0);') +
      "' data-page='" +
      (needPrev ? startNavi - 1 : '') +
      "'><img class='navi_icon start_navi' src='../../image/icon/pagination.png' alt='start navi 로고'></a>"
  );

  // 페이지 번호
  for (let i = startNavi; i <= endNavi; i++) {
    if (currentPage === i) {
      pageNation.append(
        "<a class='page_navi active' href='javascript:void(0);' data-page='" +
          i +
          "'>" +
          i +
          '</a> '
      );
    } else {
      pageNation.append(
        "<a class='page_navi' href='#' data-page='" + i + "'>" + i + '</a> '
      );
    }
  }

  // '마지막 페이지로' 버튼
  pageNation.append(
    "<a class='page_navi arr_navi end_arr" +
      (needNext ? '' : ' disabled') +
      "' href='" +
      (needNext ? '#' : 'javascript:void(0);') +
      "' data-page='" +
      (needNext ? endNavi + 1 : '') +
      "'><img class='navi_icon end_navi' src='../../image/icon/pagination.png' alt='end navi 로고'></a>"
  );
}

// 페이지네이션 클릭 이벤트 처리
$(document).on('click', '.page_navi:not(.disabled)', function (event) {
  event.preventDefault(); // 기본 동작 방지
  let nextPage = $(this).data('page');
  let currentApiUrl = getCurrentApiUrl();
  let gameId = getCurrentGameId();
  updateUrlAndFetchData(currentApiUrl, nextPage, gameId);
});

// 현재 선택된 API 경로 반환 함수
function getCurrentApiUrl() {
  if ($('#recent_btn').hasClass('active')) {
    return '/list.board';
  } else if ($('#likes_btn').hasClass('active')) {
    return '/like.board';
  } else if ($('#views_btn').hasClass('active')) {
    return '/view.board';
  }
  // 기본적으로 최신순으로 설정
  return '/list.board';
}

// 현재 선택된 게임 ID 반환 함수
function getCurrentGameId() {
  return currentGameId;
}

// URL 업데이트 및 데이터 가져오는 함수
function updateUrlAndFetchData(apiUrl, page, gameId) {
  updateUrl(apiUrl, page, gameId);
  fetchAndRenderData(apiUrl, page, gameId);
}
// URL 업데이트 함수
function updateUrl(apiUrl, page) {
  history.pushState(null, null, '?api=' + apiUrl + '&page=' + page);
}

// 초기 페이지 로드 시 실행
function listLoad() {
  // 초기에 URL 파라미터에 따라 데이터 불러오기
  let urlParams = new URLSearchParams(window.location.search);
  let apiUrl = urlParams.has('api') ? urlParams.get('api') : '/list.board';
  let page = urlParams.has('page') ? parseInt(urlParams.get('page')) : 1;
  currentGameId = urlParams.has('gameId') ? urlParams.get('gameId') : 'game_id';

  fetchAndRenderData(apiUrl, page, currentGameId); // 초기에는 최신순 데이터를 가져오도록 설정

  // 버튼의 active 클래스 설정
  $('#recent_btn, #likes_btn, #views_btn').removeClass('active'); // 모든 버튼의 active 클래스 제거
  if (apiUrl === '/list.board') {
    $('#recent_btn').addClass('active');
  } else if (apiUrl === '/like.board') {
    $('#likes_btn').addClass('active');
  } else if (apiUrl === '/view.board') {
    $('#views_btn').addClass('active');
  }
}

// 문서 로드 시 listLoad 함수 실행
// $(document).ready(function() {
//   listLoad();
// });

// 최신순 버튼 클릭 시
/*$('#recent_btn').on('click', function() {
	// 최신순 버튼 색 변경 및 최신 게시글 노출
	$(this).css('background-color', 'var(--color-space-click)');
	$('.recent_list').show();

	// 조회순 및 추천순 버튼 색 변경 & 해당 리스트 숨김
	$('#views_btn,#likes_btn').css('background-color', 'var(--color-space');
	$('.views_list,.likes_list').hide();
});

// 조회순 버튼 클릭 시
$('#views_btn').on('click', function() {
	// 조회순 버튼 색 변경 및 조회순 게시글 노출
	$(this).css('background-color', 'var(--color-space-click)');
	$('.views_list').show();

	// 추천순 및 최신순 버튼 색 변경 & 해당 리스트 숨김
	$('#likes_btn, #recent_btn').css('background-color', 'var(--color-space');
	$('.likes_list, .recent_list').hide();
});

// 추천순 버튼 클릭시
$('#likes_btn').on('click', function() {
	// 추천순 버튼 색 변경 및 추천순 게시글 노출
	$(this).css('background-color', 'var(--color-space-click)');
	$('.likes_list').show();

	// 조회순, 최신순 색 변경 및 인기 게시글 숨김
	$('#views_btn, #recent_btn').css('background-color', 'var(--color-space');
	$('.views_list, .recent_list').hide();
});*/

// 게시글 상세 페이지에서 수정 버튼 클릭 시
$('#edit_btn').on('click', function () {
  location.href = '/tryUpdate.board?board_seq=' + get_board_seq();
});

// 게시글 수정 페이지에서 완료 버튼 클릭 시
$('#update_btn').on('click', function (e) {
  e.preventDefault();

  let trimmedHtml = $('#summernote').html().trim();
  $('#summernote').html(trimmedHtml);
  console.log($('#summernote').text());
  $('#update_board').submit();
  // return false;
});

// 게시글 수정 페이지에서 취소 버튼 클릭 시
$('#cancel_btn').on('click', function () {
  if (confirm('수정하신 내용은 저장되지 않습니다.')) {
    location.href = '/user/detail.board?board_seq=' + board_seq;
  }
});

// 게시글 상세 페이지에서 삭제 버튼 클릭 시
$('#del_btn').on('click', function () {
  if (confirm('정말로 삭제하시겠습니까?')) {
    location.href = '/delete.board?board_seq=' + get_board_seq();
  }
});

// 게시글의 seq 값을 반환하는 메서드
function get_board_seq() {
  return $('#board_seq').text().replace('# ', '');
}

// 게시글 상세 페이지에서 로그인된 ID의 좋아요 및 북마크 기록을 확인하는 메서드
function get_user_record() {
  // 게시글 북마크 기록
  $.ajax({
    url: '/check.bookmark',
    dataType: 'json',
    data: { board_seq: get_board_seq() },
  }).done(function (resp) {
    if (resp) {
      $('.fa-regular.fa-bookmark').hide();
      $('.fa-solid.fa-bookmark').show();
    }
  });

  // 게시글 좋아요 기록
  $.ajax({
    url: '/check.boardLike',
    dataType: 'json',
    data: { board_seq: get_board_seq() },
  }).done(function (resp) {
    if (resp) {
      $('.fa-regular.fa-thumbs-up').hide();
      $('.fa-solid.fa-thumbs-up').show();
    }
  });
}

// 해당 게시글의 북마크 수와 좋아요 수를 받아오는 메서드
function get_options_record() {
  // 북마크 수
  $.ajax({
    url: '/count.bookmark',
    dataType: 'json',
    data: { board_seq: get_board_seq() },
  }).done(function (resp) {
    $('.bookmark').text('스크랩 수 : ' + resp);
  });
  // 좋아요 수
  $.ajax({
    url: '/count.boardLike',
    dataType: 'json',
    data: { board_seq: get_board_seq() },
  }).done(function (resp) {
    $('#board_like').text(resp);
  });
}

// 해당 댓글의 좋아요 수를 받아오기 위한 메서드
function get_reply_likes(reply_seq) {
  $.ajax({
    url: '/count.replyLike',
    dataType: 'json',
    data: { reply_seq: reply_seq },
  }).done(function (resp) {
    // reply_seq와 일치하는 .comm_seq 요소 선택
    let comm_seq = $('.comm_seq').filter(function () {
      return $(this).text() == reply_seq;
    });
    // .comm_seq 요소의 가장 가까운 .comm 요소를 찾고, 그 하위에 있는 <p> 요소의 텍스트를 업데이트
    comm_seq.closest('.comm').find('p').text(resp);
  });
}

// 게시글의 파일 목록을 받아오는 메서드
function get_file_list() {
  $.ajax({
    url: '/list.file',
    dataType: 'json',
    data: { board_seq: get_board_seq() },
  }).done(function (resp) {
    if (resp.length == 0) {
      // 파일이 존재하지 않는 경우 버튼 클릭 불가능
      $('.file_option').attr('disabled', true).css('cursor', 'default');
      return 0;
    }

    let file_list = $('.file_list');
    file_list.empty();

    for (let i of resp) {
      let file = $('<div>', { class: 'files' });
      let file_name = $('<button>', { class: 'down_file' });
      file_name.text(i.oriname);
      file.append(file_name);
      file_list.append(file);

      file_name.on('click', function () {
        console.log(i.oriname);
        console.log(i.sysname);
        $.ajax({
          url: '/download.file',
          dataType: 'json',
          data: {
            oriname: i.oriname,
            sysname: i.sysname,
          },
        }).done(function (resp) {
          console(resp);
        });
      });
    }
  });
}

// 추천 or 북마크 or 파일 중 클릭한 해당 기능을 수행하는 메서드
function click_option(element) {
  if ($(element).children('.fa-regular').css('display') == 'none') {
    // 해당 기능을 취소하는 경우
    if ($(element).hasClass('likes_option')) {
      // 좋아요 취소 기능
      if ($(element).hasClass('board_like')) {
        // 게시글 좋아요 취소
        $.ajax({
          url: '/delete.boardLike',
          data: { board_seq: get_board_seq() },
        });
      } else {
        // 댓글 좋아요 취소
        let comm = $(element);
        let reply_seq = comm.closest('.comm').find('.comm_seq').text();
        $.ajax({
          url: '/delete.replyLike',
          data: { reply_seq: reply_seq },
        }).done(function () {
          console.log('좋아요 취소');
          get_reply_likes(reply_seq);
        });
      }
    } else if ($(element).hasClass('mark_option')) {
      // 북마크 취소 기능
      console.log('북마크 취소');
      $.ajax({
        url: '/unsave.bookmark',
        data: { board_seq: get_board_seq() },
        type: 'POST',
      }).done(function () {
        // 북마크 취소 완료 후 처리
        $(element).removeClass('active');
      });
    } else {
      // 파일 목록 닫기 기능
      $('.file_list').hide();
    }
    $(element).children('.fa-regular').show();
    $(element).children('.fa-solid').hide();
  } else {
    // 해당 기능을 이용하려는 경우
    if ($(element).hasClass('likes_option')) {
      // 좋아요 기능
      if ($(element).hasClass('board_like')) {
        // 게시글 좋아요
        $.ajax({
          url: '/insert.boardLike',
          data: { board_seq: get_board_seq() },
        });
      } else {
        // 댓글 좋아요
        let comm = $(element);
        let reply_seq = comm.closest('.comm').find('.comm_seq').text();
        $.ajax({
          url: '/insert.replyLike',
          data: { reply_seq: reply_seq },
        }).done(function () {
          get_reply_likes(reply_seq);
        });
      }
    } else if ($(element).hasClass('mark_option')) {
      // 북마크 기능
      console.log('북마크 등록');
      $.ajax({
        url: '/save.bookmark',
        data: { board_seq: get_board_seq() },
        type: 'POST',
      }).done(function (resp) {
        // 북마크 저장 전송
        console.log(resp);
        $(element).addClass('active');
      });
    } else {
      // 파일이 존재하는 경우 파일 목록 열기 기능
      if (get_file_list() != 0 && element != null) {
        $('.file_list').show();
      }
    }
    $(element).children('.fa-regular').hide();
    $(element).children('.fa-solid').show();
  }
  get_options_record();
}

// 추천 or 북마크 or 파일 버튼 클릭 시
$('.option_btn').on('click', function () {
  click_option($(this));
});

// 댓글 좋아요 버튼 클릭 시
$('#comm_likes_btn').click(function () {
  get_comm_list('thumbs_up'); // 추천수에 따라 정렬된 목록 불러오기
});

// 댓글 목록 불러오는 메서드
function get_comm_list(order_by) {
  if (order_by == 'default') {
    // 정렬 기준이 dafault면 최신순으로 설정
    order_by = 'write_date';
  }
  $.ajax({
    url: '/list.reply',
    dataType: 'json',
    data: {
      board_seq: get_board_seq(),
      order_by: order_by,
    },
  }).done(function (resp) {
    // console.log("${loginID}");

    let comm_list = $('.comm_list');
    comm_list.empty();
    console.log(resp);
    if (resp.length == 0) {
      // 댓글이 존재하지 않는 경우
      let no_comm = $('<div>', { class: 'no_comm' });
      no_comm.text('댓글이 존재하지 않습니다.');
      comm_list.append(no_comm);
      return;
    }

    for (let i of resp) {
      let comm = $('<div>', { class: 'comm' });

      let comm_info = $('<div>', { class: 'comm_info' });
      let comm_seq = $('<div>', { class: 'comm_seq', style: 'display:none' });
      comm_seq.text(i.reply_seq);
      let comm_writer = $('<div>', { class: 'comm_writer' });
      comm_writer.text('작성자 : ' + i.nickname);
      let comm_date = $('<div>', { class: 'comm_date' });
      comm_date.text('작성일 : ' + i.write_date);
      comm_info.append(comm_seq, comm_writer, comm_date);

      let comm_cont = $('<div>', {
        class: 'comm_cont',
        contenteditable: 'false',
      });
      comm_cont.text(i.content);

      let edit_box = $('<div>', { class: 'edit_box' });
      if (true) {
        // if (i.writer == "${nickname}") {
        let btn_box1 = $('<div>', { class: 'btn_box' });
        let edit_btn = $('<button>', {
          class: 'write_btn comm_btn',
          type: 'button',
        });
        edit_btn.text('수정');
        let submit_btn = $('<button>', {
          class: 'write_btn edit_btn',
          type: 'submit',
        });
        submit_btn.text('완료');
        btn_box1.append(edit_btn, submit_btn);

        let btn_box2 = $('<div>', { class: 'btn_box' });
        let del_btn = $('<button>', {
          class: 'write_btn comm_btn',
          type: 'button',
        });
        del_btn.text('삭제');
        let cancel_btn = $('<button>', {
          class: 'write_btn edit_btn',
          type: 'button',
        });
        cancel_btn.text('취소');
        btn_box2.append(del_btn, cancel_btn);
        edit_box.append(btn_box1, btn_box2);
      }

      let btn_box3 = $('<div>', { class: 'btn_box' });
      let option_btn = $('<button>', {
        class: 'option_btn likes_option',
        type: 'button',
      });

      let icon1 = $('<i>', {
        class: 'fa-regular fa-thumbs-up fa-xs option_icon',
      });
      let icon2 = $('<i>', {
        class: 'fa-solid fa-thumbs-up fa-xs option_icon',
      });
      let likes_num = $('<p>');

      likes_num.text(get_reply_likes(i.reply_seq));

      option_btn.append(icon1, icon2, likes_num);
      btn_box3.append(option_btn);

      edit_box.append(btn_box3);

      comm.append(comm_info, comm_cont, edit_box);
      comm_list.append(comm);

      $.ajax({
        url: '/check.replyLike',
        dataType: 'json',
        data: { reply_seq: i.reply_seq },
      }).done(function (resp) {
        if (resp) {
          icon1.hide();
          icon2.show();
        } else {
          icon1.show();
          icon2.hide();
        }
      });

      $('.option_btn')
        .off()
        .on('click', function () {
          click_option(this);
        });
      $('.comm .write_btn')
        .off()
        .on('click', function () {
          let choice = $(this).text();

          if (choice == '수정') {
            // 수정 버튼 클릭 시 완료 버튼 및 취소 버튼 노출
            $(this).closest('.edit_box').find('.comm_btn').hide();
            $(this).closest('.edit_box').find('.edit_btn').show();

            $(this)
              .closest('.comm')
              .find('.comm_cont')
              .attr('contenteditable', 'true')
              .focus();
          } else if (choice == '완료') {
            // 완료 버튼 클릭
            if (
              $(this).closest('.comm').find('.comm_cont').text().trim() == ''
            ) {
              alert('댓글을 먼저 입력해주세요');
              return;
            }
            $.ajax({
              url: '/update.reply',
              data: {
                content: $(this).closest('.comm').find('.comm_cont').html(),
                reply_seq: $(this).closest('.comm').find('.comm_seq').text(),
              },
            }).done(function () {
              location.reload();
            });
          } else if (choice == '삭제') {
            // 삭제 버튼 클릭
            if (confirm('정말로 삭제하시겠습니까 ?')) {
              $.ajax({
                url: '/delete.reply',
                data: {
                  reply_seq: $(this).closest('.comm').find('.comm_seq').text(),
                },
              }).done(function () {
                location.reload();
              });
            }
          } else if (choice == '취소') {
            // 취소 버튼 클릭
            if (confirm('수정하신 내용은 저장되지 않습니다')) {
              location.reload();
            }
          }
        });
    }
  });
}

// 댓글 작성 버튼 클릭 시
$('#write_comm').on('click', function () {
  // 작성 내용이 빈 문자열일 경우 작성 안내 후 메서드 종료
  if ($('.input_box').text().trim() == '') {
    alert('댓글을 먼저 입력해주세요.');
    $('.input_box').html('');
    return;
  }
  console.log('test');
  // 작성한 댓글 내용 ajax로 전달
  $.ajax({
    url: '/write.reply',
    method: 'post',
    data: {
      content: $('.input_box').html(),
      board_seq: get_board_seq(),
    },
  }).done(function () {
    location.reload();
  });
});

// 댓글 최신순 버튼 클릭 시
$('#comm_recent_btn').on('click', function () {
  get_comm_list('write_date');
  // 최신순 버튼 색 변경
  $(this).css('background-color', 'var(--color-space-click)');

  // 추천순 버튼 색 변경
  $('#comm_likes_btn').css('background-color', 'var(--color-space)');
});

// 댓글 추천순 버튼 클릭시
$('#comm_likes_btn').on('click', function () {
  get_comm_list('thumbs_up');
  // 추천순 버튼 색 변경
  $(this).css('background-color', 'var(--color-space-click)');

  // 최신순 버튼 색 변경
  $('#comm_recent_btn').css('background-color', 'var(--color-space');
});

// 목록 버튼 클릭 시
$('#back_btn').on('click', function () {
  location.href = '/list.board';
});

// 작성 버튼 클릭 시
$('#write_btn').on('click', function () {
  location.href = '/user/crud/write_board.jsp';
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
