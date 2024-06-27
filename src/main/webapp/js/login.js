// 문서가 준비되었을 때 실행
$(document).ready(function() {
   let isnickname = false;
   let isid = false;
   let isemail = false;
   let isphone = false;

   // 폼 제출 버튼 클릭 핸들러
   $("#signup_form button").on("click", function(event) {
      if (!isid) {
         alert("아이디를 확인해주세요!");
         event.preventDefault();
      } else if (!isnickname) {
         alert("닉네임을 확인해주세요!");
         event.preventDefault();
      } else if (!isemail) {
         alert("이메일 주소를 확인해주세요!");
         event.preventDefault();
      } else if (!isphone) {
         alert("휴대폰 번호를 확인해주세요!");
         event.preventDefault();
      }
   });

   // 닉네임 유효성 검사
   $("#input_nickname").keyup(function() {
      const nickname_pattern = /^[A-Za-z0-9가-힣]{3,10}$/;
      const nickname = $(this).val();
      const h2 = $(this).parent().children()[1];

      if (nickname === "" || !nickname_pattern.test(nickname)) {
         h2.style.color = "#ff3737";
         h2.textContent =
            "＊영어 대소문자, 숫자, 한글을 포함하여 3~10글자 사이를 입력하세요.*";
         isnickname = false;
         return;
      }

      // Ajax 요청
      $.ajax({
         url: "/check.member",
         type: "get",
         data: {
            mode: "nickname",
            value: nickname,
         },
         dataType: "json", // JSON 형식으로 받음
         success: function(response) {
            if (response.exists) {
               h2.style.color = "#ff3737";
               h2.textContent = "이미 사용 중인 닉네임 입니다.";
               isnickname = false;
            } else {
               h2.style.color = "#18ff18";
               h2.textContent = "사용 가능한 닉네임 입니다.";
               isnickname = true;
            }
         },
         error: function() {
            h2.style.color = "#ff3737";
            h2.textContent = "서버 오류가 발생했습니다.";
            isnickname = false;
         },
      });
   });

   // 아이디 유효성 검사
   $("#input_id").keyup(function() {
      const userid_pattern = /^[A-Za-z0-9]{6,12}$/;
      const userid = $(this).val();
      const h2 = $(this).parent().children()[1];

      if (userid === "" || !userid_pattern.test(userid)) {
         h2.style.color = "#ff3737";
         h2.textContent =
            "＊영문 대소문자와 숫자로 이루어진 길이가 6에서 12 사이의 문자열을 입력하세요.*";
         isid = false;
         return; // 패턴이 일치하지 않으면 종료
      }

      // Ajax 요청
      $.ajax({
         url: "/check.member",
         type: "GET",
         data: {
            mode: "userid",
            value: userid,
         },
         success: function(response) {
            if (response.exists) {
               h2.style.color = "#ff3737";
               h2.textContent = "이미 사용 중인 ID입니다.";
               isid = false;
            } else {
               h2.style.color = "#18ff18";
               h2.textContent = "사용 가능한 ID입니다.";
               isid = true;
            }
         },
         error: function() {
            h2.style.color = "#ff3737";
            h2.textContent = "서버 오류가 발생했습니다.";
            isid = false;
         },
      });
   });

   // 비밀번호 검사 및 일치 여부 확인
   $("#input_pw, #confirm_pw").keyup(function() {
      const password_pattern =
         /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*()_])[A-Za-z0-9!@#$%^&*()_]{8,15}$/;
      const input_pw = $("#input_pw").val();
      const confirm_pw = $("#confirm_pw").val();
      const h2 = $("#input_pw").parent().children()[1];

      if (input_pw === "" || !password_pattern.test(input_pw)) {
         h2.style.color = "#ff3737";
         h2.textContent =
            "＊대소문자나 숫자를 적어도 하나 포함해야 하며, 특수문자 (!@#$%^&*()_)를 반드시 포함해야 합니다. 길이는 8에서 15글자 사이여야 합니다.*";
         return;
      } else {
         h2.style.color = "#18ff18";
         h2.textContent = "사용 가능한 password입니다.";
      }

      // 비밀번호와 비밀번호 확인 일치 여부 확인
      if (confirm_pw == "") {
         $("#confirm_message").text("");
      } else if (confirm_pw === input_pw) {
         $("#confirm_message")
            .text("*비밀번호가 일치합니다.")
            .css({ color: "#18ff18", "font-size": "12px", "font-weight": "bold" });
      } else {
         $("#confirm_message")
            .text("*비밀번호가 일치하지 않습니다.")
            .css({ color: "#ff3737", "font-size": "12px", "font-weight": "bold" });
      }
   });

   // 전화번호 유효성 검사
   $("#input_phone").keyup(function() {
      const phone_pattern = /^010\d{8}$/;
      let phone_number = $(this).val();
      phone_number = phone_number.replace(/[^0-9]/g, ""); // 숫자 이외의 문자 제거

      // 입력 값 업데이트
      $(this).val(phone_number);

      const h2 = $(this).siblings("h2")[0]; // h2 요소 선택

      if (phone_number === "" || !phone_pattern.test(phone_number)) {
         h2.style.color = "#ff3737";
         h2.textContent =
            "＊숫자만 입력하고 -생략하며 모든 번호의 시작은 01입니다.*";
         isphone = false;
      } else {
         // Ajax 요청
         $.ajax({
            url: "/check.member",
            type: "GET",
            data: {
               mode: "phone",
               value: phone_number,
            },
            success: function(response) {
               if (response.exists) {
                  h2.style.color = "#ff3737";
                  h2.textContent = "이미 사용 중인 핸드폰 번호입니다.";
                  isphone = false;
               } else {
                  h2.style.color = "#18ff18";
                  h2.textContent = "사용 가능한 핸드폰 번호입니다.";
                  isphone = true;
               }
            },
            error: function() {
               h2.style.color = "#ff3737";
               h2.textContent = "서버 오류가 발생했습니다.";
               isphone = false;
            },
         });
      }
   });

   $("#input_identify").on('input', function() {
      var value = $(this).val();
      var filteredValue = value.replace(/[^1234]/g, ''); // 1234 외의 모든 문자를 제거

      $(this).val(filteredValue);

      // 입력값이 없을 때 색상 설정
      if (filteredValue.length == 0) {
         $(this)[0].style.color = "#b3b3b3";
      } else {
         $(this)[0].style.color = "black";
      }
   });





   // 입력 필드의 색상 변경
   // $("#input_identify").keyup(function() {
   //
   //    if ($(this).val().length == 0) {
   //       $(this)[0].style.color = "#b3b3b3";
   //    } else {
   //       $(this)[0].style.color = "black";
   //    }
   // });

   // 숫자 입력만 허용
   $("#input_first, #input_identify").on("keydown", function(event) {
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
   $("#input_first").on("input", function() {
      let firstPart = $(this).val().trim();

      firstPart = firstPart.replace(/\D/g, "");

      if (firstPart.length > 6) {
         firstPart = firstPart.substring(0, 6);
      }

      $(this).val(firstPart);
   });


   $("#input_identify").on("input", function() {
      let identifyPart = $(this).val().trim();

      identifyPart = identifyPart.replace(/\D/g, "");

      if (identifyPart.length > 1) {
         identifyPart = identifyPart.substring(0, 1);
      }

      $(this).val(identifyPart);
   });

   // 이메일 자동완성
   const emailDomains = ["gmail.com", "hotmail.com", "naver.com", "daum.net"];

   $("#input_email")
      .autocomplete({
         source: function(request, response) {
            const term = request.term;
            const email_pattern = /^[^\s@]+@[^\s@]*$/;
            const atIndex = term.indexOf("@");
            const suggestions = [];

            if (email_pattern.test(term)) {
               const name = term.substring(0, atIndex);
               const domain = term.substring(atIndex + 1);

               if (atIndex > -1) {
                  const filteredDomains = emailDomains.filter(function(d) {
                     return d.indexOf(domain) === 0;
                  });
                  for (let i = 0; i < filteredDomains.length; i++) {
                     suggestions.push(name + "@" + filteredDomains[i]);
                  }
               }
            }
            response(suggestions);
         },
         minLength: 1,
      })
      .keyup(function() {
         const email = $(this).val();
         const h2 = $(this).parent().children("h2")[0];

         // Ajax 요청
         $.ajax({
            url: "/check.member",
            type: "GET",
            data: {
               mode: "email",
               value: email,
            },
            success: function(response) {
               if (response.exists) {
                  h2.style.color = "#ff3737";
                  h2.textContent = "이미 사용 중인 이메일입니다.";
                  isemail = false;
               } else {
                  h2.style.color = "#18ff18";
                  h2.textContent = "사용 가능한 이메일입니다.";
                  isemail = true;
               }
            },
            error: function() {
               h2.style.color = "#ff3737";
               h2.textContent = "서버 오류가 발생했습니다.";
               isemail = false;
            },
         });
      });

   // 다음 우편번호 서비스 사용
   $(".postcode_btn").click(function() {
      new daum.Postcode({
         oncomplete: function(data) {
            $("#input_postCode").val(data.zonecode);
            $("#input_address1").val(data.jibunAddress);
            $("#input_address2").val("");
         },
      }).open();
   });

   // 현재 URL에 따라 활성화할 메뉴 항목 설정
   const currentUrl = window.location.href;
   if (currentUrl.includes("findPassword.html")) {
      $("#find_password").addClass("active");
   } else if (currentUrl.includes("findAccount.html")) {
      $("#find_id").addClass("active");
   }

   // 비밀번호 찾기 또는 계정 찾기 페이지 설정
   if (currentUrl.includes("findPassword.html")) {
      $("#find_password").prop("checked", true);
   } else if (currentUrl.includes("findAccount.html")) {
      $("#find_id").prop("checked", true);
   }

   // 갤럭시 폰인지 확인하고 클래스 추가
   function isGalaxyPhone() {
      const userAgent = navigator.userAgent.toLowerCase();
      return /samsung|galaxy|sm-|gt-/.test(userAgent);
   }

   if (isGalaxyPhone()) {
      document.documentElement.classList.add("galaxy_phone");
   }
});

function findId() {
   let find_input_reg = $("input[name='find_input_reg']").val();
   let find_input_email = $("input[name='find_input_email']").val();
   let find_input_phone = $("input[name='find_input_phone']").val();

   $.ajax({
      url: "/findAccount.member",
      type: "POST",
      data: {
         find_input_reg: find_input_reg,
         find_input_email: find_input_email,
         find_input_phone: find_input_phone,
      },
      dataType: "json",
      success: function(response) {
         if (response.userId !== "") {
            alert("찾은 아이디는 " + response.userId + " 입니다.");
            location.href = "/login/login.jsp"; // 성공 시 페이지 이동
         } else {
            alert("입력하신 정보로 등록된 계정을 찾을 수 없습니다.");
            // 페이지 이동 없음
         }
      },
      error: function(xhr, status, error) {
         console.error("아이디 찾기 중 오류 발생:", error);
         alert("서버에 오류가 발생했습니다");
         // 페이지 이동 없음
      }
   });
}

$("#find_email_form").on("click", function() {
   const find_input_reg = $("input[name='find_input_reg']").val();
   const find_input_email = $("input[name='find_input_email']").val();
   const find_input_id = $("input[name='find_input_id']").val();

   $.ajax({
      url: "/findPassword.member",
      type: "POST",
      data: {
         find_input_reg: find_input_reg,
         find_input_email: find_input_email,
         find_input_id: find_input_id,
      },
      dataType: "json",
      success: function(response) {
         alert(response.message);
         location.href = "/login/login.jsp"; // 성공 시 페이지 이동
      },
      error: function(xhr, status, error) {
         console.error("아이디 찾기 중 오류 발생:", error);
         alert("서버에 오류가 발생했습니다");
         // 페이지 이동 없음
      },
   });
});



// 서버로부터 JSON 데이터 받기
function getKakaoUserInfo() {
   $.ajax({
      url: '/kakaoauth.member', // 서버 측 URL
      type: 'GET',
      success: function (response) {
         // 성공적으로 JSON 데이터를 받았을 때 처리
         var nickname = response.nickname;
         var email = response.email;
         var gender = response.gender;
         var ageRange = response.age_range;
         var birthday = response.birthday;

         console.log("닉네임: " + nickname);
         console.log("이메일: " + email);
         console.log("성별: " + gender);
         console.log("연령대: " + ageRange);
         console.log("생일: " + birthday);

         // 원하는 방식으로 받은 데이터 활용
      },
      error: function (xhr, status, error) {
         // 에러 처리
         console.error("에러 발생: " + error);
      }
   });
}
   $("#login_form_btn").submit(function(event) {
      event.preventDefault(); // 폼 기본 제출 동작 방지

      var userid = $("#userid").val();
      var pw = $("#pw").val();

      $.ajax({
         url: "/login.member",
         type: "POST",
         data: {
            userid: userid,
            pw: pw
         },
         dataType: "json", // 서버에서 받을 데이터 타입 지정
         success: function(response) {
            if (response.success) {
               // 로그인 성공 시 리다이렉트
               location.href = "/user/main.jsp";
            } else {
               // 로그인 실패 시 메시지 출력
               alert("아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            }
         },
         error: function(xhr, status, error) {
            console.error("AJAX 요청 중 오류 발생:", error);
            alert("서버에 오류가 발생했습니다");
         }
      });
   });

// 카카오 로그인 버튼 클릭 시
$("button.kakao_Login").click(function(){
   // 카카오 로그인 페이지로 리다이렉트
   location.href = "https://kauth.kakao.com/oauth/authorize?client_id=e8b67c7e40531e53edced251340dbb25&redirect_uri=http://localhost/kakaoauth.member&response_type=code";
});