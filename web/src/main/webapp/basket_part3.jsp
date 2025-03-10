<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String total = (String)request.getAttribute("total");  //최종결제금액
	String payby = (String)request.getAttribute("payby");  //결제수단 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250219 -->
<title>basket_part3</title>
</head>
<body>

<form id="frm" method="post" action="http://172.30.1.49:8080/web/mallok.do">
	최종 결제금액 : <%=total%> 원 <br>
	결제수단 : <%= payby %> <br>
<% 	if(payby.equals("무통장입금")){ %>
		계좌번호 : 02-1234-56789 <br>
		입금자명 : <input type="text" name="payname">
<% 	}%>	
	<br><br>
	<input type="button" value="결제완료" onclick="go_mallok()">
</form>
</body>
<script>
	var payname = frm.payname;
	var paymethod = "<%=payby%>";
	
	var go_mallok = function(){
		if(paymethod=="무통장입금" && payname.value==""){
			alert("입금자명을 입력하세요.");
			payname.focus();
		}else{
			frm.submit();
		}
	}

</script>
</html>