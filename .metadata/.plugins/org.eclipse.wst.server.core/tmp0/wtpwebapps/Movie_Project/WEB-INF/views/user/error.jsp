<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>ERROR(�����ʿ�)</title>
    <style>
        /* ��ü ȭ���� flexbox�� �����Ͽ� �߾� ���� */
        body {
            display: flex;
            justify-content: center;   -
            align-items: center;       /* ���� ��� ���� */
            height: 100vh;             /* ȭ�� ��ü ���� */
            margin: 0;                 /* �⺻ margin ���� */
            background-color: rgba(0, 0, 0, 0.5); /* ��濡 ���� 0.5 ���� */
        }

        /* �̹��� ũ�� ���� */
        img {
		   max-width: 85%;
		   max-height: 78%;           /* �̹��� ���� �ִ� 80% */
            opacity: 0.5;              /* �̹����� ���� 0.5 ���� */
            margin-bottom: 20px;       /* �̹����� �ؽ�Ʈ ���� ���� */
        }

        /* �ؽ�Ʈ ��Ÿ�� */
        h1 {
            color: white;              /* �ؽ�Ʈ ������ ������� ���� */
            text-align: center;        /* �ؽ�Ʈ �߾� ���� */
            font-size: 2em;            /* ��Ʈ ũ�� ���� */
            margin: 0;
        }
        p{
        color: red;              /* �ؽ�Ʈ ������ ������� ���� */
            text-align: center;        /* �ؽ�Ʈ �߾� ���� */
            font-size: 2em;            /* ��Ʈ ũ�� ���� */
            margin: 0;
        }
    </style>
</head>
<body>
    <div>        
                
        <h1>�߸��� ��û�̿���!!!</h1>
        
  		 <p>������� :  ${msg}  ${param.msg}</p>
  		 <img alt="Error Image" src="/resources/CSS/Errorimg.jpg"> 
    </div>
</body>
</html>
