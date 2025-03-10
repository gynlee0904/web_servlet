//250306

//게시물 등록시 체크하는 함수 
function writeck(){
	if(frm.subject.value==""){
		alert("제목을 입력하세요");
		frm.subject.focus();
	}	
	else if(frm.writer.value==""){
		alert("글쓴이를 입력하세요");
		frm.writer.focus();
	}
	else if(frm.pw.value==""){
		alert("패스워드를 입력하세요");
		frm.pw.focus();
	}
	else {
		var txt = CKEDITOR.instances.editor.getData();
		//var txt = CKEDITOR.instance.아이디값.getData();
		//CKEDITOR.instances : ckeditor를 로드 
		
//		console.log(txt);
		if(txt=="" ){
			alert("내용을 입력하세요");
		}
//		else if(txt.length < 40){
//			alert("최소 40자이상 입력이 되어야 합니다");
//		}
		else {
			frm.submit();
		}
		
	}
	
	
};