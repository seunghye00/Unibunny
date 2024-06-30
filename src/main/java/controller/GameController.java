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

import dao.GameDAO;
import dto.GameDTO;


@WebServlet("*.game")
public class GameController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		// System.out.println(cmd);
		
		try {
			if(cmd.equals("/getgames.game")) {
				// 게임 목록을 가져옴
                List<GameDTO> games = GameDAO.getInstance().getAllGames();

                // 게임 목록을 JSON 형식으로 변환
                String json = g.toJson(games);

                // 응답에 JSON 데이터를 작성
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
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
