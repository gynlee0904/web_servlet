<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="./login.js?v=1"></script>
<div class="products">
  <h3>MEMBER_LOGIN</h3>
  <div class="sub_view">
    

    <div class="loginview">
      <span class="member_l"></span>
      <span class="login">
      <form id="frm" method="post" action="./loginok.do" onsubmit="return loginck();">   
      <table border="0" cellpadding="0" cellspacing="0" class="table_login">
      <tr>
      <td>
      <input type="text" placeholder="아이디를 입력해주세요" class="id" name="mid">
      </td>
      <td rowspan="2">
      <input type="submit" value="LOGIN" class="btn1">
      </td>
      </tr>
      <tr>
      <td>
      <input type="password" placeholder="패스워드를 입력해주세요" class="id" name="mpass">
      </td>
      </tr>
      </table>
          
      <div class="check1">
      <input type="checkbox" id="l"> <label for="l" class="label_login">아이디 저장</label>
      </div>
      </form>
      <ul class="btns_login">
      <li><input type="button" value="회원가입" class="a"></li>
      <li><input type="button" value="아이디 찾기" class="a"></li>
      <li><input type="button" value="비밀번호 찾기" class="a1"></li>
      </ul>
      <form>    
      <span class="font1">비회원 주문조회</span>
      <span class="search_login">
          <table border="0" cellpadding="0" cellspacing="0" class="table_login">
          <tr>
          <td>
              <input type="text" placeholder="주문자명을 입력해주세요" class="id" tabindex="1">
          </td>
          <td rowspan="2">
          <input type="submit" value="주문조회" class="btn1"></td>
          </tr>
          <tr>
              <td>
             <input type="text" placeholder="주문번호를 8자리 입력해 주세요" class="id" maxlength="8" tabindex="2">
              </td>
              </tr>
          </table>
          <label class="check1">
          주문자명과 주문번호를 잊으신 경우, 고객센터로 문의하여 주시기 바랍니다.
      </label>
      </span>
      </form>
      </span>
  </div>

  </div>
</div>