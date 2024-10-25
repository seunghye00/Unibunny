# K-Degital Academy First-Project
## 웹 게임 제작 및 커뮤니티 사이트 Unibunny
### 주제
- 다양한 게임을 플레이할 수 있으며 게임별 커뮤니티를 하나의 플랫폼에서 쉽게 접근 가능한 웹사이트 기획
### 프로젝트 명
- Unibunny
  - Universe(우주)와 Bunny(토끼)의 합성어로 우주를 여행하는 듯한 경험을 주기 위한 게임 및 커뮤니티 서비스
### 기간 : 2024.06.03 ~ 2024.06.30
### Hosting : http://43.203.1.76:8081/

## Member
- 박종호 ( Leader )
  - 프로젝트 진행 일정 조율 및 회의 진행
  - 로그인 및 회원가입 기능 구현 / 로그인 및 회원가입 페이지 제작
- 문경원
  - 프로젝트 전체적인 UI / UX 디자인, 형상관리 ( Git-Hub ), 서버 호스팅
  - 게시글 목록 조회 및 검색 기능 구현 / 메인 페이지, Index 페이지, 개발자 소개 페이지 및 사이트 아이콘 제작
  - [ 관리자 콘솔 ] 메인 배너 관리 기능 구현 / 메인 배너 관리 페이지 제작
- 조창기
  - DB 담당
  - 마이페이지 기능 구현 / 마이페이지 제작
- 한승혜
  - PM(프로젝트 일정 관리 및 서포팅), 프로젝트 문서 작성, 서버 호스팅
  - 게시글 및 댓글 CRUD, 게시글 북마크, 게시글 및 댓글 추천 기능 구현 / 커뮤니티 페이지 제작
  - [ 관리자 콘솔 ] 회원 관리 및 게시판 관리 기능 구현 / 회원 관리 페이지 및 게시판 관리 페이지 제작
- 손민서
  - QA 담당
  - 고객센터(Q&A, FAQ, 공지사항) 기능 구현 / 고객센터 및 랭킹 페이지 제작
  - [ 관리자 콘솔 ] 고객센터 관리 기능 구현 / 고객센터 관리 페이지 제작

## 아키텍처
![image](https://github.com/user-attachments/assets/a2597b4f-b1ba-4f78-b68a-36c7c8b975d1)

## 개발 환경
### Front-End
- HTML5, CSS3, Javascript(ES6+), JSP
### Back-End
- Java 11
### DB
- Oracle 11
### WAS 
- Tomcat (ver 9.0.79)
### Development Tools
- Eclipse
- VSCode
- Sql-Developer
### Design Tools
- ERD Cloud, Figma, illustrator, Photoshop 
### VCS
- Git, Github, GitKraken

## 주요 라이브러리
### Front-End
- Phaser 3: 2D 웹 게임 제작을 위한 게임 엔진
- Jquery : DOM 조작, 이벤트 처리, 애니메이션 구현
- Summernote : 게시판 에디터
- Swiper: 반응형 메인 배너 슬라이드 구현

### Back-End
- GSON: Java 객체를 JSON 형식으로 변환
- OJDBC: Oracle DB와 연동을 위한 JDBC 드라이버
- SHA-512: 비밀번호 암호화 처리
- Java Mail: 이메일을 통한 아이디 찾기 기능 구현
