package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.m_dbinfo;
import shop.m_md5;


//250310
//게시물 삭제 컨트롤러
public class notice_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	Connection conn = null;
	PreparedStatement ps = null;
	
	String sql = "";  
	int result= 0;
	
	m_dbinfo db = new m_dbinfo(); //db정보 모델 가져옴
	m_md5 md5 = new m_md5();  //md5암호화 모델 가져옴 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String nidx = request.getParameter("nidx");
		String ori_pw = request.getParameter("ori_pw");
		String npw = request.getParameter("npw");
		
		this.pw = response.getWriter();
		
		try {
			if(nidx == null || npw.equals(null)) {  //사용자가 입력한 패스워드와 게시글고유번호가 올바르게 전달되지 않을경우
				this.pw.write("<script>"
								+"alert('올바른 작동이 아닙니다.');"
								+"history.go(-1);"
								+"</script>");
				
			}else {
				npw = md5.md5_code(npw);  //사용자가 입력한 암호를 md5암호화해서 다시 변수에 넣음 
				
				if(npw.equals(ori_pw)) { //입력한 패스워드가 같을경우 
					this.conn = this.db.conn();  //반드시 조건에 맞을 경우에만 db연결하기 (보안을 위해)
					//두 패스워드가 같을 때에만 db연결 (안그러면 sql인젝션공격 가능성 커짐)
					
					this.sql = "delete from notice where nidx=?";
					this.ps = this.conn.prepareStatement(this.sql);
					this.ps.setString(1, nidx);  
					this.result = this.ps.executeUpdate();
					
					if(this.result>0) {
						this.pw.write("<script>"
									+"alert('삭제완료되었습니다');"
									+"location.href='./notice_list.do';"
									+"</script>");
					}
					
				}else {
					this.pw.write("<script>"
								+"alert('패스워드가 다릅니다.');"
								+"history.go(-1);"
								+"</script>");
				}
			}
			
		} catch (Exception e) {
			System.out.println("DB삭제오류");
			System.out.println(e);

		} finally {
			try {
				this.pw.close();
				this.ps.close();
				this.conn.close();
			
			} catch (Exception e2) {
				System.out.println("DB접속 해제권한 오류");
				System.out.println(e2);
			}
		}
		
	}

}
