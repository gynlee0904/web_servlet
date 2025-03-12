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
@WebServlet("/mallpage/index.do")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //copylight모델 정보 
	m_copylight cl = new m_copylight();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> cpdata = this.cl.copyright_info();
		request.setAttribute("cpdata", cpdata);  //jsp로 전달 
		//페이지가 많아지면 많이 뿌려야 하니까 추상클래스를 만들기도 함 

		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		rd.forward(request, response);
		

	}

}
