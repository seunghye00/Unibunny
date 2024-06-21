package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;
import commons.Pagination;

import java.util.List;
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		// 클라이언트로부터 전송되는 문자열에 대한 인코딩을 utf8로 처리
		//request에서 값을 꺼내기 전에 처리해야만 함!
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		BoardDAO dao = BoardDAO.getInstance();
		Pagination BoardConfig = new Pagination();
		try {
			if(cmd.equals("/list.board")) {
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				
				List<BoardDTO> list = dao.selectBoardList(cpage * BoardConfig.recordCountPerPage - (BoardConfig.recordCountPerPage -1),
						cpage * BoardConfig.recordCountPerPage);
				request.setAttribute("boardlist", list);
			} else if (cmd.equals("/list.board")){
				
			} else if (cmd.equals("/mylist.board")) {
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				
				List<BoardDTO> list = dao.selectBoardList(cpage * BoardConfig.recordCountPerPage - (BoardConfig.recordCountPerPage -1),
						cpage * BoardConfig.recordCountPerPage);
				request.setAttribute("boardlist", list);
				
				
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
