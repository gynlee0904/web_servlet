package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250220
public class deliveryok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null; 
	
//	HttpServletRequest request;
//	HttpServletResponse response;
	
	//action에서 클래스를 실행하면 즉시실행되는 메소드. 
	//servlet의 즉시실행메소드 : 일반클래스에서 사용하는 형태와는 구조 다름 
	//★클래스랑 서블릿은 다른거임.
//	public deliveryok() {
//		try {
//			this.request.setCharacterEncoding("utf-8");
//			this.response.setContentType("text/html;charset=utf-8");
//			this.pw = this.response.getWriter();
//			
//		} catch (Exception e) {
//			System.out.println("해당 url경로는 올바르지 않습니다.");
//		}
//	}
	
	
	//일반 메소드
    public void deliveryok(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		request.setCharacterEncoding("utf-8"); 
    		response.setContentType("text/html;charset=utf-8");
    		this.pw = response.getWriter();
    		
		} catch (Exception e) {
			System.out.println("해당 url경로는 올바르지 않습니다.");
		}
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.deliveryok(req, res);  //↑변수명을 바꿀수 있음
		//매개변수명을 변경하여 request또는 response를 다른 변수로 변경 가능 
		
		String mname = req.getParameter("mname"); //getParameter("html에서 설정한 name값")
		String memail = req.getParameter("memail");
		String mtel = req.getParameter("mtel");
		String maddr = req.getParameter("maddr");
		
		ArrayList<String> data = new ArrayList<String>();
		data.add(mname);
		data.add(memail);
		data.add(mtel);
		data.add(maddr);
		//클래스배열에 프엔에서 받아온 값 담기 
		
		req.setAttribute("data", data);
		//클래스배열에 모든 값 담아서 통째로 던짐 
		//원시배열로 보내면 jsp에서 풀기 어려움. 콘트롤러에서 클래스배열로 바꿔 받으면 편함

		RequestDispatcher rd = req.getRequestDispatcher("/deliveryok.jsp");
		rd.forward(req, res);
		
		
	}

}
