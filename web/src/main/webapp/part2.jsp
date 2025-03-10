<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String all = (String)request.getAttribute("all");
// 	String required [] = (String[])request.getAttribute("required");
// 	String option = (String)request.getAttribute("option");
	String policy [] = (String[])request.getAttribute("policy");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250218 -->
<title>회원가입 정보입력 (간편가입)</title>
</head>
<body>
	<p>회원가입 정보입력 (간편가입)</p>
<!-- 	<form id="frm" method="post" action="http://172.30.1.15:8080/web/agreeok.do" enctype="multipart/form-data"> -->
	<form id="frm" method="post" action="./part2.do" enctype="multipart/form-data">
		아이디 : <input type="text" name="userid" required> <br>
		고객명 : <input type="text" name="username" required> <br>
		비밀번호 : <input type="password" name="userpass" required> <br>
		이메일 : <input type="email" name="usermail" pattern="[a-zA-Z0-9._-]+@" required> <br>
		휴대폰번호 : <input type="tel" name="userphone" pattern="(010|011)\d{3,4}\d{4}" name="phone" maxlength="11" required> <br>
		이미지 : <input type="file" name="ufile" > <br>
		<input type="button" value="가입완료" onclick="go()">
	</form>
</body>
<script>
	function go() {
// 		console.log("te");
		frm.submit();
	}
	
	var w = 0;
	while(w<policy.length){ 
		console.log(policy);
		console.log(policy[w] + " ");
		w++;
	} 
	

</script>
</html>