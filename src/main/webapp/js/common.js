// 모바일 햄버거 기능
$(document).ready(function() {
	$('.mob_ham').click(function() {
		$('.mob_menu,.page_cover,html').addClass('mob_open');
		window.location.hash = '#open';
	});

	window.onhashchange = function() {
		if (location.hash != '#open') {
			$('.mob_menu,.page_cover,html').removeClass('mob_open');
		}
	};

	$('.mob_menu ul.sub_mobile').hide();
	$('.mob_menu ul.nav li').click(function() {
		$('ul', this).slideToggle('fast');
	});
	// 메인 스와이퍼 백그라운드 이미지
	let main_slide = [];
	$('.banner_cont .swiper_bg').each(function(i) {
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
// 마이페이지 js
$(document).ready(function() {
	// 모든 탭 버튼 가져오기
	const tabButtons = $('.tab_item button');

	// 각 탭 버튼에 클릭 이벤트 추가
	tabButtons.on('click', function() {
		// 모든 섹션 숨기기
		const allContents = $('.profile_contents_wrap');
		allContents.hide();
		// 클릭한 버튼에 해당하는 섹션 보이기
		const sectionTitle = $(this).text().trim();
		const targetContent = allContents.filter(function() {
			return $(this).find('.profile_title').text().trim() === sectionTitle;
		});

		if (targetContent.length) {
			targetContent.show();
		}
	});







	// 드롭다운 메뉴
	$('.tab-dropdown').on('change', function() {
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
		const targetContent = allContents.filter(function() {
			return $(this).find('.profile_title').text().trim() === sectionTitle;
		});

		if (targetContent.length) {
			targetContent.show();
		}
	});
});



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
$('#edit_btn').on('click', function() {
	location.href = '/tryUpdate.board?board_seq=' + get_board_seq();
});

// 게시글 수정 페이지에서 완료 버튼 클릭 시
$('#update_btn').on('click', function(e) {
	e.preventDefault();

	let trimmedHtml = $('#summernote').html().trim();
	$('#summernote').html(trimmedHtml);
	console.log($('#summernote').text());
	$('#update_board').submit();
	// return false;
});

// 게시글 수정 페이지에서 취소 버튼 클릭 시
$('#cancel_btn').on('click', function() {
	if (confirm('수정하신 내용은 저장되지 않습니다.')) {
		location.href = '/user/detail.board?board_seq=' + board_seq;
	}
});

// 게시글 상세 페이지에서 삭제 버튼 클릭 시
$('#del_btn').on('click', function() {
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
	}).done(function(resp) {
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
	}).done(function(resp) {
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
	}).done(function(resp) {
		$('.bookmark').text('스크랩 수 : ' + resp);
	});
	// 좋아요 수
	$.ajax({
		url: '/count.boardLike',
		dataType: 'json',
		data: { board_seq: get_board_seq() },
	}).done(function(resp) {
		$('#board_like').text(resp);
	});
}

// 해당 댓글의 좋아요 수를 받아오기 위한 메서드
function get_reply_likes(reply_seq) {
	$.ajax({
		url: '/count.replyLike',
		dataType: 'json',
		data: { reply_seq: reply_seq },
	}).done(function(resp) {
		// reply_seq와 일치하는 .comm_seq 요소 선택
		let comm_seq = $('.comm_seq').filter(function() {
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
	}).done(function(resp) {
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

			file_name.on('click', function() {
				console.log(i.oriname);
				console.log(i.sysname);
				$.ajax({
					url: '/download.file',
					dataType: 'json',
					data: {
						oriname: i.oriname,
						sysname: i.sysname,
					},
				}).done(function(resp) {
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
				}).done(function() {
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
			}).done(function() {
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
				}).done(function() {
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
			}).done(function(resp) {
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
$('.option_btn').on('click', function() {
	click_option($(this));
});

// 댓글 좋아요 버튼 클릭 시
$('#comm_likes_btn').click(function() {
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
	}).done(function(resp) {
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
			}).done(function(resp) {
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
				.on('click', function() {
					click_option(this);
				});
			$('.comm .write_btn')
				.off()
				.on('click', function() {
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
						}).done(function() {
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
							}).done(function() {
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
$('#write_comm').on('click', function() {
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
	}).done(function() {
		location.reload();
	});
});

// 댓글 최신순 버튼 클릭 시
$('#comm_recent_btn').on('click', function() {
	get_comm_list('write_date');
	// 최신순 버튼 색 변경
	$(this).css('background-color', 'var(--color-space-click)');

	// 추천순 버튼 색 변경
	$('#comm_likes_btn').css('background-color', 'var(--color-space)');
});

// 댓글 추천순 버튼 클릭시
$('#comm_likes_btn').on('click', function() {
	get_comm_list('thumbs_up');
	// 추천순 버튼 색 변경
	$(this).css('background-color', 'var(--color-space-click)');

	// 최신순 버튼 색 변경
	$('#comm_recent_btn').css('background-color', 'var(--color-space');
});

// 목록 버튼 클릭 시
$('#back_btn').on('click', function() {
	location.href = '/list.board';
});

// 작성 버튼 클릭 시
$('#write_btn').on('click', function() {
	location.href = '/user/crud/write_board.jsp';
});

$(document).ready(function() {
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
});


