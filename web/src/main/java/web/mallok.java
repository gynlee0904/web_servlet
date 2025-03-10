package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250219
//mallok.do (basket_part3)
public class mallok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;     

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			
			pw.write("<script>"
					  +"alert('정상적으로 결제가 완료되었습니다.');"
					  +"</script>");
			

		} catch (Exception e) {
			pw.write("<script>"
					  +"alert('시스템 오류로 인해 데이터 처리가 되지 않았습니다.');"
					  +"</script>");
			System.out.println(e);
		}
		finally {
			pw.close();
		}
	}

}
