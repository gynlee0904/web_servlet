package mallpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//250312
@WebServlet("/mallpage/loginok.do")
public class loginok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter pw = null;
    m_memberDTO mb = new m_memberDTO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		this.mb.setMid(request.getParameter("mid"));
		this.mb.setMpass(request.getParameter("mpass"));

		/*
		String result = new m_copylight().user_login(this.mb);
		m_memberDTO mb2 = new m_copylight().mb; 
		*/ 
		//=>new 때리면 초기화되기때문에 모델에서 dto에 set한값을 못가져옴 
		
		m_copylight cr = new m_copylight();
		String result = cr.user_login(this.mb);  //컨트롤러에서 모델로 값을 전송  
		m_memberDTO mb2 = cr.mb;   //모델에서 적용된 dto값을 컨트롤에서 받음  
		
//		System.out.println("getMname : "+mb2.getMname());
//		System.out.println("getMid : "+mb2.getMid());
		
		if(result=="ok") {
			//모델에서 적용된 dto값을 사용해 session생성 
			//로그인ok, 로그아웃에서만 세션을 활용함. 나머지 컨트롤러는 세션 활용안함. 
			HttpSession session = request.getSession();
			session.setAttribute("mid", mb2.getMid());
			session.setAttribute("mname", mb2.getMname());
			session.setAttribute("memail", mb2.getMemail());
			
			this.pw.print("<script>"
							+"alert('로그인되었습니다');"
							+"location.href='./index.do';"
							+"</script>");
		}else {
			this.pw.print("<script>"
						+"alert('아이디 및 패스워드를 확인하세요');"
						+"history.go(-1);"
						+"</script>");
		}
		this.pw.close();
	}
}
