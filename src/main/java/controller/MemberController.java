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

import dao.BoardDAO;
import dao.Duptype;
import dao.MemberDAO;
import dto.BoardDTO;
import dto.MemberDTO;

//import dao.MemberDAO;
//import dto.MemberDTO;

@WebServlet("*.member")
public class MemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI();
		// 멤버 DAO 		
		MemberDAO mdao = MemberDAO.getInstance();
		
		
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
					int result = mdao.insert(new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode,
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
				boolean result = mdao.login(userid, pw);

				if (result) {

					HttpSession session = request.getSession();
					session.setAttribute("loginID", userid);
					System.out.println(userid);
				}
				response.sendRedirect("/index.jsp");

			}else if(cmd.equals("/mypage.member")) {
				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("user1의 회원정보 가져오기완료");
				int board_count = BoardDAO.getInstance().searchBoardCount(id);
//				my_info : 해당 멤버의 칼럼들
				request.setAttribute("my_info", mdto );
//				board_count : 해당 멤버의 게시물 작성 수
				request.setAttribute("board_count", board_count );
				
				
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);
				
				
			}
			
			
			}
		catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
