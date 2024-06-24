package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
        response.setContentType("text/html; charset=UTF-8");

        // 접속 경로 저장
        String cmd = request.getRequestURI();
        System.out.println(cmd);

        QNADAO dao = QNADAO.getInstance();
        QNAFilesDAO filesDao = QNAFilesDAO.getInstance();

        try {
            if (cmd.equals("/write.qna")) {
                int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
                String realPath = request.getServletContext().getRealPath("files");
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }

                MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                        new DefaultFileRenamePolicy());

                String title = multi.getParameter("question_title");
                String content = multi.getParameter("question_content");
                String userId = multi.getParameter("userId");

                System.out.println("Title: " + title);
                System.out.println("Content: " + content);
                System.out.println("UserId: " + userId);

                Timestamp writeDate = new Timestamp(System.currentTimeMillis());

                QNADTO dto = new QNADTO();
                dto.setQuestion_title(title);
                dto.setQuestion_content(content);
                dto.setWrite_date(writeDate);
                dto.setId(userId);

                int result = dao.insertQnA(dto);

                if (result > 0) {
                    int question_seq = dao.getLastInsertedId();
                    Enumeration<String> fileNames = multi.getFileNames();

                    while (fileNames.hasMoreElements()) {
                        String name = fileNames.nextElement();
                        String oriname = multi.getOriginalFileName(name);
                        String sysname = multi.getFilesystemName(name);

                        System.out.println("File input name: " + name);
                        System.out.println("Original File Name: " + oriname);
                        System.out.println("System File Name: " + sysname);

                        if (oriname != null && sysname != null) {
                            QNAFilesDTO fileDto = new QNAFilesDTO();
                            fileDto.setOriname(oriname);
                            fileDto.setSysname(sysname);
                            fileDto.setQuestion_seq(question_seq);
                            filesDao.insertFile(fileDto);
                        }
                    }

                    response.sendRedirect("/list.faq");
                } else {
                    response.sendRedirect("/write_qna.jsp");
                }
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
