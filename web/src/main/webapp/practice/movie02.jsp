<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> data = (ArrayList)request.getAttribute("data");  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250220 - 영화예매 -->
<title>영화 예매 결과 확인</title>
</head>
<body>
<p>영화 예매 확인</p>
고객명 : <%=data.get(0)%><br>
연락처 : <%=data.get(1)%><br>
영화선택 : <%=data.get(2)%><br>
예매일자 : <%=data.get(3)%><br><br>
<input type="button" value="확인" onclick="movieok();">
</body>
<script src="./movie01.js?v=1"></script>
</html>