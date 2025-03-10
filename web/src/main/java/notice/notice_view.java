package notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class notice_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nidx = Integer.parseInt(request.getParameter("nidx"));
//		System.out.println(nidx);
		
		//조회수 +1증가 
		m_noticeview nv = new m_noticeview();
		nv.viewcount(nidx);
		
		ArrayList<String> notice_v = nv.db_data;
		request.setAttribute("notice_v", notice_v);  //1개의 게시물 데이터 내용을 jsp로 전달 
		
	
		RequestDispatcher rd = request.getRequestDispatcher("./notice_view.jsp");
		rd.forward(request, response);
	
	
	}

}
