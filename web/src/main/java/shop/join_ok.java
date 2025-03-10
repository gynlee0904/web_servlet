package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250304
//Controller 
public class join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn = null;  //database연결 (SQL 쿼리문 실행) 
    PrintWriter pw = null;
    PreparedStatement ps = null;
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m_dbinfo db = new m_dbinfo();
		try {
			this.conn = db.conn();
			
		} catch (Exception e) {
			System.out.println("DB연결 실패 ");
		} finally {
			try {
				this.conn.close();
			} catch (Exception e2) {
				System.out.println("DB가 올바르게 해제되지 않았습니다.");
			}
		}
	}
	*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");  //오타주의, 해당페이지 다운로드될수있음(=>데이터 유출)
	
		this.pw = response.getWriter();
		m_dbinfo db = new m_dbinfo();
		
		try {
			this.conn = db.conn();
			
			String spart = request.getParameter("spart");
			String sid = request.getParameter("sid"); 
			String spw = request.getParameter("spw"); 
			String sname = request.getParameter("sname"); 
			String stel = request.getParameter("stel"); 
			String semail = request.getParameter("semail");
			spw= new m_md5().md5_code(spw);  //문자 pw를 md5로 암호화
			
			String sql = "";  //데이터베이스 쿼리문 
			
			if(spart.equals("P")) {  //일반회원일때 
				sql = "insert into shop_member values ('0',?,?,?,?,?,?,null,now())";
				this.ps = this.conn.prepareStatement(sql);
				this.ps.setString(1, spart);
				this.ps.setString(2, sid);
				this.ps.setString(3, spw);
				this.ps.setString(4, sname);
				this.ps.setString(5, stel);
				this.ps.setString(6, semail);
				
				int result = this.ps.executeUpdate();
				if(result>0) {  //정상적으로 db에 인서트 되었을 때 
					this.pw.write("<script>"
									+"alert('회원가입이 정상적으로 완료되었습니다');"
									+"location.href='./login.jsp';"
									+"</script>");
					
				}
				
				
			}else {  //사업자회원일때 
				String sno = request.getParameter("sno");
				sql = "insert into shop_member values ('0',?,?,?,?,?,?,?,now())";
				this.ps = this.conn.prepareStatement(sql);
				this.ps.setString(1, spart);
				this.ps.setString(2, sid);
				this.ps.setString(3, spw);
				this.ps.setString(4, sname);
				this.ps.setString(5, stel);
				this.ps.setString(6, semail);
				this.ps.setString(7, sno);
				
				int result = this.ps.executeUpdate();
				if(result>0) {  //정상적으로 db에 인서트 되었을 때 
					this.pw.write("<script>"
									+"alert('사업자 회원가입이 정상적으로 완료되었습니다');"
									+"location.href='./login.jsp';"
									+"</script>");
				}
			}
			
		} catch (IllegalArgumentException ie) {  //db에 unique가 중복값 발생시
			System.out.println(ie);
			this.pw.write("<script>"
						+"alert('연락처 및 이메일이 중복되어서 가입이 취소되었습니다')"
						+"history.go(-1);"
						+"</script>");
		} catch (Exception e) {
			
		} finally {
			try {
				this.ps.close();
				this.conn.close();
				this.pw.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			
			
		}
	
	}
    
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//    	//service() : doGet, doPost 다 받아줌 but 보안이 제일 약함 (test할때만 쓰임) 
//    }

}
