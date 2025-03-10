//250221

function notice_write(){
	if(frm.subject.value==""){
		alert("제목을 입력하세요");
	}else if(frm.writer.value==""){
		alert("글쓴이를 입력하세요");
	}else if(frm.ntext.value==""){
		alert("내용을 입력하세요");
	}else{
		frm.submit();
	}
	
}