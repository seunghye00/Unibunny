package controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FilesDAO;

@WebServlet("*.file")
public class FilesController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSON 라이브러리
		Gson g = new Gson();

		// 접속 경로 저장
		String cmd = request.getRequestURI();
		System.out.println("Request URI: " + cmd);
		
		try {
			if(cmd.equals("/uploadProfile.file")) {
				System.out.println("파일 업로드 요청 처리 시작");
				
				// getRealPath를 사용하여 실제 경로를 확인
				String realPath = getServletContext().getRealPath("/image/mypage_image");
				System.out.println("Real Path: " + realPath);
				
				if (realPath == null) {
				    throw new ServletException("경로가 유효하지 않습니다: " + realPath);
				}

				int maxSize = 1024 * 1024 * 10; // 10 메가 사이즈 제한
				File uploadPath = new File(realPath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				
				Enumeration<String> names = multi.getFileNames();
				String userid = (String) request.getSession().getAttribute("loginID");
				String sysName = null;
				
				while(names.hasMoreElements()) {
					String name = names.nextElement();
					String oriName = multi.getOriginalFileName(name); // 원본 이름
					sysName = multi.getFilesystemName(name); // 서버에 저장 되었을 때 이름
					System.out.println("Original Name: " + oriName);
					System.out.println("System Name: " + sysName);
					
					if(oriName != null) {
						FilesDAO.getInstance().updateProfileImage(userid, sysName);
					}
				}
				System.out.println("파일 업로드 요청 처리 완료");
				response.sendRedirect("/mypage.member");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET 요청은 지원하지 않습니다. 파일 업로드는 POST 요청에서만 처리합니다.
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET 요청은 지원하지 않습니다.");
	}
}
