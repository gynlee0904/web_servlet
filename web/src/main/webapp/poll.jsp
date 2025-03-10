<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String word = request.getAttribute("abc").toString();  //=>""
// 	String word = (String)request.getAttribute("abc");  //=>null or ""
// 	String word = String.valueOf(request.getAttribute("abc")); //=>null or ""
// 	out.print(word);

	String subject = (String)request.getAttribute("subject");
	String etc [] = (String[])request.getAttribute("etc");  //배열로 던진것 배열로 받아야 함 
	//같은 name값을 가진 
// 	out.print(etc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>250218 - 설문조사 결과값</title>
</head>
<body>
선택하신 과목 : <%=subject %> <br>

배우고 싶은 과목 : <%
	int w = 0;
	while(w<etc.length){  //컨트롤러에서 원시배열로 값을 이관받은 데이터를 반복문으로 view에서 처리
		out.print(etc[w] + " ");
		w++;
	} 
%>
</body>
</html>