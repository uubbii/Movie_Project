<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;" charset="utf-8">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script defer type="text/javascript" src="/resources/JS/user.js" charset="euc-kr"></script>
<link rel= "stylesheet" href ="/resources/CSS/userform.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">

<title>회원가입</title>
</head>
<body>
   <div class="container">
      <h1>회원가입</h1>     
      
            <form action="/user/join" name="userform" method="post" enctype="multipart/form-data">
         <!-- 사용자 입력 폼 -->
         <div class="formdiv">
            <span id="icon" class="material-symbols-outlined">person</span>
            <input type="text" name="uid" id="uid" placeholder="아이디를 입력해주세요">
            <input type="hidden" name="dupl" id="dupl" value=0 /> 
            <button type="button" class="btn1" id="duplidbtn" onclick="duplidchk();">아이디 중복 체크</button>
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">vpn_key</span>
            <input type="password" name="upw" id="upw" placeholder="비밀번호를 입력해주세요">
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">person_add</span>
            <input type="text" name="uname" id="uname" placeholder="이름을 입력해주세요">
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">email</span>
            <input type="text" name="umail" id="umail" placeholder="이메일을 입력해주세요">
            <button class="btn1" id="emailCheckButton" onclick="javascript:sendmail();">이메일 확인</button>
         </div>
         <div class="emailchk">   
          <span class="material-symbols-outlined">mark_email_read</span>
          <input type="text" name="umailChk" id="umailChk" placeholder="인증번호를 입력해주세요">
          <input type="hidden" name="mailChk" id="mailChk" value=0/>
          <button class="btn1" type="button" onclick="verifycode();">확인</button>
       </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">phone_iphone</span>
            <input class="phone" type="tel" name="uphone1" id="uphone1" value="010" readonly >-
               <input class="phone" type="tel" name="uphone2" id="uphone2" placeholder="1234" maxlength = "4" >-
              <input class="phone" type="tel" name="uphone3" id="uphone3" placeholder="5678" maxlength = "4"><br>
               <input type="hidden" name="uphone" id="uphone">
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">calendar_month</span>
            <input class="date" type="date" name="frontbirth" id="frontbirth" placeholder="생년월일 6자리를 입력해주세요">
                  <input type="hidden" name="ubirth" id="ubirth">
               <input type="radio" name="ugender" value="M">남
               <input type="radio" name="ugender" value="F">여<br>
            </div>
      <div class="formdiv">
            <span class="material-symbols-outlined">account_box</span>
            <input type="text" name="jspsaveprofile" id="jspsaveprofile" value="프로필사진" readonly>
            <input type="file" name="jspprofile" id="jspprofile" onchange="changeprofile()"/><br>
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">home</span>
            <input type="text" name="upost" id="upost" readonly placeholder="우편번호">
            <input class="btn1" type="button" id="button" value="우편번호찾기" onclick="postSearch();">
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">home</span>
            <input type="text" name="uaddr1" id="uaddr1" readonly placeholder="주소"><br>
         </div>
         <div class="formdiv">
            <span class="material-symbols-outlined">home</span>
            <input type="text" name="uaddr2" id="uaddr2" placeholder="상세주소를 입력해주세요"><br>
         </div>
         
         <!-- <a href="javascript:userSubmit();"><button class="btn2">가입!</button></a> -->
         <button class="insertbtn" type="button" onclick="return userSubmit();">회원가입</button>

         
         
      </form>
      
      
   </div>
   
   <script>
   function sendmail() {
       let umail = $("#umail").val();
       console.log(umail);

       $.ajax({
           url: '/send-email',
           method: 'POST',
           data: { to: umail },
           success: function(result) {
               console.log("서버 응답:", result);
               if (result.trim() === "0") { 
                   console.log("에러");
                   alert("메일 전송 실패");
                   return false;
               } else {
                   console.log("성공");
                   alert("메일 전송 성공");
                   return false;
               }
           },
           error: function(xhr, status, error) {
               console.error("AJAX 요청 실패:", status, error);
               alert("AJAX 요청 실패: " + error);
           }
       });

       return false;
   }

   function verifycode(){
      let umail = $("#umail").val();
      let inputCode = $("input[name='umailChk']").val();
      
      console.log(inputCode);
      console.log(umail);
      $.ajax({
         url: '/verify-code',
         method: 'POST',
         data:{ to: umail,
               code: inputCode
         },
         success: function(result) {
            
               if (result.trim() === "0") { 
                   console.log("에러");
                   alert("메일 확인 실패");
               } else {
                   console.log("성공");
                   alert("메일 확인 성공");
                   $("#mailChk").val("1");
                   console.log(mailChk);
               }
           },
           error: function(xhr, status, error) {
               console.error("AJAX 요청 실패:", status, error);
               alert("AJAX 요청 실패: " + error);
           }
       });
   }
   </script>
   
<style>
    .emailchk{
       display:none;
    }
</style>   

<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
        window.location.href = "/"; // alert을 띄운 후 홈으로 이동
    </script>
</c:if>
   
</body>
</html>