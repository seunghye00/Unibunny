// Document Ready
$(document).ready(function() {
	// 회원가입 버튼 클릭 시 페이지 이동
	$('#signup_btn').on('click', function() {
		location.href = '/members/signup.jsp';
	});

	// 닉네임 유효성 검사
	$('#input_nickname').keyup(function() {
		const nickname_pattern = /^[A-Za-z0-9가-힣]{3,10}$/;
		const nickname = $(this).val();
		const h2 = $(this).parent().children()[1];


		if (nickname === '' || !nickname_pattern.test(nickname)) {
			h2.style.color = '#ff3737';
			h2.textContent =
				'＊영어 대소문자, 숫자, 한글을 포함하여 3~10글자 사이를 입력하세요.*';
		} else {
			h2.style.color = '#18ff18';
			h2.textContent = '사용 가능한 닉네임입니다.';
		}
	});


	$('#input_id').keyup(function() {
		const userid_pattern = /^[A-Za-z0-9]{6,12}$/;
		const userid = $(this).val();
		const h2 = $(this).parent().children()[1];

		if (userid === '' || !userid_pattern.test(userid)) {
			h2.style.color = '#ff3737';
			h2.textContent = '＊영문 대소문자와 숫자로 이루어진 길이가 6에서 12 사이의 문자열을 입력하세요.*';
			return; // Exit if the input does not match the pattern
		} else {
			h2.style.color = '#18ff18';
			h2.textContent = '사용 가능한 ID입니다.';
		}

		// Ajax 요청
		$.ajax({
			url: "/check.member",
			type: 'GET',
			data: {
				userid: userid
			},
			success: function(response) {
				if (response.exists) {
					h2.style.color = '#ff3737';
					h2.textContent = '이미 사용 중인 ID입니다.';
				} else {
					h2.style.color = '#18ff18';
					h2.textContent = '사용 가능한 ID입니다.';
				}
			},
			error: function() {
				h2.style.color = '#ff3737';
				h2.textContent = '서버 오류가 발생했습니다.';
			}
		});
	});



	// 비밀번호 검사 및 일치 여부 확인
	$('#input_pw, #confirm_pw').keyup(function() {
		const password_pattern =
			/^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*()_])[A-Za-z0-9!@#$%^&*()_]{8,15}$/;
		const input_pw = $('#input_pw').val();
		const confirm_pw = $('#confirm_pw').val();
		const h2 = $('#input_pw').parent().children()[1];



		if (!password_pattern.test(input_pw)) {
			h2.style.color = '#ff3737';
			h2.textContent =
				'＊대소문자나 숫자를 적어도 하나 포함해야 하며, 특수문자 (!@#$%^&*()_)를 반드시 포함해야 합니다. 길이는 8에서 15글자 사이여야 합니다.*';
		} else {
			h2.style.color = '#18ff18';
			h2.textContent = '사용 가능한 password입니다.';
		}

		// 비밀번호와 비밀번호 확인 일치 여부 확인
		if (confirm_pw == '') {
			$('#confirm_message').text('');
		} else if (confirm_pw === input_pw) {
			$('#confirm_message')
				.text('*비밀번호가 일치합니다.')
				.css({ color: '#18ff18', 'font-size': '12px' });
		} else {
			$('#confirm_message')
				.text('*비밀번호가 일치하지 않습니다.')
				.css({ color: '#ff3737', 'font-size': '12px' });
		}
	});

	// 전화번호 유효성 검사
	$('#input_phone').keyup(function() {
		const phone_pattern = /^01\d{9}$/;
		let phone_number = $(this).val();
		phone_number = phone_number.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거

		// 입력 값 업데이트
		$(this).val(phone_number);

		const h2 = $(this).siblings('h2')[0]; // h2 요소 선택

		if (phone_number === '') {
			h2.textContent = '';
		} else if (!phone_pattern.test(phone_number)) {
			h2.style.color = '#ff3737';
			h2.textContent =
				'＊숫자만 입력하고 -생략하며 모든 번호의 시작은 01입니다.*';
		} else {
			h2.style.color = '#18ff18';
			h2.textContent = '사용 가능한 전화번호입니다.';
		}
	});

	// 입력 필드의 색상 변경
	$('#input_identify').keyup(function() {
		if ($(this).val().length == 0) {
			$(this)[0].style.color = '#b3b3b3';
		} else {
			$(this)[0].style.color = 'black';
		}
	});

	// 숫자 입력만 허용
	$('#input_first, #input_identify').on('keydown', function(event) {
		const charCode = event.which ? event.which : event.keyCode;
		// 허용할 특수키: 백스페이스(8), 탭(9), 엔터(13), 좌우 화살표(37, 39)
		if (
			charCode == 8 ||
			charCode == 9 ||
			charCode == 13 ||
			charCode == 37 ||
			charCode == 39
		) {
			return;
		}
		// 입력된 값이 숫자가 아니면 입력을 막음
		if (charCode < 48 || charCode > 57) {
			event.preventDefault();
		}
	});

	// 입력 필드의 값 변경 감지
	$('#input_first').on('input', function() {
		let firstPart = $(this).val().trim();

		firstPart = firstPart.replace(/\D/g, '');

		if (firstPart.length > 6) {
			firstPart = firstPart.substring(0, 6);
		}

		$(this).val(firstPart);
	});

	$('#input_identify').on('input', function() {
		let identifyPart = $(this).val().trim();

		identifyPart = identifyPart.replace(/\D/g, '');

		if (identifyPart.length > 1) {
			identifyPart = identifyPart.substring(0, 1);
		}

		$(this).val(identifyPart);
	});

	// 이메일 자동완성
	const emailDomains = ['gmail.com', 'hotmail.com', 'naver.com', 'daum.net'];
	$('#input_email').autocomplete({
		source: function(request, response) {
			const term = request.term;
			const email_pattern = /^[^\s@]+@[^\s@]*$/;
			const atIndex = term.indexOf('@');
			const suggestions = [];

			if (email_pattern.test(term)) {
				const name = term.substring(0, atIndex);
				const domain = term.substring(atIndex + 1);

				if (atIndex > -1) {
					const filteredDomains = emailDomains.filter(function(d) {
						return d.indexOf(domain) === 0;
					});
					for (let i = 0; i < filteredDomains.length; i++) {
						suggestions.push(name + '@' + filteredDomains[i]);
					}
				}
			}
			response(suggestions);
		},
		minLength: 1,
	});

	// 다음 우편번호 서비스 사용
	$('.postcode_btn').click(function() {
		new daum.Postcode({
			oncomplete: function(data) {
				$('#input_postCode').val(data.zonecode);
				$('#input_address1').val(data.jibunAddress);
				$('#input_address2').val('');
			},
		}).open();
	});

	// 현재 URL에 따라 활성화할 메뉴 항목 설정
	const currentUrl = window.location.href;
	if (currentUrl.includes('findPassword.html')) {
		$('#find_password').addClass('active');
	} else if (currentUrl.includes('findAccount.html')) {
		$('#find_id').addClass('active');
	}

	// 비밀번호 찾기 또는 계정 찾기 페이지 설정
	if (currentUrl.includes('findPassword.html')) {
		$('#find_password').prop('checked', true);
	} else if (currentUrl.includes('findAccount.html')) {
		$('#find_id').prop('checked', true);
	}

	// 갤럭시 폰인지 확인하고 클래스 추가
	function isGalaxyPhone() {
		const userAgent = navigator.userAgent.toLowerCase();
		return /samsung|galaxy|sm-|gt-/.test(userAgent);
	}

	if (isGalaxyPhone()) {
		document.documentElement.classList.add('galaxy_phone');
	}
});
