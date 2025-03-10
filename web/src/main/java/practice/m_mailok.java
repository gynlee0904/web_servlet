package practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import shop.m_dbinfo;

//250306
public class m_mailok {
	Connection conn = null;
	PreparedStatement ps = null;
	
//	String to_name,to_mail,subject,context,senddate; 
	String sql = "";  
	int result = 0;
	
	m_dbinfo db = new m_dbinfo();
	
	public m_mailok(String to_name, String to_mail, String subject, String context, String sendmail) {
		try {
			this.conn = this.db.conn();  //db연결
			
			this.sql = "insert into sendmail (midx,to_name,to_mail,subject,context,senddate) values ('0',?,?,?,?,now())";
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, to_name);
			this.ps.setString(2, to_mail);
			this.ps.setString(3, subject);
			this.ps.setString(4, context);
			this.result = this.ps.executeUpdate();  //db에 저장

		} catch (Exception e) {
			System.out.println("DB연결실패" );
			System.out.println(e);
		} finally {
			try {
				this.ps.close();
				this.conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
