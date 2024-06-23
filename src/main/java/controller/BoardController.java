package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import commons.Pagination;
import dao.BoardDAO;
import dao.BookMarkDAO;
import dao.MemberDAO;
import dto.BoardDTO;
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		// 클라이언트로부터 전송되는 문자열에 대한 인코딩을 utf8로 처리
		//request에서 값을 꺼내기 전에 처리해야만 함!
		String cmd = request.getRequestURI();
		System.out.println("진입");
		System.out.println(cmd);
		BoardDAO dao = BoardDAO.getInstance();
		try {
			if(cmd.equals("/list.board")) {
				System.out.println("진입");
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				List<BoardDTO> list = dao.selectListAll(cpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage -1),
						cpage * Pagination.recordCountPerPage);
				request.setAttribute("boardlist", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("record_count_per_page", Pagination.recordCountPerPage);
				request.setAttribute("navi_count_per_page", Pagination.naviCountPerPage);
				request.setAttribute("record_total_count", dao.getRecordCount());
				request.getRequestDispatcher("/user/crud/list.jsp").forward(request, response);
			}  else if (cmd.equals("/mylist.board")) {
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				
				List<BoardDTO> list = dao.selectListAll(cpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage -1),
						cpage * Pagination.recordCountPerPage);
				request.setAttribute("boardlist", list);
				
			} else if (cmd.equals("/user/detail.board")) {
				// 게시글 상세 페이지
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				request.setAttribute("dto", dao.selectBySeq(board_seq));
				String loginID = (String)request.getSession().getAttribute("loginID");
				// 해당 게시글의 북마크 수
				request.setAttribute("bookmark", BookMarkDAO.getInstance().selectByBoardSeq(board_seq));
				request.setAttribute("nickname", MemberDAO.getInstance().getNickname(loginID));
				request.getRequestDispatcher("/user/crud/detail.jsp").forward(request, response);
			} else if(cmd.equals("/like.board")) {
				// 게시글 좋아요
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				dao.boardLike(board_seq);
				Gson g = new Gson();
				response.getWriter().append(g.toJson(dao.selectBySeq(board_seq).getThumbs_up()));
			} else if(cmd.equals("/unlike.board")) {
				// 게시글 좋아요 취소
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				dao.boardUnLike(board_seq);
				Gson g = new Gson();
				response.getWriter().append(g.toJson(dao.selectBySeq(board_seq).getThumbs_up()));
			} else if(cmd.equals("/tryUpdate.board")) {
				// 게시글 수정 페이지로 이동
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				request.setAttribute("dto", dao.selectBySeq(board_seq));
				request.getRequestDispatcher("/user/crud/modi_board.jsp").forward(request, response);
			} else if(cmd.equals("/update.board")) {
				// 게시글 수정
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				System.out.println(request.getParameter("edit_content"));
				dao.update(board_seq, request.getParameter("edit_title"), request.getParameter("edit_content"));
				response.sendRedirect("/user/detail.board?board_seq=" + board_seq);
			} else if(cmd.equals("/delete.board")) {
				// 게시글 삭제
				int board_seq = Integer.parseInt(request.getParameter("board_seq"));
				dao.deleteBySeq(board_seq);
				response.sendRedirect("/list.board");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		} 
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
