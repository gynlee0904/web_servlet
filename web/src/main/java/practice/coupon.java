package practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//250228
public class coupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null; 

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); 
		res.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = res.getWriter();
			
			String mid = req.getParameter("mid");
			String cno = req.getParameter("cno");
			String add_agree = req.getParameter("add_agree");
			
			//쿠폰번호 확인
			if(cno.equals("A1316B1004")|| cno.equals("C4024A0096") || cno.equals("B1987C3136")){
				if(add_agree == null){
					add_agree = "동의 안";
					
				}else if(add_agree.equals("Y")){
					add_agree = add_agree.replace("Y","동의");
					
				};
				
				req.setAttribute("mid", mid);
				req.setAttribute("cno", cno);
				req.setAttribute("add_agree", add_agree);
				
				RequestDispatcher rd = req.getRequestDispatcher("/practice/couponok.jsp");
				rd.forward(req, res);
				
			}else {
				this.pw.write("<script>"
						+"alert('해당 쿠폰번호를 확인하세요.');"
						+"history.go(-1);"
						+"</script>");
			}
			
		} catch (Exception e) {
			pw.write("<script>"
					+"alert('!!ERROR!!');"
					+"history.go(-1);"
					+"</script>");
			
			System.out.println(e);
		}
	}
}
