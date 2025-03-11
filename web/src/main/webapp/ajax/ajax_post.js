//250311

function ajaxs(){   //버튼 클릭시 발동되는 함수
	var mid = document.getElementById("mid");
	var memail = document.getElementById("memail");
	
	if(mid.value==""){
		alert("아이디를 입력하세요");
	}
	else if(memail.value==""){
		alert("이메일을 입력하세요");
	}
	else {
		this.ajax_post(mid.value,memail.value);  //ajax post통신을 위한 함수 호출		
	}
}

//ajax post로 전송하는 함수. 밖으로 뺌
function ajax_post(mid,memail){
	var http;   //BE와 통신용 변수
	var result;  //BE쪽에서 제공한 데이터 받는 변수
	
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		
		if(http.readyState == 4 && http.status == 200){
			console.log(this.response);
		}
		else if(http.status == 404){  //예외처리
			console.log("경로 오류 발생!!");
		}
		else if(http.status == 405){
			console.log("통신규격 오류 발생!!");
		}
	}

	http.open("post","./ajax_postok.do",true);
//	http.open("post","http://172.30.1.90:8080/web/ajax/ajax_postok.do",true);
	/* CORS error : 도메인 규칙이 조금이라도 다를 경우 발생하는 에러(ajax통신에만 해당)
		BE에서 처리해야함. 컨트롤러에 
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		기재 필요 
	*/

	http.setRequestHeader("content-type","application/x-www-form-urlencoded");  
	//ajax post전송시 content-type 적용해야 정상적으로 BE로 값 보냄. 꼭 써줄것
	
//	http.send("userid="+mid);  //값을 하나 날릴때 
	http.send("userid="+mid+"&useremail="+memail);	//값을 한개 이상 날릴때 &를 붙임 
	//get방식은 open()안의 url뒤에 붙이고 post방식은 send()안에 넣는다
	/*get방식 : 
	http.open("get","./ajax_postok.do?userid="+mid,true);
	http.send(); 
	*/
	
}