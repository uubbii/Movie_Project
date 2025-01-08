<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>main</title>
<style>
   body{
   height:100%;
   margin:0;
   padding:0;
   }
   header{
      background-color: #fff;
      box-shadow: 0 1px #00000014;
      display: block;
      text-align: center;
      height: 62px;
      width: 100%;
      left: 0;
   }
   nav{
      width: 100%;
      margin-left: auto;
      margin-right: auto;
      display: block;
   }
   section{
      max-width: 1320px;
      margin-left:100px;
      margin-right:110px;
      display: block;
   }
   ul{
      list-style: none;
      display: flex;
      align-items: center;
      margin:0;
      padding:0;
      margin-top:0;
      margin-bottom:0;
      margin-left:0;
      margin-right:0;
   }
   li{
      position: relative;
         height: 62px;
         unicode-bidi: isolate;
         margin: 0 0 0 10px;
       display: flex;           /* 추가 */
       align-items: center;     
   }
   .search{
      margin: 0 0 0 auto;
   }
   a{
      height: 40px;
       padding: 0 16px;
       display: flex;           /* 추가 */
       align-items: center;    
   }
   
   .modal{ position:fixed; top:0; bottom:0; left:0; right:0; z-index: 20; display:none;}
   .modal{}.pwChk{}
   .modal{} .bg{position:fixed; top:0; bottom:0; left:0; right:0; background:#71717142}
   .modal{} .box{position:absolute; z-index:21; background:#ffff; border-radius: 5px;
   top:0; bottom:0; left:0; right:0; margin:auto; width:fit-content; height:fit-content; padding:50px;}
   .modal{} .box h5{font-size:20px; text-align:center; margin-bottom:20px;}
   .modal{} .box p{text-align:center;}
   .modal{} .box input{display:block; margin: 20px 0;}
   .modal{} .box a{}
   .is-show{display:block !important;}
   
</style>
</head> 
<body>
   <header class="" style="position:fixed; top: 0px;">
   <!-- 로그인 모달 -->
   <div class="modal login">
      <div class="bg"></div>
      <div class="box">
         <p>아이디 입력하게</p>
         <input type="text" class=""/>
         <p>비밀번호 입력하게</p>
         <input type="password"/>
         <button>로그인</button>
         <a  href="/userform" ><button>회원가입 들어갈곳</button></a>
         
         <a href="/">아이디를 잊으셨나요</a><a href="/">비밀번호를 잊으셨나요?</a>
      </div>
   </div>
   <div class="modal ID">
      <div class="bg"></div>
      <div class="box">
         <p>이메일을 입력하세요</p>
         <input type="text"/>
         <p>전화번호를 입력하세요<p>
         <input type="text"/>
         <button>확인</button>
      </div>
   </div>
   <div class="modal pw">
      <div class="bg"></div>
      <div class="box">
         <p>이메일을 입력하세요</p>
         <input type="text"/>
         <!-- 여기에 이제 컨트롤러에서 이메일 중복 확인, 확인이 되면 
         send-email로 이메일에 인증번호를 보낼 수 있도록 할 것임. -->
         <button>확인</button>
      </div>
   </div>
      <nav>
         <section>
            <ul>
               <li class="logo">
                  <a href="" type="button"><span>왓챠피디아(로고)</span></a>
               </li>
               <li class="board">
                  <a type="button"><span>게시판</span></a>
               </li>
               <li class="search">
                  <input type="text" name="Search" placeholder="영화감독,제목,배우를 검색해보세요">
               </li>
               <li class="login">
                  <button class="loginbtn"><span>로그인</span></button>
               </li>
               <li class="profile">
                  <a type="button"><span>프로필사진</span></a>
               </li>
            </ul>
         </section>
      </nav>
   </header>
   <script>
   //로그인 modal is-show
   document.querySelector('.loginbtn').addEventListener('click', function () {
       document.querySelector('.modal.login').classList.add('is-show');
   });
   //로그인 modal 클릭하면 닫히도록
   document.querySelector('.modal .bg').addEventListener('click', function () {
       document.querySelector('.modal.login').classList.remove('is-show');
   });
   //로그인 modal 안에서 아이디찾기
   document.querySelector('.modal.login .box a:nth-of-type(2)').addEventListener('click', function (event) {
        event.preventDefault();
        document.querySelector('.modal.login').classList.remove('is-show');
        document.querySelector('.modal.ID').classList.add('is-show');
    });
   //로그인 modal 안에서 비밀번호 찾기
   document.querySelector('.modal.login .box a:nth-of-type(3)').addEventListener('click', function (event){
        event.preventDefault(); 
        document.querySelector('.modal.login').classList.remove('is-show');
        document.querySelector('.modal.pw').classList.add('is-show');
    });
   //아이디찾기에서 다른곳 클릭하면 닫히는
   document.querySelector('.modal.ID .bg').addEventListener('click', function () {
       document.querySelector('.modal.ID').classList.remove('is-show');
   });
   //비밀번호찾기에서 다른곳을 클릭하면 닫히는
   document.querySelector('.modal.pw .bg').addEventListener('click', function () {
       document.querySelector('.modal.pw').classList.remove('is-show');
   });
   </script>
</body>
</html>