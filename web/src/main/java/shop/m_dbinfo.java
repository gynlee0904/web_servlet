package shop;

import java.sql.Connection;
import java.sql.DriverManager;

//250304
//Model - database, 사용자 아이디, 사용자 패스워드 연결 
public class m_dbinfo {
	public static Connection conn() throws Exception {
		/*private 사용 안함. 여러 패키지에서 사용시에는 public, 해당 패키지에서만 사용하면 protected 
		  database의 기본 메소드명 : getConnection()  이거 안써도됨. 난 conn으로 씀  */
		
		//해당 라이브러리를 가져오는 역할 변수 
		String db_driver = "com.mysql.cj.jdbc.Driver";
		
		//Database 경로 연결 -  jdbc:mysql://IP또는 도메인:port번호/db명  
		//포트번호 바뀔수 있음 (보안 위해) 
		String db_url = "jdbc:mysql://localhost:3306/mrp";
		
		//mysql에 접속하는 사용자 
		String db_user = "project";
		String db_pw = "a123456";
		
		//실제 db에 연결하는 메소드와 클래스  
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url, db_user, db_pw);  
		
//		System.out.println(con);
		
		return con;  //db가 정상적으로 연결되었는지 확인결과 회신 
	}
		
	
	
}
