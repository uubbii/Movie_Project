<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>userinfo</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script defer src="/resources/JS/user.js"></script>
<link rel= "stylesheet" href ="/resources/CSS/userinfo.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">
<style>
   @font-face {
       font-family: 'Pretendard-Regular';
       src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
          font-weight: 400;
       font-style: normal;
   }
   *{
      font-family: 'Pretendard-Regular';
   }
</style>

</head>
<body>
	<header class="" style="position: fixed; top: 0px;">
		<nav>
			<section>
				<ul class="menu_bar">
					<li class="logo"><a href="/" type="button">
						<img class="logoimg" src="resources/IMG/moviepedia_22.png"></a></li>
					<li class="search">
                   		<form action="/search?=" method="get">
                       		<input type="text" name="word" placeholder="영화감독, 제목, 배우를 검색해보세요">
                   		</form>
               		</li>
					<li class="login">
					    <button class="loginbtn">
					        <c:set var="loginId" value="${sessionScope eq null ? '' : sessionScope.usaveprofile}" />
					        <c:set var="loginOut" value="${loginId eq null ? '로그인' : '로그아웃'}" />
					        <c:out value="${loginOut}" />
					    </button>
					</li>
					<li class="profile">
					    <c:if test="${not empty sessionScope && not empty sessionScope.usaveprofile}">
					        <!-- 세션이 존재하고 프로필 사진 경로가 있을 때만 표시 -->
					        <a href="userupdate" type="button">
					            <img
					                id="profileImage"
					                src="${sessionScope.usaveprofile}"
					                alt="프로필 사진"
					                style="width: 50px; height: 50px; border-radius: 50%;"
					            />
					        </a>
					    </c:if>
					</li>
				</ul>
			</section>
		</nav>
	</header>
<!-- 비밀번호 수정 modal  -->
   <div class="modal pw">
      <div class="bg"></div>
      <div class="box">
      <form name="modalpw" method="post" action="User_Update-password">
      <input type="password" name="upupw" id="upupw" placeholder="새 비밀번호를 입력하세요.">
      <input type="password" name="upupw2" id="upupw2" placeholder="다시 한번 입력해주세요.">
      <button type="button" ><a href="javascript:updatepassword();">이이잉</a></button>
      </form>
      </div>
   </div>
   <!-- 회원 정보 수정 modal -->
   <div class="modal info">
               <div class="bg"></div>
               <div class="box">
                  <form name = "modalinfo" id = "modalinfo" method="post" action="User_Update-info" enctype="multipart/form-data">
                  <input type="hidden" name="modalid" id="modalid" value=${sessionScope.uid }>
                  <input type="hidden" name = "uphone" id = "modalphone" value = ${sessionScope.uphone }>
                  <input type="hidden" name = "umail" id ="modalmail" value = ${sessionScope.umail }>
                  <input type="hidden" name="modalpost" id="modalpost" value = "${sessionScope.upost }">
              <input class="btn1" type="hidden" id="addrbutton" value="우편번호찾기" onclick="uppostSearch();"><br>
                 <input type="hidden" name="modaladdr1" id="modaladdr1" value="${sessionScope.uaddr1 }"><br>
                 <input type="hidden" name="modaladdr2" id="modaladdr2" value="${sessionScope.uaddr2 }"><br>
                  <button type="button" onclick="javascript:updateinfo();">확인</button>
                  </form>
               </div>
            </div>


 
 
 
<div class="bodylayout">
   <div class="userChk">
      <h2 class="content-title">비밀번호 확인</h2>
      <div class="info-item">
         <label>이름 : </label>
         <span>${sessionScope.uname}</span>
      </div>
      <div class="info-item">
         <label>아이디 : </label>
         <span>${sessionScope.uid}</span>
      </div>
      <div class="info-item">
         <label>비밀번호 : </label>
         <input type="password" name="upw" id="upwCheck" placeholder="비밀번호를 입력해주세요.">
         <button class="verify-btn">확인</button> 
      </div>             
   </div>
   <div id="userInfo" class="content-section" style="display: none;">
      <h2 class="content-title">회원정보</h2>
      <div class="info-grid">
         <form name="userinfo" method="post">
            <div class="info-item">
               <label>이름 : </label>
               <span>${sessionScope.uname}</span>
            </div>
            <div class="info-item">
               <label>아이디 : </label>
               <span>${sessionScope.uid}</span>
            </div>
            <div class="info-item">
               <label>비밀번호</label>
               <span class="material-symbols-outlined">vpn_key</span>
               <button><a href="javascript:modalpassword();">수정</a></button>
            </div>
            <div class="info-item">
               <label>생년월일:</label>
               <span>${sessionScope.ubirth}</span>
            </div>
            <div class="info-item">
               <label>전화번호:</label>
               <span>${sessionScope.uphone}</span>
               <button><a href="javascript:updatephone();">수정</a></button>
            </div>
            <div class="info-item">
               <label>이메일:</label>
               <span>${sessionScope.umail}</span>
               <button><a href="javascript:updatemail();">수정</a></button>
            </div>
            <div class="info-item">
               <label>주소:</label>
               <span>${sessionScope.uaddr1} ${sessionScope.uaddr2 }</span>
               <button><a href="javascript:updateaddr();">수정</a></button>
            </div>
         </form>
         <form id="userForm" action="/User_Update-photo" method="POST" enctype="multipart/form-data">
            <div class="info-item">
               <label>프로필사진:</label>
               <input type="hidden" name = "uid" id = "uid" value = ${sessionScope.uid }>
               <input type="hidden" name="jspsaveprofile" id="jspsaveprofile" value=${sessionScope.usaveprofile }/>
               <input type="file" name="jspprofile" id="jspprofile" value=${sessionScope.uprofile }/>
               <input type="submit" onclick="updatephoto();" value="업로드" />
            </div>
         </form>
      </div>
   </div>
</div>
</body>

<script>

function updatepassword(){
   const upw = document.getElementById('upw').value;
   const uid = document.getElementById('uid').value;
   console.log(upw);
   console.log(uid);
   document.userinfo.action = '/user/update';
   document.userinfo.submit();
};

document.addEventListener('DOMContentLoaded', function() {
    const verifyBtn = document.querySelector('.verify-btn');
    const userChk = document.querySelector('.userChk');
    const userInfo = document.getElementById('userInfo');

    // 비밀번호 확인 버튼 클릭 이벤트
    verifyBtn.addEventListener('click', function() {
        userChk.style.display = 'none';
        userInfo.style.display = 'block';
    });
});


//로그인 modal is-show
document.querySelector('.loginbtn').addEventListener(
		'click',
		function() {
			// 현재 버튼의 텍스트가 '로그아웃'인지 확인
			const isLogout = this.textContent.trim() === '로그아웃';

			if (isLogout) {
				// 로그아웃 상태면 로그아웃 처리
				window.location.href = '/logout';
			} else {
				// 로그인 상태가 아니면 모달창 표시
				document.querySelector('.modal.login').classList
						.add('is-show');
			}
		});



</script>
</html>