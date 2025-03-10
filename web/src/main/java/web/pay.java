package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250217
//Controller : 프엔에서 전송된 값 처리 및 연산 후 View로 전달하는 역할 
public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setCharacterEncoding("utf-8");  
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		try {
			//프엔에서 전송된 값을 받는 역할 
			String m = request.getParameter("money");
			String s = request.getParameter("sales");
			String p = request.getParameter("point");
			
			int product_m = Integer.parseInt(m);
			int product_s = Integer.parseInt(s);
			int product_p = Integer.parseInt(p);
			//연산을 위해 숫자형으로 변환 
			
			//받은 데이터를 기반으로 사칙연산 
			int total = (product_m-(product_m*product_s/100))-product_p;
			System.out.println("최종결제금액 : " + total);
		
			//request.setAttribute : 컨트롤러에서 뷰로 전송하기위한 세팅(.jsp에서 사용할 수 있도록 함)
			request.setAttribute("product_m", product_m);
			request.setAttribute("product_s", product_s);
			request.setAttribute("product_p", product_p);
			request.setAttribute("total", total);
			
			//RequestDispatcher : jsp로 이관하는 역할. view를 선언하여 웹페이지에서 출력되도록 설정 
			RequestDispatcher rd = request.getRequestDispatcher("/pay.jsp");
			rd.forward(request, response);  //jsp페이지로 포워딩(컨트롤러에서 사용된 정보를 jsp로 이관) 
			
		} catch (Exception e) {  
			//프엔에서 보낸값이 숫자로 변환되지 못하고 비정상적일경우 해당 스크립트 발동
			pw.write("<script>"
						+"alert('올바른 값이 전달되지 않았습니다.');"
						+"history.go(-1);"
						+"</script>");
			//history.go(-1): 이전페이지로 돌아가기. (-2)이면 전전페이지로 돌아감
			System.out.println(e);
		}
		
	
	}

}
