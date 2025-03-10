package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250217 응용문제
public class idsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setCharacterEncoding("utf-8");  
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		try {
			String username = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String result = "";
			
			long phone0 = Long.parseLong(phone); //검토할때만 long으로 체크, jsp로 넘길때는 String으로.
			
			
			if(username.equals("홍길동") && phone.equals("01010041919") && email.equals("hong@naver.com")){
				result = "hong2025";
				request.setAttribute("result", result);
				RequestDispatcher rd = request.getRequestDispatcher("/idsearch.jsp");
				rd.forward(request, response); 
			}
			else {
				result = "가입정보가 확인되지 않습니다";
				request.setAttribute("result", result);
				RequestDispatcher rd = request.getRequestDispatcher("/idsearch.jsp");
				rd.forward(request, response); 
			}
		} 
		catch (NumberFormatException ne) {
			pw.write("<script>"
					+"alert('연락처는 숫자만 입력하세요.');"
					+"history.go(-1);"
					+"</script>");
			System.out.println(ne);
		}
		catch (Exception e) {
			pw.write("<script>"
					+"alert('올바른 값이 전달되지 않았습니다.');"
					+"history.go(-1);"
					+"</script>");
			
			System.out.println(e);
		}
		
		
	}

}
