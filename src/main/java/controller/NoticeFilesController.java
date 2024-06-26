package controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.NoticeFilesDAO;
import dto.NoticeFilesDTO;

@WebServlet("*.noticefile")
public class NoticeFilesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmd = request.getRequestURI();
        System.out.println(cmd);

        NoticeFilesDAO filesDao = NoticeFilesDAO.getInstance();

        try {
            if (cmd.equals("/upload.noticefile")) {
                int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
                String realPath = request.getServletContext().getRealPath("files");
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }

                MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                        new DefaultFileRenamePolicy());

                int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));
                Enumeration<String> fileNames = multi.getFileNames();

                while (fileNames.hasMoreElements()) {
                    String name = fileNames.nextElement();
                    String oriname = multi.getOriginalFileName(name);
                    String sysname = multi.getFilesystemName(name);

                    System.out.println("Original File Name: " + oriname);
                    System.out.println("System File Name: " + sysname);

                    if (oriname != null && sysname != null) {
                        NoticeFilesDTO fileDto = new NoticeFilesDTO();
                        fileDto.setOriname(oriname);
                        fileDto.setSysname(sysname);
                        fileDto.setNotice_seq(notice_seq);
                        filesDao.insertFile(fileDto);
                    }
                }

                response.sendRedirect("/list.notice");
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
