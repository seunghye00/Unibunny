package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ReplyLikeDAO;

@WebServlet("*.replyLike")
public class ReplyLikeController extends HttpServlet {

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

		// ReplyLikeDAO 인스턴스 생성
		ReplyLikeDAO dao = ReplyLikeDAO.getInstance();
		PrintWriter pw = response.getWriter();

		// 모든 경로에서 사용할 댓글의 seq 값 저장
		int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
		String user_id = (String) request.getSession().getAttribute("loginID");

		try {

			if (cmd.equals("/count.replyLike")) {
				// 해당 댓글의 좋아요 수
				int likes_count = dao.countByReplySeq(reply_seq);
				pw.append(g.toJson(likes_count));

			} else if (cmd.equals("/insert.replyLike")) {
				// 해당 댓글의 좋아요 기능
				dao.insertRecord(reply_seq, user_id);

			} else if (cmd.equals("/delete.replyLike")) {
				// 해당 댓글의 좋아요 취소 기능
				dao.deleteRecord(reply_seq, user_id);

			} else if (cmd.equals("/check.replyLike")) {
				// 로그인한 ID로 해당 게시글의 북마크 여부 조회
				pw.append(g.toJson(dao.checkLog(reply_seq, user_id)));
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
