package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250219
//payok.do (basket_part2)
public class payok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			
			String total = request.getParameter("total");  //최종결제금액값
			String payby = request.getParameter("payby");  //결제수단
			
		
			request.setAttribute("total", total);
			request.setAttribute("payby", payby);
				
			
			RequestDispatcher rd = request.getRequestDispatcher("/basket_part3.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			pw.write("<script>"
					  +"alert('시스템 오류로 인해 데이터 처리가 되지 않았습니다.');"
					  +"</script>");
			System.out.println(e);
		} finally {
			pw.close();
		}
	
	}

}
