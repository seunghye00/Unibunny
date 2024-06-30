package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookMarkDAO;
import dao.ScoreDAO;
import dto.BookMarkDTO;

@WebServlet("*.bookmark")
public class BookMarkController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		
		// reponse writer 변수 저장
		PrintWriter pw = response.getWriter();
		BookMarkDAO dao = BookMarkDAO.getInstance();
		
		try {
			if(cmd.equals("/save.bookmark")) {
				String user_id = (String)request.getSession().getAttribute("loginID");
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				int result = dao.saveBookMark(user_id, board_seq);
				pw.append(g.toJson(result));
			} else if(cmd.equals("/unsave.bookmark")) {
				String user_id = (String)request.getSession().getAttribute("loginID");
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				int result = dao.unsaveBookMark(user_id, board_seq);
				pw.append(g.toJson(result));
			} else if(cmd.equals("/count.bookmark")) {
				// 해당 게시글의 북마크 수
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				pw.append(g.toJson(dao.selectByBoardSeq(board_seq)));
			} else if(cmd.equals("/check.bookmark")) {
				// 로그인한 ID로 해당 게시글의 북마크 여부 조회
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				String user_id = (String)request.getSession().getAttribute("loginID");
				pw.append(g.toJson(dao.checkLog(board_seq, user_id)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
