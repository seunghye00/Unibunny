<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/sub.css">
<link rel="stylesheet" href="../../css/layout.css">
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
						<div class="con_faq_con">
							<div class="title_box">
								<p class="title">고객센터</p>
							</div>
							<div class="agent_list">
								<li><a href="/list.faq">FAQ</a></li>
								<li><a href="/user/crud/qna_write.jsp">Q&A</a></li>
							</div>
							<div style="padding: 20px;"></div>
							<div class="list_table faq_table">
								<ul id="faqList">
									<c:forEach var="faq" items="${faqList}">
										<li class="faq"><span class="faq-title">${faq.title}</span>
											<span class="faq-text">${faq.content}</span>
											<button class="faq-toggle">
												<i class="fas fa-chevron-down"></i> <i class="fas fa-times"></i>
											</button></li>
									</c:forEach>
								</ul>
							</div>
							<div></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../../common/footer.jsp" />
	</div>

	<script>
		$(document).ready(function() {
			// FAQ 토글!
			$('.faq-toggle').on('click', function() {
				var $faq = $(this).closest('.faq');
				$faq.toggleClass('active');
			});
		});
	</script>

</body>
</html>
