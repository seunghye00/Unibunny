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
					<jsp:include page="../../common/header.jsp" />
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
																<button class="option_btn mark_option" id="bookmark_btn" type="button">
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
															<div class="bookmark"></div>
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
															<p id="board_like"></p>
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
					<jsp:include page="../../common/footer.jsp" />
				</div>
				
				<script>
					$(document).ready(function () {
						get_options_record();
						get_file_list("board");
						get_comm_list("default");
						get_user_record();
					});
				</script>
			</body>

			</html>