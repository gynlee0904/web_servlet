<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String product [] = (String [] )request.getAttribute("product");  //선택한 제품 
	ArrayList<ArrayList<String>> pName2 = (ArrayList)request.getAttribute("pName2");
	String total = (String)request.getAttribute("total");  //최종결제금액
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250219 -->
<title>basket_part2</title>
</head>
<style>
	li {
		 list-style-type : none;
	}
</style>
<body>
<p>구매확정 물품</p>

<form id="frm" method="post" action="http://172.30.1.49:8080/web/payok.do">
	<ul id="plist">
	<%
		int f=0;
		while(f < pName2.size()){
			out.print("<li>" + pName2.get(f).get(0) + " " + pName2.get(f).get(1) + "원</li>");
			f++;
		}
	%>
	</ul>

	<br>
	최종결제금액 : <input type="text" name="total" value="<%= total %>" style="text-align:right" readonly>원 <br>
	결제수단 : <br>
	<input type="radio" name="payby" value="신용카드">신용카드 <br>
	<input type="radio" name="payby" value="가상계좌">가상계좌 <br>
	<input type="radio" name="payby" value="휴대폰결제">휴대폰 결제 <br>
	<input type="radio" name="payby" value="무통장입금">무통장 입금 <br>
	<br><br>
	<input type="button" value="결제진행" onclick="go_part3();">
</form>
</body>
<script>
	var go_part3 = function () {
		var payby = frm.payby;
		var count=0;
		var f;
		for(f=0; f<payby.length; f++){
			if(payby[f].checked==true){
				count++;
			}	
		}
		if(count>0){
			frm.submit();
		}else{
			alert("결제수단을 선택하세요.");
		}
	}
</script>
</html>