package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import commons.Pagination;
import dao.MemberDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.ReplyDTO;


@WebServlet("*.reply")
public class ReplyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
		
		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		
		// ReplyDAO 인스턴스 생성
		ReplyDAO dao = ReplyDAO.getInstance();

		try {
			if(cmd.equals("/list.reply")) {
				// 게시글 상세 페이지에서 해당 게시글의 댓글 목록 조회
				int board_seq = Integer.parseInt(request.getParameter("board_seq")); 
				String order_by = request.getParameter("order_by");
				String user_id = (String)request.getSession().getAttribute("loginID");
				
				Map<String, Object> result = new HashMap<>();
				result.put("nickname", MemberDAO.getInstance().getNickname(user_id));
				result.put("board_info", dao.selectByBoardSeq(board_seq, order_by));
				// 직렬화한 데이터 전송
				response.getWriter().append(gson.toJson(result));
			
			} else if(cmd.equals("/write.reply")){
				// 댓글 작성
				// 세션에 저장된 로그인 ID 저장
				String user_id = (String)request.getSession().getAttribute("loginID");
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				String nickname = MemberDAO.getInstance().getNickname(user_id);
				dao.insert(new ReplyDTO(0, nickname, request.getParameter("content"), null, board_seq, "N"));
			} else if(cmd.equals("/like.reply")) {
				// 댓글 좋아요
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
				dao.replyLike(reply_seq);
				response.getWriter().append(g.toJson(dao.selectLikesBySeq(reply_seq)));
			
			} else if(cmd.equals("/unlike.reply")) {
				// 댓글 좋아요 취소
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
				dao.replyUnLike(reply_seq);
				response.getWriter().append(g.toJson(dao.selectLikesBySeq(reply_seq)));
			
			} else if(cmd.equals("/update.reply")) {
				// 댓글 수정
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
				String update_content = request.getParameter("content");
				dao.updateContent(reply_seq, update_content);
			
			} else if(cmd.equals("/delete.reply")) {
				// 댓글 삭제
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
				dao.deleteBySeq(reply_seq);
			
			} else if (cmd.equals("/manager/total.reply")) {
				// 관리자 페이지에서 삭제된 전체 댓글의 수를 구하는 경로

				Map<String, Object> result = new HashMap<>();
				result.put("total_data", dao.getDeletedReplyCount());
				result.put("record_count_per_page", Pagination.recordCountPerPage);
				result.put("navi_count_per_page", Pagination.naviCountPerPage);
				response.getWriter().append(g.toJson(result));

			} else if (cmd.equals("/manager/list.reply")) {
				// 관리자 페이지에서 해당 페이지 내에 삭제된 전체 댓글의 수를 구하는 경로
				String cpage = request.getParameter("cpage");

				if (cpage == null) {
					cpage = "1";
				}
				int pcpage = Integer.parseInt(cpage);
				int start_num = pcpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage - 1);
				int end_num = pcpage * Pagination.recordCountPerPage;
				
				response.getWriter().append(gson.toJson(dao.searchDeletedReply(start_num, end_num)));

			} else if(cmd.equals("/deleteYN_N_To_Y.reply")) {
				
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
				
				ReplyDAO.getInstance().updateToY(reply_seq);
				
			} else if(cmd.equals("/deleteYN_Y_To_N.reply")) {
				
				int reply_seq = Integer.parseInt(request.getParameter("replySeq"));
				
				ReplyDAO.getInstance().updateToN(reply_seq);
				response.sendRedirect("/deletedreply.reply");
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
