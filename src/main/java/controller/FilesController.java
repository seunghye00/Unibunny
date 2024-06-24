package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.FilesDAO;


@WebServlet("*.file")
public class FilesController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 인코딩 설정
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      
      // JSON 라이브러리
      Gson g = new Gson();

      // 접속 경로 저장
      String cmd = request.getRequestURI();
      System.out.println(cmd);
      
      //FilesDAO 인스턴스 생성
      FilesDAO dao = FilesDAO.getInstance();
      
      try {
         if(cmd.equals("/list.file")) {
            // 파일 목록 조회
            int board_seq = Integer.parseInt(request.getParameter("board_seq")); 
            
            // 댓글 목록을 담은 데이터 직렬화
            String file_list = g.toJson(dao.selectByBoardSeq(board_seq));
            // 직렬화한 데이터 전송
            response.getWriter().append(file_list);
            
         } else if (cmd.equals("/download.file")) {
            // 파일 다운로드 (추후 수정 작업 필요)
            String filepath = request.getServletContext().getRealPath("files"); // 파일이 저장되어 있는 위치
            String sysname = request.getParameter("sysname");                // 다운 받을 파일이 하드디스크에 저장된 이름
            String oriname = request.getParameter("oriname");               // 다운 받을 파일의 원래 이름
            
            System.out.println(oriname);
            System.out.println(sysname);
            System.out.println(filepath);

            oriname = new String(oriname.getBytes("UTF-8"),"ISO-8859-1");      // 인코딩 방식 수정. 크롬의 인코딩 방식
            
            
            response.reset();   // 기존의 응답 방식 초기화. 즉, 기존의 코드를 돌려보내는 방식 대신 파일 내용을 돌려보내는 방식으로 동작하기 위한 코드
            response.setHeader("Content-Disposition","attachment;filename=\"" + oriname + "\"");   
            
            File target = new File(filepath + "/" + sysname); // 타겟 파일이 저장된 경로를 통해 파일 인스턴스 생성

            byte[] fileContents = new byte[(int) target.length()]; // 하드디스크에서 뽑아낸 타겟 파일 내용을 저장할 배열

            try (FileInputStream fis = new FileInputStream(target);
                  DataInputStream dis = new DataInputStream(fis); // 타겟 파일에 스트림을 연결 ( 데이터 통신 준비 )
                  ServletOutputStream sos = response.getOutputStream(); // 클라이언트에게 데이터를 보낼 수 있는 스트림 개방
            ) {
               
               dis.readFully(fileContents); // 하드디스크에서 타겟 파일 내용을 RAM으로 복사.

               sos.write(fileContents); // 파일의 내용을 전송
               sos.flush();
            }
         }
         
      } catch(Exception e) {
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      doGet(request, response);
   }

}
