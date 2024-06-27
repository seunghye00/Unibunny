package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.QNAFilesDAO;
import dto.QNAFilesDTO;

@WebServlet("*.qnafile")
public class QNAFilesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 접속 경로 저장
        String cmd = request.getRequestURI();
        System.out.println(cmd);

        QNAFilesDAO filesDao = QNAFilesDAO.getInstance();

        try {
            if (cmd.equals("/upload.qnafile")) {
                int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
                String realPath = request.getServletContext().getRealPath("files");
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }

                MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                        new DefaultFileRenamePolicy());

                int question_seq = Integer.parseInt(multi.getParameter("question_seq"));
                Enumeration<String> fileNames = multi.getFileNames();

                while (fileNames.hasMoreElements()) {
                    String name = fileNames.nextElement();
                    String oriname = multi.getOriginalFileName(name);
                    String sysname = multi.getFilesystemName(name);

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

                response.getWriter().write("{\"status\":\"success\"}");
            } else if (cmd.equals("/download.qnafile")) {
                // 파일 다운로드 처리
                String fileName = request.getParameter("fileName");
                String filePath = getServletContext().getRealPath("files") + File.separator + fileName;
                File file = new File(filePath);

                if (file.exists()) {
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859_1"));
                    try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    response.getWriter().write("Requested file not found.");
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
