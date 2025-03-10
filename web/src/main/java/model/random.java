package model;

import java.util.ArrayList;
import java.util.Random;

//250221
//Model - 난수생성모델
public class random {
	ArrayList<String> num = null;
	int ea = 0;
	public random(int n) { //즉시실행메소드
		this.ea= n ; //자동등록방지 자릿수 설정 
		this.num = new ArrayList<String>();
		
	}
	
	public ArrayList<String> make_num(){  //난수생성 메소드
		Random rd = new Random();
		int w = 1;
		while(w<=this.ea) {
			this.num.add(String.valueOf(rd.nextInt(10)));  //0~9까지 랜덤숫자 추출
			w++;
		}
		
		return this.num;
		
	}
}
