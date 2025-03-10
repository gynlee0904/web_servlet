package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250217
//HttpServlet : 자바를 웹에서 사용할수 있도록 처리하는 클래스 
public class login extends HttpServlet {
	//serialVersionUID : 백엔드측 가상의 페이지 일련번호 
	private static final long serialVersionUID = 1L;
       
	//doGet, doPost: 프론트에서 form태그에 method를 설정한 사항과 동일한 정보를 처리하는 메소드 
	//form태그의 method를 get으로 쓰면 doGet메소드 작동, post로  쓰면 doPost 메소드 작동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//request  : 프론트엔드에게 전달받는 값. 
	//HttpServletRequest : 데이터 전달 통신 규격 인터페이스 
	//response : 백엔드가 처리한 결과값을 출력하는 역할. 
	//HttpServletResponse : 결과에 대한 응답 통신 규격 인터페이스 
		
		request.setCharacterEncoding("utf-8");  //프론트에서 한글 작성해서 전달시 한글 깨짐 방지 
		response.setCharacterEncoding("utf-8");  // 출력결과에서 한글 깨짐 방지 
		response.setContentType("text/html");
		//text/html : do를 html화 시킨다는 뜻 
		//프론트에서 한글 입력되어 전송시 한글이 깨지기 때문에 상단에 작성 
		
		String mid = request.getParameter("mid");  //("")안에는 네임명 
		//getParameter : 프론트엔드에서 전달되는 name값을 가져옴. 사용자에게 직접 입력받은 값만 가져옴.
		//넘어오는 값은 무조건 String임.
		String mpass = request.getParameter("mpass");
		
		PrintWriter pw = response.getWriter();  
		//PrintWriter => java에서 JS를 핸들링할 수 있게함 
		//response.getWriter() : 현재 페이지에서 문자로 결과값을 출력시킴  
		
		if(mid.equals("hong") && mpass.equals("1234")) {
			pw.write("<script>"+"alert('정상적으로 로그인했습니다');"+"</script>");
		}
		else {
			pw.write("<script>"+"alert('아이디 및 패스워드를 다시 확인하세요');"+"location.href='./login.html';"+"</script>");
		}
		
	
	}

}
