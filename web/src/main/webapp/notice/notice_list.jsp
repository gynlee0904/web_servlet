<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ArrayList<String>> notice = (ArrayList<ArrayList<String>>)request.getAttribute("result");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250306 -->
<title>공지사항 리스트</title>
</head>
<body>
	<p>현재 등록된 게시물 : <%= notice.size() %> 개</p>
	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="50">번호</th>
				<th width="500">제목</th>
				<th width="100">글쓴이</th>
				<th width="100">조회</th>
				<th width="150">등록일</th>
			</tr>
		</thead>
		<tbody>
		<% 
			int total = notice.size();
			int w= 0;
		   	while(w<notice.size()){ 
		%>
			<tr height="30" align="center">
				<td ><%= total %></td>
				<td align="left" onclick="notice_view(<%=notice.get(w).get(0)%>)"><%= notice.get(w).get(1)%></td>
				<td ><%= notice.get(w).get(2)%></td>
				<td ><%=notice.get(w).get(3) %></td>
				<td ><%= notice.get(w).get(4).substring(0,10)%></td>
			</tr>
		<% 
			total--;
			w++;
		   } 
		%>
		</tbody>
	</table>
</body>
<script>
//해당 게시물의 내용 및 첨부파일 확인할 수 있는 view페이지
function notice_view(no){
	location.href='./notice_view.do?nidx='+no;
}
</script>
</html>