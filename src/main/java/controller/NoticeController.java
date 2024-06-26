package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import commons.Pagination;
import dao.NoticeDAO;
import dao.NoticeFilesDAO;
import dto.NoticeDTO;
import dto.NoticeFilesDTO;

@WebServlet("*.notice")
public class NoticeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmd = request.getRequestURI();
        System.out.println(cmd);
        NoticeDAO dao = NoticeDAO.getInstance();
        NoticeFilesDAO filesDao = NoticeFilesDAO.getInstance();
        Pagination pagination = new Pagination();
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
        PrintWriter pw = response.getWriter();

        try {
            if (cmd.equals("/list.notice")) {
                String pcpage = request.getParameter("cpage");
                if (pcpage == null) {
                    pcpage = "1";
                }
                int cpage = Integer.parseInt(pcpage);

                List<NoticeDTO> list = dao.selectListAll(
                    cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage - 1),
                    cpage * pagination.recordCountPerPage
                );

                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Map<String, Object> result = new HashMap<>();
                    result.put("data", list);
                    result.put("cpage", cpage);
                    result.put("record_count_per_page", pagination.recordCountPerPage);
                    result.put("navi_count_per_page", pagination.naviCountPerPage);
                    result.put("record_total_count", dao.getRecordCount());

                    String jsonResult = gson.toJson(result);
                    PrintWriter out = response.getWriter();
                    out.print(jsonResult);
                    out.flush();
                    out.close();
                } else {
                    request.setAttribute("boardlist", list);
                    request.setAttribute("cpage", cpage);
                    request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
                    request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
                    request.setAttribute("record_total_count", dao.getRecordCount());
                    request.getRequestDispatcher("/user/crud/notice.jsp").forward(request, response);
                }
            } else if (cmd.equals("/write.notice")) {
                int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
                String realPath = request.getServletContext().getRealPath("noticefiles");
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }

                MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                        new DefaultFileRenamePolicy());

                String title = multi.getParameter("notice_title");
                String content = multi.getParameter("notice_content");
                String nickname = multi.getParameter("nickname");

                System.out.println("title: " + title); // 디버깅 메시지
                System.out.println("content: " + content); // 디버깅 메시지
                System.out.println("nickname: " + nickname); // 디버깅 메시지

                if (title == null || title.trim().isEmpty() || 
                    content == null || content.trim().isEmpty() || 
                    nickname == null || nickname.trim().isEmpty()) {
                    response.sendRedirect("/error.jsp");
                    return;
                }

                NoticeDTO dto = new NoticeDTO();
                dto.setTitle(title);
                dto.setContent(content);
                dto.setNickname(nickname);

                int result = dao.insertNotice(dto);

                if (result > 0) {
                    int notice_seq = dao.getLastInsertedId(); // 마지막으로 삽입된 공지사항의 ID 가져오기
                    Enumeration<String> fileNames = multi.getFileNames();

                    while (fileNames.hasMoreElements()) {
                        String name = fileNames.nextElement();
                        String oriname = multi.getOriginalFileName(name);
                        String sysname = multi.getFilesystemName(name);

                        System.out.println("File input name: " + name);
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
                } else {
                    response.sendRedirect("/error.jsp");
                }
            } else if (cmd.equals("/admin_list.notice")) {
                String pcpage = request.getParameter("cpage");
                if (pcpage == null) {
                    pcpage = "1";
                }
                int cpage = Integer.parseInt(pcpage);

                List<NoticeDTO> list = dao.selectListAll(
                    cpage * pagination.recordCountPerPage - (pagination.recordCountPerPage - 1),
                    cpage * pagination.recordCountPerPage
                );

                request.setAttribute("boardlist", list);
                request.setAttribute("cpage", cpage);
                request.setAttribute("record_count_per_page", pagination.recordCountPerPage);
                request.setAttribute("navi_count_per_page", pagination.naviCountPerPage);
                request.setAttribute("record_total_count", dao.getRecordCount());
                request.getRequestDispatcher("/manager/notice.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
