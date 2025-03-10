package notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250306
//noticelist 컨트롤러 - 
public class notice_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		m_noticelist nl = new m_noticelist();  //list를 출력하기 위한 데이터베이스 테이블을 로드하는 모델 
		ArrayList<ArrayList<String>> result = nl.db_data();  //2차클래스배열로 저장된 테이블에 모든 데이터를 세팅
//		System.out.println(result);
		
		request.setAttribute("result", result);  //jsp로 2차클래스배열값을 전달
		
		RequestDispatcher rd = request.getRequestDispatcher("./notice_list.jsp");
		rd.forward(request, response);
	}

}
