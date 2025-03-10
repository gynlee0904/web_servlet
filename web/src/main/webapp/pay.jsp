<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int product_m = (int)request.getAttribute("product_m");
	int product_s = (int)request.getAttribute("product_s");
	int product_p = (int)request.getAttribute("product_p");
	int total = (int)request.getAttribute("total");
	//getAttribute : 컨트롤러에서 setAttribute로 생성된 값을 받아옴 
	//기본데이터형이 바이트로 전달받음(통신이니까). 이 페이지에서 mm변수로 받으려면 mm으로 형변환 해줘야 함 
	//getAttribute("콘트롤러에서 setAttribute할때 설정한 키값")

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>270217 - 결제진행상황</title>
</head>
<body>
<%-- <%= %> = <% out.print(product_m) %> --%>
<!-- jsp페이지에서 오류나면 controller에서 오류난것과 같음.
	컨트롤러에서 jsp페이지를 불러서 자식으로 데리고 있기 때문에.
	try~catch나 syso 찍어서 어디서 잘못된건지 찾아야함 -->
상품금액 : <%=product_m %> 원<br>
할인율 : <%=product_s %> % <br>
적립금 : <%=product_p %> 원  <br>
최종결제금액 : <%=total %> 원<br>
<input type="button" value="결제하기">
</body>
</html>