package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



//250218 - 하나의 파일을 전송하는 단일방식 
//cos(외부라이브러리다운)사용없이 자체라이브러리를 이용하여 설정
//why?너무 오래된 라이브러리는 보안상 문제가 있음 
@MultipartConfig(  //첨부파일 기능이 있을때만 사용하는 어노테이션 (첨부파일 환경설정 파트). 꼭 붙여야 함  
	fileSizeThreshold = 1024*1024*2,  //한개의 파일 전송 크기 설정(2MB가 최대 한계치로 설정)
	maxFileSize = 1024*1024*5, //파일 최대 크기 용량 설정 (5MB가 최대 한계치로 설정)=>5MB이상의 파일이 첨부되면 서버 죽음 
	maxRequestSize = 1024*1024*100  //한번에 여러개의 파일이 첨부되었을 때, 여러개의 첨부파일 전체의 용량 설정(총 100MB가 최대 한계치로 설정)
)
public class fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			
			Part mfile = request.getPart("mfile");  //프엔에서 전송된 네임값에 해당하는 IO처리
			//File : 자기 피씨, Part : 네트워크
			//Part(클라이언트)  <=> Multipart(서버) 
			String filename = mfile.getSubmittedFileName();  //첨부된 파일의 파일명 가져옴 
			long filesize = mfile.getSize();  //첨부된 파일의 용량크기 
			
			if(filesize>2097152) { //파일크기가 2MB(=2097152byte)보다 클경우 
				this.pw.write("<script>"
								+"alert('파일 첨부 용량은 최대 2MB까지입니다.');"
								+"history.go(-1);"
								+"</script>");
			}else {  //사이즈가 맞으면 웹서버에 파일 저장 
				String url = request.getServletContext().getRealPath("/upload/");
//				System.out.println(url);
				mfile.write(url+filename);
				this.pw.write("<script>"
								+"alert('정상적으로 파일 업로드 되었습니다.');"
								+"history.go(-1);"
								+"</script>");

				/*저장되는 경로 : D:\web_servlet\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\web\
				  웹서버에서 적용된 디렉토리에 저장되도록함. (src경로 x) 
				  WEB IO는 웹전용이미지 디렉토리를 별도로 구성해야만 정상적으로 웹에서 이미지를 확인할 수 있음 (http://localhost:8080/web/upload/wsjung.jpeg)
				  .getServletContext().getRealPath("저장디렉토리"); 
				  
				  APP IO => File => InputStream => OutputStream
				  */
			}
			
			
		} catch (Exception e) {
			this.pw.write("<script>"
							+"alert('올바른 접근이 아닙니다');"
							+"history.go(-1);"
							+"</script>");
			
		}
		finally {
			this.pw.close();
		}
	}

}
