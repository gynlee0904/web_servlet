//250312

function joinOk(){
	if(frm.mid.value==""){
		alert("아이디를 입력하세요");
	}else if(frm.mpass.value==""){
		alert("패스워드를 입력하세요");
	}else{
		//세부검토 (비밀번호 확인 등)
		var pw2 = document.getElementById("mpass2").value;
		if(pw2!=frm.mpass.value){
			alert("동일한 패스워드를 입력하세요");
		}else{
			frm.submit();
		}
	}
	
}