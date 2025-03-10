package review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(  
		fileSizeThreshold = 1024*1024*2,  
		maxFileSize = 1024*1024*5, 
		maxRequestSize = 1024*1024*100  
	)
public class io_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;     
   
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String url = request.getServletContext().getRealPath("/notice/");
//			System.out.println(request.getServletContext().getRealPath(""));
			
			Collection<Part> p = request.getParts();
			for(Part f : p) {
				String filename = f.getSubmittedFileName();
				long filesize = f.getSize();
				if(!filename.equals("") && filesize < 2097152) {  //파일명이 있을경우 + 파일용량이 2MB이하일 경우 
					f.write(url+filename);  //조건이 맞으면 받은 파일 저장 
				}
			}
			this.pw.write("<script>"
							+"alert('정상적으로 파일 업로드 되었습니다.');"
							+"history.go(-1);"
							+"</script>");
			
		} catch (Exception e) {
			this.pw.write("<script>"
						+"alert('올바른 접근이 아닙니다');"
						+"history.go(-1);"
						+"</script>");
		} finally {
			this.pw.close();
		}
		
		
	}

}
