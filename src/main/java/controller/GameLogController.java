package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.GameLogDAO;
import dao.MemberDAO;


@WebServlet("*.gamelog")
public class GameLogController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		GameLogDAO dao = GameLogDAO.getInstance();
		try {
			if(cmd.equals("/submit.gamelog")){
				String user_id = (String)request.getSession().getAttribute("loginID");
				String nickname = "guest";
				if(user_id != null) {
					nickname = MemberDAO.getInstance().getNickname(user_id);
				}
				int gameID = Integer.parseInt(request.getParameter("gameID"));
				int log_seq = dao.insertGameLog(gameID, nickname);
				
				System.out.println(log_seq);
				// JSON 응답 생성
                LogSeqResponse logSeqResponse = new LogSeqResponse(log_seq);
                String jsonResponse = g.toJson(logSeqResponse);
                response.getWriter().write(jsonResponse);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	 // 응답 객체를 위한 클래스
    class LogSeqResponse {
        private int log_seq;
        
        public LogSeqResponse(int log_seq) {
            this.log_seq = log_seq;
        }

        public int getLog_seq() {
            return log_seq;
        }

        public void setLog_seq(int log_seq) {
            this.log_seq = log_seq;
        }
    }

}
