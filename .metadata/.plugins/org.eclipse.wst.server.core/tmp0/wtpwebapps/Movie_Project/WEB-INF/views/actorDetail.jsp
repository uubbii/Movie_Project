<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>${movie.title}의자세한 정보!</title>
<!-- 1월 22일 11:48분 최종 수정함 -->
<link rel="stylesheet" href="/resources/CSS/home.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">

<style>
body {
	height: 100%;
	margin: 0;
	padding: 0;
}

header {
	background-color: #fff;
	box-shadow: 0 1px #00000014;
	display: block;
	text-align: center;
	height: 62px;
	width: 100%;
	left: 0;
}

nav {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
	display: block;
}

section {
	max-width: 1320px;
	margin-left: 100px;
	margin-right: 110px;
	display: block;
}

ul {
	list-style: none;
	display: flex;
	align-items: center;
	margin: 0;
	padding: 0;
	margin-top: 0;
	margin-bottom: 0;
	margin-left: 0;
	margin-right: 0;
}

.search {
	margin: 0 0 0 auto;
}

.modal {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	z-index: 20;
	display: none;
}

.bg {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	background: #71717142
}

.box {
	position: absolute;
	z-index: 21;
	background: #ffff;
	border-radius: 5px;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
	width: fit-content;
	height: fit-content;
	padding: 50px;
}

.box h5 {
	font-size: 20px;
	text-align: center;
	margin-bottom: 20px;
}

.box p {
	text-align: center;
}

.box input {
	display: block;
}

.is-show {
	display: block !important;
}

.bodylayout {
	margin-top: 80px;
}
/* 자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성자체생성 */
/* 기본 레이아웃 */
body {
    font-family: 'Arial', sans-serif;
    background-color: #f8f9fa; /* 밝은 배경 */
    color: #333; /* 글자 색 */
    margin: 0;
    padding: 0;
}

.Profile-details{
	display: flex;
    justify-content: space-evenly;
}

/* 전체 컨테이너 스타일 */
article.person-details {
    max-width: 1200px;
    margin: 2rem auto;
    padding: 2rem;
    background-color: #ffffff; /* 하얀 배경 */
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
    margin-top:62px;
}

/* 헤더 스타일 */
header h2 {
    font-size: 2rem;
    color: #F216A5; /* 파란색 제목 */
    margin-bottom: 1rem;
    text-align: center;
}

/* 프로필 이미지 스타일 */
figure {
    display: inline-block;
    text-align: center;
    margin: 0;
}

figure img {
    /* border-radius: 50%;
    border: 3px solid #007bff; */
    max-width: 150px;
    margin-bottom: 1rem;
    transition: transform 0.3s ease, box-shadow 0.3s ease, border-color 0.3s ease; /* 부드러운 변환 효과 */
}
/* 
figure img:hover {
    transform: scale(1.2); /* 이미지 크기를 약간 키움 */
    box-shadow: 0 4px 15px rgba(0, 123, 255, 0.5); /* 그림자 추가 */
    border-color: #0056b3; /* 테두리 색상 변경 */
}
 */


figcaption {
    font-size: 0.9rem;
    color: #888; /* 밝은 회색 */
}

/* 개인 정보 섹션 */
section.personal-info {
    margin-bottom: 2rem;
}


section.personal-info h3 {
    font-size: 2.5rem;
    color: #F216A5;
    margin-bottom: 1rem;
    text-align: center;
    /* text-shadow: 3px 2px 3px black; */
}

section.personal-info p {
    font-size: 1rem;
    margin: 0.5rem 0;
}

section.personal-info a {
    color: #F216A5;
    text-decoration: none;
}

section.personal-info a:hover {
    text-decoration: underline;
}


.aliases{
    display: flex;
    flex-direction: column;
    gap: 16px;
}
/* 별명 목록 스타일 */
section.aliases ul {
    padding-left: 20px;
    list-style-type: disc;
}

section.aliases li {
    font-size: 1rem;
    color: #555;
}

/* 영화 목록 */
/* 영화 목록 */
section.known-for-movies h2 {
    font-size: 1.5rem; /* 제목 크기를 좀 더 작게 */
    color: #F216A5;
    margin-top: 2rem;
    margin-bottom: 1rem;
    text-align: center;
}

.movie-list {
    display: grid;
    grid-template-columns: repeat(4, 1fr); /* 한 줄에 5개의 영화 포스터가 보이도록 설정 */
    gap: 1rem; /* 각 영화 아이템 간의 간격을 줄여서 더 많은 영화가 화면에 보이도록 */
}

.movie-item {
    width: 210px;
    height: 322px;
    background-color: #f1f1f1;
    padding: 0.5rem;
    border-radius: 8px;
    /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
    text-align: center;
    overflow: hidden;
}

.movie-item h3 {
    font-size: 1rem; /* 제목 크기를 작게 */
    color: #343a40;
    margin-bottom: 0.5rem;
}

.movie-item p {
    font-size: 0.875rem; /* 설명 텍스트를 작게 */
    color: #666;
}

.movie-item figure {
    margin: 0;
    padding: 0;
}

.movie-item img {
    border-radius: 0%;   
    margin-bottom: 1rem;
}

.movie-item a {
    color: #007bff;
    text-decoration: none;
}

/* .movie-item a:hover {
    text-decoration: underline;
}
 */
.movie-poster {
    width: 100%;
    max-height: 300px;
    object-fit: cover;
    transition: transform 0.3s ease;
}

/* .movie-poster:hover {
    transform: scale(2);
}
 */
    </style>


</head>
<body>
	<header class="" style="position: fixed; top: 0px;">
		<!-- 로그인 모달 -->
		<div class="modal login">
			<div class="bg"></div>
			<div class="box">
				<form action="/login" method="POST">
					<p>아이디</p>
					<input type="text" name="uid" />
					<p>비밀번호</p>
					<input type="password" name="upw" />
					<button class="menubtn" type="submit">로그인</button>
				</form>
				<div class="box_btn">
					<a href="/userform"><button class="menubtn">회원가입</button></a> 
					<a href="/"><button class="menubtn">아이디찾기</button></a>
					<a href="/"><button class="menubtn">비밀번호찾기</button></a>
				</div>
			</div>
		</div>
		<div class="modal ID">
         <div class="bg"></div>
         <div class="box">
            <form name="findid" method="post" action="/Find_Uid">
            <p>이메일을 입력하세요</p>
            <input type="text" id="umail" name="umail" />
            <p>전화번호를 입력하세요
            <p>
               <input type="text" id="uphone" name="uphone"/>
               <button class="menubtn">확인</button>
            </form>
         </div>
      </div>
      <div class="modal pw">
         <div class="bg"></div>
         <div class="box">
            <form name="findpw" method="post" action="/Find_Upw">
            <p>이메일을 입력하세요</p>
            <input type="text" id="umail" name="umail"/>
            <p>아이디를 입력하세요</p>
            <input type="text" id="uid" name="uid"/>
            <!-- 여기에 이제 컨트롤러에서 이메일 중복 확인, 확인이 되면 
         send-email로 이메일에 인증번호를 보낼 수 있도록 할 것임. -->
            <button class="menubtn">확인</button>
            </form>
         </div>
      </div>
	<nav>
			<section>
				<ul class="menu_bar">
					<li class="logo"><a href="/" type="button">
						<img class="logoimg" src="resources/IMG/moviepedia_22.png"></a></li>
					<li class="board"><a type="button"><span></span></a></li>
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

 
 <article class="person-details">
    <!-- 기본 정보 출력 -->
    <div class ="Profile-details">        
        <figure>
			    <c:choose>
	      		    <c:when test="${not empty person.profile_path and person.profile_path ne 'https://image.tmdb.org/t/p/w500null'}">
			            <img src="${person.profile_path}" alt="${person.name}" />
			        </c:when>
			        <c:otherwise>
			            <img src="${pageContext.request.contextPath}/resources/IMG/default2.jpg" alt="사진이 제공되지 않는 인물입니다." />
			        </c:otherwise>
			    </c:choose>
            <figcaption>
            		${person.name} 
				    <strong>(</strong> 
				    <c:choose>
				        <c:when test="${person.known_for_department == 'Acting'}">배우</c:when>
				        <c:when test="${person.known_for_department == 'Directing'}">감독</c:when>
				        <c:when test="${person.known_for_department == 'Writing'}">각본가</c:when>
				        <c:otherwise>N/A</c:otherwise>
				    </c:choose>
				    <strong>)</strong>
			</figcaption>
						            			
			<c:forEach var="alias" items="${person.also_known_as}">
			    <div class="alias-item" style="display: none;">
			        ${alias}<br>
			    </div>
			</c:forEach>
			
			<!-- "더보기" 버튼 -->
			<c:if test="${person.also_known_as.size() > 2}">
			    <button id="toggleBtn">배우의 다른 이름</button>
			</c:if>


        </figure>
        
            <section class="personal-info">
        <h3>인물 정보</h3>


        <p><strong>생일 : </strong> ${person.birthday}</p>
        <p><strong>성별 </strong> ${person.gender == 1 ? '여성' : '남성'}</p>
        <p><strong>국적 및 출신</strong> ${person.place_of_birth}</p>
        <p><strong>인기도</strong> ${person.popularity}</p>
        <p><strong>개인 홈페이지:</strong> <a href="${person.homepage}" target="_blank">${person.homepage}</a></p>


        <p><strong>인물 설명 : </strong> ${person.biography}</p>
        <c:if test="${not empty person.deathday}">
    		<p><strong>사망일 : </strong> ${person.deathday}</p>
		</c:if>

    </section>
        
    </div>


    <section class="known-for-movies">
        <h2>참여작품</h2>
        <ul class="movie-list">
            <c:forEach var="movie" items="${person.movie_credits.cast}">
					<li class="movie-item">
					    <figure>					        
					        <a href="<c:url value='/detail?id=${movie.id}'/>" class="movie-link">
							    <c:choose>
					      		    <c:when test="${not empty movie.poster_path and movie.poster_path ne 'https://image.tmdb.org/t/p/w500null'}">
							            <img src="${movie.poster_path}" alt="${actor.name}" class = "movie-poster"/>
							        </c:when>
							        <c:otherwise>
							            <img src="${pageContext.request.contextPath}/resources/IMG/default2.jpg" alt="사진이 제공되지 않는 포스터입니다." />
							        </c:otherwise>
							    </c:choose>
							</a>												      
					        <figcaption class="movie-title">${movie.title}</figcaption>
					    </figure>
					
					    <div class="movie-details">
					        <p><strong>개봉일 : </strong> ${movie.release_date}</p>
					        <p><strong>참여 배역 : </strong> ${movie.character}</p>
					    </div>
						</li>

            </c:forEach>
        </ul>
    </section>
</article>




	<div class="footlayout">
		<div class="foot_bar">
			<!-- <span>여기는 </span> -->
			<img class="logoimg" src="resources/IMG/moviepedia_22.png">
			<!-- <span> 입니다.</span> -->
		</div>
		<div class="foot_main">
			<ul class="foot_ulf">
				<li class="foot_li">서비스 이용약관</li>
				<li class="foot_li">개인정보 처리방침</li>
				<li class="foot_lif">회사 안내</li>
			</ul>
			<ul class="foot_ul">
				<li class="foot_li">고객센터</li>
				<li class="foot_lif">moviepeia@jj.ac.kr, +82-1577-7177</li>
			</ul>
			<ul class="foot_ul">
				<li class="foot_li">광고 문의</li>
				<li class="foot_lif">moviepeia@jj.ac.kr / 최신 광고 소개서</li>
			</ul>
			<ul class="foot_ul">
				<li class="foot_li">제휴 및 대외 협력</li>
				<li class="foot_lif">https://moviepedia.team/contact</li>
			</ul>
			<ul class="foot_ul">
				<li class="foot_li">무비피디아</li>
				<li class="foot_li">대표 구함</li>
				<li class="foot_lif">전북특별자치도 전주시 완산구 천잠로 303</li>
			</ul>
			<div class="copyright">
			<img class="foot_logo" src="resources/IMG/moviepedia_33.png">
			<span>© 2025 by MOVIEPEDIA, Inc. All rights reserved.</span>
			</div>
		</div>
	</div>
	
	
	

	<script>
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

	// 기존 모달 관련 이벤트 리스너들은 유지
	document.querySelector('.modal .bg').addEventListener(
			'click',
			function() {
				document.querySelector('.modal.login').classList
						.remove('is-show');
			});

	document.querySelector('.modal.login .box a:nth-of-type(2)')
			.addEventListener(
					'click',
					function(event) {
						event.preventDefault();
						document.querySelector('.modal.login').classList
								.remove('is-show');
						document.querySelector('.modal.ID').classList
								.add('is-show');
					});

	document.querySelector('.modal.login .box a:nth-of-type(3)')
			.addEventListener(
					'click',
					function(event) {
						event.preventDefault();
						document.querySelector('.modal.login').classList
								.remove('is-show');
						document.querySelector('.modal.pw').classList
								.add('is-show');
					});

	document.querySelector('.modal.ID .bg').addEventListener(
			'click',
			function() {
				document.querySelector('.modal.ID').classList
						.remove('is-show');
			});

	document.querySelector('.modal.pw .bg').addEventListener(
			'click',
			function() {
				document.querySelector('.modal.pw').classList
						.remove('is-show');
			});
	
	//상단 메뉴바 투명설정
	const header = document.querySelector("#header");
      window.addEventListener("scroll", function () {
        if (window.scrollY > 75) {
          header.classList.add("on");
        } else {
          header.classList.remove("on");
        }
      });
     const img = document.querySelector("logoimg");
     	window.addEventListener("scroll", function (){
     		if(window.scrollY > 75){
     			document.getElementById("logoimg").src="resources/IMG/moviepedia_22.png";
     		}else {
     			document.getElementById("logoimg").src="resources/IMG/moviepedia_33.png";
     		}
     	});
     	
  		//추천, 신고 버튼 기능
        document.getElementById("recommendButton").onclick = function() {
        	console.log("추천버튼 Clicked(detail 243번째줄)")
            // 추천 기능 추가
        };

        document.getElementById("reportButton").onclick = function() {
            console.log("신고버튼 Clicked(detail 248번째줄)")
            // 신고 기능 추가
        };
   
     	
     	
     	
     	
        
        

     	
     	// 리뷰 더보기 버튼 , 갤러리 넘기기 버튼 변수명 변경해야함 !01/24! 
     	
        document.getElementById('reviewToggleButton').addEventListener('click', function() {
   		const reviewLists = document.querySelectorAll('.review_list');
    	const isShowingAll = this.textContent === '숨기기';

    	if (isShowingAll) {
        	// 9번째부터 숨기기
        	reviewLists.forEach((review, index) => {
        	    if (index >= 8) {
        	        review.style.display = 'none';
        	    }
        	});
        	this.textContent = '더보기';
    	} else {
        // 모든 리뷰 보이게 하기
        reviewLists.forEach((review, index) => {
            if (index >= 8) {
                review.style.display = 'block';
            }
        	});
        this.textContent = '숨기기';
    		}
		});
     	
     	
     	document.addEventListener('DOMContentLoaded', function() {
     	    const container = document.querySelector('.carousel-container');
     	    const prevBtn = document.querySelector('.prev');
     	    const nextBtn = document.querySelector('.next');
     	    
     	    nextBtn.addEventListener('click', () => {
     	        container.scrollLeft += container.offsetWidth;
     	    });
     	    
     	    prevBtn.addEventListener('click', () => {
     	        container.scrollLeft -= container.offsetWidth;
     	    });
     	});
     	
     	document.addEventListener('DOMContentLoaded', function() {
     	    const container = document.querySelector('.carousel2-container');
     	    const prevBtn = document.querySelector('.prev2');
     	    const nextBtn = document.querySelector('.next2');
     	    
     	    nextBtn.addEventListener('click', () => {
     	        container.scrollLeft += container.offsetWidth;
     	    });
     	    
     	    prevBtn.addEventListener('click', () => {
     	        container.scrollLeft -= container.offsetWidth;
     	    });
     	});
	</script>
	
	
	<!-- 배우의 다른 이름들에 대한 스크립트문 -->
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const aliasItems = document.querySelectorAll('.alias-item');
	    const toggleBtn = document.getElementById('toggleBtn');

	    // 처음에 2개 항목만 보이게 설정
	    for (let i = 0; i < aliasItems.length; i++) {
	        aliasItems[i].style.display = 'none'; // 2개 초과 항목은 숨김
	    }

	    // "더보기"/"감추기" 버튼 클릭 시 동작
	    if (toggleBtn) {
	        toggleBtn.addEventListener('click', function() {
	            // 현재 버튼 텍스트가 "더보기"일 때
	            if (toggleBtn.textContent === "배우의 다른 이름") {
	                for (let alias of aliasItems) {
	                    alias.style.display = 'block'; // 모든 항목 보이게 변경
	                }
	                toggleBtn.textContent = "감추기"; // 버튼 텍스트 변경
	            } else {
	                // 현재 버튼 텍스트가 "감추기"일 때
	                for (let i = 0; i < aliasItems.length; i++) {
	                    aliasItems[i].style.display = 'none'; // 숨기기
	                }
	                toggleBtn.textContent = "배우의 다른 이름"; // 버튼 텍스트 변경
	            }
	        });
	    }
	});

	</script>
	
</body>
</html>