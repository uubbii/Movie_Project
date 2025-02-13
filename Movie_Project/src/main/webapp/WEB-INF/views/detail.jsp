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
<link rel="stylesheet" href="/resources/CSS/detail.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	

<style>
section {
	max-width: 1320px;
	margin-left: 110px;
	margin-right: 110px;
	display: block;
}

ul {
	list-style: none;
	display: flex;
	align-items: center;
	gap:10px;
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

.top_a {
	height: 40px;
	padding-right: 16px;
	display: flex; 
	align-items: center;
}




/* 배우 정보 섹션 */


</style>

</head>
<body>
	<header>
		<!-- 로그인 모달 -->
		<div class="modal login">
			<div class="bg"></div>
			<div class="box">
				<form action="/login" method="POST">
					<p>아이디</p>
					<input class="login_input" type="text" name="uid" />
					<p>비밀번호</p>
					<input class="login_input" type="password" name="upw" />
					<button class="menubtn" type="submit">로그인</button>
				</form>
				<a href="/userform"><button class="menubtn">회원가입</button></a> 
				<a href="/"><button class="menubtn">아이디찾기</button></a>
				<a href="/"><button class="menubtn">비밀번호찾기</button></a>
			</div>
		</div>
		<div class="modal ID">
         <div class="bg"></div>
         <div class="box">
            <form name="findid" method="post" action="Find_Uid" onsubmit="return handleFormSubmit(event);">
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
            <form name="findpw" method="post" action="Find_Upw" onsubmit="return handleFormSubmit2(event);">
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
		<nav id="header">
			<section>
				<ul class="menu_bar">
					<li class="logo">
						<a href="/" type="button">
							<img id="logoimg" class="logoimg" src="resources/IMG/moviepedia_33.png">
						</a>
					</li>
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
    					<c:if test="${not empty sessionScope}">
        				<!-- 프로필 사진이 있고 값이 null이 아닐 때 -->
        					<c:if test="${not empty sessionScope.usaveprofile && sessionScope.usaveprofile ne 'null'}">
            					<a href="userupdate" type="button">
                					<img src="${sessionScope.usaveprofile}" alt="프로필 사진" style="width: 50px; height: 50px; border-radius: 50%;">
            					</a>
       						</c:if>
        				<!-- 프로필 사진이 없거나 null일 때 기본 이미지 -->
        					<c:if test="${empty sessionScope.usaveprofile || sessionScope.usaveprofile eq 'null'}">
           	 					<a href="userupdate" type="button">
                					<img src="${pageContext.request.contextPath}/resources/UPLOAD/Default.jpg" alt="기본 프로필 사진" style="width: 50px; height: 50px; border-radius: 50%;">
            					</a>
        					</c:if>
    					</c:if>
					</li>
				</ul>
			</section>
		</nav>
	</header>

	
	<div class="bodylayout">
		<div class="movie_top_box">
			<div class="movie_back">
			    <c:choose>
	     		    <c:when test="${not empty movie.backdrop_path and movie.backdrop_path ne 'https://image.tmdb.org/t/p/original/null'}">
						<img class="backimg" src="https://image.tmdb.org/t/p/original${movie.backdrop_path}" alt="${movie.title}" />
		      		</c:when>
		        <c:otherwise>
		            <img src="${pageContext.request.contextPath}/resources/IMG/default2.jpg" alt="포스터의 사진이 제공되지 않는 작품입니다." />
		        </c:otherwise>
		 	   </c:choose>
			</div>
			<div class="movie_container">
				<div class="movie_info">
					<h1>${movie.title }</h1>
					<div class="movie_title">${movie.original_title }</div>
					<div class="movie_releasedate">${movie.release_date }</div>
					<div class="movie_ganreids">
						<c:forEach items="${movie.genrename}" var="genreName"
							varStatus="status">
        	                    ${genreName}${!status.last ? ', ' : ''}
        	            </c:forEach>
					</div>
				</div>
			</div>
			
							
			
		</div>
		<div class="movie_detail_box">
			<div class="detail_container">
				<div class="detail_img">
					<div class="detail_img_poster">
					    <c:choose>
			      		    <c:when test="${not empty movie.poster_path and movie.poster_path ne 'https://image.tmdb.org/t/p/w500null'}">
					            <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}" />
					        </c:when>
					        <c:otherwise>
					            <img src="${pageContext.request.contextPath}/resources/IMG/default2.jpg" alt="포스터의 사진이 제공되지 않는 작품입니다." />
					        </c:otherwise>
					    </c:choose>											
					</div>
				</div>
				<div class="detail_info">
					 <h3>${movie.title }</h3>
					<div class="detail_overview">
						<p>${movie.overview}</p>
					</div>
				</div>
			</div>
				
		</div>
	

		<div class="movie_actor_box">
			<div class="actor_container">
				<h2>배우</h2>
				<div class="actor_list">
					<button class="actor_btn">&lt;</button>
					<ul class="actor_ul">
						<c:forEach var="actor" items="${movie.casts }">
							<li class="actor_li">
								<a href="<c:url value='/actorDetail?id=${actor.id}'/>" class="actor_link">
			                    	<img src="<c:choose>
			                               <c:when test="${not empty actor.profile_path and actor.profile_path ne 'https://image.tmdb.org/t/p/w500null'}">
			                                    ${actor.profile_path}
			                                </c:when>
			                                <c:otherwise>
			                                   ${pageContext.request.contextPath}/resources/IMG/default2.jpg
			                                </c:otherwise>
			                              </c:choose>" alt="${actor.name}"  class="actor_img"/>
			                        <div class="actor_name_box">
			                        	<div class="actor_name_box2">
			                        		<div class="actor_name">${actor.name} (${actor.original_name })</div>
			                        		<div class="actor_name2">${actor.character}</div>
			                        	</div>
			                        </div>
			                	</a>
							</li>
						</c:forEach>
					</ul>
					<button class="actor_btn1">&gt;</button>
				</div>
			</div>
		</div>



		<div class="movie_url_box">
			<div class="url_img_container">
				<h2>갤러리</h2>
				<div class="url_img">
					<button class="carousel-button prev">&lt;</button>
    				<div class="carousel-container">
        				<c:forEach var="imageUrl" items="${movie.imageUrls}">
            				<div class="carousel-item">
                				<img src="${imageUrl}" alt="영화 이미지">
            				</div>
        				</c:forEach>
    				</div>
    				<button class="carousel-button next">&gt;</button>
				</div>			
			</div>
			
		
			<div class="url_video_box">
				<div class="url_video_container">
					<h2>동영상</h2>
					<div class="url_video">
						<button class="carousel2-button prev2">&lt;</button>
    					<div class="carousel2-container">
        					<c:forEach var="videoUrl" items="${movie.videoUrls}">
            					<div class="carousel2-item">
            						<a class="url_link" href="https://www.youtube.com/watch?v=${videoUrl }" target="blank">
                						<img class="thumbnail" src="https://img.youtube.com/vi/${videoUrl }/mqdefault.jpg" alt="영화 이미지">
                					</a>
            					</div>
        					</c:forEach>
    					</div>
    					<button class="carousel2-button next2">&gt;</button>
					</div>	
				</div>
			</div>
		</div>
		
		<div class="review_box">
			<div class="review_container">
				<div class="review_container_head">
					<h2 class = "page_title">영화 리뷰</h2>
					<div class="review_button">
    					<button class="reviewToggleButton" id="reviewToggleButton">더보기</button>
					</div>
				</div>
				
				
				<!-- 여기부터 -->
					<ul class="review_card">
					    <c:forEach var="review" items="${reviews}">
					        <li class="review_list" id="review_${review.ridx}">
					            <div class="review_comment">
					                <div class="review_info_top">
					                    <div class="review_info_title">${review.rtitle}</div>
					                    <div class="review_info_uidx">${review.uidx}</div>
					                </div> 
								<div class="review_info_rcontent">
                                  <form id="commentForm${review.ridx}" action="comment" method="post">
                                      <input type="hidden" name="ridx" value="${review.ridx}">
                                      <input type="hidden" name="mid" value="${movie.id}">
                                      <input type="hidden" name="poster_path" value="${movie.poster_path}">
                                      <input type="hidden" name="mname" value="${movie.title}">
                                  </form>                                            
                                  <a href="#" onclick="document.getElementById('commentForm${review.ridx}').submit(); return false;" class="review_link">
                                      <div>${review.rcontent}</div>
                                  </a>
                              </div>
<script>
    function submitFormAndLog(rvo, mid) {
        // 콘솔에 로그 찍기
        console.log("리뷰 폼 전송 - rvo: " + rvo + ", mid: " + mid);

        // 폼 전송
        document.getElementById('commentForm' + rvo).submit();
    }
</script>
					                <div class="review_info_mid">
					                    <div class="review_info_rgood">
					                        <button class="recommendButton"
					                                data-targetridx="${review.ridx}"
					                                data-type="1"
					                                data-movieid="${movie.id}"> <!-- movie.id 추가 -->
					                            <span class="material-symbols-outlined">thumb_up</span>
					                        </button>
					                        <div>${review.rgood}</div>
					                    </div>
					                    <div class="review_info_rreport">
					                        <button class="reportButton"
					                                data-targetridx="${review.ridx}"
					                                data-type="2"
					                                data-movieid="${movie.id}"> <!-- movie.id 추가 -->
					                            <span class="material-symbols-outlined">siren</span>
					                        </button>
					                        <div>${review.rreport}</div>
					                    </div>
					                </div>
					                <div class="review_info_rinsertdate">${review.rinsertdate}</div>
					            </div>
					        </li>
					    </c:forEach>
					</ul>

								
				<!-- 여기까지 수정됨 !! -->
				
			</div>
				
			<div class="comment_box">
				<div class="comment_container">
					<h2>리뷰 남기기</h2>
					<div class="form_box">
						<form action="/submitPost" method="post">
							<div class="review_title">
								<div class="review_rtitle">
				    				<label for="rtitle"></label>
				    				<%-- <label class="rtitle">${movie.title }</label> --%>
				    				<input class="r_input" type="text" id="rtitle" name="rtitle" value="${post.rtitle != null ? post.rtitle : ''}" placeholder="리뷰 제목을 적어주세요." required />
								</div>
								<div class="review_rname">
				    				<label for="uidx"></label>
				    				<label class="작성자">작성자</label>
				    				<input class="r_input" readonly type="text" id="uidx" name="uidx" value="${sessionScope.uname}" placeholder="로그인하셔야 리뷰를 작성할 수 있습니다." required />
								</div>	
							</div>
							
							<div class="review_content">
				    			<label for="rcontent"></label>
				    			<textarea id="r_content" name="rcontent" rows="4" cols="50" placeholder="이 ${movie.title }에 대한 생각을 자유롭게 표현해주세요!" required>${post.rcontent != null ? post.rcontent : ''}</textarea>
							</div>
							<input type="hidden" name="uidx" value="${sessionScope.uidx}" />
							<input type="hidden" name="mid" value="${movie.id }" />
				    		<button class="review_sbm_btn" type="submit">게시글 작성</button>
    					</form>
					</div>
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
(function(){
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
        document.querySelector('.modal.login').classList.add('is-show');
      }
    });

  // 기존 모달 관련 이벤트 리스너들은 유지
  document.querySelector('.modal .bg').addEventListener(
    'click',
    function() {
      document.querySelector('.modal.login').classList.remove('is-show');
    });

  document.querySelector('.modal.login .box a:nth-of-type(2)').addEventListener(
    'click',
    function(event) {
      event.preventDefault();
      document.querySelector('.modal.login').classList.remove('is-show');
      document.querySelector('.modal.ID').classList.add('is-show');
    });

  document.querySelector('.modal.login .box a:nth-of-type(3)').addEventListener(
    'click',
    function(event) {
      event.preventDefault();
      document.querySelector('.modal.login').classList.remove('is-show');
      document.querySelector('.modal.pw').classList.add('is-show');
    });

  document.querySelector('.modal.ID .bg').addEventListener(
    'click',
    function() {
      document.querySelector('.modal.ID').classList.remove('is-show');
    });

  document.querySelector('.modal.pw .bg').addEventListener(
    'click',
    function() {
      document.querySelector('.modal.pw').classList.remove('is-show');
    });
   
  // 상단 메뉴바 투명설정
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
      document.getElementById("logoimg").src = "resources/IMG/moviepedia_22.png";
    } else {
      document.getElementById("logoimg").src = "resources/IMG/moviepedia_33.png";
    }
  });
  
  // 리뷰 더보기 버튼, 갤러리 넘기기 버튼
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
  
  // 배우 목록 넘기기 버튼 
  document.addEventListener("DOMContentLoaded", function () {
    const actorList = document.querySelector(".actor_list"); // 배우 리스트 컨테이너
    const btnPrev = document.querySelector(".actor_btn"); // 왼쪽 버튼
    const btnNext = document.querySelector(".actor_btn1"); // 오른쪽 버튼

    const scrollAmount = 600; // 한 번에 스크롤할 거리 (픽셀)

    // 오른쪽에서 왼쪽으로 슬라이드
    btnNext.addEventListener("click", function () {
      const maxScrollLeft = actorList.scrollWidth - actorList.clientWidth;
      actorList.scrollLeft = Math.min(actorList.scrollLeft + scrollAmount, maxScrollLeft);
    });

    // 왼쪽에서 오른쪽으로 슬라이드
    btnPrev.addEventListener("click", function () {
      actorList.scrollLeft = Math.max(actorList.scrollLeft - scrollAmount, 0);
    });
  });
})();
</script>

<script>	
	(function(){
	  $(document).ready(function() {
		    // 추천/신고 수 갱신 함수
function updateReactionCounts(data) {
    // data.updatedReviews 배열을 순회
    data.updatedReviews.forEach(function(review) {
        // review 객체의 ridx와 rgood, rreport 값을 로그로 확인
        console.log("Review Data: ", review);  // 객체 출력
        console.log("Review ID: " + review.ridx);  // ridx 값 확인
        console.log("Review rgood: " + review.rgood);  // rgood 값 확인
        console.log("Review rreport: " + review.rreport);  // rreport 값 확인

        // review.ridx에 해당하는 요소 찾기
        var reviewElement = $("#review_" + review.ridx);
        
        if (reviewElement.length > 0) {
            // 추천 수 갱신
            reviewElement.find(".recommendButton + div").text(review.rgood);
            // 신고 수 갱신
            reviewElement.find(".reportButton + div").text(review.rreport);
        } else {
            console.log("Review element not found for ridx:", review.ridx);  // 요소를 찾지 못했을 경우
        }
    });
}





		  
		  
	    // 추천/신고 공통 처리 함수
	    function handleReaction(button, targetridx, type) {	    
	      console.log(type + " 버튼 클릭됨");
	      console.log("targetridx: " + targetridx);
	      console.log("type: " + type);

	      // movieId 값 가져오기
	      var movieId = button.data("movieid");
	      console.log("movieId: " + movieId); // movieId 확인

	      // Ajax 요청
	      $.ajax({
	        url: "/recommend",   // 컨트롤러의 요청 URL
	        type: "POST",
	        data: {
	          targetridx: targetridx,
	          type: type,  // 1 또는 2로 전송
	          movieId: movieId  // 영화 ID 추가
	        },
            dataType: "json",
            success: function(response) {
                // 서버에서 받은 JSON 데이터
                if (response.status ==='success'){
                	console.log(response.status);
                	updateReactionCounts(response);    	
                }
                else if (response.status === 'error') {                    
                    alert(response.message);  // 이미 반응한 경우 메시지 출력
                } else if (response.status === 'fail') {
                    // 'fail'일 경우 다른 메시지 처리
                    alert(response.message);
                } else {
                    // 다른 상태일 때의 처리
                }
            },
	        error: function(xhr, status, error) {
	          console.log("AJAX 요청 실패");
	          console.log("status: " + status);
	          console.log("error: " + error);
	          alert(response.message); 
	        }
	      });
	    }



	    // 추천 버튼 클릭 시
	    $(".recommendButton").click(function() {
	      var targetridx = $(this).data("targetridx");
	      var type = 1;  // 추천은 1로 고정

	      // 현재 버튼을 참조
	      var button = $(this);
	      console.log("추천 버튼 클릭됨");
	      handleReaction(button, targetridx, type);
	    });

	    // 신고 버튼 클릭 시
	    $(".reportButton").click(function() {
	      var targetridx = $(this).data("targetridx");
	      var type = 2;  // 신고는 2로 고정

	      // 현재 버튼을 참조
	      var button = $(this);
	      console.log("신고 버튼 클릭됨");
	      handleReaction(button, targetridx, type);
	    });
	  });

	  const header = document.querySelector("#header");
	  const loginBtn = document.querySelector(".loginbtn");
	  const boardSpan = document.querySelector(".board span");
	  const img = document.querySelector("logoimg");

	  window.addEventListener("scroll", function() {
	    if (window.scrollY > 75) {
	      header.classList.add("on");
	      loginBtn.style.color = "black";
	      boardSpan.style.color = "black";
	      document.getElementById("logoimg").src = "resources/IMG/moviepedia_22.png";
	    } else {
	      header.classList.remove("on");
	      loginBtn.style.color = "white";
	      boardSpan.style.color = "white";
	      document.getElementById("logoimg").src = "resources/IMG/moviepedia_33.png";
	    }
	  });
	})();
	</script>
<script>
//아이디찾기
function handleFormSubmit(event) {
	    event.preventDefault(); 

	    const form = document.forms['findid']; 

	    fetch(form.action, {
	        method: "POST",
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

	    })
	    .catch(error => {
	        console.error("서버 오류:", error);
	        alert("서버 오류 발생! 다시 시도해주세요.");
	    });

	    return false;
	}

  //임시비밀번호
	function handleFormSubmit2(event) {
	    event.preventDefault(); 

	    const form = document.forms['findpw']; 

	    fetch(form.action, {
	        method: "POST",
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

	          
	    })
	    .catch(error => {
	        console.error("서버 오류:", error);
	        alert("서버 오류 발생! 다시 시도해주세요.");
	    });

	    return false;
	}
</script>
</body>
</html>