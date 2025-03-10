<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>250218 응용문제(선생님코드)</title>
</head>
<body>

	아이디 : <%=result %> 
<!-- jsp에는 조건문 쓰지 말긔 => 모두 컨트롤러에서 해결한다.-->
</body>
</html>