package controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dao.Duptype;
import dao.MemberDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.MemberDTO;

//import dao.MemberDAO;
//import dto.MemberDTO;

@WebServlet("*.member")
public class MemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI();
		// 멤버 DAO 		
		MemberDAO mdao = MemberDAO.getInstance();
		
		
		Gson g = new Gson();
		JsonObject js = new JsonObject();
		try {
			if (cmd.equals("/signup.member")) {
				String userid = request.getParameter("userid");
				String nickname = request.getParameter("nickname");
				String pw = request.getParameter("pw");
				String phone = request.getParameter("phone");
				String reg_num_first = request.getParameter("reg_num_fisrt");
				String reg_num_second = request.getParameter("reg_num_second");
				String email = request.getParameter("email");
				String postcode = request.getParameter("postcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				String reg_num = reg_num_first + "-" + reg_num_second + "******";

				try {
					int result = mdao.insert(new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode,
							address1, address2, null, 0,null));
					if (result > 0) {
						response.sendRedirect("/index.jsp");
					} else {
						response.sendRedirect("/error.jsp");
					}
				} catch (Exception e) {
					// Log the exception for debugging
					e.printStackTrace();
					response.sendRedirect("/error.jsp");
				}
			}

			else if (cmd.equals("/login.member")) {
				String userid = request.getParameter("userid");
				String pw = request.getParameter("pw");
				
				String ipAddress = request.getRemoteAddr();

				System.out.println("Client IP Address: " + ipAddress); // IP 주소 출력
				boolean result = mdao.login(userid, pw);

				if (result) {

					HttpSession session = request.getSession();
					session.setAttribute("loginID", userid);
					System.out.println(userid);
				}
				response.sendRedirect("/index.jsp");

			}else if(cmd.equals("/mypage.member")) {
//				마이페이지에 진입 시, 회원의 정보를 조회해 줌
//				회원의 프로필 이미지, 가입날짜, 게시물 수, 댓글 단 수 등등
				
				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("회원정보 가져오기완료");
				
				// 가입 날짜 변환
				String formattedJoinDate = MemberDAO.getInstance().getFormattedJoinDate(mdto);
				
				// 게시물 작성 수와 댓글 작성 수 가져오기
				int board_count = BoardDAO.getInstance().searchBoardCount(id);
				int reply_count = ReplyDAO.getInstance().searchReplyCount(id);
//				my_info : 해당 멤버의 칼럼들
				request.setAttribute("my_info", mdto );
//				board_count : 해당 멤버의 게시물 작성 수
				request.setAttribute("board_count", board_count );
				request.setAttribute("reply_count", reply_count );
				request.setAttribute("formattedJoinDate", formattedJoinDate); // 형식화된 가입 날짜
				
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);
				
				
			}else if(cmd.equals("/edit.member")) {
//				마이페이지 계정관리에서 회원의 정보를 수정된값으로 갱신함
//				회원 정보 수정
				
				String id = (String)request.getSession().getAttribute("loginID"); //변조의 가능성이 있기때문에, 세션에서 받아와야한다.
				String pw = request.getParameter("pw");
				String nickname = request.getParameter("nickname");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String postcode = request.getParameter("postcode");
				
				int result = MemberDAO.getInstance().updateUserInfo(new MemberDTO(id, nickname, pw, phone, null, email, postcode, address1, address2, null, 1,null));
				
				response.sendRedirect("/mypage.member");
			}else if(cmd.equals("/account.member")) {
//				마이페이지 계정관리에서 회원의 정보를 뽑아옴
				
				System.out.println("mypage요청");
				String id = (String)request.getSession().getAttribute("loginID");
				MemberDTO mdto = (MemberDTO) mdao.searchProfileInfo(id);
				System.out.println("user1의 회원정보 가져오기완료");
				
				request.setAttribute("my_info", mdto );
//				
				request.setAttribute("activeTab", "myAccount");
				request.getRequestDispatcher("/user/mypage/mypage.jsp").forward(request,response);
			
			}else if(cmd.equals("/memberout.member")) {
//				로그인한 유저의 세션을 날리고, 회원의 정보를 삭제한다
//				마이 페이지 계정관리 -> 회원 탈퇴 기능
		
				HttpSession session = request.getSession();
				
				
				String id = (String) session.getAttribute("loginID");
				System.out.println((String) session.getAttribute("loginID"));
				mdao.deleteMember(id);
//				session.invalidate(); //무효화 -> 해당하는 명령쓰면 세션 다 날라감
				
				
				
				System.out.println("회원탈퇴 성공");
				
				response.sendRedirect("/index.jsp");
			}else if(cmd.equals("/logout.member")){
//				로그인한 유저의 세션을 날림
//				로그아웃 기능
				
				HttpSession session = request.getSession();
				session.invalidate(); //무효화 -> 해당하는 명령쓰면 세션 다 날라감

				response.sendRedirect("/index.jsp");
			
			}else if(cmd.equals("/uploadProfile.member")) {
//				회원이 선택한 프로필 사진을 서버로 업로드 함
//				마이페이지 프로필 편집 이후 적용이 될때 실행
//				서버에 프로필 사진을 업로드하고, DB의 회원 테이블에 프로필 사진 정보를 저장함
				
				 // 업로드된 파일이 저장될 실제 서버 경로를 가져옴
			    String realPath = getServletContext().getRealPath("/image/mypage_image");
			    
			    // 경로가 유효한지 확인, 유효하지 않다면 예외 발생
			    if (realPath == null) {
			        throw new ServletException("경로가 유효하지 않습니다.");
			    }

			    // 업로드 파일의 최대 크기를 10MB로 제한
			    int maxSize = 10 * 1024 * 1024; // 10MB 제한
			    
			    // 업로드 경로가 존재하지 않으면 디렉토리를 생성
			    File uploadPath = new File(realPath);
			    if (!uploadPath.exists()) {
			        uploadPath.mkdirs(); // 디렉토리 생성
			    }

			    // MultipartRequest 객체를 생성하여 파일 업로드 처리 
			    // - realPath: 업로드된 파일이 저장될 경로
			    // - DefaultFileRenamePolicy: 동일한 이름의 파일이 존재할 경우 파일명을 변경하는 정책
			    MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			    
			    // 세션에서 로그인된 사용자의 ID를 가져옴
			    String userid = (String) request.getSession().getAttribute("loginID");
			    
			    // 업로드된 파일의 원본 이름과 서버에 저장된 이름을 가져옴
			    String oriName = multi.getOriginalFileName("profileImage"); // 원본 이름
			    String sysName = multi.getFilesystemName("profileImage"); // 서버에 저장된 이름

			    // 파일이 정상적으로 업로드된 경우 (원본 이름이 null이 아닌 경우)
			    if (oriName != null) {
			        // DB에 저장된 회원 테이블의 프로필 이미지 정보를 업데이트
			        MemberDAO.getInstance().updateProfileImage(userid, sysName);
			    }
			    
			    // 파일 업로드 완료 후 마이페이지로 리디렉션
			    response.sendRedirect("/mypage.member");
			}
	
	
			
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
