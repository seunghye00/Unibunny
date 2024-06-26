package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import dao.BoardDAO;
import dao.NoticeDAO;
import dto.BoardDTO;
import dto.NoticeDTO;


@WebServlet("*.notice")
public class NoticeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 클라이언트로부터 전송되는 문자열에 대한 인코딩을 utf8로 처리
		// request에서 값을 꺼내기 전에 처리해야만 함!
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		NoticeDAO dao = NoticeDAO.getInstance();
		Pagination pagination = new Pagination();
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
		// reponse writer 변수 저장
		PrintWriter pw = response.getWriter();
		try {
			if (cmd.equals("/list.notice")) {
			    String pcpage = request.getParameter("cpage");
			    if (pcpage == null) {
			        pcpage = "1";
			    }
			    int cpage = Integer.parseInt(pcpage);

			    List<NoticeDTO> list = dao.selectListAll(
			        cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage - 1),
			        cpage * pagination.recordCountPerPage
			    );

			    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			        // AJAX 요청인 경우 JSON으로 응답
			        response.setContentType("application/json");
			        response.setCharacterEncoding("UTF-8");

			        Map<String, Object> result = new HashMap<>();
			        result.put("data", list);
			        result.put("cpage", cpage);
			        result.put("record_count_per_page", pagination.recordCountPerPage);
			        result.put("navi_count_per_page", pagination.naviCountPerPage);
			        result.put("record_total_count", dao.getRecordCount());

			        String jsonResult = gson.toJson(result);
			        PrintWriter out = response.getWriter();
			        out.print(jsonResult);
			        out.flush();
			        out.close();
			    } else {
			        // 일반 요청인 경우 JSP로 포워딩
			        request.setAttribute("boardlist", list);
			        request.setAttribute("cpage", cpage);
			        request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
			        request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
			        request.setAttribute("record_total_count", dao.getRecordCount());
			        request.getRequestDispatcher("/user/crud/notice.jsp").forward(request, response);
			    }
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
