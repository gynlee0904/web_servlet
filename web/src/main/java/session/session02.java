package session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//250224
public class session02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
		
//		System.out.println(se.getAttribute("id"));  //세션에 저장된 정보(값) 가져오기  
//		System.out.println(se.getAttribute("name"));
		
		System.out.println(se.getMaxInactiveInterval());  //=>1800 출력됨
		//se.getMaxInactiveInterval() : 세션 유지시간 확인하는 클래스 
		//기본 유지시간 약 30분 =  1800초  (유지시간 조정할 수 있음)
		//페이지 이동시 세션은 계속 유지됨. 아무 동작 없이 유지시간 지나면 자동으로 세션 삭제됨.
		
		//jsp로 세션 전달 
		request.setAttribute("se",se);  
		RequestDispatcher rd = request.getRequestDispatcher("./session02.jsp");
		rd.forward(request, response);
		
		
		
	}

}
