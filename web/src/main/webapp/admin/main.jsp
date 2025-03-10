<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250220 -->
<title>관리자 메인페이지</title>
</head>
<body>
<header>
<%-- 	<%@ include file="./top.jsp"  %> --%>
	<jsp:include page="./top.jsp" flush="true"></jsp:include>
	<!-- file과 page는 둘 다 외부 페이지를 가져옴 but 둘의 선언방법이 다름(코드모양이 다름) -->
</header>
<section>
	<%@ include file="./product.jsp"  %>
</section>
<footer>
<%-- 	<%@ include file="./copyright.jsp"  %> --%>
	<jsp:include page="./copyright.jsp" flush="true"/>
</footer>
</body>
</html>