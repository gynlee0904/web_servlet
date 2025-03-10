//250228

function regi(){
	if(f.mid.value==""){
		alert("아이디를 입력해주세요.");
	}else if(f.cno.value==""){
		alert("쿠폰번호를 입력해주세요.");
	}
	else if(f.add_agree.checked == false){
		alert("광고수신에 동의해주셔야 쿠폰등록이 가능합니다.");
	}
	else {
		f.submit();
	}
	
}