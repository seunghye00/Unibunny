package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QNADAO;
import dao.QNAFilesDAO;
import dto.QNADTO;
import dto.QNAFilesDTO;

@WebServlet("*.qna")
public class QNAController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 접속 경로 저장
        String cmd = request.getRequestURI();
        System.out.println(cmd);

        QNADAO dao = QNADAO.getInstance();
        QNAFilesDAO filesDao = QNAFilesDAO.getInstance();
        
        HttpSession session = request.getSession();

        try {
        	if (cmd.equals("/write.qna")) {
        	    // Q&A 데이터 저장
        	    String title = request.getParameter("question_title");
        	    String content = request.getParameter("question_content");
        	    String userId = (String) request.getSession().getAttribute("loginID");

        	    // 디버깅용 출력
        	    System.out.println("Title: " + title);
        	    System.out.println("Content: " + content);
        	    System.out.println("UserId: " + userId);

        	    if (userId == null || userId.trim().isEmpty()) {
        	        throw new Exception("User ID cannot be null or empty");
        	    }
        	    if (title == null || title.trim().isEmpty()) {
        	        throw new Exception("Title cannot be null or empty");
        	    }
        	    if (content == null || content.trim().isEmpty()) {
        	        throw new Exception("Content cannot be null or empty");
        	    }

        	    Timestamp writeDate = new Timestamp(System.currentTimeMillis());

        	    QNADTO dto = new QNADTO();
        	    dto.setQuestion_title(title);
        	    dto.setQuestion_content(content);
        	    dto.setWrite_date(writeDate);
        	    dto.setUserid(userId);

        	    int result = dao.insertQnA(dto);

        	    if (result > 0) {
        	        // 마지막으로 삽입된 Q&A의 ID를 가져옴
        	        int question_seq = dao.getLastInsertedId();
        	        response.getWriter().write("{\"status\":\"success\", \"question_seq\":" + question_seq + "}");
        	    } else {
        	        response.getWriter().write("{\"status\":\"failure\"}");
        	    }
        	} else if (cmd.equals("/list.qna")) {
                List<QNADTO> qnaList = dao.selectAllQnA();
                request.setAttribute("qnaList", qnaList);
                request.getRequestDispatcher("/admin/qna.jsp").forward(request, response);
            } else if (cmd.equals("/detail.qna")) {
                int question_seq = Integer.parseInt(request.getParameter("question_seq"));
                QNADTO qna = dao.selectQnABySeq(question_seq);
                QNAFilesDTO file = filesDao.selectFileByQuestionSeq(question_seq);

                request.setAttribute("qna", qna);
                request.setAttribute("file", file);
                request.getRequestDispatcher("/admin/qna_detail.jsp").forward(request, response);
            } else if (cmd.equals("/user/detail.qna")) {
            	//유저 qna디테일
                int question_seq = Integer.parseInt(request.getParameter("question_seq"));
                QNADTO qna = dao.selectQnABySeq(question_seq);
                QNAFilesDTO file = filesDao.selectFileByQuestionSeq(question_seq);

                String cleanedContent = qna.getQuestion_content().replaceAll("(?i)<p[^>]*>", "").replaceAll("(?i)</p>", "");
                request.setAttribute("cleanedContent", cleanedContent);
                request.setAttribute("qna", qna);
                request.setAttribute("file", file);
                request.getRequestDispatcher("/user/crud/qna_detail.jsp").forward(request, response);
            } else if (cmd.equals("/answer.qna")) {
                int question_seq = Integer.parseInt(request.getParameter("question_seq"));
                String answer_content = request.getParameter("answer_content");

                QNADTO dto = new QNADTO();
                dto.setQuestion_seq(question_seq);
                dto.setAnswer_content(answer_content);
                dto.setAnswer_yn("Y");
                dto.setAnswer_date(new Timestamp(System.currentTimeMillis()));

                int result = dao.insertAnswer(dto);
                if (result > 0) {
                    response.sendRedirect("/detail.qna?question_seq=" + question_seq);
                } else {
                    response.sendRedirect("/index.jsp");
                }
            } else if(cmd.equals("/myqna.qna")) {
				
				String id = (String)request.getSession().getAttribute("loginID");

				System.out.println("회원의 북마크 조회");
				
				List<QNADTO> list = QNADAO.getInstance().searchMyQNAList(id);
				System.out.println("북마크 게시글 조회 완료");
				
				request.setAttribute("myqna", list);	
				request.setAttribute("activeTab", "questions");
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request, response);
				
				
				
			}

            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
