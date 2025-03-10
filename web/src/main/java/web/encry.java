package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//250219
//Controller
public class encry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//doPost는 프엔에서 무조건 값이 넘어와야만 작동함 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//↓해당문자열을 base64를 이용하여 encode(암호화)
		String passwd = "a123456"; 
		encry_model em = new encry_model();  //모델클래스 로드. 
		String result = em.dataencode(passwd);  //해당메소드에 문자값 전달 
		System.out.println("암호화 : "+ result);  //최종 암호화된 사항 출력(=>a123456을 넘겼는데 YTEyMzQ1Ng== 가 출력됨 )
	
		//↓변환된 문자열을 base64를 이용하여 decode(복호화)하여 원본문자로 변환
		String repasswd = "YTEyMzQ1Ng==";
		String result2 = em.datadecode(repasswd);
		System.out.println("복호화 : "+ result2);  //암호화 푼 결과 출력됨 
		
		//↓md5 암호화 알고리즘
		String result3 = em.md5_encode(passwd);
		System.out.println("md5 암호화 : "+ result3);  
		//MessageDigest.getInstance("md5")=>a123456이 dc483e80a7a0bd9ef71d8cf973673924 으로 출력
		//MessageDigest.getInstance("sha-1")=>895b317c76b8e504c2fb32dbb4420178f60ce321 으로 출력(더 복잡)
		
		//복합 암호화기술 : ex) base64로 변환한걸 한번 더 md5로 변환, md5 + sha-2, 등등  

		
	}

}
