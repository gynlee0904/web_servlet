package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shop.m_dbinfo;
import shop.m_md5;

//250306
//컨트롤러는 프론트에서 받은 값을 모델로 던지고, 모델에서 처리한 값을 받아서 프론트로 보내주는 역할 
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 5,  //5MB
		maxFileSize = 1024 * 1024 * 50,  //50MB
		maxRequestSize = 1024*1024 * 500  //500MB 
)
public class notice_writeok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;   
	Connection conn = null;
	PreparedStatement ps = null;

	//Model
	m_dbinfo db = new m_dbinfo(); //db접속정보 
	m_md5 md5 = new m_md5();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw =response.getWriter();
		
		//첨부파일 유무에 따라 SQL 저장방식 달라지므로 파일 먼저 체크함 
		Part nfile = request.getPart("nfile");
		long filesize = nfile.getSize();
		
		try {
			this.conn = this.db.conn();  //db연결 
			String subject = request.getParameter("subject");
			String writer = request.getParameter("writer");
			String pw = request.getParameter("pw");
			String texts = request.getParameter("texts");
			
			//패스워드 암호화 
			pw = this.md5.md5_code(pw);
			
			String sql = "";  //db쿼리문 
			int result = 0;  //db에 저장여부 결과값(insert,update,delete에만 씀) 

			if(filesize == 0) {  //첨부파일이 없을경우 
				sql="insert into notice (nidx,subject,writer,pw,texts,ndate) values ('0',?,?,?,?,now())";
				this.ps = this.conn.prepareStatement(sql);
				this.ps.setString(1, subject);
				this.ps.setString(2, writer);
				this.ps.setString(3, pw);
				this.ps.setString(4, texts);
				result = this.ps.executeUpdate();  //db에 저장
				if(result>0) {
					this.pw.write("<script>"
									+"alert('게시물이 등록되었습니다.');"
									+"location.href='./notice_list.do';"
									+"</script>");
				}
			}
			else {  //첨부파일 있을경우 
				//Model을 이용하여 첨부파일 저장 
				m_notice nt = new m_notice(nfile,subject,writer,pw,texts, request);
				String msg = nt.msg; //리턴메소드fileok()가 private이므로 전역변수msg의 값을 직접 가져옴 
				
				if(msg.equals("ok")) {
					this.pw.write("<script>"
								+"alert('게시물이 등록되었습니다.');"
								+"location.href='./notice_list.do';"
								+"</script>");
				}else {
					this.pw.write("<script>"
								+"alert('데이터베이스 및 첨부파일 오류');"
								+"history.go(-1);"
								+"</script>");
				}
			}
			
		} catch (Exception e) {
			this.pw.write("<script>"
						+"alert('DB문제로 인해 게시물 저장에 실패했습니다.');"
						+"history.go(-1);"
						+"</script>");
			System.out.println(e);
			e.printStackTrace();
			
		} finally {
			try {
				this.ps.close();
				this.conn.close();
				this.pw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
}
