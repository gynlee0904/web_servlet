<%@page import="mallpage.m_productDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function errmsg(){
		alert("해당상품의 정보가 올바르지 않습니다");
		location.href="./product_list.do";
	}
</script>
<%
	m_productDTO pd = (m_productDTO)request.getAttribute("dto");
// 	out.print(pd.getPnm());
	
	if(pd.getMidx()== 0){
		out.print("<script>errmsg();</script>");  //<script defer> : defer 작성하면 script가 아래에 있어도 정상적으로 작동함
	}
%>
<!-- JSP > Javascript > HTML -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세페이지</title>
</head>
<body>
<img src=".<%=pd.getPimg()%>"> <br>
상품명 : <%=pd.getPnm()%> <br>
상품가격 : <%=pd.getPmoney()%> <br>
상품 할인율 : <%=pd.getPsales()%> <br>
할인금액 : <%=pd.getPsmoney()%> <br>
<input type="button" value="상품리스트" onclick="location.href='./product_list.do';">
</body>

</html>