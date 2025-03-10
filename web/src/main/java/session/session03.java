package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//250224
public class session03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
//		se.invalidate(); //세션에 저장된 모든 정보들 파기 (=>null)
		se.removeAttribute("tel");  //세션에 저장된 정보 중 특정 정보값만 삭제할 수 있음.
		
	
	}

}
