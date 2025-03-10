//250304

function frm_view(part){
	var sp = document.getElementById("corp");
	if(part=="C"){  //사업자회원(C)를 눌렀을때 
		sp.style.display = "block";  //보이게 함 
		//style.display : 해당 오브젝트를 웹에 노출 혹은 미노출 
	}
	else {  //일반회원(P)를 눌렀을때 
		sp.style.display = "none";  //안보이게 함 
	}
}

//아이디 중복체크 
function idcheck(){
	if(frm.sid.value==""){
		alert("아이디를 입력하세요");
	}
	else {  //아이디를 입력하면 작동 
		//Ajax 역할(BE와 통신) 
		ajaxpost(frm.sid.value);
		//외부함수로 빼서 아이디 중복체크 (외부로 굳이 안빼도 됨)
		
		/*Ajax 통신(비동기통신) : url 안바뀜. 현재페이지에서 그대로 통신 
		  					javascript에서만 사용 가능. js로 web통신하는 역할 
		  form 통신 : url이 바뀜. 다른 페이지로 이동 
					HTML 기본.
		 */
	}
}

//Ajax 함수를 이용하여 중복체크 
var ok="";  //BE에게 통신을 보내는 역할 
function ajaxpost(data){
	function wck(){
		if(window.XMLHttpRequest){  //XMLHttpRequest : XHR통신(=Ajax통신)
		//window.XMLHttpRequest : 현재웹페이지에서 XHR통신을 사용함  	
			return new XMLHttpRequest();
			//new XMLHttpRequest(): 새로운 XHR통신 객체 생성
		}
	}
	
	function getdata(){
//		console.log(ok.readyState);
		if(ok.readyState==XMLHttpRequest.DONE && ok.status==200){
		// XMLHttpRequest.DONE => ok.readyState==4 라고 써도 됨 
		/* XMLHttpRequest.뫄뫄  
			XMLHttpRequest.UNSENT : 객체를 생성(new XMLHttpRequest()) (=0과 같음) 
			XMLHttpRequest.OPENED : XHR 통신 연결(open()) (=1과같음)
			XMLHttpRequest.RECEIVED : BE URL및 통신규격(post or Get)핸들링 (=2와같음)
			XMLHttpRequest.LOADING : BE경로 응답 대기시간 (=3과같음)
			XMLHttpRequest.DONE : BE가 요청한 처리완료 결과값을 보낸 경우 (=4와같음)
		*/
		/* .status : 통신에 대한 결과값 
			(200 : 성공, 405 :BE쪽 문제발생, 404 : url경로오류) 
		*/
			//console.log(this.response);  //BE에서 보낸 결과값을 받음 (=>this.response)
			if(this.response=="ok"){
				alert("해당아이디는 사용가능합니다");
				document.getElementById("idck").value="ok";
				frm.sid.readOnly = true;  //아이디를 더이상 수정 못하도록 해당 오브젝트를 읽기전용으로 바꿈
			}else {
				alert("해당아이디는 이미 사용중입니다");
				frm.sid.value = "";  //입력값 초기화 
				
			}
		}
	}
	
	//순서에 맞게 통신을 실행 
	ok = wck();  
	ok.onreadystatechange = getdata;  //해당 함수 결과를 받는 역할 
	ok.open("GET","./idcheck.do?sid="+data, true);  //값을 BE로 이관 
	//.open("통신규약", BE 경로 url, true or false);
	//Ajax 통신규약 =>POST, GET, PUT, DELETE... 
	//기본 true:비동기통신(async) / false : 동기통신(sync) 여부 
	/* 비동기통신 : 여러 데이터를 지속적으로 보낼 수 있으며, 결과값을 따로따로 받을 수 있음  
	   동기 통신 : 순차적 처리하는 방식, 1대1처리방식. 요청과 응답이 순차적으로 이뤄져야함  ex)FORM통신
	 */
	ok.send();  //Ajax 통신 실행 
}

//회원가입하기
function memberok(){
	if(frm.sid.value==""){
		alert("아이디를 입력 후 중복체크를 하세요");
	}else if(frm.spw.value==""){
		alert("패스워드를 입력하세요");
	}else if(frm.sname.value==""){
		alert("성함 혹은 회사명을 입력하세요");
	}else if(frm.stel.value==""){
		alert("연락처를 입력하세요");
	}else if(frm.semail.value==""){
		alert("이메일을 입력하세요");
	}else{
		if(frm.spart[0].checked == true ){  //일반회원일때
			//아이디 중복체크 했는지 확인 
			if(document.getElementById("idck").value==""){
				alert("아이디 중복체크를 해야합니다");
			}else {
				frm.submit();
			}
		}else {  //사업자회원일 때 
			if(frm.sno.value==""){
				alert("사업자번호를 입력하세요");
			}else if(frm.sno.value.length < 10){
				alert("사업자번호를 똑바로 확인하세요");
			}else {
				frm.submit();
			}
			
		}
	}
	
	
}
