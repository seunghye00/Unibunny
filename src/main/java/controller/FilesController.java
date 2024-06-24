package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilesDAO;
import dto.FilesDTO;

@WebServlet("*.files")
public class FilesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 접속 경로 저장
        String cmd = request.getRequestURI();
        System.out.println(cmd);

        try {
            if (cmd.equals("/upload.files")) {
                String oriname = request.getParameter("oriname");
                String sysname = request.getParameter("sysname");
                int board_seq = Integer.parseInt(request.getParameter("board_seq"));

                FilesDTO dto = new FilesDTO();
                dto.setOriname(oriname);
                dto.setSysname(sysname);
                dto.setBoard_seq(board_seq);

                FilesDAO.getInstance().insertFile(dto);

                response.sendRedirect("/list.faq");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
