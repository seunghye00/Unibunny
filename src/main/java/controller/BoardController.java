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
		System.out.println("진입");
		System.out.println(cmd);
		BoardDAO dao = BoardDAO.getInstance();
		Pagination pagination = new Pagination();
		try {
			if(cmd.equals("/list.board")) {
				System.out.println("진입");
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				List<BoardDTO> list = dao.selectListAll(cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage -1),
						cpage * pagination.recordCountPerPage);
				request.setAttribute("boardlist", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
				request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
				request.setAttribute("record_total_count", dao.getRecordCount());
				request.getRequestDispatcher("/user/crud/list.jsp").forward(request, response);
			}  else if (cmd.equals("/mylist.board")) {
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				
				List<BoardDTO> list = dao.selectListAll(cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage -1),
						cpage * pagination.recordCountPerPage);
				request.setAttribute("boardlist", list);
			}
//				else if (cmd.equals("/myboard.board")) {
			
//				System.out.println("회원의 게시글 조회");
//				String id = (String)request.getSession().getAttribute("loginID");
//				
//				List<BoardDTO> list = dao.searchMyBoardList(id);
//				System.out.println("게시글 조회 완료");
//				request.setAttribute("mylist", list);
//				request.setAttribute("activeTab", "myPosts");
//				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request, response);
				
//			}
				else if (cmd.equals("/myboard.board")) {
					
					String id = (String)request.getSession().getAttribute("loginID");
					System.out.println("진입");
					String pcpage = request.getParameter("cpage");
					if( pcpage == null) {
						pcpage = "1";
					}
					int cpage = Integer.parseInt(pcpage);
					System.out.println("회원의 게시글 조회");
					
					List<BoardDTO> list = dao.searchMyBoardList(cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage -1),
							cpage * pagination.recordCountPerPage,id);
					System.out.println("게시글 조회 완료");
					
					request.setAttribute("mylist", list);
					request.setAttribute("cpage", cpage);
					request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
					request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
					request.setAttribute("record_total_count", dao.getRecordCount());	
					request.setAttribute("activeTab", "myPosts");
					request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request, response);
					
					
					
					
					
					
					
					
					
					
					
					
				
				
			
				
				
				
				
				
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
