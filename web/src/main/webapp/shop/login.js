//250304
function part_check(part){
	var sp = document.getElementById("corp");
	if(part=="C"){  //사업자회원(C)를 눌렀을때 
		sp.style.display = "block";  //보이게 함 
	}
	else {  //일반회원(P)를 눌렀을때 
		sp.style.display = "none";  //안보이게 함 
	}
}

function loginck(){
	if(frm.sid.value==""){
		alert("아이디를 입력하세요");
		return false;
	}else if(frm.spw.value==""){
		alert("패스워드를 입력하세요");
		return false;
	}else {
		if(frm.spart[0].checked == true){//일반회원 체크시 
			//frm.submit(); //이렇게 쓰거나 
			//return;  //이렇게 쓰거나 
			return true;
			
		}else if(frm.spart[1].checked == true){  //사업자회원 체크시 
			if(frm.sno.value==""){
				alert("사업자번호를 입력하세요");
				return false;
			}else if(frm.sno.value.length < 10){
				alert("사업자번호를 똑바로 입력하세요");
				return false;
			}else {
				return true;
			}
		}
		
	}
	
	
	
	
}