package controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import commons.EncryptionUitls;
import commons.MailSender;
import commons.Pagination;
import commons.PasswordGenerator;
import dao.BoardDAO;
import dao.Duptype;
import dao.MemberDAO;
import dao.ReplyDAO;
import dto.MemberDTO;

//import dao.MemberDAO;
//import dto.MemberDTO;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	public static MemberDAO mdao;

    //HttpSession session으로 세션 오브젝트 생성
    private static HttpSession session;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // !!!! 중요. REQUEST로 세션값을 받아오지 않으면 사용할 수 없음. (NULL 반환)
        session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI();
		//mdao = MemberDAO.getInstance();
		
		//MemberDTO mdto = new MemberDTO();
		Gson g = new Gson();
		JsonObject js = new JsonObject();

		System.out.println(cmd);
		
		PrintWriter print_writer = response.getWriter();
		
		try {
            //회원가입
            if (cmd.equals("/signup.member")) {
                String userid = request.getParameter("userid");
                String nickname = request.getParameter("nickname");
                String pw = request.getParameter("pw");
                String pwsha512 = EncryptionUitls.getSHA512(pw);
                String phone = request.getParameter("phone");
                String reg_num_first = request.getParameter("reg_num_first");
                String reg_num_second = request.getParameter("reg_num_second");
                String email = request.getParameter("email");
                String postcode = request.getParameter("postcode");
                String address1 = request.getParameter("address1");
                String address2 = request.getParameter("address2");

                String reg_num = reg_num_first + "-" + reg_num_second + "******";

                try {
                    int result = mdao.insert(new MemberDTO(userid, nickname, pwsha512, phone, reg_num, email, postcode,
                            address1, address2, null, 0, ""));
                    if (result > 0) {
                        response.sendRedirect("/login/login.jsp");
                    } else {

                        response.sendRedirect("/error.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/error.jsp");
                }
            }
//			로그인 
            else if (cmd.equals("/login.member")) {
                String userid = request.getParameter("userid");
                String pw = EncryptionUitls.getSHA512(request.getParameter("pw"));
                String ipAddress = request.getRemoteAddr();
                System.out.println("Client IP Address: " + ipAddress); // IP 주소 출력
                
                boolean result = mdao.login(userid, pw);
                System.out.println("Login Result : " + result);

                response.setContentType("text/html; charset=UTF-8"); // 응답 형식 설정

                if (result) {
                    // 로그인 성공 시
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loginID", userid);
                    Map<String, String> map = mdao.getAccount(userid);

                    session.setAttribute("nickName", map.get("nickname"));
                    session.setAttribute("profileImg", map.get("profile_img"));
                    session.setAttribute("memcode", map.get("memcode"));

                    if ("0".equals(map.get("memcode"))) {
                        response.sendRedirect("user/main.jsp");
                    } else {
                        response.sendRedirect("manager/main.jsp");
                    }
                } else {
                    // 로그인 실패 시
                    response.getWriter().write("<script>alert('로그인 정보를 다시 확인하세요'); location.href='login/login.jsp'</script>");
                }



//				회원가입 ajax 정규표현식 코드
            } else if (cmd.equals("/check.member")) {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter pw = response.getWriter();

                String mode = request.getParameter("mode");
                String value = request.getParameter("value");

                boolean isExist = false;
                String json = "";

                try {
                    if (mode.equals("userid"))
                        isExist = mdao.isExist(Duptype.Userid, value);
                    else if (mode.equals("email"))
                        isExist = mdao.isExist(Duptype.Email, value);
                    else if (mode.equals("phone"))
                        isExist = mdao.isExist(Duptype.Phone, value);
                    else if (mode.equals("nickname"))
                        isExist = mdao.isExist(Duptype.Nickname, value);

                    JsonObject jsonResponseTest = new JsonObject();
                    jsonResponseTest.addProperty("exists", isExist);
                    Gson gson = new Gson();
                    json = gson.toJson(jsonResponseTest);

                    pw.println(json);
                } catch (SQLException e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("error", "Database Error: " + e.getMessage());

                    pw.println(json);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("error", "Internal Server Error");

                    pw.println(json);
                } finally {
                    pw.flush();
                    pw.close();
                }
            }
            // 계정찾기
            else if (cmd.equals("/findAccount.member")) {
                String find_input_reg = request.getParameter("find_input_reg");
                String find_input_email = request.getParameter("find_input_email");
                String find_input_phone = request.getParameter("find_input_phone");

                try {
                    String result = mdao.findAccount(find_input_reg, find_input_email, find_input_phone);

                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("userId", result);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonResponse.toString());
                } catch (SQLException e) {
                    e.printStackTrace(); // 예외 로깅
                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("message", "error");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonResponse.toString());
                }
            }
            //비밀번호 재설정
            else if (cmd.equals("/findPassword.member")) {
                PasswordGenerator pwGen = new PasswordGenerator();

                String find_input_reg = request.getParameter("find_input_reg");
                String find_input_email = request.getParameter("find_input_email");
                String find_input_id = request.getParameter("find_input_id");


                String newPassword = pwGen.generateRandomPassword();

                boolean result = mdao.findPassword(find_input_id, newPassword, find_input_email, find_input_reg);

                JSONObject jsonResponse = new JSONObject();

                if (result) {
                    String Title = "[UniBunny] 임시 비밀번호를 알려드립니다.";
                    String Content = "임시 비밀번호는 " + newPassword + "입니다.";
                    MailSender mailSender = new MailSender(find_input_email, Title, Content);

                    boolean sendResult = mailSender.Send();

                    if (sendResult) {
                        //성공
                        jsonResponse.put("message", "임시 비밀번호가 발급되었습니다. 이메일을 확인하세요");
                    } else {
                        //메일 전송 실패
                        jsonResponse.put("message", "메일 전송 시스템에 오류가 발생했습니다. 잠시 후 다시시도 해주세요");
                    }
                } else {
                    //Account Can't Found;
                    jsonResponse.put("message", "해당 계정을 찾을 수 없습니다.");
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse.toString());
            }
            // 카카오 로그인
            else if (cmd.equals("/kakaoauth.member")) {
                String kakaoResponse = request.getParameter("code");
                String error = request.getParameter("error");
                String accessToken = "";

                if (kakaoResponse == null || kakaoResponse.isEmpty()) {
                    if (error != null && error.equals("access_denied")) {
                        response.getWriter().write("사용자 거부");
                        return;
                    }
                }

                HttpURLConnection connection = null;
                String urlTarget = "https://kauth.kakao.com/oauth/token?";
                urlTarget += "grant_type=authorization_code";
                urlTarget += "&client_id=e8b67c7e40531e53edced251340dbb25";
                urlTarget += "&redirect_uri=http://localhost/kakaoauth.member";
                urlTarget += "&code=" + URLEncoder.encode(kakaoResponse, "UTF-8");

                URL url = new URL(urlTarget);

                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    StringBuilder sb = new StringBuilder();
                    String line;

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                    }

                    JSONObject tokenJson = new JSONObject(sb.toString());
                    accessToken = tokenJson.getString("access_token");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

                urlTarget = "https://kapi.kakao.com/v2/user/me";
                url = new URL(urlTarget);

                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("Authorization", "Bearer " + accessToken);

                    StringBuilder sb = new StringBuilder();
                    String line;

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                    }

                    JSONObject userInfoJson = new JSONObject(sb.toString());

                    String email = userInfoJson.getJSONObject("kakao_account").optString("email");
                    String gender = userInfoJson.getJSONObject("kakao_account").optString("gender");
                    String ageRange = userInfoJson.getJSONObject("kakao_account").optString("age_range");
                    String birthday = userInfoJson.getJSONObject("kakao_account").optString("birthday");

                    JSONObject responseJson = new JSONObject();
                    responseJson.put("access_token", accessToken);
                    responseJson.put("userid", userInfoJson.getLong("id"));
                    responseJson.put("nickname", userInfoJson.getJSONObject("kakao_account").getJSONObject("profile").getString("nickname"));
                    responseJson.put("connected_at", userInfoJson.getString("connected_at"));
                    responseJson.put("email", email);
                    responseJson.put("neg_num", gender);
                    responseJson.put("age_range", ageRange);
                    responseJson.put("birthday", birthday);

                    // Send JSON response to client
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responseJson.toString());

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            } else if (cmd.equals("/logout.member")) {
                if(session != null) {
                    session.invalidate();
                }
				response.sendRedirect("/index.jsp");
            } else if(cmd.equals("/mypage.member")) {
//				마이페이지에 진입 시, 회원의 정보를 조회해 줌
//				회원의 프로필 이미지, 가입날짜, 게시물 수, 댓글 단 수 등등
				
				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("회원정보 가져오기완료");
				
				// 가입 날짜 변환
				String formattedJoinDate = MemberDAO.getInstance().getFormattedJoinDate(mdto);
				
				// 게시물 작성 수와 댓글 작성 수 가져오기
				int board_count = BoardDAO.getInstance().searchBoardCount(id);
				int reply_count = ReplyDAO.getInstance().searchReplyCount(id);
//				my_info : 해당 멤버의 칼럼들
				request.setAttribute("my_info", mdto );
//				board_count : 해당 멤버의 게시물 작성 수
				request.setAttribute("board_count", board_count );
				request.setAttribute("reply_count", reply_count );
				request.setAttribute("formattedJoinDate", formattedJoinDate); // 형식화된 가입 날짜
				
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);

            } else if(cmd.equals("/edit.member")) {

//				마이페이지 계정관리에서 회원의 정보를 수정된값으로 갱신함
//				회원 정보 수정

				
				
			}else if(cmd.equals("/edit.member")) {
//				마이페이지 계정관리에서 회원의 정보를 수정된값으로 갱신함
//				회원 정보 수정
				
				String id = (String)request.getSession().getAttribute("loginID"); //변조의 가능성이 있기때문에, 세션에서 받아와야한다.
				String pw = request.getParameter("pw");
				String nickname = request.getParameter("nickname");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String postcode = request.getParameter("postcode");
				

				int result = MemberDAO.getInstance().updateUserInfo(new MemberDTO(id, nickname, pw, phone, null, email, postcode, address1, address2, null, 1,null));

				response.sendRedirect("/mypage.member");
				
				}else if(cmd.equals("/account.member")) {
				//마이페이지 계정관리에서 회원의 정보를 뽑아옴

				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("user1의 회원정보 가져오기완료");
				
				request.setAttribute("my_info", mdto );
//				
				request.setAttribute("activeTab", "myAccount");
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);

			
			}else if(cmd.equals("/memberout.member")) {
//				로그인한 유저의 세션을 날리고, 회원의 정보를 삭제한다
//				마이 페이지 계정관리 -> 회원 탈퇴 기능
		
				HttpSession session = request.getSession();
				
				
				String id = (String) session.getAttribute("loginID");
				System.out.println((String) session.getAttribute("loginID"));
				mdao.deleteMember(id);
//				session.invalidate(); //무효화 -> 해당하는 명령쓰면 세션 다 날라감
				
				System.out.println("회원탈퇴 성공");
				
				response.sendRedirect("/index.jsp");
			}else if(cmd.equals("/logout.member")){
//				로그인한 유저의 세션을 날림
//				로그아웃 기능
				
				HttpSession session = request.getSession();
				session.invalidate(); //무효화 -> 해당하는 명령쓰면 세션 다 날라감

				response.sendRedirect("/index.jsp");
			
			}else if(cmd.equals("/account.member")) {
//				마이페이지 계정관리에서 회원의 정보를 뽑아옴
				
				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("user1의 회원정보 가져오기완료");
				
				request.setAttribute("my_info", mdto );
//				
				request.setAttribute("activeTab", "myAccount");
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);
			
			}else if(cmd.equals("/memberout.member")) {
//				로그인한 유저의 세션을 날리고, 회원의 정보를 삭제한다
//				마이 페이지 계정관리 -> 회원 탈퇴 기능
		
				HttpSession session = request.getSession();
				
				
				String id = (String) session.getAttribute("loginID");
				System.out.println((String) session.getAttribute("loginID"));
				mdao.deleteMember(id);
//				session.invalidate(); //무효화 -> 해당하는 명령쓰면 세션 다 날라감
				
				
				
				System.out.println("회원탈퇴 성공");
				
				response.sendRedirect("/index.jsp");
			}else if(cmd.equals("/uploadProfile.member")) {
//				회원이 선택한 프로필 사진을 서버로 업로드 함
//				마이페이지 프로필 편집 이후 적용이 될때 실행
//				서버에 프로필 사진을 업로드하고, DB의 회원 테이블에 프로필 사진 정보를 저장함
				
				 // 업로드된 파일이 저장될 실제 서버 경로를 가져옴
			    String realPath = getServletContext().getRealPath("/image/mypage_image");
			    
			    // 경로가 유효한지 확인, 유효하지 않다면 예외 발생
			    if (realPath == null) {
			        throw new ServletException("경로가 유효하지 않습니다.");
			    }

			    // 업로드 파일의 최대 크기를 10MB로 제한
			    int maxSize = 10 * 1024 * 1024; // 10MB 제한
			    
			    // 업로드 경로가 존재하지 않으면 디렉토리를 생성
			    File uploadPath = new File(realPath);
			    if (!uploadPath.exists()) {
			        uploadPath.mkdirs(); // 디렉토리 생성
			    }

			    // MultipartRequest 객체를 생성하여 파일 업로드 처리 
			    // - realPath: 업로드된 파일이 저장될 경로
			    // - DefaultFileRenamePolicy: 동일한 이름의 파일이 존재할 경우 파일명을 변경하는 정책
			    MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			    
			    // 세션에서 로그인된 사용자의 ID를 가져옴
			    String userid = (String) request.getSession().getAttribute("loginID");
			    
			    // 업로드된 파일의 원본 이름과 서버에 저장된 이름을 가져옴
			    String oriName = multi.getOriginalFileName("profileImage"); // 원본 이름
			    String sysName = multi.getFilesystemName("profileImage"); // 서버에 저장된 이름

			    // 파일이 정상적으로 업로드된 경우 (원본 이름이 null이 아닌 경우)
			    if (oriName != null) {
			        // DB에 저장된 회원 테이블의 프로필 이미지 정보를 업데이트
			        MemberDAO.getInstance().updateProfileImage(userid, sysName);
			    }
			    
			    // 파일 업로드 완료 후 마이페이지로 리디렉션
			    response.sendRedirect("/mypage.member");
			
			} else if(cmd.equals("/list.member")) {
				// 관리자 페이지에서 해당 등급의 회원 목록 전체를 조회 하기
				String grade = request.getParameter("grade");
				String cpage = request.getParameter("cpage");
				
				if (cpage == null) {
					cpage = "1";
				}
				int pcpage = Integer.parseInt(cpage);
				int start_num = pcpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage - 1);
				int end_num = pcpage * Pagination.recordCountPerPage;	

				Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
				
				print_writer.append(gson.toJson(mdao.selectNtoM(start_num, end_num, grade)));
				
			} else if (cmd.equals("/total.member")) {
				// 관리자 페이지에서 해당 등급의 회원 데이터 총 갯수를 얻기 위한 경로
				String grade = request.getParameter("grade");
				Map<String, Object> result = new HashMap<>();
                
				result.put("total_data", mdao.selectAll(grade));
                result.put("record_count_per_page", Pagination.recordCountPerPage);
                result.put("navi_count_per_page", Pagination.naviCountPerPage);
				
                print_writer.append(g.toJson(result));
				
			} else if (cmd.equals("/changeGrade.member")) {
				// 관리자 페이지에서 해당 회원의 등급을 블랙리스트로 바꾸기 위한 경로
				String user_id = request.getParameter("user_id");
				String grade = request.getParameter("grade");
				
				print_writer.append(g.toJson(mdao.toChageGrade(grade, user_id)));
			
			} else if (cmd.equals("/getSearchNum.member")) {
				// 관리자 페이지에서 회원을 검색한 결과의 데이터 총 갯수를 얻기 위한 경로
				String grade = request.getParameter("grade");
				String user_info = request.getParameter("user_info");
				Map<String, Object> result = new HashMap<>();
                
				result.put("total_data", mdao.getNumBySearchMem(grade, user_info));
                result.put("record_count_per_page", Pagination.recordCountPerPage);
                result.put("navi_count_per_page", Pagination.naviCountPerPage);
				
                print_writer.append(g.toJson(result));
                
			} else if (cmd.equals("/trySearch.member")) {
				// 관리자 페이지에서 회원을 검색한 결과 전체를 얻기 위한 경로
				String grade = request.getParameter("grade");
				String user_info = request.getParameter("user_info");
				String cpage = request.getParameter("cpage");
				
				if (cpage == null) {
					cpage = "1";
				}
				int pcpage = Integer.parseInt(cpage);
				int start_num = pcpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage - 1);
				int end_num = pcpage * Pagination.recordCountPerPage;	

				Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
				
				print_writer.append(gson.toJson(mdao.searchMemAndSelectNtoM(start_num, end_num, grade, user_info)));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
		
		print_writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
