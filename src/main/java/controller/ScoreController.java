package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ScoreDAO;
import dto.ScoreDTO;


@WebServlet("*.score")
public class ScoreController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
		Gson g = new Gson();
		// reponse writer 변수 저장
		PrintWriter pw = response.getWriter();
		
		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		ScoreDAO dao = ScoreDAO.getInstance();
		try {
			if(cmd.equals("/list.score")){
				int gamelistNum = Integer.parseInt(request.getParameter("gamelistNum"));
				System.out.println(gamelistNum);
				List<ScoreDTO> list = dao.selectListGame(gamelistNum);
				String jsonResult = gson.toJson(list);
				pw = response.getWriter();
				pw.append(jsonResult);
				pw.flush();
				pw.close();
			}
			else if(cmd.equals("/submit.score")) {
				String loginID = (String)request.getSession().getAttribute("loginID");
				// loginID에 임시 데이터 대입 => 이후에 삭제할 코드
				if(loginID == null) {
					loginID = "user001";
				}
				//System.out.println(request.getParameter("score"));
				//System.out.println(request.getParameter("gameId"));
				int score = Integer.parseInt(request.getParameter("score"));
				int gameId = Integer.parseInt(request.getParameter("gameId"));
				pw.append(g.toJson(gameId));
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
