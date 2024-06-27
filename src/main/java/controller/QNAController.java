package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import commons.Pagination;
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
                dto.setUserid(userId);

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
            } else if (cmd.equals("/list.qna")) {
                List<QNADTO> qnaList = dao.selectAllQnA();
                request.setAttribute("qnaList", qnaList);
                request.getRequestDispatcher("/manager/qna.jsp").forward(request, response);
            } else if (cmd.equals("/detail.qna")) {
                int question_seq = Integer.parseInt(request.getParameter("question_seq"));
                QNADTO qna = dao.selectQnABySeq(question_seq);
                QNAFilesDTO file = filesDao.selectFileByQuestionSeq(question_seq);
                
                request.setAttribute("qna", qna);
                request.setAttribute("file", file);
                request.getRequestDispatcher("/manager/qna_detail.jsp").forward(request, response);
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
            }else if(cmd.equals("/myqna.qna")) {
//				회원이 작성한 QNA를 마이페이지에서 조회
				
				String id = (String)request.getSession().getAttribute("loginID");
				System.out.println("진입");
				String pcpage = request.getParameter("cpage");
				if( pcpage == null) {
					pcpage = "1";
				}
				int cpage = Integer.parseInt(pcpage);
				System.out.println("회원의 북마크 조회");
				
				List<QNADTO> list = QNADAO.getInstance().searchMyQNAList(cpage * Pagination.recordCountPerPage - (Pagination.recordCountPerPage -1),
						cpage * Pagination.recordCountPerPage,id);
				System.out.println("북마크 게시글 조회 완료");
				
				request.setAttribute("myqna", list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("record_count_per_page", Pagination.recordCountPerPage);
				request.setAttribute("navi_count_per_page", Pagination.naviCountPerPage);
				request.setAttribute("record_total_count", QNADAO.getInstance().getRecordCount());	
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
