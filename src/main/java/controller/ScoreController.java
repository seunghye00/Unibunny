package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ScoreDAO;
import dto.ScoreDTO;


@WebServlet("*.score")
public class ScoreController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		// System.out.println(cmd);
		ScoreDAO dao = ScoreDAO.getInstance();
		try {
			if(cmd.equals("/submit.score")) {
				String loginID = (String)request.getSession().getAttribute("loginID");
				System.out.println(request.getParameter("score"));
				System.out.println(loginID);
                int scoreValue = Integer.parseInt(request.getParameter("score"));
                int gameId = Integer.parseInt(request.getParameter("gameId"));
                String nickname = request.getParameter("nickname");
                int logSeq = Integer.parseInt(request.getParameter("logSeq"));
                Timestamp endTime = new Timestamp(System.currentTimeMillis());
                if(loginID != null && !loginID.isEmpty()) {
                    // Score 객체 생성
                    ScoreDTO score = new ScoreDTO();
                    score.setScore(scoreValue);
                    score.setGame_id(gameId);
                    score.setNickname(nickname);
                    score.setEnd_time(endTime);
                    score.setLog_seq(logSeq);

                    // ScoreDAO를 통해 데이터베이스에 저장
                   
                    boolean result = dao.insertScore(score);

                    if(result) {
                        // 성공 응답
                        response.setStatus(HttpServletResponse.SC_OK);
                        PrintWriter out = response.getWriter();
                        out.print(g.toJson("Score submitted successfully"));
                        out.flush();
                    } else {
                        // 실패 응답
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        PrintWriter out = response.getWriter();
                        out.print(g.toJson("Failed to submit score"));
                        out.flush();
                    }
                } else {
                    // 로그인되지 않은 경우
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    out.print(g.toJson("User not logged in"));
                    out.flush();
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
