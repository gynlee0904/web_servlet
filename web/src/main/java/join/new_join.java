package join;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.random;


public class new_join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//자동등록방지기능 : 랜덤4자리 번호 생성  
		ArrayList<String> seNo = new random(4).make_num();
		
		System.out.println(seNo);
		
		request.setAttribute("seNo", seNo);  //jsp로 자동난수 4자리 전달 
		RequestDispatcher rd = request.getRequestDispatcher("./new_join.jsp");
		rd.forward(request, response); 
	}

}

