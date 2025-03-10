<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> views = (ArrayList)request.getAttribute("notice_v");
	
	if(views==null){  //데이터가 없을 경우
		out.print("<script>alert('올바른 접근이 아닙니다'); location.href='./notice_list.do'; </script>");
	}
	else{  //데이터가 있을경우 (=null이 아닐경우)
	//html자체를 else{}안에 넣음 
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250306 -->
<title>공지사항 내용 확인</title>
<style>
	.data1{
		width: 400px;
		height : 30px;
		line-height : 30px;
		border-bottom : 1px solid gray;
	}
	
	.n{
		display : flex;
		flex-direction : row;
		justify-content : flex-start;
		align-content : center;
	}
	
	.data2{
		border : 1px solid grey;
		width : 800px;
		height: 400px;
		overflow-y : auto;
	}

</style>
</head>
<body>
	<span class="n"> 등록일 : <div class="data1"><%= views.get(8) %></div> </span> <br>
	<span class="n"> 제목 : <div class="data1"><%= views.get(1) %></div> </span> <br>
	<span class="n"> 글쓴이 : <div class="data1"><%= views.get(2) %></div> </span> <br>
	<span class="n"> 조회수 : <div class="data1"><%= views.get(7) %></div> </span> <br>
	<span class="n"> 내용: <div class="data2"><%= views.get(4) %></div> </span><br>
	
	<%
		if(views.get(5) != null) {  //첨부파일이 있을 경우만 해당 태그가 보이도록 설정 
	%>
	<span class="n"> 첨부파일 : <div class="data1"><a href="../notice_file/<%= views.get(5)%>" target="_blank" ><%= views.get(5) %></a></div> </span> <br> 
	<%
		}
	%>
	
	<br><br>
	<form id="frm" method="post" action="./notice_delete.do">
		패스워드 : <input type="password" name="npw">  <!-- 사용자가 게시물 삭제 위해 입력한 패스워드 값  -->
		<input type="hidden" name="ori_pw" value="<%=views.get(3)%>">  <!-- db에 저장된 md5 암호화 패스워드  -->
		<!-- 패스워드 대조 후 게시물 삭제 -->
	
		<input type="hidden" name="nidx" value="<%=views.get(0)%>">  <!-- 게시글의 고유번호 --> 
	</form>
	
	<input type="button" value="글목록" onclick="notice_info(1);">
	<input type="button" value="글수정" onclick="notice_info(2);">
	<input type="button" value="글삭제" onclick="notice_info(3);">
</body>

<script>
	function notice_info(p){
		switch(p){
		case 1 : 
			location.href='./notice_list.do';
			break;
			
		case 2: 
			location.href='./notice_modify.do';
			break;
			
		case 3:
			if(confirm("삭제 후 복구 불가합니다.")){
// 				location.href='./notice_delete.do';
				if(frm.npw.value==""){
					alert("게시물 삭제시 패스워드를 입력하셔야 합니다.");
				}else { 
					frm.submit();
				}
			}
			break;
		}
	}

</script>
</html>
<%
	}  //else 닫는중괄호
%>