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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmd = request.getRequestURI();
        System.out.println("Command: " + cmd);

        NoticeFilesDAO filesDao = NoticeFilesDAO.getInstance();

        try {
            if (cmd.equals("/upload.noticefile")) {
                System.out.println("파일 업로드 요청 처리");
                handleFileUpload(request, response, filesDao);
            } else if (cmd.equals("/deleteFile.noticefile")) {
                System.out.println("파일 삭제 요청 처리");
                handleFileDelete(request, response, filesDao);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
        }
    }

    private void handleFileUpload(HttpServletRequest request, HttpServletResponse response, NoticeFilesDAO filesDao) throws Exception {
        System.out.println("handleFileUpload 호출");
        int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
        String realPath = request.getServletContext().getRealPath("files");
        File uploadPath = new File(realPath);
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
            System.out.println("업로드 경로 생성: " + realPath);
        }

        MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                new DefaultFileRenamePolicy());

        String noticeSeqStr = multi.getParameter("notice_seq");
        if (noticeSeqStr == null || noticeSeqStr.isEmpty()) {
            System.out.println("notice_seq 파라미터가 없습니다.");
            response.getWriter().write("fail");
            return;
        }

        int notice_seq = Integer.parseInt(noticeSeqStr);
        System.out.println("공지사항 시퀀스: " + notice_seq);
        Enumeration<String> fileNames = multi.getFileNames();

        while (fileNames.hasMoreElements()) {
            String name = fileNames.nextElement();
            String oriname = multi.getOriginalFileName(name);
            String sysname = multi.getFilesystemName(name);

            System.out.println("파일 필드 이름: " + name);
            System.out.println("Original File Name: " + oriname);
            System.out.println("System File Name: " + sysname);

            if (oriname != null && sysname != null) {
                NoticeFilesDTO fileDto = new NoticeFilesDTO();
                fileDto.setOriname(oriname);
                fileDto.setSysname(sysname);
                fileDto.setNotice_seq(notice_seq);
                int insertResult = filesDao.insertFile(fileDto);
                System.out.println("파일 삽입 결과: " + insertResult);
            }
        }

        response.getWriter().write("success");
        System.out.println("파일 업로드 처리 완료");
    }

    private void handleFileDelete(HttpServletRequest request, HttpServletResponse response, NoticeFilesDAO filesDao) throws Exception {
        System.out.println("handleFileDelete 호출");
        String fileSeqStr = request.getParameter("file_seq");
        if (fileSeqStr == null || fileSeqStr.isEmpty()) {
            System.out.println("file_seq 파라미터가 없습니다.");
            response.getWriter().write("fail");
            return;
        }

        int fileSeq = Integer.parseInt(fileSeqStr);
        System.out.println("파일 시퀀스: " + fileSeq);

        NoticeFilesDTO fileDto = filesDao.getFile(fileSeq);
        if (fileDto != null) {
            String realPath = request.getServletContext().getRealPath("files");
            File file = new File(realPath, fileDto.getSysname());

            if (file.exists()) {
                boolean deleted = file.delete();
                System.out.println("파일 삭제: " + (deleted ? "성공" : "실패") + " - " + fileDto.getSysname());
            }

            int deleteResult = filesDao.deleteFileById(fileSeq);
            System.out.println("DB에서 파일 삭제 결과: " + deleteResult);
        }

        response.getWriter().write("success");
        System.out.println("파일 삭제 처리 완료");
    }
}
