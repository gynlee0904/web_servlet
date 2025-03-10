package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//250218 - 여러개의 파일을 전송하는 방식 
@MultipartConfig(   
	fileSizeThreshold = 1024*1024*2,  
//	maxFileSize = 1024*1024*5, //파일크기제한 삭제해도됨 
	maxRequestSize = 1024*1024*100  
	//필요에 맞춰 작성하면 됨 
)
public class fileupload_multi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;  
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String url = request.getServletContext().getRealPath("/upload/");  //받은 파일 저장할 경로 설정 
			
			Collection<Part> p = request.getParts();
			//Collection : 모든 name값을 다 받을 수 있음. 
			//getParts : 원시배열이 아니라 컬렉션 구조를 가지고 있음
			//name을 따로 설정하지 않으며, 배열클래스를 이용하여 IO값을 핸들링함 
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
			System.out.println(e);
		} finally {
			this.pw.close();
		}
	
	}

}
