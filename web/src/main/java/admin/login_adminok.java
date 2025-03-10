package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.encry_model;

//250220
public class login_adminok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String aid = req.getParameter("adm_id");  //js에서 암호화했기때문에 암호회되어 날아옴 
		String apw = req.getParameter("adm_pw");
		String ano = req.getParameter("adm_no");
	
		//Model(encry_model.java)을 이용하여 암호회된 내용을 복호화 
		encry_model em = new encry_model();
		String id = em.datadecode(aid).intern();  //해당데이터를 base64로 복호화
		String pw = em.datadecode(apw).intern();  //.intern() : 조건문에서 연산기호 쓸 수 있게 해줌  
		String no = em.datadecode(ano).intern();
		
		//검토
		if(id=="admin" && pw=="a1234" && no=="0556") {  //셋 다 일치시 
			System.out.println("관리자 로그인 성공");
		}else {  //하나라도 안맞으면 
			System.out.println("회원정보를 다시 확인하세요");
		}
		
	}

}
