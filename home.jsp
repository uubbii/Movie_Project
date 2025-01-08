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
	*{
		
	}
	header{
		background-color: #fff;
		box-shadow: 0 1px #00000014;
		display: block;
		text-align: center;
		height: 65px;
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
		padding-inline-start:0;
	}
	li{
		position:relative;
		height:62px;
		unicode-bidi: isolate;
		margin: 0 0 0 10px;
	}
	.search{
		margin: 0 0 0 auto;
	}
	a{
		height: 40px;
		padding: 0 16px;
	}
</style>
</head> 
<body>
	<header class="" style="position:fixed; top: 0px;">
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
						<a type="button"><span>로그인</span></a>
					</li>
					<li class="profile">
						<a type="button"><span>프로필사진</span></a>
					</li>
				</ul>
			</section>
		</nav>
	</header>
</body>
</html>