package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250219
//paylist.do (basket_part1)
public class paylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
	
		try {
			this.pw = response.getWriter();
			String product [] = request.getParameterValues("product");
			String total = request.getParameter("total");
			
			String pn [] = new String[2];
			
			ArrayList<String> pName = null;
			ArrayList<ArrayList<String>> pName2 = new ArrayList<ArrayList<String>>();
			
			int i = 0;
			while(i<product.length) {
				pn = product[i].split("-");
				pName = new ArrayList<String>(Arrays.asList(pn));
				pName2.add(pName);
				
				i++;
			}
			//일반 반복문 안에 foreach , foreach안에 foreach, foreach안에 일반 반복문 써도 됨 
			
			request.setAttribute("pName2", pName2);  //2차클래스배열 통째로 던짐 
//			request.setAttribute("product", product);
			request.setAttribute("total", total);
			
			RequestDispatcher rd = request.getRequestDispatcher("/basket_part2.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			pw.write("<script>"
					  +"alert('시스템 오류로 인해 데이터 처리가 되지 않았습니다.');"
					  +"</script>");
			System.out.println(e);
		}
		finally {
			pw.close();
		}
	}


	

}
