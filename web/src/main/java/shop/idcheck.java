package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//250304
public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter pw = null;
    Connection con = null;
    
	//Ajax통신을 받는 메소드 (id중복체크)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg="";  //FE에게 결과값을 보내주는 변수
		this.pw=response.getWriter();
		try {
			//Ajax로 FE가 보낸 데이터를 받는 역할 
			String id = request.getParameter("sid");

			if(id.equals("")) {
				msg="error";
			}
			else {
				m_dbinfo db = new m_dbinfo();
				this.con = db.conn();  //db연결시작
				String sql = "select count(*) as ctn from shop_member where sid='"+id+"'";
				Statement st = this.con.createStatement();
				//Statement : db의 쿼리문을 작성할 수 있도록 사용하는 클래스(메소드)
				//.createStatement(): create, alter, drop에서 사용
				
				ResultSet rs = st.executeQuery(sql);
				//ResultSet : select문에서만 사용 , 결과값을 받는 역할 
				
				/* sql쿼리문 작성방법 
				   1.select => Resultset, executeQuery
				   2.insert, update, delete => int, executeUpdate
				*/
				
				
				/*   //반복문 사용할경우 (데이터가 여러개 있을경우)
				while(rs.next()) {
				//rs.next() : 결과값에 대해 반복문 실행, 값이 있으면 true, 값이 없으면 false
//					System.out.println(rs.getString("count(*)"));
					System.out.println(rs.getString("ctn"));
				}
				*/
				
				
				if(rs.next()==true) {   //정상적으로 쿼리문이 작동했을 때 
					if(rs.getString("ctn").equals("0")) { //검색한 데이터가 없을 경우 
						msg="ok";
					}else {  //검색한 데이터가 있을 경우 
						msg="no";
					}
				}
				rs.close();
				st.close();
			}
			this.pw.write(msg);
		} 
		catch (NullPointerException ne) {  //FE가 올바른 값을 전달하지 못할경우 
			msg="error";
			this.pw.write(msg);
		}
		catch (Exception e) {
			msg="DB error";
			this.pw.write(msg);
			System.out.println(e);
		}
		finally {
			this.pw.close();
		}
		
		
		//BE가 FE에게 결과값을 통보하는 역할 
		this.pw = response.getWriter();
		pw.write(msg);
		//ok : 사용가능아이디, no : 사용불가아이디, error : 데이터오류
		pw.close();
		
		
	
	}

}
