//250311

function ajax_file(){
	var mfile = document.getElementById("mfile");
	
	if(mfile.value==""){
		alert("파일첨부를 해야합니다");
	}else {
		this.ajax_post(mfile);  //ajax post통신을 위한 함수 호출		
	}
}

//ajax IO로 파일전송하는 방식
function ajax_post(mfile){
	var http;   //BE와 통신용 변수
	var result;  //BE쪽에서 제공한 데이터 받는 변수
	var formdata = new FormData();  //form태그를 이용한 것과 동일
	formdata.append("mfile",mfile.files[0]);  //배열기준으로 파일을 처리함
	
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http.status == 200){
			console.log(this.response);
		}
	}
	
	//POST전송 
	http.open("post","./ajax_fileok.do",true);
	//file은 setRequestHeader 안씀
	http.send(formdata);  //formdata함수의 값을 send함수에 인자값으로 넣어 전송
}