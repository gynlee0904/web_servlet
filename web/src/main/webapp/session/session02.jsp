<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	HttpSession se = (HttpSession)request.getAttribute("se"); 
	//↑컨트롤러에서 세션을 받은 후 ↓해당 세션값을 문자열로 변환
	String id = (String)se.getAttribute("id");  //세션에 저장된 키값을 가져옴 
	String name = (String)se.getAttribute("name");
	String tel = (String)se.getAttribute("tel");
// 	String tel = se.getAttribute("tel").toString();  //이렇게 쓰면 세션 삭제 후 새로고침하면 500페이지 뜸 
	if(id==null){  //세션에서 가져오는 값이기 때문에 equals로 쓰면 안먹힘
		out.print("<script> alert('로그인하셔야 합니다.'); location.href='./login_session.html'; </script>");
		//out.print는 한줄안에 다 써넣어야함. +로 연결 안됨 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250224 -->
<title>로그인 정보 출력</title>
</head>
<body>
	<%=name %>님 환영합니다. <br>
	아이디 : <%=id %> <br>
	전화번호 : <%=tel %> <br>
	<br><br>
	<input type="button" value="로그아웃" onclick="logout();">
</body>
<script>
	function logout(){
		if(confirm("로그아웃 하시겠습니까?")){
			location.href="./session03.do";
		}
	}

</script>
</html>