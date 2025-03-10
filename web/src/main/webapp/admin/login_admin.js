//250220

function login(){
	console.log("ss");
	if(frm.adm_id.value==""){
		alert("아이디를 입력하세요")
	}else if(frm.adm_pw.value==""){
		alert("패스워드를 입력하세요")
	}else if(frm.adm_no.value==""){
		alert("사원번호를 입력하세요")
	}else{
//		frm.method="get";
//		frm.action="./login_adminok.do";
		//js에서 폼 전송 핸들링 (보안을 위해)

		var id= btoa(frm.adm_id.value);
		//btoa() : JS에서 유일하게 사용할 수 있는 암호화기술.=>base64 
		var pw= btoa(frm.adm_pw.value);
		var no= btoa(frm.adm_no.value);
		
		//get방식으로 보낼때 location.href을 이용해도됨 
		location.href="./login_adminok.do?adm_id="+id
						+"&adm_pw="+pw
						+"&adm_no="+no;
		//입력정보를 암호화해서 url창에 띄움 
		//백에서는 받은 정보를 decode화해애함 
		
//		frm.submit();
	}
	
}