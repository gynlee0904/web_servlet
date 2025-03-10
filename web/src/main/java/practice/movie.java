package practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//250220 - 영화예매
public class movie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		
		
		try {
			this.pw = response.getWriter();
			
			String uName = request.getParameter("uName");
			String uPhone = request.getParameter("uPhone");
			String movieName = request.getParameter("movieName");
			String mDay = request.getParameter("mDay");
			
			ArrayList<String> data = new ArrayList<String>();
			data.add(uName);
			data.add(uPhone);
			data.add(movieName);
			data.add(mDay);
			
			request.setAttribute("data", data);
			RequestDispatcher rd = request.getRequestDispatcher("/practice/movie02.jsp");
			rd.forward(request, response); 
			
		} catch (Exception e) {
			pw.write("<script>"
					+"alert('!!ERROR!!');"
					+"history.go(-1);"
					+"</script>");
			
			System.out.println(e);
		}finally{
			this.pw.close();
		}
		
		
		
	}

}
