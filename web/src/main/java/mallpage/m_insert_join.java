package mallpage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import shop.m_dbinfo;

//250312
//회원가입 처리 모델 
public class m_insert_join {
	Connection con = null;
	PreparedStatement ps = null;
	
	String sql = "";
	Integer result = null;
	
	m_dbinfo db = new m_dbinfo();
//	m_memberDTO mb =new m_memberDTO();  //=>1차배열등을 만들때 필요함. 아니면 안써도 됨  
	
	public Integer insert_member(m_memberDTO m) {
//		System.out.println(m.getMname());
		
		try {
			this.con = this.db.conn();
			this.sql = "insert into joins values ('0',?,?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, m.getMid());
			this.ps.setString(2, m.getMpass());
			this.ps.setString(3, m.getMname());
			this.ps.setString(4, m.getMemail());
			this.ps.setString(5, m.getMtel());
			this.ps.setString(6, m.getEvent_mail());
			this.ps.setString(7, m.getEvent_sms());
			//dto에서 getter로 db에 값을 저장 
			
			this.result = this.ps.executeUpdate();
			
		} catch (Exception e) {
			this.result = null;
			System.out.println("e : "+ e);
			
		} finally {
			try {
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println("e2:" + e2);
				
			}
		}
		
		return this.result;
	}
	
}
