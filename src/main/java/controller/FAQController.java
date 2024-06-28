package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.FAQDAO;
import dto.FAQDTO;

@WebServlet("*.faq")
public class FAQController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // JSON 라이브러리
        Gson g = new Gson();

        // 접속 경로 저장
        String cmd = request.getRequestURI();
        System.out.println(cmd);

        // FAQDAO 인스턴스 생성
        FAQDAO dao = FAQDAO.getInstance();

        try {
            if (cmd.equals("/write.faq")) {
                String title = request.getParameter("title");
                String content = request.getParameter("content");

                // DTO 생성
                // FAQ를 작성할떄만 사용해서 여기에서 인스턴스를 생성하는게 더 좋다 생각해서 여기에 뒀습니다!
                FAQDTO faq = new FAQDTO();
                
                faq.setTitle(title);
                faq.setContent(content);

                // DAO를 통해 데이터베이스에 저장
                int result = dao.insertFAQ(faq);

                // 결과에 따라 페이지 이동
                if (result > 0) {
                	// 관리자페이지로 리다이렉션
                    response.sendRedirect("/list.faq?userType=manager"); 
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (cmd.equals("/list.faq")) {
                List<FAQDTO> faqList = dao.getFAQs();

                // 조회된 FAQ 목록을 JSP에 전달
                request.setAttribute("faqList", faqList);
                
                // 관리자인지 사용자인지 확인 (여기서는 간단히 파라미터로 확인)
                String userType = request.getParameter("userType");
                
                if ("manager".equals(userType)) {
                    request.getRequestDispatcher("/manager/faq.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/user/crud/faq.jsp").forward(request, response);
                }
                
            } else if (cmd.equals("/delete.faq")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int result = dao.deleteFAQ(id);
                
                response.getWriter().write(result > 0 ? "success" : "failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
