package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import commons.EncryptionUitls;
import commons.MailSender;
import commons.PasswordGenerator;
import dao.Duptype;
import dao.MemberDAO;
import dto.MemberDTO;

//import dao.MemberDAO;
//import dto.MemberDTO;

@WebServlet("*.member")
public class MemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI();
		MemberDAO mdao = MemberDAO.getInstance();
		Gson g = new Gson();
		JsonObject js = new JsonObject();

		System.out.println(cmd);
		try {
//			회원가입
			if(cmd.equals("/signup.member")) {
				String userid = request.getParameter("userid");
				String nickname = request.getParameter("nickname");
				String pw = EncryptionUitls.getSHA512(request.getParameter("pw"));
				String phone = request.getParameter("phone");
				String reg_num_first = request.getParameter("reg_num_first");
				String reg_num_second = request.getParameter("reg_num_second");
				String email = request.getParameter("email");
				String postcode = request.getParameter("postcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				String reg_num = reg_num_first + "-" + reg_num_second + "******";

				try {
					int result = mdao.insert(new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode,
							address1, address2, null, 0, ""));
					if (result > 0) {
						response.sendRedirect("/index.jsp");
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
			    String pw = request.getParameter("pw");

			    String ipAddress = request.getRemoteAddr();
			    System.out.println("Client IP Address: " + ipAddress); // IP 주소 출력

			    boolean result = mdao.login(userid, pw);
			    System.out.println("Login Result : " + result);

			    if (result) {
			        HttpSession session = request.getSession();
			        session.setAttribute("loginID", userid);
			        System.out.println(userid);
			        response.sendRedirect("/index.jsp"); // 로그인 성공 시 리다이렉트
			    } else {
			        response.sendRedirect("/login/login.jsp?error=invalid"); // 로그인 실패 시 리다이렉트
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
			else if(cmd.equals("/findPassword.member")) {
				PasswordGenerator pwGen = new PasswordGenerator();

				String find_input_reg = request.getParameter("find_input_reg");
			    String find_input_email = request.getParameter("find_input_email");
			    String find_input_id = request.getParameter("find_input_id");
			    
			    
			    String newPassword = pwGen.generateRandomPassword(); 
			    
			    boolean result = mdao.findPassword(find_input_id, newPassword, find_input_email, find_input_reg);
			    
			    JSONObject jsonResponse = new JSONObject();
		        		        
			    if(result) {
			    	String Title = "[UniBunny] 임시 비밀번호를 알려드립니다.";
			    	String Content = "임시 비밀번호는 " + newPassword + "입니다.";
			    	MailSender mailSender = new MailSender(find_input_email, Title, Content);
			    	
			    	boolean sendResult = mailSender.Send();
			    	
			    	if(sendResult) {
			    		//성공
			    		jsonResponse.put("message", "임시 비밀번호가 발급되었습니다. 이메일을 확인하세요");
			    	}
			    	else {
			    		//메일 전송 실패
			    		jsonResponse.put("message", "메일 전송 시스템에 오류가 발생했습니다. 잠시 후 다시시도 해주세요");
			    	}
			    }
			    else {
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
			
			}}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
