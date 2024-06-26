$(document).ready(function () {
  // 삭제 버튼 클릭 시 해당 배너 행을 제거하는 이벤트
  // $(document).on('click', '#delete_btn', function () {
  //   $(this).closest('.banner_row').remove();
  //   // 순번 업데이트 함수 호출
  //   updateBannerSequence();
  //   // 삭제 버튼 상태 업데이트
  //   toggleDeleteButton();
  // });

  $('#delete_btn').click(function () {
    $('input[name="checked"]:checked').each(function () {
      $(this).closest('.banner_row').remove();
    });
    // 순번 업데이트 함수 호출
    updateBannerSequence();
    // 삭제 버튼 상태 업데이트
    toggleDeleteButton();
  });
  // 체크박스 상태에 따라 삭제 버튼 활성화/비활성화 함수
  function toggleDeleteButton() {
    var checked = $('input[type="checkbox"]:checked').length > 0;
    $('#delete_btn').prop('disabled', !checked);
  }

  // 체크박스 상태 변경 시 삭제 버튼 활성화/비활성화
  $(document).on('change', 'input[type="checkbox"]', function () {
    toggleDeleteButton();
  });

  // 배너 추가 버튼 클릭 시 새로운 배너 행을 추가하는 이벤트
  $('#add_banner_btn').click(function () {
    // 헤더 행을 제외한 현재 배너 개수 계산
    var bannerCount = $('.banner_row').length - 1;
    var newBannerRow = `
        <div class="banner_row">
          <div class="banner_col banner_checked">
            <input type="checkbox" name="checked">
            <label for="checked"></label>
          </div>
          <div class="banner_col banner_seq">
            <span>${bannerCount + 1}</span>
          </div>
          <div class="banner_col banner_img">
            <div class="image_container">
              <input type="file" class="real_upload" accept="image/*" required>
              <div class="upload">+</div>
            </div>
          </div>
          <div class="banner_col banner_url">
            <div class="url_cont">
              <span>url:</span>
              <input type="text" class="url_input">
            </div>
          </div>
          <div class="banner_col banner_use">
            <div class="use_select">
              <fieldset>
                <label>
                  <input type="radio" name="contact${
                    bannerCount + 1
                  }" value="use" checked />
                  <span>사용</span>
                </label>
                <label>
                  <input type="radio" name="contact${
                    bannerCount + 1
                  }" value="unuse" />
                  <span>사용 안함</span>
                </label>
              </fieldset>
            </div>
          </div>
        </div>
      `;
    $('.select_banner').append(newBannerRow);
    // 순번 업데이트 함수 호출
    updateBannerSequence();
    // 삭제 버튼 상태 업데이트
    toggleDeleteButton();
  });

  // 전체 선택 체크 박스 기능 추가
  $('#check_all').change(function () {
    var isChecked = $(this).prop('checked');
    $('input[name="checked"]').prop('checked', isChecked);
    // 삭제 버튼 상태 업데이트
    toggleDeleteButton();
  });

  // 개별 체크 박스 선택 시 전체 선택 체크 박스 상태 변화
  $('input[name="checked"]').change(function () {
    var allChecked = true;
    $('input[name="checked"]').each(function () {
      if (!$(this).prop('checked')) {
        allChecked = false;
      }
    });
    $('#check_all').prop('checked', allChecked);
    // 삭제 버튼 상태 업데이트
    toggleDeleteButton();
  });

  // 배너 순번 업데이트 함수
  function updateBannerSequence() {
    $('.banner_row').each(function (index) {
      if (index > 0) {
        // 헤더 행을 제외하고 순번 업데이트
        $(this).find('.banner_seq span').text(index);
      }
    });
  }

  // 이미지 업로드 버튼 클릭 시 실제 파일 업로드 입력을 클릭하도록 하는 이벤트
  $(document).on('click', '.upload', function () {
    $(this).siblings('.real_upload').click();
  });

  // 파일 입력 변경 시 미리보기 및 재업로드 기능
  $(document).on('change', '.real_upload', function () {
    var input = this;
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        var imageContainer = $(input)
          .closest('.banner_img')
          .find('.image_container');
        // 기존 이미지 삭제
        imageContainer.find('img').remove();
        imageContainer.append(
          '<img src="' + e.target.result + '" alt="banner image">'
        );
      };
      reader.readAsDataURL(input.files[0]);
    }
  });

  // 수정 완료 버튼 클릭 시 처리할 내용
  $('#submit_changes_btn').click(function () {
    // 여기에 수정된 내용을 전송하는 로직 추가
    alert('수정된 내용을 전송합니다.');
  });
});
