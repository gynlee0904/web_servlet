package web;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

//250219
//문자를 암호화로 변경하는 Model (MVC중 M)
public class encry_model {
	public String dataencode(String word) {  //base64 암호화 : 가장 기초적인 암호화기술(지금은 잘 안씀) 
	//encode :암호화 <-> decode :복호화
		Encoder ec = Base64.getEncoder();  //암호화 만들겠다는 것 
		String security = ec.encodeToString(word.getBytes());  //모든 암호화는 바이트단위이기 때문에 바이트로 바꿔야 함
		return security;
	}
	
	public String datadecode(String word) {  //base64 복호화
		Decoder dc = Base64.getDecoder();  //암호화 풀겠다는것
//		byte dec [] = dc.decode(word);  //다시 문자화시킴
//		String security = new String(dec);
		//위 두줄을 한줄로 쓰긔↓
		String security = new String(dc.decode(word));
		return security;
	}
	
	
	public String md5_encode(String word) {  //md5, sha-1 암호화 => 복호화기술 없음
	//md5 => try~catch 넣어야함
	//sha-1 : 16진수 40자로 생성되는 암호화기술 (%01x,%02x)
	//sha-2 : sha-1보다 가속화된 기술. sha-224,sha-256,sha-384,sha-512
	//sha-n : n비트로 암호화. 최대 512까지.
	//sha-3 : sha-2보다 가속화된 기술.sha3-224,sha3-256,sha3-384,sha3-512
		String security = "";
		try {
			//MessageDigest : 암호화클래스 구성 형태를 가지고 있는 라이브러리
//			MessageDigest md = MessageDigest.getInstance("md5");  
			MessageDigest md = MessageDigest.getInstance("sha3-256"); 
			
			md.update(word.getBytes());  //해당 암호화 기준으로 문자를 바이트로 변환 
			byte md5byte [] = md.digest();  //원시배열에 암호화된 byte 저장 
			StringBuilder sb = new StringBuilder();  //문자열 클래스로 연속화 
			for(byte s : md5byte) {
				sb.append(String.format("%02x", s));
				//s를 %02x로 변환해서 sb에 담음 
				//%02x : 2자리문자화 ex)1234 => 01,02,03,04
				//%x or %01x : md5는 %02x를 기본으로 함. %03x이상일때부터 자릿수 더 길어짐
				//sha-1이면 %01x 과  %02x의 자릿수 서로 다름
				//%0nx : n자리문자화 ex)1234 => 000..01,000..0,000..03,000..04
				//암호화시 소문자x이면 소문자로, 대문자X이면 대문자로 변환됨
			}
			security = String.valueOf(sb);
			
		} catch (Exception e) {
			security = "MD5 ERROR!!";
		}
		
		return security;
	}
}
