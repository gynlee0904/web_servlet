package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class part1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		this.pw = response.getWriter();
//		String all = request.getParameter("all");
//		String required [] = request.getParameterValues("required");
//		String option = request.getParameter("optional");
		String policy [] = request.getParameterValues("policy");
		
		try {
//			request.setAttribute("all", all);
//			request.setAttribute("required", required);
//			request.setAttribute("option", option);
			
			request.setAttribute("policy", policy);
			
			RequestDispatcher rd = request.getRequestDispatcher("/part2.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			pw.write("<script>"
					  +"alert('올바른 접근이 아닙니다');"
					  +"</script>");
		}
		finally {
			pw.close();
		}
		
	}

}
