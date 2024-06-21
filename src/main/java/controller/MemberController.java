package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		MemberDAO dao = MemberDAO.getInstance();
		Gson g = new Gson();
		JsonObject js = new JsonObject();
		try {
			if (cmd.equals("/signup.member")) {
				String userid = request.getParameter("userid");
				String nickname = request.getParameter("nickname");
				String pw = request.getParameter("pw");
				String phone = request.getParameter("phone");
				String reg_num_first = request.getParameter("reg_num_fisrt");
				String reg_num_second = request.getParameter("reg_num_second");
				String email = request.getParameter("email");
				String postcode = request.getParameter("postcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				String reg_num = reg_num_first + "-" + reg_num_second + "******";

				try {
					int result = dao.insert(new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode,
							address1, address2, null, 0));
					if (result > 0) {
						response.sendRedirect("/index.jsp");
					} else {
						response.sendRedirect("/error.jsp");
					}
				} catch (Exception e) {
					// Log the exception for debugging
					e.printStackTrace();
					response.sendRedirect("/error.jsp");
				}
			}

			else if (cmd.equals("/login.member")) {
				String userid = request.getParameter("userid");
				String pw = request.getParameter("pw");

				String ipAddress = request.getRemoteAddr();

				System.out.println("Client IP Address: " + ipAddress); // IP 주소 출력
				boolean result = dao.login(userid, pw);

				if (result) {

					HttpSession session = request.getSession();
					session.setAttribute("loginID", userid);
					System.out.println(userid);
				}
				response.sendRedirect("/index.jsp");

			} else if (cmd.equals("/check.member")) {
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter pw = response.getWriter();

				String userid = request.getParameter("userid");
				String nickname = request.getParameter("nickname");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");

				// 개인적으로 추가한 부분
				String mode = request.getParameter("mode");
				String value = request.getParameter("value");

				boolean isExist = false;
				if (mode.equals("userid"))
					isExist = dao.isExist(Duptype.Userid, value);
				else if (mode.equals("email"))
					isExist = dao.isExist(Duptype.Email, value);
				else if (mode.equals("phone"))
					isExist = dao.isExist(Duptype.Phone, value);
				else if (mode.equals("nickname"))
					isExist = dao.isExist(Duptype.Nickname, value);

				// JSON 객체 생성
				JsonObject jsonResponseTest = new JsonObject();
				jsonResponseTest.addProperty("exists", isExist);
				Gson gson = new Gson();
				String json = gson.toJson(jsonResponseTest);

				pw.println(json);

				try {
					boolean idExists = dao.isExist(Duptype.Userid, userid);
					boolean emailExists = dao.isExist(Duptype.Email, email);
					boolean phoneExists = dao.isExist(Duptype.Phone, phone);
					boolean nicknameExists = dao.isExist(Duptype.Nickname, nickname);

					// JSON 객체 생성
					js.addProperty("useridExists", idExists);
					js.addProperty("emailExists", emailExists);
					js.addProperty("phoneExists", phoneExists);
					js.addProperty("nicknameExists", nicknameExists);

					// Gson 객체를 사용하여 JSON 문자열로 변환

					pw.println(json);
				} catch (SQLException e) {
					// 데이터베이스 관련 예외 처리
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					JsonObject errorResponse = new JsonObject();
					errorResponse.addProperty("error", "Database Error: " + e.getMessage());


					pw.println(json);
				} catch (Exception e) {
					// 기타 예외 처리
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					JsonObject errorResponse = new JsonObject();
					errorResponse.addProperty("error", "Internal Server Error");

					// Gson 객체를 사용하여 JSON 문자열로 변환

					pw.println(json);
				} finally {
					// PrintWriter 닫기
					pw.flush();
					pw.close();
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
