<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script defer src="/resources/JS/user.js"></script>	
<title>회원가입</title>
</head>
<body>
	<div class="container">
		<h1>회원가입</h1>
		
		<form action="/user/join" name="userform" method="post">
			<!-- 사용자 입력 폼 -->
			<input type="text" name="uid" id="uid" placeholder="아이디를 입력해주세요"><br>
			
			<input type="text" name="upw" id="upw" placeholder="비밀번호를 입력해주세요"><br>
			<input type="text" name="uname" id="uname" placeholder="이름을 입력해주세요"><br>
			
			<input type="text" name="umail" id="umail" placeholder="이메일을 입력해주세요"><br>
			<input type="text" name="uphone" id="uphone" placeholder="전화번호를 입력해주세요"><br>
			<input type="text" name="ubirth" id="ubirth" placeholder="생년월일 6자리를 입력해주세요">
			<input type="text" name="ugender" id="ugender" placeholder="주민번호 맨 앞자리를 입력해주세요"><br>
			
			<input type="text" name="" id="" placeholder="프로필사진들어갈자리"><br>
			<input type="text" name="upost" id="upost" readonly placeholder="우편번호"><br>
			<input type="button" value="우편번호찾기" onclick="postSearch();"><br>
			<input type="text" name="uaddr1" id="uaddr1" readonly placeholder="주소"><br>
			<input type="text" name="uaddr2" id="uaddr2" placeholder="상세주소를 입력해주세요"><br>
			
			<a href="javascript:userSubmit();">가입!</a>
		</form>
	</div>
	
</body>
</html>