package mallpage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250312
@WebServlet("/mallpage/login.do")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//copylight모델 정보 
	m_copylight cl = new m_copylight();   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> cpdata = this.cl.copyright_info();
		request.setAttribute("cpdata", cpdata);  //jsp로 전달 

		RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
		rd.forward(request, response);

	}

}
