package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//250227
public class event_infook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement st = null;
	PrintWriter pw = null;
	
	public event_infook() {
		try {
//			dbconfig db = new dbconfig();
//			this.con = db.info();
			this.con = new dbconfig().info(); 
			
			System.out.println("Database 연결성공");

		} catch (Exception e) {
			System.out.println("Database 연결실패" +e);
		}
	}
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		String ename= req.getParameter("ename");
		String etel= req.getParameter("etel");
		String email= req.getParameter("email");
		String ememo= req.getParameter("ememo");
		String info1= req.getParameter("info1");
		String info2= req.getParameter("info2");
		
		//sql쿼리문 작성 (DDL : select, insert, update, delete)
		String sql ="insert into event (eidx, ename, etel, email, info1, info2, ememo, ejoin)"
				+ "values ('0','"+ename+"','"+etel+"','"+email+"','"+info1+"','"+info2+"','"+ememo+"',now())";
		this.pw = res.getWriter();
		
		try {
			
			//sql문법을 실행시키는 클래스(기초)
			this.st = this.con.createStatement();
			int result = st.executeUpdate(sql);  //executeUpdate() : insert,update,delete 쿼리문에서만 사용
			if(result>0) { //정상실행시 결과값 1이 됨.
				this.pw.write("<script>"
							+"alert('정상적으로 이벤트에 참여되었습니다.');"
							+"location.href='./event_info.html';"
							+"</script>"
							);
			}
			
		} catch (Exception e) {
			System.out.println("SQL 문법 오류" + e);
			
		} finally {  //정상적인 DB를 모두 핸들링 후 역순으로 클로징 적용
			try {
				this.st.close();
				this.pw.close();
				this.con.close();
				
			} catch (Exception e) {
				System.out.println("데이터베이스가 올바르게 종료되지 않았습니다." +e);
			}
			
			
		}
		
		
		
	}

}
