<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>ERROR(설정필요)</title>
    <style>
        /* 전체 화면을 flexbox로 설정하여 중앙 정렬 */
        body {
            display: flex;
            justify-content: center;   -
            align-items: center;       /* 세로 가운데 정렬 */
            height: 100vh;             /* 화면 전체 높이 */
            margin: 0;                 /* 기본 margin 제거 */
            background-color: rgba(0, 0, 0, 0.5); /* 배경에 투명도 0.5 적용 */
        }

        /* 이미지 크기 조정 */
        img {
		   max-width: 85%;
		   max-height: 78%;           /* 이미지 높이 최대 80% */
            opacity: 0.5;              /* 이미지에 투명도 0.5 적용 */
            margin-bottom: 20px;       /* 이미지와 텍스트 사이 여백 */
        }

        /* 텍스트 스타일 */
        h1 {
            color: white;              /* 텍스트 색상을 흰색으로 설정 */
            text-align: center;        /* 텍스트 중앙 정렬 */
            font-size: 2em;            /* 폰트 크기 설정 */
            margin: 0;
        }
        p{
        color: red;              /* 텍스트 색상을 흰색으로 설정 */
            text-align: center;        /* 텍스트 중앙 정렬 */
            font-size: 2em;            /* 폰트 크기 설정 */
            margin: 0;
        }
    </style>
</head>
<body>
    <div>        
                
        <h1>잘못된 요청이에요!!!</h1>
        
  		 <p>논란일자 :  ${msg}  ${param.msg}</p>
  		 <img alt="Error Image" src="/resources/CSS/Errorimg.jpg"> 
    </div>
</body>
</html>
