package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BoardLikeDAO;

@WebServlet("*.boardLike")
public class BoardLikeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println(cmd);

		// BoardLikeDAO 인스턴스 생성
		BoardLikeDAO dao = BoardLikeDAO.getInstance();
		PrintWriter pw = response.getWriter();
		
		// 모든 경로에서 사용할 게시글의 seq 값 저장 
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		String user_id = (String)request.getSession().getAttribute("loginID");
		
		try {
			if(cmd.equals("/count.boardLike")) {
				// 해당 게시글의 좋아요 수
				pw.append(g.toJson(dao.countByBoardSeq(board_seq)));
				
			} else if(cmd.equals("/insert.boardLike")) {
				// 해당 게시글의 좋아요 기능
				// 임시 데이터 (수정할 부분)
				user_id = "user001";
				dao.insertRecord(board_seq, user_id);
				
			} else if(cmd.equals("/delete.boardLike")) {
				// 해당 게시글의 좋아요 취소 기능
				// 임시 데이터 (수정할 부분)
				user_id = "user001";
				dao.deleteRecord(board_seq, user_id);
				
			} else if(cmd.equals("/check.boardLike")) {
				// 로그인한 ID로 해당 게시글의 북마크 여부 조회
				// 임시 데이터 추후 삭제 !!!!
				user_id = "user001";
				System.out.println((dao.checkLog(board_seq, user_id)));
				pw.append(g.toJson(dao.checkLog(board_seq, user_id)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
