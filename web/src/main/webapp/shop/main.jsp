<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	HttpSession hs = request.getSession();
	String mid = (String)hs.getAttribute("mid");
	String mnm = (String)hs.getAttribute("mnm");
	//out.print 썼을때 null출력이 될 경우 (session)=>연산기호 사용 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250305 -->
<title>쇼핑몰 메인화면 </title>
</head>
<body>
<% if(mid== null || mnm ==null){ %>   <!-- 세션에 값이 없을 경우  -->
	<input type="button" value="로그인">
<% } else { %>  <!-- 컨트롤러에서 정상적으로 로그인해서 session이 적용된 경우  -->

[<%=mid %>] <%=mnm %>님 환영합니다.  <input type="button" value="로그아웃" onclick="logout()">

<% } %>
</body>
<script>
function logout() {
	location.href='./logout.jsp';
}

</script>
</html>