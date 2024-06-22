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
import com.google.gson.JsonObject;

import commons.Pagination;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 클라이언트로부터 전송되는 문자열에 대한 인코딩을 utf8로 처리
		// request에서 값을 꺼내기 전에 처리해야만 함!
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		BoardDAO dao = BoardDAO.getInstance();
		Pagination pagination = new Pagination();
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
		PrintWriter pw = response.getWriter();
		try {
			if (cmd.equals("/list.board")) {
			    String pcpage = request.getParameter("cpage");
			    if (pcpage == null) {
			        pcpage = "1";
			    }
			    int cpage = Integer.parseInt(pcpage);

			    List<BoardDTO> list = dao.selectListAll(
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
			        request.getRequestDispatcher("/user/crud/list.jsp").forward(request, response);
			    }
			} else if (cmd.equals("/like.board")) {
				String pcpage = request.getParameter("cpage");

		        // 페이지네이션 초기값 설정
		        if (pcpage == null) {
		            pcpage = "1";
		        }

		        // 문자열 숫자열로 형변환
		        int cpage = Integer.parseInt(pcpage);
		        int recordCountPerPage = pagination.recordCountPerPage; // 한 페이지당 레코드 수
		        int start = cpage * recordCountPerPage - (recordCountPerPage - 1);
		        int end = cpage * recordCountPerPage;

		        List<BoardDTO> list = dao.selectListLike(start, end);
		        // 총 레코드 수 가져오기
		        int totalCount = dao.getRecordCount();
		        // JSON 변환
		        JsonObject result = new JsonObject();
		        result.addProperty("total_count", totalCount);
		        result.addProperty("current_page", cpage);
		        result.add("data", gson.toJsonTree(list));

		        // PrintWriter 사용하여 JSON 데이터 전송
		        pw.write(result.toString());
		        pw.flush();
		        pw.close();
			} else if (cmd.equals("/view.board")) {
				String pcpage = request.getParameter("cpage");

		        // 페이지네이션 초기값 설정
		        if (pcpage == null) {
		            pcpage = "1";
		        }

		        // 문자열 숫자열로 형변환
		        int cpage = Integer.parseInt(pcpage);
		        int recordCountPerPage = pagination.recordCountPerPage; // 한 페이지당 레코드 수
		        int start = cpage * recordCountPerPage - (recordCountPerPage - 1);
		        int end = cpage * recordCountPerPage;

		        List<BoardDTO> list = dao.selectListView(start, end);
		        // 총 레코드 수 가져오기
		        int totalCount = dao.getRecordCount();
		        // JSON 변환
		        JsonObject result = new JsonObject();
		        result.addProperty("total_count", totalCount);
		        result.addProperty("current_page", cpage);
		        result.add("data", gson.toJsonTree(list));

		        // PrintWriter 사용하여 JSON 데이터 전송
		        pw.write(result.toString());
		        pw.flush();
		        pw.close();
			} else if (cmd.equals("/mylist.board")) {
				String pcpage = request.getParameter("cpage");
				if (pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);

				List<BoardDTO> list = dao.selectListAll(
						cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage - 1),
						cpage * pagination.recordCountPerPage);
				request.setAttribute("boardlist", list);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
