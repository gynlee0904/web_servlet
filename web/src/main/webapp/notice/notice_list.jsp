<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ArrayList<String>> notice = (ArrayList<ArrayList<String>>)request.getAttribute("result");

	//페이지 번호 생성 
	String total_page = notice.get(0).get(5);
	int pg = 1;
	if(total_page != null || !total_page.equals("1")){  //게시물이 하나라도 있을때
		float pg2 = Integer.parseInt(total_page)/3f;
		pg = (int)Math.ceil(pg2);
		
// 		out.print(pg);

	}
	
	/*페이징 생성 방법 
	  1. 한페이지 당 데이터를 몇개씩 출력할건지 설정 
	  2. 데이터베이스에 있는 데이터의 총갯수/한페이지당 개수 (=>소수점)
	  3. Math.ceil 사용하는 이유는 소수점으로 나올시 반올림으로 페이지가 추가되도록 하기 위함.
	*/
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250306 -->
<title>공지사항 리스트</title>
</head>
<body>
	<p>현재 등록된 게시물 : <%= notice.get(0).get(5) %> 개</p>
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
	<br><br><br>
	<table border="1">
		<tr>
		<% 
			int ww =1;
			while(ww<=pg){
		%>
			<td width=20 height=20 align="center"><a href="./notice_list.do?pageNo=<%=ww %>"><%=ww %></a></td>
		<%
				ww++;
			}
			
		%>
		</tr>
	</table>
</body>
<script>
//해당 게시물의 내용 및 첨부파일 확인할 수 있는 view페이지
function notice_view(no){
	location.href='./notice_view.do?nidx='+no;
}
</script>
</html>