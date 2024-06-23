<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>Board</title>
				<link rel="stylesheet" href="../../../css/common.css">
				<link rel="stylesheet" href="../../../css/sub.css">
				<link rel="stylesheet" href="../../../css/layout.css">
				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
				<script defer src="../../../js/common.js"></script>
				<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
			</head>

			<body>
				<div class="wrapper">
					<div class="header_area">
						<div class="header">
							<div class="wrap mob_hidden">
								<h1 class="logo">
									<a href="javascript:;" title="메인으로 가기"> <img src="../../image/logo.png" alt="">
									</a>
								</h1>
								<div class="header_con">
									<ul class="header_gnb">
										<li><a href="javascript:;" class="gnb_comu"><span>커뮤니티</span></a>
										</li>
										<li><a href="javascript:;" class="gnb_rank"><span>랭킹</span></a>
										</li>
										<li><a href="javascript:;" class="gnb_our"><span>OUR
													PAGE</span></a></li>
										<li><a href="javascript:;" class="gnb_cs"><span>고객센터</span></a>
										</li>
									</ul>
									<ul class="header_my">
										<li class="my_01 "><a href="javascript:;" class="btn_mypage"><img
													src="../../image/icon/mypageW.png" alt="마이페이지 로고"></a></li>
										<li class="my_02"><a href="javascript:;" class="btn_login"><img
													src="../../image/icon/login.png" alt="로그인 로고"></a></li>
									</ul>
								</div>
							</div>
							<div class="mob_wrap">
								<h1 class="mob_logo">
									<a href="javascript:;" title="메인으로 가기"> <img src="../../image/logo.png" alt="">
									</a>
								</h1>
								<div class="mob_ham"></div>
								<div onclick="history.back();" class="mob_page_cover"></div>
								<div class="mob_menu">
									<ul class="mob_list">
										<li><strong><a href="/list.board">커뮤니티</a></strong></li>
										<li><strong><a href="javascript:;">랭킹</a></strong></li>
										<li><strong><a href="javascript:;">OUR PAGE</a></strong></li>
										<li><strong><a href="javascript:;">고객센터</a></strong></li>

									</ul>
									<div class="mob_my">
										<ul>
											<li><a href="javascript:;" class="mob_mypage"><img
														src="../../image/icon/mypage.png" alt="마이페이지 로고"></a></li>
											<li><a href="javascript:;" class="mob_login"><img
														src="../../image/icon/login_b.png" alt="로그인 로고"></a></li>
											<li>
												<div onclick="history.back();" class="mob_close"></div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="body_area">
						<div class="body for_pc">
							<div class="wrap">
								<div class="con_wrap">
									<div class="con">
										<div class="title_box">
											<p class="title" id="board_seq"># ${dto.board_seq}</p>
										</div>
										<div class="cont_box">
											<div class="cont_body">
												<div class="board_box">
													<div class="board_head">
														<div class="title">
															<div id="board_title">${dto.title }</div>
															<div class="btn_box">
																<button class="option_btn mark_option" type="button">
																	<i class="fa-regular fa-bookmark option_icon"></i>
																	<i class="fa-solid fa-bookmark option_icon"></i>
																</button>
																<button class="option_btn file_option" type="button">
																	<i class="fa-regular fa-folder option_icon"></i>
																	<i class="fa-solid fa-folder option_icon"></i>
																</button>
																<div class="file_list"></div>
															</div>
														</div>
														<div class="board_info">
															<div class="writer">작성자 : ${dto.nickname}</div>
															<div class="write_date">
																작성날짜 :
																<fmt:formatDate value="${dto.write_date}"
																	pattern="yyyy.MM.dd HH:mm" />
															</div>
															<div class="views">조회수 : ${dto.view_count}</div>
															<div class="likes">스크랩 수 : ${bookmark}</div>
															<div class="edit_box">

																<div class="btn_box">
																	<button class="write_btn" id="edit_btn"
																		type="button">수정</button>
																</div>
																<div class="btn_box">
																	<button class="write_btn" id="del_btn"
																		type="button">삭제</button>
																</div>

																<div class="btn_box">
																	<button class="write_btn back_btn" id="back_btn"
																		type="button">목록</button>
																</div>
															</div>
														</div>
													</div>
													<div class="board_body">
														<div class="board_cont" id="board_cont">${dto.content}</div>
													</div>
												</div>
												<div class="option_box">
													<div class="btn_box">
														<button class="option_btn likes_option board_like"
															type="button">
															<i class="fa-regular fa-thumbs-up option_icon"></i> <i
																class="fa-solid fa-thumbs-up option_icon"></i>
															<p>${dto.thumbs_up}</p>
														</button>
													</div>
												</div>
												<div style="padding: 10px;"></div>
												<div class="comm_box">
													<div class="write_comm">
														<div class="input_comm">
															<div class="input_box" contenteditable="true"></div>
														</div>
														<div class="btn_box">
															<button class="write_btn" id="write_comm">작성</button>
														</div>
													</div>
													<div style="padding: 10px;"></div>
													<div class="choi_box">
														<div class="btn_box">
															<button class="write_btn choi_btn" type="button"
																id="comm_recent_btn">최신순</button>
														</div>
														<div class="btn_box">
															<button class="write_btn choi_btn" type="button"
																id="comm_likes_btn">추천순</button>
														</div>
														<div class="flex_space mob_hidden"></div>
													</div>
													<div class="comm_list"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="footer_area">
					<div class="footer">
						<div class="wrap">
							<div class="footer_info">
								<ul class="footer_link">
									<li class="personal"><a href="javascript:;">개인정보처리방침</a></li>
									<li><a href="javascript:;">이용약관</a></li>
								</ul>
								<ul class="footer_address">
									<li>서울 동대문구 한빛로 12 <br class="mob_visible">5층 505호
									</li>
									<li>Tel : 010-5482-9107</li>
								</ul>
							</div>
							<div class="footer_service">
								<strong class="service_center"><span class="ico_chat">고객센터</span>010-5482-9107</strong>
								<ul class="copy_desc">
									<li class="footer_copy">Copyright Team HoduSnack. All Right
										Reserved</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<script>
					$(document).ready(function () {
						get_comm_list("default");
					});
				</script>
			</body>

			</html>