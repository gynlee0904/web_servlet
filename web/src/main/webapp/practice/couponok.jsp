<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String mid = (String)request.getAttribute("mid");
	String cno = (String)request.getAttribute("cno");
	String add_agree = (String)request.getAttribute("add_agree");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250228 -->
<title>쿠폰 등록 완료</title>
</head>
<body>
<p>쿠폰 등록 완료페이지</p>
아이디 : <%= mid %> <br>
쿠폰번호 : <%= cno %> <br>
광고수신 <%= add_agree %>함 
</body>
</html>