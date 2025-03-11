package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//250311
public class ajax_postok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter pw = null;   


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CORS를 해결하기 위해 사용하는 코드. Origin, Credentials을 사용하여 도메인이 다르게 접근하더라도 ajax통신이 되도록 허락해줌
		response.addHeader("Access-Control-Allow-Origin", "*");   //*:모든IP,도메인을 다 받겠다는 뜻. 접근 허용할 특정 서버 아이피나 도메인을 작성할 수 있음  
		response.addHeader("Access-Control-Allow-Credentials", "true");
		

		String userid = request.getParameter("userid");
		String useremail = request.getParameter("useremail");

//		System.out.println(userid);
//		System.out.println(useremail);
		
		String msg = "";  //ok: 아이디사용가능, no:아이디중복, error:BE오류 
		
		this.pw = response.getWriter();
		if(userid.equals("hong") && useremail.equals("hong@nate.com")) {
			msg="no";	
		}else {
			msg="ok";
		}
		this.pw.write(msg);  //FE에게 보내는 값
		
	}

}
