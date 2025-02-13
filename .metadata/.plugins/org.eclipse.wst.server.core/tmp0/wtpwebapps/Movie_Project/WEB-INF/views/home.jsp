<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>main</title>
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
            <form name="findid" method="post" action="/Find_Uid" onsubmit="return handleFormSubmit(event);">
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
            <form name="findpw" method="post" action="/Find_Upw" onsubmit="return handleFormSubmit2(event);">
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
	<div class="bodylayout">	
		<h3 class="title_upcoming">개봉 예정 영화</h3>
		<div class="movie_box_up">
			<button class="nav_button left">&#8249;</button>
			<div class="movie_container">
				<c:forEach items="${UPCOMING}" var="movieResponse">
					<c:forEach items="${movieResponse.results}" var="movie">
						<div class="movie_item">
							<div class="movie_content">
								<a href="<c:url value='/detail?id=${movie.id}'/>" class="movie_blank">
									<img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" />
								</a>
								<div class="movie_info">
								<div class="movie_title">${movie.title }</div>
									<div class="movie_date_ganre">
										<div class="movie_releasedate">${movie.release_date }</div>
										<span class="movie_span">&#183;</span>
										<div class="movie_ganreids">
											<c:forEach items="${movie.genrename}" var="genreName"
												varStatus="status">
                            					${genreName}${!status.last ? ', ' : ''}
                        					</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
			<button class="nav_button right">&#8250;</button>
		</div>
		<h3 class="title_day">오늘의 인기영화</h3>
		<div class="movie_box_day">
			<button class="nav_button left">&#8249;</button>
			<div class="movie_container">
				<c:forEach items="${DAY}" var="movieResponse">
					<c:forEach items="${movieResponse.results}" var="movie">
						<div class="movie_item">
							<div class="movie_content">
								<a href="<c:url value='/detail?id=${movie.id}'/>" class="movie_blank">
									<img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" />
								</a>
								<div class="movie_info">
									<div class="movie_title">${movie.title }</div>
									<div class="movie_date_ganre">
										<div class="movie_releasedate">${movie.release_date }</div>
										<span class="movie_span">&#183;</span>
										<div class="movie_ganreids">
											<c:forEach items="${movie.genrename}" var="genreName"
												varStatus="status">
                            					${genreName}${!status.last ? ', ' : ''}
                        					</c:forEach>
										</div>
									</div>
									<%-- <div class="movie_overview">줄거리 : ${movie.overview}</div> --%>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
			<button class="nav_button right">&#8250;</button>
		</div>
		<h3 class="title_day">이번 주의 인기영화</h3>
		<div class="movie_box_week">
			<button class="nav_button left">&#8249;</button>
			<div class="movie_container">
				<c:forEach items="${WEEK}" var="movieResponse">
					<c:forEach items="${movieResponse.results}" var="movie">
						<div class="movie_item">
							<div class="movie_content">
								<a href="<c:url value='/detail?id=${movie.id}'/>" class="movie_blank">
									<img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" />
								</a>
								<div class="movie_info">
								<div class="movie_title">${movie.title }</div>
									<div class="movie_date_ganre">
										<div class="movie_releasedate">${movie.release_date }</div>
										<span class="movie_span">&#183;</span>
										<div class="movie_ganreids">
											<c:forEach items="${movie.genrename}" var="genreName"
												varStatus="status">
                            					${genreName}${!status.last ? ', ' : ''}
                        					</c:forEach>
										</div>
									</div>
									<%-- <div class="movie_overview">줄거리 : ${movie.overview}</div> --%>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
			<button class="nav_button right">&#8250;</button>
		</div>
		
		<h3>지금 뜨는 영화리뷰</h3>
		<div class="review_box">
			<div class="review_container">
				<div class="review_list">
					<button class="review_btn left">&#8249;</button>
					<ul class="review_ul">
						<c:forEach var="review" items="${RecentReviews}">
							<li class="review_li">
								<a class="review_link" href="/detail?id=${review.mid}">
									<div class="review_detail">
										<div class="review_top">
									<div class="review_top">
		                                 <div class="review_title">${review.rtitle }</div>
		                                 <div class="review_count">
		                                    조회수 : ${review.rview }
		                                 </div>
		                            </div>
										</div>
										<div class="review_mid">
											<div class="review_poster"></div>
											<div class="review_content">${review.rcontent }</div>
										</div>
										<div class="review_bottom">
											<span class="material-symbols-outlined">thumb_up</span>
											<div class="review_good">${review.rgood }</div>
											<span class="material-symbols-outlined">siren</span>
											<div class="review_view">${review.rreport }</div>
										</div>
									</div>
								</a>
							</li>
						</c:forEach>
					</ul>
					<button class="review_btn right">&#8250;</button>
				</div>
			</div>
		</div>
	</div>
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

		function handleFormSubmit(event) {
		    event.preventDefault(); 

		    const form = document.forms['findid']; 

		    fetch(form.action, {
		        method: form.method,
		        body: new URLSearchParams(new FormData(form))
		    })
		    .then(response => response.text())
		    .then(result => {
		        console.log("서버 응답:", result);

		        if (result.trim() === "1") {
		            alert("메일이 발송되었습니다.");
		        } else {
		            alert("메일 전송 실패");
		        }

		        window.location.href = "/";  
		    })
		    .catch(error => {
		        console.error("서버 오류:", error);
		        alert("서버 오류 발생! 다시 시도해주세요.");
		        window.location.href = "/";
		    });

		    return false;
		}

	      
		function handleFormSubmit2(event) {
		    event.preventDefault(); 

		    const form = document.forms['findpw']; 

		    fetch(form.action, {
		        method: form.method,
		        body: new URLSearchParams(new FormData(form))
		    })
		    .then(response => response.text())
		    .then(result => {
		        console.log("서버 응답:", result);

		        if (result.trim() === "1") {
		            alert("임시비밀번호가 메일로 발송되었습니다.");
		        } else {
		            alert("메일 전송 실패");
		        }

		        window.location.href = "/";  
		    })
		    .catch(error => {
		        console.error("서버 오류:", error);
		        alert("서버 오류 발생! 다시 시도해주세요.");
		        window.location.href = "/";
		    });

		    return false;
		}

		//영화 슬라이드
		document.addEventListener("DOMContentLoaded", () => {
    // 슬라이드 이동 함수
    function slide(container, direction) {
        const scrollAmount = container.offsetWidth; // 컨테이너 가로 크기만큼 이동
        if (direction === "left") {
            container.scrollBy({ left: -scrollAmount, behavior: "smooth" });
        } else if (direction === "right") {
            container.scrollBy({ left: scrollAmount, behavior: "smooth" });
        }
    }
    
    // 개봉예정영화 슬라이드 버튼 이벤트
    const upMovieContainer = document.querySelector(".movie_box_up .movie_container");
    const upLeftButton = document.querySelector(".movie_box_up .nav_button.left");
    const upRightButton = document.querySelector(".movie_box_up .nav_button.right");

    upLeftButton.addEventListener("click", () => slide(upMovieContainer, "left"));
    upRightButton.addEventListener("click", () => slide(upMovieContainer, "right"));

    // 오늘의 인기영화 슬라이드 버튼 이벤트
    const dayMovieContainer = document.querySelector(".movie_box_day .movie_container");
    const dayLeftButton = document.querySelector(".movie_box_day .nav_button.left");
    const dayRightButton = document.querySelector(".movie_box_day .nav_button.right");

    dayLeftButton.addEventListener("click", () => slide(dayMovieContainer, "left"));
    dayRightButton.addEventListener("click", () => slide(dayMovieContainer, "right"));

    // 이번 주의 인기영화 슬라이드 버튼 이벤트
    const weekMovieContainer = document.querySelector(".movie_box_week .movie_container");
    const weekLeftButton = document.querySelector(".movie_box_week .nav_button.left");
    const weekRightButton = document.querySelector(".movie_box_week .nav_button.right");

    weekLeftButton.addEventListener("click", () => slide(weekMovieContainer, "left"));
    weekRightButton.addEventListener("click", () => slide(weekMovieContainer, "right"));

		
		
	const reviewContainer = document.querySelector(".review_list .review_ul");
	const reviewLeftButton = document.querySelector(".review_list .review_btn.left");
    const reviewRightButton = document.querySelector(".review_list .review_btn.right");

	reviewLeftButton.addEventListener("click", () => slide(reviewContainer, "left"));
	reviewRightButton.addEventListener("click", () => slide(reviewContainer, "right"));
		
	});	
	</script>
</body>
</html>