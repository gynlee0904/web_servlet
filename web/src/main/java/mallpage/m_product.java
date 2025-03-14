package mallpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

//250314
//상품리스트 DB연결 모델 
public class m_product {
	m_productDTO pd = new m_productDTO();  //dto(setter,getter)
	
	ArrayList<ArrayList<String>> all = null;
	ArrayList<String> al = null; 
	
	//DB정보 가져오기 
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String sql = "";
	Integer result = null;
	
	m_dbinfo db = new m_dbinfo();  //db접속정보 
	
	//상품 하나의 상세정보를 가져오는 메소드
	//컨트롤러에서 보내준 dto값을 받음 
	public void oneProduct(m_productDTO p) {  //주의!!컨트롤러의 m_productDTO변수명과 겹치면 안됨 
//		System.out.println(p.getMidx()); 
		
		try {
			this.con = this.db.conn();
			this.sql = "select * from mall_product where midx=?";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setInt(1, p.getMidx());
			this.rs = this.ps.executeQuery();
			
			//데이터 하나만 가져올 때는 while문 안돌려도됨 
			if(this.rs.next()==false){  //this.rs.next()는 false, true로만 나옴
//				System.out.println("데이터없음");
				this.pd.setMidx(0);
				
			}else {  
//				System.out.println("데이터있음");
				this.pd.setMidx(this.rs.getInt("midx"));
				this.pd.setPcode(this.rs.getString("pcode"));
				this.pd.setPdate(this.rs.getString("pdate"));
				this.pd.setPimg(this.rs.getString("pimg"));
				this.pd.setPmoney(this.rs.getString("pmoney"));
				this.pd.setPnm(this.rs.getString("pnm"));
				this.pd.setPsales(this.rs.getString("psales"));
				this.pd.setPsmoney(this.rs.getString("psmoney"));
				//db에서 가져온 값 필드에 있는 dto에 싹 넣음  
				
			}

		} catch (Exception e) {
			this.pd.setMidx(0);
			System.out.println("e : "+e);
		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println("e2 : "+e2);
			}
		}
		
	}
	
	
	//전체 상품정보를 가져오는 메소드 
	public ArrayList<ArrayList<String>> product_all(){
		try {
			this.con = this.db.conn();
			this.sql = "select * from mall_product order by midx desc";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();
			
			this.all = new ArrayList<ArrayList<String>>();
			
			while(this.rs.next()){  //데이터가 여러개면 반복문 돌림 
				 this.al = new ArrayList<String>();
				 this.al.add(String.valueOf(this.rs.getInt("midx")));
				 this.al.add(this.rs.getString("pcode"));
				 this.al.add(this.rs.getString("pnm"));
				 this.al.add(this.rs.getString("pmoney"));
				 this.al.add(this.rs.getString("psales"));
				 this.al.add(this.rs.getString("psmoney"));
				 this.al.add(this.rs.getString("pimg"));
				 this.al.add(this.rs.getString("pdate"));
				 
				 this.all.add(this.al);
			}
			
		} catch (Exception e) {
			this.all = null;
			System.out.println("e : "+e);
		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println("e2 : "+e2);
			}
		}
		
		return this.all;
	}
	
}
