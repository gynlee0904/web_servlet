<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>250217 응용문제</title>
</head>
<body>

	<p>아이디 : <%=result %> </p>
<!-- jsp에는 조건문 쓰지 말긔 => 모두 컨트롤러에서 해결한다.-->
</body>
</html>