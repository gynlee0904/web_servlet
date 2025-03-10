package event;

import java.sql.Connection;
import java.sql.DriverManager;

//250227
//Model - DB환경설정 및 세팅값 설정하는 모델 
public class dbconfig {
	public static Connection info() throws Exception{
		//mysql버전의 연결 설정 파트 
//		String database ="com.mysql.jdbc.Driver";  //v5.1~5.5
		String database ="com.mysql.cj.jdbc.Driver";  //v5.7~
		
		//mysql 연결 경로
		String dburl = "jdbc:mysql://localhost:3306/mrp";
		
		//mysql DB 사용자 아이디 
		String user = "project";
		
		//mysql DB 사용자 패스워드 
		String passwd = "p402402";
		
		
		Class.forName(database);  //어떤 라이브러리를 이용해 DB에 접속할것인지를 정함 
		Connection con = DriverManager.getConnection(dburl,user,passwd);  //=>mysql -u 아이디 -p 와 같음 
		
		return con;
	}
}
