<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 250219 -->
<title>회원가입 정보입력 (간편가입)</title>
</head>
<body>
	<p>회원가입 정보입력 (간편가입)</p>
	<form id="frm" method="post" action="./part2_t.do" enctype="multipart/form-data">
	<!--  첨부파일 보낼떈 무조건 method="post"로 해야함 -->
		아이디 : <input type="text" name="userid" > <br>
		고객명 : <input type="text" name="username" > <br>
		비밀번호 : <input type="password" name="userpass" maxlength="12"> * 비밀번호는 최소 6~12자리까지 입니다. <br>
		이메일 : <input type="email" name="usermail" > <br>
		휴대폰번호 : <input type="tel" name="userphone" maxlength="11"> * '-'는 입력하지 않습니다.<br>
		이미지 : <input type="file" name="ufile" accept="image/*"> * 이미지 첨부는 최대 2MB까지 가능합니다.<br>
		<input type="button" value="가입완료" onclick="go_nextpage()">
		<!-- 회원가입할때 버튼은 인풋타입 button임. submit 아님  -->
		<!-- 인풋타입 button일때는 pattern, required 속성 사용 불가  -->
	</form>
</body>
<script>
	function go_nextpage() {
		if(frm.userid.value==""){
			alert("아이디를 입력하세요");
		}else if(frm.username.value==""){
			alert("고객명을 입력하세요");
		}else if(frm.userpass.value==""){
			alert("패스워드를 입력하세요");
		}else if(frm.userpass.value.length < 6){
			alert("패스워드는 최소 6자 이상 입력하세요");
		}else if(frm.usermail.value==""){
			alert("이메일을 입력하세요");
		}else if(frm.userphone.value==""){
			alert("연락처를 입력하세요");
		}else {
			if(confirm("회원가입을 진행하시겠습니까?")){  //취소를 누르면 진행 안되도록 조건문 설정
				frm.submit();
			}else{  //취소를 누른 경우 
				history.go(-1);
			}
		}
	}
	

	

</script>
</html>