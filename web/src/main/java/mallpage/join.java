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
@WebServlet("/mallpage/join.do")
public class join extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		footers ft = new footers();
//		ArrayList<String> cpdata = ft.cpdata;
//		request.setAttribute("cpdata", cpdata);  //jsp로 전달
		
//		ft.fts();
		request.setAttribute("cpdata", ft.cpdata); 
				
		
		RequestDispatcher rd = request.getRequestDispatcher("./join.jsp");
		rd.forward(request, response);

	}

}

//푸터 전용 클래스 
class footers extends ab_footer{
	public footers() {
		this.fts();
	}

}
