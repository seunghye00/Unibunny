$(document).ready(function() {
	// 삭제 버튼 클릭 시 해당 배너 행을 제거하는 이벤트
	// $(document).on('click', '#delete_btn', function () {
	//   $(this).closest('.banner_row').remove();
	//   // 순번 업데이트 함수 호출
	//   updateBannerSequence();
	//   // 삭제 버튼 상태 업데이트
	//   toggleDeleteButton();
	// });

	$('#delete_btn').click(function() {
		$('input[name="checked"]:checked').each(function() {
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
	$(document).on('change', 'input[type="checkbox"]', function() {
		toggleDeleteButton();
	});

	// 배너 추가 버튼 클릭 시 새로운 배너 행을 추가하는 이벤트
	$('#add_banner_btn').click(function() {
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
                  <input type="radio" name="contact${bannerCount + 1
			}" value="use" checked />
                  <span>사용</span>
                </label>
                <label>
                  <input type="radio" name="contact${bannerCount + 1
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
	$('#check_all').change(function() {
		var isChecked = $(this).prop('checked');
		$('input[name="checked"]').prop('checked', isChecked);
		// 삭제 버튼 상태 업데이트
		toggleDeleteButton();
	});

	// 개별 체크 박스 선택 시 전체 선택 체크 박스 상태 변화
	$('input[name="checked"]').change(function() {
		var allChecked = true;
		$('input[name="checked"]').each(function() {
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
		$('.banner_row').each(function(index) {
			if (index > 0) {
				// 헤더 행을 제외하고 순번 업데이트
				$(this).find('.banner_seq span').text(index);
			}
		});
	}

	// 이미지 업로드 버튼 클릭 시 실제 파일 업로드 입력을 클릭하도록 하는 이벤트
	$(document).on('click', '.upload', function() {
		$(this).siblings('.real_upload').click();
	});

	// 파일 입력 변경 시 미리보기 및 재업로드 기능
	$(document).on('change', '.real_upload', function() {
		var input = this;
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
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
	$('#submit_changes_btn').click(function() {
		// 여기에 수정된 내용을 전송하는 로직 추가
		alert('수정된 내용을 전송합니다.');
	});
});

// 전체 회원을 출력할 지 블랙 리스트를 출력할 지 선택하는 메서드
function change_grade(grade) {

	if (grade == '회원') {
		// 일반 회원을 출력하고 싶은 경우

		$(".blacklist").removeClass("cpage");
		$(".user").addClass("cpage");

		get_member_list("회원", 1);

	} else {
		// 블랙리스트를 출력하고 싶은 경우

		$(".blacklist").addClass("cpage");
		$(".user").removeClass("cpage");

		get_member_list("정지된 회원", 1);
	}
}

// 회원 관리 영역에서 검색창에 enter 키 눌렀을 때 검색 회원 검색 기능
$(".member_con .input_tag").on("keydown", function(e) {
	if (e.key === 'Enter' || e.keyCode === 13 || e.which === 13) {
		// Enter 키가 눌렸을 때 실행할 코드
		search_user();
	}
});

// 검색한 회원을 출력하는 메서드
function search_user(cpage) {

	if ($(".input_tag").val() == "") {
		alert("검색할 내용을 먼저 입력해주세요");
		return;
	}

	$(".list_table>div:not(.table_header)").remove();
	$(".pagination").empty();

	if (cpage === undefined) {
		// 페이지 첫 시작이거나 변수가 정의되지 않았을 경우 초기값 설정
		cpage = 1;
	}
	let grade = $("a.grade.cpage").text();
	let user_info = $(".input_tag").val();

	$.ajax({
		url: "/getSearchNum.member",
		dataType: "json",
		data: {
			grade: grade,
			user_info: user_info
		}
	}).done(function(resp) {
		let record_count_per_page = resp.record_count_per_page;
		let navi_count_per_page = resp.navi_count_per_page;
		let record_total_count = resp.total_data;

		console.log(record_total_count);
		if (record_total_count > 0) {

			// 필요한 Page navigator의 수
			let page_total_count = 0;

			if (record_total_count % record_count_per_page > 0) {
				// 전체 게시글을 한 페이지 당 노출할 게시글 수로 나눴을 때 나머지가 존재한다면 navi 수는 (전체 게시글 / 한 페이지 당 게시글 + 1)
				page_total_count = record_total_count / record_count_per_page + 1;
			} else {
				// 나머지가 존재하지 않는다면 navi 수는 (전체 게시글 / 한 페이지 당 게시글)
				page_total_count = record_total_count / record_count_per_page;
			}

			// 현재 페이지의 Page Navigator들 중 시작 번호
			let start_navi = Math.floor((cpage - 1) / navi_count_per_page) * navi_count_per_page + 1;
			// 현재 페이지의 Page Navigator들 중 끝 번호
			let end_navi = start_navi + navi_count_per_page - 1;

			if (end_navi > page_total_count) {
				end_navi = page_total_count;
			}

			let need_next = end_navi < page_total_count;
			let need_prev = start_navi > 1;

			// 페이지네이션 HTML 생성
			let page_nation = $(".pagination");
			page_nation.empty();

			// '이전 페이지로' 버튼
			let prev_btn = $("<a>", { "class": "page_navi arr_navi start_arr", "href": "javascript:search_user(" + (start_navi - 1) + ")" });
			let prev_img = $("<img>", { "class": "navi_icon start_navi", "src": "../image/icon/pagination.png", "alt": "start navi 로고" });
			if (!need_prev) {
				prev_btn.addClass("disabled");
			}
			prev_btn.append(prev_img);
			page_nation.append(prev_btn);


			// 페이지 번호
			for (let i = start_navi; i <= end_navi; i++) {
				let page_navi = $("<a>", { "class": "page_navi", "href": "javascript:search_user(" + i + ")" });
				page_navi.text(i);
				if (cpage == i) {
					page_navi.addClass("active");
				}
				page_nation.append(page_navi);
			}

			// '다음 페이지로' 버튼
			let next_btn = $("<a>", { "class": "page_navi arr_navi end_arr", "href": "javascript:search_user(" + (end_navi - 1) + ")" });
			let next_img = $("<img>", { "class": "navi_icon end_navi", "src": "../image/icon/pagination.png", "alt": "end navi 로고" });
			if (!need_next) {
				next_btn.addClass("disabled");
			}
			next_btn.append(next_img);
			page_nation.append(next_btn);

			// 검색한 데이터를 ajax로 받아오기	
			$.ajax({
				url: "/trySearch.member",
				dataType: "json",
				data: {
					grade: grade,
					user_info: user_info,
					cpage: cpage
				}
			}).done(function(resp) {
				let index = cpage * 10 - 9;
				for (let i of resp) {

					let row = $("<div>", { "class": "table_row" });
					let col = $("<div>", { "class": "table_col" });
					let span = $("<span>");

					// table_col 첫번째 요소에 index 값 삽입
					span.text(index++);
					col.append(span);
					row.append(col);

					// table_col 두번째 요소에 userid 값 삽입
					col = $("<div>", { "class": "table_col user_id" });
					span = $("<span>");
					span.text(i.userid);
					col.append(span);
					row.append(col);

					// table_col 세번째 요소에 nickname 값 삽입
					col = $("<div>", { "class": "table_col" });
					span = $("<span>");
					span.text(i.nickname);
					col.append(span);
					row.append(col);

					// table_col 네번째 요소에 join_date 값 삽입
					col = $("<div>", { "class": "table_col" });
					span = $("<span>");
					span.text(i.join_date);
					col.append(span);
					row.append(col);

					// table_col 다섯번째 요소에 member_seq 및 버튼 삽입
					col = $("<div>", { "class": "table_col" });
					let btn = $("<button>");
					if (grade == "정지된 회원") {
						btn.addClass("to_user");
						btn.text("해제");
					} else {
						btn.addClass("to_black");
						btn.text("등록");
					}
					col.append(btn);
					row.append(col);

					// 테이블에 데이터 출력
					$(".list_table").append(row);

					// 해당 회원의 등급을 변경하는 경우
					$('button').off().on('click', function() {
						let user_id = $(this).closest(".table_row").children(".user_id").text();
						$.ajax({
							url: "/changeGrade.member",
							dataType: "json",
							data: {
								user_id: user_id,
								grade: grade
							}
						}).done(function(resp) {
							if (resp < 1) {
								alert("오류가 발생했습니다.");
							}
							search_user(cpage);
						});
					});
				}
			});
		} else {
			// 검색한 결과가 없을 경우
			let row = $("<div>", { "class": "table_row" });
			row.css({
				"font-size": "16px",
				"justify-content": "center",
				"color": "var(--color-white)",
				"padding": "15px"
			});
			row.text("검색한 결과가 존재하지 않습니다");
			$(".list_table").append(row);
		}
		$(".input_tag").val("");
	});
}

// 해당 페이지의 회원 목록을 불러오는 메서드 
function get_member_list(grade, cpage) {
	// 테이블 헤더 영역을 제외한 데이터 비우기
	$(".list_table>div:not(.table_header)").remove();

	if (grade === undefined) {
		// 페이지 첫 시작이거나 변수가 정의되지 않았을 경우 초기값 설정
		grade = "회원";
	}
	if (cpage === undefined) {
		// 페이지 첫 시작이거나 변수가 정의되지 않았을 경우 초기값 설정
		cpage = 1;
	}

	// 해당 등급의 총 회원 수를 불러오기 위한 코드
	$.ajax({
		url: "/total.member",
		dataType: "json",
		data: { grade: grade }
	}).done(function(resp) {
		let record_count_per_page = resp.record_count_per_page;
		let navi_count_per_page = resp.navi_count_per_page;
		let record_total_count = resp.total_data;

		if (record_total_count > 0) {

			
		// 필요한 Page navigator의 수
		let page_total_count = 0;

		if (record_total_count % record_count_per_page > 0) {
			// 전체 게시글을 한 페이지 당 노출할 게시글 수로 나눴을 때 나머지가 존재한다면 navi 수는 (전체 게시글 / 한 페이지 당 게시글 + 1)
			page_total_count = record_total_count / record_count_per_page + 1;
		} else {
			// 나머지가 존재하지 않는다면 navi 수는 (전체 게시글 / 한 페이지 당 게시글)
			page_total_count = record_total_count / record_count_per_page;
		}

		// 현재 페이지의 Page Navigator들 중 시작 번호
		let start_navi = Math.floor((cpage - 1) / navi_count_per_page) * navi_count_per_page + 1;
		// 현재 페이지의 Page Navigator들 중 끝 번호
		let end_navi = start_navi + navi_count_per_page - 1;

		if (end_navi > page_total_count) {
			end_navi = page_total_count;
		}

		let need_next = end_navi < page_total_count;
		let need_prev = start_navi > 1;

		// 페이지네이션 HTML 생성
		let page_nation = $(".pagination");
		page_nation.empty();

		// '이전 페이지로' 버튼
		let prev_btn = $("<a>", { "class": "page_navi arr_navi start_arr", "href": "javascript:get_member_list(`" + grade + "`, " + (start_navi - 1) + ")" });
		let prev_img = $("<img>", { "class": "navi_icon start_navi", "src": "../image/icon/pagination.png", "alt": "start navi 로고" });
		if (!need_prev) {
			prev_btn.addClass("disabled");
		}
		prev_btn.append(prev_img);
		page_nation.append(prev_btn);


		// 페이지 번호
		for (let i = start_navi; i <= end_navi; i++) {
			let page_navi = $("<a>", { "class": "page_navi", "href": "javascript:get_member_list(`" + grade + "`, " + i + ")" });
			page_navi.text(i);
			if (cpage == i) {
				page_navi.addClass("active");
			}
			page_nation.append(page_navi);
		}

		// '다음 페이지로' 버튼
		let next_btn = $("<a>", { "class": "page_navi arr_navi end_arr", "href": "javascript:get_member_list(`" + grade + "`, " + (end_navi - 1) + ")" });
		let next_img = $("<img>", { "class": "navi_icon end_navi", "src": "../image/icon/pagination.png", "alt": "end navi 로고" });
		if (!need_next) {
			next_btn.addClass("disabled");
		}
		next_btn.append(next_img);
		page_nation.append(next_btn);
		
		$.ajax({
			url: "/list.member",
			dataType: "json",
			data: {
				grade: grade,
				cpage: cpage
			}
		}).done(function(resp) {
			let index = cpage * 10 - 9;
			for (let i of resp) {
	
				let row = $("<div>", { "class": "table_row" });
				let col = $("<div>", { "class": "table_col" });
				let span = $("<span>");
	
				// table_col 첫번째 요소에 index 값 삽입
				span.text(index++);
				col.append(span);
				row.append(col);
	
				// table_col 두번째 요소에 userid 값 삽입
				col = $("<div>", { "class": "table_col user_id" });
				span = $("<span>");
				span.text(i.userid);
				col.append(span);
				row.append(col);
	
				// table_col 세번째 요소에 nickname 값 삽입
				col = $("<div>", { "class": "table_col" });
				span = $("<span>");
				span.text(i.nickname);
				col.append(span);
				row.append(col);
	
				// table_col 네번째 요소에 join_date 값 삽입
				col = $("<div>", { "class": "table_col" });
				span = $("<span>");
				span.text(i.join_date);
				col.append(span);
				row.append(col);
	
				// table_col 다섯번째 요소에 member_seq 및 버튼 삽입
				col = $("<div>", { "class": "table_col" });
				let btn = $("<button>");
				if (grade == "정지된 회원") {
					btn.addClass("to_user");
					btn.text("해제");
				} else {
					btn.addClass("to_black");
					btn.text("등록");
				}
				col.append(btn);
				row.append(col);
	
				// 테이블에 데이터 출력
				$(".list_table").append(row);
	
				// 해당 회원의 등급을 변경하는 경우
				$('button').off().on('click', function() {
	
					let user_id = $(this).closest(".table_row").children(".user_id").text();
					$.ajax({
						url: "/changeGrade.member",
						dataType: "json",
						data: {
							user_id: user_id,
							grade: grade
						}
					}).done(function(resp) {
						if (resp < 1) {
							alert("오류가 발생했습니다.");
						}
						get_member_list(grade, cpage);
					});
				});
			}
		});
	
	} else {
		// 검색한 결과가 없을 경우
		let row = $("<div>", { "class": "table_row" });
		row.css({
			"font-size": "16px",
			"justify-content": "center",
			"color": "var(--color-white)",
			"padding": "15px"
		});
		row.text("검색한 결과가 존재하지 않습니다");
		$(".list_table").append(row);
	}
});
}
