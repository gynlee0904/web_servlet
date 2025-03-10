package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250221
public class noticeok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter pw = null;   
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = res.getWriter();
			String se = req.getParameter("se");
			if(se.equals("korea.com")) {
				String subject = req.getParameter("subject");
				String writer = req.getParameter("writer");
				String ntext = req.getParameter("ntext");
				
				req.setAttribute("subject", subject);
				req.setAttribute("writer", writer);
				req.setAttribute("ntext", ntext);
				
				RequestDispatcher rd = req.getRequestDispatcher("./noticeview.jsp");  
				//  /로 시작되면 webapp에서 파일을 찾게됨. 그래서 경로에 맞는 폴더명을 같이 써줘야 함. 
				//  ./로 시작되면 web.xml에 저장된 do경로와 같은것으로 인식함 
//				RequestDispatcher rd = req.getRequestDispatcher("/review/noticeview.jsp");  //이렇게 써도 됨 
				rd.forward(req, res);
				
			}else {
				this.pw.write("<script>"
						  	+"alert('올바른 접근방법이 아닙니다.');"
						  	+"history.go(-1);"
						  	+"</script>");
			}
			
		} catch (Exception e) {
			this.pw.write("<script>"
					  +"alert('시스템 오류로 인해 데이터 처리가 되지 않았습니다.');"
					  +"</script>");
			System.out.println(e);
		} finally {
			this.pw.close();
		}
	
	}

}
