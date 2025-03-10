package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250218 응용문제(선생님코드)
public class idsearch_t extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;  //필드에 선언 
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			
			String username = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String result = ""; 

			//컨트롤러에서 다 거르고 결과값만 던질것. jsp에는 조건문 쓰지 말긔
			if(username.equals("홍길동") && phone.equals("01010041919") && email.equals("hong@naver.com")){
				result = "hong2025";
			}
			else {
				result = "가입정보가 확인되지 않습니다";
			}
			request.setAttribute("result", result);
			RequestDispatcher rd = request.getRequestDispatcher("/idsearch_t.jsp");
			rd.forward(request, response); 
			
		} 
		catch (Exception e) {
			pw.write("<script>"
					+"alert('올바른 값이 전달되지 않았습니다.');"
					+"history.go(-1);"
					+"</script>");
			
			System.out.println(e);
		}
		finally{
			this.pw.close();
		}
		
		
	}

}
