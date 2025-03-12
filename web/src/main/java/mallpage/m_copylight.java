package mallpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

//250312
//copylight Model + login Model
//모델 하나에 여러 컨트롤러가 붙을 수 있음 
public class m_copylight {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String sql = "";
	String result = null;
	ArrayList<String> cp = null;
	
	m_dbinfo db = new m_dbinfo();
	m_memberDTO mb = new m_memberDTO();
	
	//login에 사용하는 메소드
	public String user_login(m_memberDTO dto) {  //=>컨트롤에서 날아온 dto
		try {
			this.con = this.db.conn();
			this.sql = "select mid, mname, memail from joins where mid=? and mpass=?";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, dto.getMid());
			this.ps.setString(2, dto.getMpass());
			this.rs = this.ps.executeQuery();
			if(this.rs.next()==true) {   //값이 있을때 
				this.result = "ok";
				this.mb = new m_memberDTO();  //기존에 사용된 DTO 초기화 및 새로운 dto로 값 구성 
				mb.setMid(this.rs.getString("mid"));  //dto에 아이디값 저장 
				mb.setMname(this.rs.getString("mname"));
				mb.setMemail(this.rs.getString("memail"));

			}

		} catch (Exception e) {
			this.result = null;
			
		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println("에러 : " + e2);
			}
		}
		
		return this.result;
	}
	
	
	//copyright에 사용하는 메소드
	public ArrayList<String> copyright_info() {
		try {
			this.con = this.db.conn();
			this.sql = "select * from corp_info";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();
			this.rs.next();  //이게 있어야 읽어들임 
			
			this.cp = new ArrayList<String>();
			this.cp.add(this.rs.getString("corp_nm"));
			this.cp.add(this.rs.getString("ceo_nm"));
			this.cp.add(this.rs.getString("corp_addr"));
			this.cp.add(this.rs.getString("corp_tel"));
			this.cp.add(this.rs.getString("corp_time"));
			this.cp.add(this.rs.getString("ceo_email"));
			this.cp.add(this.rs.getString("corp_no"));
			this.cp.add(this.rs.getString("corp_no2"));
			this.cp.add(this.rs.getString("corp_master"));
			this.cp.add(this.rs.getString("corp_domain"));
			
		} catch (Exception e) {
			System.out.println("뭔가의 오류 : " + e);
		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println("데이터베이스 연결해제 오류 : " + e2);
				//모델에서는 printwright 못씀 
			}
		}
		
		return this.cp;
	}
}


//외부클래스 쓰는게 핸들링하기 쉬움 

