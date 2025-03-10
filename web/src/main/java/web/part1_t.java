package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250219
public class part1_t extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8"); //프엔에서 한글이 날라올 경우에만 씀  
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String agree [] = request.getParameterValues("agree");
			//getParameterValues : 배열임. 무조건 배열로 받아야 함 
//			System.out.println(agree.length);//=>체크한 갯수만큼 날아옴 
			
			int ckcount = 0;
			int w= 0;
			while(w<agree.length) {  			//agree.length만큼 검토 
				if(agree[w].equals("y0") 
					|| agree[w].equals("y1") 
					|| agree[w].equals("y2")) {  //필수값이 모두 선택되었을 때
					ckcount++;
				}
				w++;
			}
			if(ckcount < 3) {
				pw.write("<script>"
						  +"alert('필수약관에 모두 동의하지 않았습니다');"
						  +"location.href='./part1_t.html';"
						  +"</script>");
			}else {  //조건이 맞을경우 다음으로 이동 
				RequestDispatcher rd = request.getRequestDispatcher("/part2_t.jsp");
				rd.forward(request, response);
			}
			
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
