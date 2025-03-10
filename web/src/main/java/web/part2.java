package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//250218
@MultipartConfig(   
	fileSizeThreshold = 1024*1024*2,  
	maxFileSize = 1024*1024*5,  
	maxRequestSize = 1024*1024*100  
)
public class part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String userpass = request.getParameter("userpass");
			String usermail = request.getParameter("usermail");
			String userphone = request.getParameter("userphone");
			
			String url = request.getServletContext().getRealPath("/upload/");
			String uplodedfile = null;
			
			Collection<Part> memberjoin = request.getParts();
			
			for(Part p : memberjoin) {
				String filename = p.getSubmittedFileName();
				long filesize = p.getSize();
				
				if(filename != null && !filename.isEmpty() && filesize < 2097152) {  
					//파일명이 있을경우 + 파일용량이 2MB이하일 경우
					//조건이 맞으면 받은 파일 저장
					uplodedfile = filename;
					p.write(url+filename);  
				}
			}
			//입력정보 jsp로 전달
			request.setAttribute("userid", userid);
			request.setAttribute("username", username);
			request.setAttribute("usermail", usermail);
			request.setAttribute("userphone", userphone);
			request.setAttribute("uplodedfile", uplodedfile);
			
			RequestDispatcher rd = request.getRequestDispatcher("/part3.jsp");
			rd.forward(request, response); 
			
			this.pw.write("<script>"
						+"alert('가입 완료 되었습니다.');"
						+"</script>");
			
		} catch (Exception e) {
			pw.write("<script>"
					+"alert('올바른 접근이 아닙니다.');"
					+"history.go(-1);"
					+"</script>");
			
			System.out.println(e);
		}
		finally{
			this.pw.close();
		}
	}

}
