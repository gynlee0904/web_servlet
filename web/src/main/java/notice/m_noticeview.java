package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

//250306
//notice view 모델 - 데이터베이스에 테이블 사항 중 where및 조회수 증가시키는 파트 
public class m_noticeview {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String sql = "";  
	
	m_dbinfo db = new m_dbinfo(); //db정보 가져옴
	
	ArrayList<String> db_data = null;  //1개의 데이터 그룹만 저장시킴 
	
	public void viewcount(int nidx) {
		try {
			this.conn = this.db.conn(); //db연결
			
			//해당 컬럼의 값을 +1씩 증가시키는 쿼리문 
			this.sql = "update notice set nview = nview+1 where nidx=?";
			this.ps = this.conn.prepareStatement(this.sql);
			this.ps.setInt(1, nidx);  //nidx자료형이 int니까 setInt
			this.ps.executeUpdate();  //쿼리문 실행

			//해당 테이블에 맞는 컬럼값을 select 
			this.sql = "select * from notice where nidx=? order by nidx desc";
			this.ps = this.conn.prepareStatement(this.sql);
			this.ps.setInt(1, nidx);  
			this.rs = this.ps.executeQuery();
			
			if(this.rs.next()!=false) {  //해당 조건에 맞는 데이터값이 있을때 
				this.db_data = new ArrayList<String>();
				this.db_data.add(this.rs.getString("nidx"));
				this.db_data.add(this.rs.getString("subject"));
				this.db_data.add(this.rs.getString("writer"));
				this.db_data.add(this.rs.getString("pw"));
				this.db_data.add(this.rs.getString("texts"));
				this.db_data.add(this.rs.getString("filenm"));
				this.db_data.add(this.rs.getString("nfile"));
				this.db_data.add(this.rs.getString("nview"));
				this.db_data.add(this.rs.getString("ndate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
