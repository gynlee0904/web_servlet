package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(   
	fileSizeThreshold = 1024*1024*10,  //모바일은 최대 10mb까지 허용해줘야함(화질좋을수있기때문에)  
	maxFileSize = 1024*1024*50, 
	maxRequestSize = 1024*1024*100  
)
/*★★★프엔에서 enctype="multipart/form-data" 붙이면 콘트롤러에도 무조건 @MultipartConfig 붙여야함!!!
	 안붙이면 데이터 안넘어옴 */
public class mobileok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");  
		res.setContentType("text/html;charset=utf-8");
		
		String se = req.getParameter("security");  //인풋타입히든에서 넘어온 값 받아옴(보안용) 
		if(!se.equals("m")) {  //히든값이 m이 아닐때
			PrintWriter pw = res.getWriter();
			pw.write("<script>"
						+"alert('!!ERROR!!');"
						+"history.go(-1);"
						+"</script>");
			pw.close();
		}else {  //히든값이 m이 맞을때
			try {
				new reviews(req, res);
				//조건문 통과하면 외부클래스 메소드 실행 
			} catch (Exception e) {
				System.out.println("e: " + e);
			}
			
		}
	}
}


//외부클래스 만들어 핸들링 
//외부클래스 사용시 한글깨짐처리는 메인 doPost나 doGet에 코드 작성해서 처리함(외부클래스에 붙이면 효과없음)
class reviews {
	PrintWriter pw = null;
	public reviews(HttpServletRequest req, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
	
		String mname = req.getParameter("mname");  //고객명
		String pname = req.getParameter("pname");  //상품명 
		String star = req.getParameter("star");  //별점 
		String mtext = req.getParameter("mtext"); //리뷰내용 
		Part p = req.getPart("mfile");
		
		String filename = p.getSubmittedFileName(); 
		if(filename!="") {  //이미지가 첨부된 경우 
			/*String url = req.getServletContext().getRealPath("/reviews2/");
			File f = new File(url);
			f.mkdir();
			폴더가 없을경우 폴더 만들어줌 */
			
			String url = req.getServletContext().getRealPath("/reviews/");
			//경로에 저장할 폴더 없으면 오류날 수 있으므로 꼭 폴더 만들어줘야함 
			p.write(url+filename);
		}
		pw.write("<script>"
				+"alert('정상적으로 리뷰가 등록되었습니다.');"
				+"location.href='./mobile.html';"  
				//location.href은 이동시 글쓴내용 등 초기화됨, history.go(-1)은 전상태 그대로 돌아감  
				+"</script>");

	}
	
}