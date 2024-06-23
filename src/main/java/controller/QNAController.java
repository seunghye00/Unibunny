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
import dao.QNADAO;
import dto.BoardDTO;
import dto.QNADTO;


@WebServlet("*.qna")
public class QNAController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		// System.out.println(cmd);
		Pagination pagination = new Pagination();
		
		try {
			
			if(cmd.equals("/aaa.qna")) {
				
				
			}else if(cmd.equals("/myqna.qna")) {
//				회원이 작성한 QNA를 마이페이지에서 조회
				
				String id = (String)request.getSession().getAttribute("loginID");
				System.out.println("진입");
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				System.out.println("회원의 북마크 조회");
				
				List<QNADTO> list = QNADAO.getInstance().searchMyQNAList(cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage -1),
						cpage * pagination.recordCountPerPage,id);
				System.out.println("북마크 게시글 조회 완료");
				
				request.setAttribute("myqna", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
				request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
				request.setAttribute("record_total_count", QNADAO.getInstance().getRecordCount());	
				request.setAttribute("activeTab", "questions");
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request, response);
				
				
				
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
