package practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250306
//mail 컨트롤러
public class mailok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");	
		try {
			this.pw =response.getWriter();
			
			String to_name = request.getParameter("to_name");
			String to_mail = request.getParameter("to_mail");
			String subject = request.getParameter("subject");
			String context = request.getParameter("context");
			String senddate = request.getParameter("senddate");
			
			m_mailok sm = new m_mailok(to_name, to_mail, subject, context, senddate);
			int result = sm.result; 
			if(result>0) {
				this.pw.write("<script>"
							+"alert('메일이 발송되었습니다.');"
							+"location.href='./mail.html';"
							+"</script>");
			}
			
		} catch (Exception e) {
			this.pw.write("<script>"
						+"alert('메일 발송에 실패했습니다.');"
						+"history.go(-1);"
						+"</script>");
			System.out.println(e);
		}finally {
			this.pw.close();
		}
		
	}

}
