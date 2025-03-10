package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//250224
//session01 - session을 생성하는 컨트롤러 
/* session : 일정시간 동안 브라우저에 해당 값을 저장 및 유지시키는 방식 (Back-end).보안 가장 좋음
   cookie : 브라우저에 cache메모리에 도메인 및 IP기준으로 값을 저장하는 방식 (FE + BE)
   storage : 메모리에 저장 (FE + BE) - local storage, session storage. 보안 제일 안좋음  */
public class session01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String mpass = request.getParameter("mpass");
	
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		if(mid.equals("hong") && mpass.equals("a1234")) {  //정상적으로 로그인 되었을 때 세션 만듦 
			String username = "홍길동";
			String usertel = "01009041011";
			
			HttpSession se = request.getSession();  //세션 만들기 시작 
			//HttpSession : 브라우저 cache메모리에 데이터를 임시저장 
			
			//세션에 저장하는건 아이디와 유저이름. ★★패스워드는 절대 저장하지 않는다 
			se.setAttribute("id", mid);  //세션(브라우저)에 정보 저장 (세션 생성)
			se.setAttribute("name", username);
			se.setAttribute("tel", usertel);
			
			/*브라우저 모두 닫으면 세션 파기됨(초기화),
			  탭 일부만 닫으면 초기화 안됨  
			  다른 브라우저에는 정보 저장 안됨(브라우저별로 따로 놂) 
			 */    
			
			
			
			
		}else {  //아이디 및 패스워드가 틀릴경우 세션 안만듦 
			this.pw.write("<script>"
							+"alert('아이디 및 패스워드를 확인하세요.');"
							+"history.go(-1);"
							+"</script>");
		}
	
	
	}

}
