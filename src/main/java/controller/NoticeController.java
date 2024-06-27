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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmd = request.getRequestURI();
		System.out.println(cmd);
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeFilesDAO filesDao = NoticeFilesDAO.getInstance();
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
						cpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage - 1),
						cpage * Pagination.recordCountPerPage);

				if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");

					Map<String, Object> result = new HashMap<>();
					result.put("data", list);
					result.put("cpage", cpage);
					result.put("record_count_per_page", Pagination.recordCountPerPage);
					result.put("navi_count_per_page", Pagination.naviCountPerPage);
					result.put("record_total_count", dao.getRecordCount());

					String jsonResult = gson.toJson(result);
					PrintWriter out = response.getWriter();
					out.print(jsonResult);
					out.flush();
					out.close();
				} else {
					request.setAttribute("noticelist", list);
					request.setAttribute("cpage", cpage);
					request.setAttribute("record_count_per_page", Pagination.recordCountPerPage);
					request.setAttribute("navi_count_per_page", Pagination.naviCountPerPage);
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

				if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()
						|| nickname == null || nickname.trim().isEmpty()) {
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

					response.sendRedirect("/admin_list.notice");
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
						cpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage - 1),
						cpage * Pagination.recordCountPerPage);

				request.setAttribute("noticelist", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("record_count_per_page", Pagination.recordCountPerPage);
				request.setAttribute("navi_count_per_page", Pagination.naviCountPerPage);
				request.setAttribute("record_total_count", dao.getRecordCount());
				request.getRequestDispatcher("/manager/notice.jsp").forward(request, response);

			} else if (cmd.equals("/user/detail.notice")) { // 사용자 상세 조회 기능 추가
				String noticeSeq = request.getParameter("notice_seq");
				if (noticeSeq != null) {
					dao.increaseViewCount(Integer.parseInt(noticeSeq));
					NoticeDTO notice = dao.getNoticeById(Integer.parseInt(noticeSeq));
					if (notice != null) {
						request.setAttribute("notice", notice);
						request.getRequestDispatcher("/user/crud/ntc_detail.jsp").forward(request, response);
					} else {
						System.out.println("Notice not found for ID: " + noticeSeq);
						response.sendRedirect("/list.notice");
					}
				} else {
					System.out.println("NoticeSeq parameter is missing");
					response.sendRedirect("/list.notice");
				}
			} else if (cmd.equals("/admin_detail.notice")) { // 관리자 상세 조회 기능 추가
				String noticeSeq = request.getParameter("notice_seq");
				if (noticeSeq != null) {
					dao.increaseViewCount(Integer.parseInt(noticeSeq));
					NoticeDTO notice = dao.getNoticeById(Integer.parseInt(noticeSeq));
					request.setAttribute("notice", notice);
					request.getRequestDispatcher("/manager/ntc_detail.jsp").forward(request, response);
				} else {
					response.sendRedirect("/admin_list.notice");
				}
			} else if (cmd.equals("/delete.notice")) {
				int noticeSeq = Integer.parseInt(request.getParameter("notice_seq"));

				// 파일 정보 가져오기
				List<NoticeFilesDTO> files = filesDao.getFilesByNoticeSeq(noticeSeq);

				// 파일 삭제
				String realPath = request.getServletContext().getRealPath("files");
				for (NoticeFilesDTO file : files) {
					File fileToDelete = new File(realPath, file.getSysname());
					if (fileToDelete.exists()) {
						fileToDelete.delete();
					}
				}

				// DB에서 파일 삭제
				filesDao.getFilesByNoticeSeq(noticeSeq);
				// 공지사항 삭제
				dao.deleteNotice(noticeSeq);
				response.sendRedirect("/admin_list.notice");

			} else if (cmd.equals("/update.notice")) {
				int noticeSeq = Integer.parseInt(request.getParameter("notice_seq"));

				// 수정할 공지사항 정보 가져오기
				NoticeDTO notice = dao.getNoticeById(noticeSeq);
				List<NoticeFilesDTO> files = filesDao.getFilesByNoticeSeq(noticeSeq);

				// 수정 페이지로 데이터 전달
				request.setAttribute("notice", notice);
				request.setAttribute("files", files);
				request.getRequestDispatcher("/manager/modi_notice.jsp").forward(request, response);

			} else if (cmd.equals("/modify.notice")) {
                int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
                String realPath = request.getServletContext().getRealPath("files");
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }

                MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
                        new DefaultFileRenamePolicy());

                String noticeSeqStr = multi.getParameter("notice_seq");
                if (noticeSeqStr == null || noticeSeqStr.trim().isEmpty()) {
                    throw new ServletException("공지사항 시퀀스가 전달되지 않았습니다.");
                }
                int noticeSeq = Integer.parseInt(noticeSeqStr);
                String title = multi.getParameter("notice_title");
                String content = multi.getParameter("notice_content");

                NoticeDTO notice = new NoticeDTO();
                notice.setNotice_seq(noticeSeq);
                notice.setTitle(title);
                notice.setContent(content);

                // 공지사항 수정
                dao.updateNotice(notice);
                System.out.println("공지사항이 수정되었습니다.");

                response.sendRedirect("/admin_detail.notice?notice_seq=" + noticeSeq);
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