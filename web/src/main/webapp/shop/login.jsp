<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	HttpSession hs = request.getSession();
	String mid = (String)hs.getAttribute("mid");
	String mnm = (String)hs.getAttribute("mnm");
	if(mid!=null || mnm != null){    //로그인이 되어있을
		out.print("<script>"
					+"alert('이미 로그인되어있습니다');"
					+"location.href='./main.jsp';"
					+"</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250304 -->
<title>회원 로그인</title>
</head>
<body>
<form id="frm" method="post" action="./shop_loginok.do" onsubmit="return loginck()">

	<p>회원 로그인</p>
	<div>
		<label><input type="radio" name="spart" value="P" checked onclick="part_check(this.value)">일반회원 </label>
		<label><input type="radio" name="spart" value="C" onclick="part_check(this.value)">사업자 회원 </label>
		<!-- <label>에 onclick쓰면 더블이벤트 발생함. -->
		<br><br>
		
		아이디 : <input type="text" name="sid" placeholder="아이디를 입력하세요"> <br>
		패스워드 : <input type="password" name="spw" placeholder="비밀번호를 입력하세요"> <br> 
		<span id="corp" style="display:none;">
		사업자 등록번호 :  <input type="text" name="sno" placeholder="사업자등록번호">
		</span> <br> 
		<br> 
		<input type="submit" value="로그인"> 
	</div>
</form>

</body>
<script src="./login.js?v=1"></script>
</html>