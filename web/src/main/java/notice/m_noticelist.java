package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shop.m_dbinfo;

//250306
//noticelist 모델 - DB에 있는 모든 데이터를 가져오는 역할의 모델 
public class m_noticelist {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null; 
	
	String sql;  //sql쿼리 
	ArrayList<String> data = null;  //각 컬럼별 값을 저장 
	ArrayList<ArrayList<String>> alldata = null;  //데이터베이스 전체 데이터 
	
	m_dbinfo db = new m_dbinfo();
	
	public m_noticelist() {
		
	}
	
	public ArrayList<ArrayList<String>> db_data(){
		
		try {
			this.con = db.conn();
			//필요한 컬럼만 지정하여 select문법으로 데이터를 가져오는 코드 
			this.sql = "select nidx, subject, writer, nview, ndate from notice order by nidx desc";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();
			
			//반복문으로 테이블에 있는 컬럼을 하나씩 1차배열에 넣은 후 2차배열에 넣는 코드  
			this.alldata = new ArrayList<ArrayList<String>>();
			while(this.rs.next()) {
				this.data = new ArrayList<String>();
				this.data.add(this.rs.getString("nidx"));
				this.data.add(this.rs.getString("subject"));
				this.data.add(this.rs.getString("writer"));
				this.data.add(this.rs.getString("nview"));
				this.data.add(this.rs.getString("ndate"));
				
				this.alldata.add(this.data);
			}
//			System.out.println(this.alldata);

		} catch (Exception e) {
			this.alldata = null;
			
		} finally {
			try {
				this.ps.close();
				this.con.close();
				
			} catch (SQLException e) {
				this.alldata = null;
			}
		}
		
		//모델에서 컨트롤러로 데이터를 회신
		return this.alldata;
	}
}
