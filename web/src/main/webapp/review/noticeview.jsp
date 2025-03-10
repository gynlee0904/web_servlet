<%@page import="javax.imageio.plugins.tiff.GeoTIFFTagSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String subject = (String)request.getAttribute("subject");
	String writer = (String)request.getAttribute("writer");
	String ntext = (String)request.getAttribute("ntext");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250221 -->
<title>게시판 내용확인</title>
<style>
	.n{
		width : 500px;
		height : 300px;
		border : 1px solid black;
		overflow-y : auto;
	}
</style>
</head>
<body>
제목 : <%= subject%> <br>
글쓴이 : <%= writer%> <br>
내용 : <div class="n"> <%= ntext%> </div>
<%-- 내용 : <%= request.getAttribute("ntext") %> 바로 이렇게 쓸수도 있음  --%>
</body>
</html>