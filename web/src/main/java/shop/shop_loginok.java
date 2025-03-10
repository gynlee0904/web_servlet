package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//250304
public class shop_loginok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	Connection conn = null;  
    PreparedStatement ps = null; 
    ResultSet rs = null;
    HttpSession session = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		m_dbinfo db = new m_dbinfo();
		this.pw = response.getWriter();
		
		String spart = request.getParameter("spart");
		String sid = request.getParameter("sid");
		String spw = request.getParameter("spw");  //사용자가 입력한 사항  //db에는 암호화되어있음 
		
		try {
			this.conn = db.conn();
			spw = new m_md5().md5_code(spw);  //사용자가 입력한 값을 암호화해야 db에 저장된 값과 비교할 수 있음 
			String sql="";

			//resultset의 값을 받기위한 변수
			String id ="";
			String pw ="";
			String nm ="";
			String no ="";
			if(spart.equals("P")) {  //개인회원
				sql="select sid, spw, sname from shop_member where sid=?";
				
				this.ps= this.conn.prepareStatement(sql);  //sql실행 
				this.ps.setString(1,sid);
				this.rs=this.ps.executeQuery();
				while(this.rs.next()) {
					id = this.rs.getString("sid");
					pw = this.rs.getString("spw");
					nm = this.rs.getString("sname");
				}
				if(id.equals("")||nm.equals("")) {  //조회된 내용이 없을경우 
					this.pw.write("<script>"
									+"alert('해당 아이디를 확인할 수 없습니다');"
									+"history.go(-1);"
									+"</script>");
					
				}else {  //조회된 내용이 있을경우 
					if(spw.equals(pw)) {  //사용자가 입력한 값과 db에 저장된 값이 동일할때
						this.session = request.getSession();  //세션 생성 
						this.session.setAttribute("mid", id);  //아이디, 고객명을 세션에 저장 
						this.session.setAttribute("mnm", nm);  //pw는 절대 세션에 저장하면 안됨 
						
						this.pw.write("<script>"
									+"alert('로그인되었습니다');"
									+"location.href='./main.jsp';"
									+"</script>");
					}
				}
			}else {  //사업자회원
				String sno = request.getParameter("sno");
				
				sql="select sid, spw, sname, sno from shop_member where sid=? and sno=?";
				this.ps= this.conn.prepareStatement(sql);  
				this.ps.setString(1,sid);
				this.ps.setString(2,sno);
				this.rs=this.ps.executeQuery();
				while(this.rs.next()) {
					id = this.rs.getString("sid");
					pw = this.rs.getString("spw");
					nm = this.rs.getString("sname");
					no = this.rs.getString("sno");
				}
				if(id.equals("") || nm.equals("") || no.equals("")) {  
					this.pw.write("<script>"
									+"alert('해당 아이디를 확인할 수 없습니다');"
									+"history.go(-1);"
									+"</script>");
					
				}else {  //조회된 내용이 있을경우 
					if(spw.equals(pw)) {  
						this.session = request.getSession();  
						this.session.setAttribute("mid", id);  
						this.session.setAttribute("mnm", nm);  
						this.pw.write("<script>"
									+"alert('로그인되었습니다');"
									+"location.href='./main.jsp';"
									+"</script>");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				this.conn.close();
				this.pw.close();
			} catch (SQLException se) {
				System.out.println(se);
				se.printStackTrace();
			}
			
		}
		
	}

}
