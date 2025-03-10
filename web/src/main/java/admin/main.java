package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250220
/*하나의 컨트롤러에 하나의 뷰가 적용됨. 
  단, 해당 뷰 안에 여러개의 include(jsp)가 있을 수 있음 (=> treepage)*/
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String corp = "apink 사단법인(사)";
		request.setAttribute("corp", corp);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/main.jsp");
		rd.forward(request, response); 
	}

}
