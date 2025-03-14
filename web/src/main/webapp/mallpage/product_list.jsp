<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ArrayList<String>> all = (ArrayList<ArrayList<String>>)request.getAttribute("all");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품리스트 출력파트</title>
</head>
<body>
<table>
	<%
		int w=0;
		while(w<all.size()){
			DecimalFormat df = new DecimalFormat("###,###,###");
			//DecimalFormat : 숫자만 먹음 (문자 x)
			//java v8.x 밑은 NumberFormat 사용
	%>
	<tr onclick="product_view('<%=all.get(w).get(0)%>');">
		<td><img src=".<%=all.get(w).get(6)%>"></td>
		<td><%=all.get(w).get(2)%> </td> <!-- 상품명 -->
		<% if(!all.get(w).get(4).equals("0")){ %>
		<td><s><%=df.format(Integer.parseInt(all.get(w).get(3)))%> 원</s></td> <!-- 상품금액 -->
		<% }else{ %>
			<td><%=df.format(Integer.parseInt(all.get(w).get(3)))%> 원</td>
		<% } %>
		<!-- 태그적용시에는 <td>안에 씀  -->
		
		<% if(!all.get(w).get(4).equals("0")){ %>
		<td><%=all.get(w).get(4)%> %</td> <!-- 할인율 -->
		<td><%=df.format(Integer.parseInt(all.get(w).get(5)))%> 원</td> <!-- 할인금액 -->
		<% } %>
	</tr>
	<%
			w++;
		}
	%>
</table>
</body>
<script>
	function product_view(midx){
		location.href="./product_list.do?midx="+midx;
		//SPA방식 (React, Vue, ECMA에서 주로  씀)
	}

</script>
</html>