//250220
var uName = frm.uName;
var uPhone = frm.uPhone;
var mName = frm.movieName;
var mDay = frm.mDay;



function reservation(){
	if(uName.value==""){
		alert("고객명을 입력하세요");
		uName.focus();
	}else if(uPhone.value==""){
		alert("연락처를 입력하세요");
		uPhone.focus();
	}else if(uPhone.value.indexOf("-")!= -1){
		alert("연락처는 -를 빼고 숫자만 입력하세요");
		uPhone.focus();
	}else {
		check_m();   //영화선택여부 
	}
	
}

//영화선택여부 확인 
function check_m(){
	var count = 0;
	var w=0;
	while(w<mName.length){
		if(mName[w].checked==true){
			count++;
		}
		w++;
	}
	if(count>0){
		check_d();  //날짜 선택 
	}else{
		alert("관람하실 영화를 선택하세요");
	}
}

//날짜 선택여부 확인 
function check_d(){
	var selectedDate = new Date(mDay.value);
	var today = new Date();

	// 시간을 00:00:00으로 초기화하여 날짜만 비교
    today.setHours(0, 0, 0, 0);
    selectedDate.setHours(0, 0, 0, 0);

	if(mDay.value==""){
		alert("관람할 날짜를 선택하세요.")
		
	}else if(selectedDate < today){
		alert("오늘 이전의 날짜는 선택할 수 없습니다. 다시 선택해주세요")
		mDay.value="";  //선택날짜 초기화
		mDay.focus();
		
	}else {
		frm.method="post";
		frm.action="./movie.do";

		frm.submit();
	}

}


function movieok(){
	alert("영화예매가 완료되었습니다.");
	location.href="./movie01.html"
}