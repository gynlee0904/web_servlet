<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = (String)request.getAttribute("userid");
	String username = (String)request.getAttribute("username");
	String usermail = (String)request.getAttribute("usermail");
	String userphone = (String)request.getAttribute("userphone");
// 	userphone = userphone.substring(0, 3) + "****" + userphone.substring(7);
	//substring(문자배열번호(0~), 해당문자배열번호 앞자리까지(생략시 끝까지 출력))
	if(userphone.length()==11){  //폰번호가 11자리일경우 
		userphone = userphone.substring(0,3) + "****" + userphone.substring(7);
	}else if(userphone.length()==10){  //폰번호가 10자리일경우 
		userphone = userphone.substring(0,3) + "***" + userphone.substring(6);
	}
	
	String image = (String)request.getAttribute("image");  
	//파일명만 가져온거니까 String으로 받음
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250218 -->
<title>회원가입 완료</title>
</head>
<body>
	<p>회원가입이 완료되었습니다</p>
	아이디 : <%=userid %> <br>
	고객명 : <%=username %> <br>
	이메일 : <%=usermail %> <br>
	휴대폰번호 : <%=userphone %> <br>
	
	<!-- 이미지 출력 유무를 결정하기위해 예외적으로 jsp페이지에 조건문 사용 -->
	<% if(image !=""){%>
		첨부파일 : <img src="./upload/<%=image%>" style="width:100px; height:100px;">
	<% }%>
	
<!-- 	JS가 HTML보다 상위언어. JSP가 JS보다 상위언어. -->
<!-- length : 원시배열의크기 / length() : 문자열의 길이   -->
</body>
</html>