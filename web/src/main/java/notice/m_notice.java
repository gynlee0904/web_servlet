package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import shop.m_dbinfo;

//250306
//공지게시판 파일 저장하는 Model
public class m_notice {
	//컨트롤에서 로드한 Http~Reuest를 여기서 다시 extends할 필요 없음  
	Connection conn = null;
	PreparedStatement ps = null;

	//Model
	m_dbinfo db = new m_dbinfo(); //db접속정보 
	
	String sql = "";  //db쿼리문 
	int result = 0;   //db저장 결과값 
	String subject,writer,pw,texts;
	
	String msg = "";  //모델에서 처리된 결과값을 컨트롤러로 반환하는 변수 
	
	//즉시실행 메소드는 첨부파일을 저장하는 역할만 함
	public m_notice(Part nfile,String subject,String writer,String pw,String texts,HttpServletRequest request) {
		this.subject = subject;
		this.writer = writer;
		this.pw = pw;
		this.texts = texts;
		
		long filesize = nfile.getSize();  //파일용량
		String filenm = nfile.getSubmittedFileName();  //파일명
		//getName()은 html의 name명을 가져옴. 
		//첨부파일의 파일명을 가져오려면 getSubmittedFileName()을 써야함
		
//		System.out.println(request.getServletContext().getRealPath(filenm));
		//D:\web_servlet\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\web
		
		//첨부파일 저장될 경로 지정
		String url = request.getServletContext().getRealPath("/notice_file/");
		
		try {
			nfile.write(url+filenm);  //웹에 파일 저장 
			
			this.fileok(filenm);  //정상적으로 해당경로에 파일이 저장된 경우
			
		} catch (IOException e) {
			this.fileok("error");  //비정상적으로 해당경로에 파일이 저장안된 경우 
			e.printStackTrace();
		}
	}
	
	//DB 저장 및 컨트롤러로 결과값을 보내는 역할 
	private String fileok(String data) {
		if(data.equals("error")) {  //파일이 저장 안된경우 
			this.msg="error";
			
		}else {  //파일이 정상적으로 저장되었을경우 
			try {
				this.conn = this.db.conn();  //db연결
				this.sql = "insert into notice (nidx,subject,writer,pw,texts,filenm,nfile,ndate) values ('0',?,?,?,?,?,?,now())";
				this.ps = this.conn.prepareStatement(this.sql);
				this.ps.setString(1, this.subject);
				this.ps.setString(2, this.writer);
				this.ps.setString(3, this.pw);
				this.ps.setString(4, this.texts);
				this.ps.setString(5, data);
				this.ps.setString(6, data);
				this.result = this.ps.executeUpdate();  //db에 저장 
				if(result>0) {
					this.msg="ok";
				}
				
			} catch (Exception e) {
				this.msg="error";
				
			}
			finally {
				try {
					this.ps.close();
					this.conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return this.msg;  //컨트롤로 보내는 값
	}
}
