package mallpage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250314
//컨트롤러 
@WebServlet("/mallpage/product_list.do")
public class product_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	m_productDTO pd = new m_productDTO();  //dto
	m_product mp = new m_product();  //select 모델 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String midx = request.getParameter("midx");
		String page_nm ="";
		
		//SPA방식
		if(midx==null){  //.equals 쓰지않음
			ArrayList<ArrayList<String>> all = this.mp.product_all();
			request.setAttribute("all", all);
			
			page_nm="./product_list.jsp";
			
		}else {
			this.pd.setMidx(Integer.parseInt(midx));  //dto로 전달 
			this.mp.oneProduct(this.pd);  //dto의 객체를 모델에게 전달
			
			this.pd = this.mp.pd;  //모델에 있는 dto를 가져옴(여기에 상품정보 저장되어있으니까) 
//			System.out.println(this.pd.getMidx());
			request.setAttribute("dto", this.pd);  //dto 통째로 넘김 
			
			page_nm = "./product_view.jsp";
		}
	
		RequestDispatcher rd = request.getRequestDispatcher(page_nm);
		rd.forward(request, response);
	}
}
