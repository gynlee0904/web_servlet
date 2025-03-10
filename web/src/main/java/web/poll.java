package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250218
public class poll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		
		//동일한 네임값으로 radio를 보낼때 여러개의 오브젝트 중 하나만 선택해서 보내기 떄문에 변수 하나로 받음 
		String subject = request.getParameter("subject");
		//request.getParameter().inturn() -> 무조건 프엔에서 값이 넘어와야함. 안넘어오면 에러 발생
		
		//checkbox는 여러개를 선택해 보낼 수 있으므로 배열로 받는다.
		String etc [] = request.getParameterValues("etc");
		//.getParameterValues() : 여러개를 받을때 사용 
		
		//원시배열로 받은것을 클래스배열로 만들기 
//		ArrayList<String> etc = new ArrayList<String>(Arrays.asList(request.getParameterValues("etc")));
		
		PrintWriter pw = response.getWriter();
		//PrintWriter -> view부분이 없을때 controller에서 처리 종료할 떄 쓰는것.
		//RequestDispatcher -> view로 보내서 결과를 처리. 
		//PrintWriter와 RequestDispatcher는 같은공간에 쓰지 않음 
		
		try {
		//try~catch는 try안에서 문제가 발생했을 떄 catch로 빠지는것. try밖에서 발생한 오류는 catch 작동 안함 
			request.setAttribute("subject", subject);
			request.setAttribute("etc", etc); //배열 받은거 통째로 던지기 -> jsp에서 반복문 돌림 
			
//			System.out.println(Arrays.toString(etc));
//			System.out.println(etc);
			
		
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/poll.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			pw.write("<script>"
					  +"alert('올바른 접근이 아닙니다');"
					  +"</script>");
		}finally {
			pw.close();
			//꼭 닫아줄것 
		}
		
	}


}
