<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/CSS/review.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">
<script>var contextPath = '${pageContext.request.contextPath}';</script>
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


.modal-content {
    width: 400px;
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1000;
    text-align: center;
}

.modal-content h2 {
    margin-bottom: 15px;
    font-size: 1.5em;
}

.close {
    position: absolute;
    top: 10px;
    right: 15px;
    font-size: 24px;
    cursor: pointer;
}

.modal-content p {
    margin-bottom: 10px;
    font-size: 1em;
}

textarea {
    width: 100%;
    height: 80px;
    margin-top: 5px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    resize: none;
}

#blockButton{
    background: white;
    color: black;
    border: none;
    cursor: pointer;
    margin : 0 0 0 3px;
}

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
		<nav id="header">
			<section>
				<ul class="menu_bar">
					<li class="logo">
						<a href="/" type="button">
							<img id="logoimg" class="logoimg" src="resources/IMG/moviepedia_22.png">
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

	<div class="body_layout">
		<div class="review_box">
			<div class="review_container">
				<div class="review_detail">
					<div class="review_top">
						<div class="review_mp">
							<div class="review_mp_img">
								<img src="${poster_path}" alt="영화포스터" class="review_poster" />
							</div>
						</div>																		
						<div class="review_name">
    						<div class="review_mn">${mname}</div>
    						<div class="review_title">${rvo.rtitle}</div>
    						<div class="review_user_info">
        						<div class="review_up">
            						<img src="${rvo.usaveprofile}" alt="기본 프로필 사진" class="img_img"/>
        						</div>
        					<div class="review_un">${rvo.uidx}</div>
        					<div class="review_date">${rvo.rinsertdate}</div>                    
							<c:if test="${not empty sessionScope.usaveprofile}">            
			    				<button id="blockButton" onclick="openModal()"><span class="material-symbols-outlined">person_off</span></button>
							</c:if>
    					</div>
					</div>
				</div>
					<div id="blockModal" class="modal">
		               <div class="bg"></div>
			                <div class="box">
			                       <h2>차단 사유를 입력해주세요</h2>
			                       <form id="blockForm" action="/Block" method="POST" onsubmit="return validateForm()">
			                           <div>
                                 <p>${rvo.uidx}님을 차단합니다.</p>
                					<label for="reason">차단할 사유를 적어주세요</label>
                					<textarea id="reason" name="reason" required></textarea>
            					</div>
            					<input type="hidden" name="ridx" value="${rvo.ridx}">
            					<input type="hidden" name = mid value = "${mid }">
            					<button type="submit"><span class="material-symbols-outlined">person_off</span></button>
        					</form>
    					</div>
					</div>
				</div>
<script>
//모달 열기
function openModal() {
    document.getElementById("blockModal").style.display = "block";
}

// 모달 닫기
document.querySelector('#blockModal .bg').addEventListener('click', function() {
    document.getElementById("blockModal").style.display = "none";
});

// 폼 유효성 검사
function validateForm() {
    const reason = document.getElementById("reason").value;
    if (reason.trim() === "") {
        alert("차단 사유를 입력해주세요.");
        return false; 
    }
    return true; 
}

</script>
				<div class="review_mid">
					<div class="review_content">
						<div class="review_uc">${rvo.rcontent} </div>
					</div>
				</div>
				<div class="review_bottom">
					<div class="review_upre">
						<span class="material-symbols-outlined thumb">thumb_up</span>
                		<div>추천 ${rvo.rgood }</div>
                		<span class="material-symbols-outlined siren">siren</span>
                		<div>신고 ${rvo.rreport }</div>
					</div>
				</div>
			</div>
	         <div class="review_comment">
	            <div class="comment_container">
	               <h3>코멘트</h3>
	               <ul class="comment_ul">
	                  <c:forEach var="comment" items="${comments}">
	                     <li class="comment_li">
	                        <div class="comment_li_box">
	                           <div class="user_profile">
	                                   <img class="user_profile"  src = "${comment.usaveprofile}" ></img>
	                               </div>
	                               <div class="comment_comment">                                  
	                                  <div class="user_name">${comment.uidx}</div> 
	                                  <div class="write_date">${comment.cinsertdate}</div>
	                                  <div class="user_content">${comment.ccomment}</div>
	                               </div>
	                            </div>
	                         </li>
	                     </c:forEach>
	                  </ul>
	            </div>
	         </div>
			
			<div class="comment_input_box">
				<h3>코멘트 작성</h3>
				<div class="comment_input_container">
					<div class="comment_input">
						<form id="commentForm" class="comment-form" action="/commentwrite" method="POST">
							<textarea id="rcomment" name="ccomment" class="comment-textarea" placeholder="comment를 입력해주세요" onkeydown="resize(this)" onkeyup="resize(this)" required></textarea>
  							<%-- <input type="hidden" name="user_profile" value="${}" class="comment-img"> --%> 
    						<input type="hidden" name="mid" value="${mid}" class="comment-hidden">

    						<input type="hidden" name="ridx" value="${ridx}" class="comment-hidden">
    
   					 		<input type="hidden" name="uidx" value="${uidx}" class="comment-hidden">
							
							<div>
    							<button type="submit" class="comment-submit-button">댓글 작성</button>
    						</div>
						</form>
					</div>
					<!-- <button type="submit" class="comment-submit-button">댓글 작성</button> -->
				</div>
			</div>
		</div>
	</div>
<script>
function resize(obj) {
    obj.style.height = '1px';
    obj.style.height = (12 + obj.scrollHeight) + 'px';
}
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>






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
function blockUser(ridx) {
    fetch("Block", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ ridx: ridx }),
        credentials: 'same-origin'  // 세션 쿠키를 포함해서 전송
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.text().then(errorText => {
                console.error("Error response: ", errorText);
                alert(errorText || "차단에 실패했습니다.");
            });
        }
    })
    .then(data => {
        if (data && data.status === "success") {
            alert("사용자를 차단했습니다.");
        } else if (data && data.NoLogin) {
            alert(data.NoLogin);
        }
    })
    .catch(error => {
        console.error("오류 발생!", error);
        alert("알 수 없는 오류가 발생했습니다.");
    });
}


</script>


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

  
function updateCommentsUI(response) {
    console.log("받은 데이터:", response);
    console.log(JSON.stringify(response, null, 2)); 

    // 댓글 배열은 response.comments에 있음
    const comments = response.comments;

    let commentList = $(".comment_ul");
    // commentList가 실제로 존재하는지 확인 (콘솔에 길이 출력)
    console.log("댓글 리스트 요소 개수:", commentList.length);

    commentList.empty(); // 기존 댓글 삭제

    for (let i = 0; i < comments.length; i++) {
        let comment = comments[i];
        let commentHtml = '<li class="comment_li">' +
            '<div class="comment_li_box">' +
        	    '<div class="user_profile">' +
	        	    '<img class="user_profile" src="' + comment.usaveprofile + '" />' +
		        '</div>' +
                '<div class="comment_comment">' +                	
                    '<div class="user_name">' + comment.uidx + '</div>' +
                    '<div class="write_date">' + comment.cinsertdate + '</div>' +
                    '<div class="user_content">' + comment.ccomment + '</div>' +
                    
                '</div>' +
            '</div>' +
        '</li>';
        console.log("생성된 HTML:", commentHtml);
        commentList.append(commentHtml);
    }

}

$(document).ready(function() {
    $('#commentForm').on('submit', function(event) {
        event.preventDefault();  // 폼 기본 제출 방지

        var formData = $(this).serialize();

        $.ajax({
            url: '/commentwrite',
            type: 'POST',
            data: formData,
            dataType: "json",
            success: function(response) {
                if (response.status === 'success') {
                    updateCommentsUI(response);
                    alert('댓글 작성 성공');
                } else {
                    alert('댓글 작성 실패: ' + response.message);
                }
            },
            error: function() {
                alert('로그인 후 작성해주세요.');
            }
        });
    });
});

</script>
</body>
</html>