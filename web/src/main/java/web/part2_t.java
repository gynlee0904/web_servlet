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

//250219
@MultipartConfig(   
	fileSizeThreshold = 1024*1024*2,  //한개의 파일 최대 용량 
//	maxFileSize = 1024*1024*5,  //프엔에서 검토를 했으면 써도됨. 못했으면 쓰면 안됨 
	maxRequestSize = 1024*1024*100  //여러개의 파일 전송시 총 용량 
)
public class part2_t extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	String url = null;  //웹 경로를 저장하는 변수 
	String filename = null;  //파일명을 저장하는 변수 
	
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
			Part ufile = request.getPart("ufile");
			
			//제대로 값 넘어왔는지 우선적으로 검토 먼저 할것  
			if(userid.equals("")
				|| username.equals("")
				|| userpass.equals("")
				|| usermail.equals("")
				|| userphone.equals("")) {  //필수입력란 하나라도 공란일 경우 
				
				this.pw.write("<script>"
						+"alert('올바른 접근이 아닙니다.');"
						+"history.go(-1);"
						+"</script>");
				
			}else {  //사용자 정보가 모두 정상 입력되었을 때 => 검토 끝난 이후에 io가 있는지없는지 확인 
				this.filename = ufile.getSubmittedFileName();
				//jsp에서도 이미지 확인하려면 String url, String filename 변수선언을 필드에 해야함.
				if(this.filename != null && this.filename != "") {  //값이 제대로 넘어왔을경우 
					//★파일 첨부가 안되었을경우도 확인하기 위해 this.filename != "" 도 작성해야함
					long filesize = ufile.getSize();
					if(filesize > 2097152) {  //첨부파일사이즈가 2MB이상일 경우
						this.pw.write("<script>"
									+"alert('첨부파일 용량은 최대 2MB까지입니다.');"
									+"history.go(-1);"
									+"</script>");
					}else {  //첨부파일사이즈가 2MB이하일 경우 파일 저장 
						this.url = request.getServletContext().getRealPath("/upload/");
//						System.out.println(request.getServletContext().getRealPath(""));
						//웹서버 경로 확인 해볼것
						ufile.write(this.url+this.filename);  
					}
				}
			}
	
			//입력정보값 jsp로 전달
			request.setAttribute("userid", userid);
			request.setAttribute("username", username);
			request.setAttribute("usermail", usermail);
			request.setAttribute("userphone", userphone);
			request.setAttribute("image", this.filename);  //첨부파일 이미지 파일명 전송
			
			RequestDispatcher rd = request.getRequestDispatcher("/part3.jsp");
			rd.forward(request, response); 
			
			this.pw.write("<script>"
						+"alert('가입 완료 되었습니다.');"
						+"</script>");
			
		} catch (Exception e) {
			this.pw.write("<script>"
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
