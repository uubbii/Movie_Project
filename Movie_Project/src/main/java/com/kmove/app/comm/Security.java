package com.kmove.app.comm;

import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpSession;
import com.kmove.app.user.vo.UserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Security {
    private static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY"); // ȯ�� �������� ��� Ű ��������

    private static Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // ��� Ű ����Ʈ �迭
        return Keys.hmacShaKeyFor(keyBytes); // Key ��ü ����
    }

    // ���ǿ� ����� ���� ����
    public static void setSession(HttpSession session, UserVo uvo) {
    	session.setAttribute("uidx", uvo.getUidx());
        session.setAttribute("uname", uvo.getUname());
        session.setAttribute("uid", uvo.getUid());        
        session.setAttribute("ubirth", uvo.getUbirth());
        session.setAttribute("ugender", uvo.getUgender());
        session.setAttribute("uphone", uvo.getUphone());
        session.setAttribute("uaddr1", uvo.getUaddr1());
        session.setAttribute("uaddr2", uvo.getUaddr2());
        session.setAttribute("umail", uvo.getUmail());
        session.setAttribute("upost", uvo.getUpost());
        session.setAttribute("uinsertdate", uvo.getUinsertdate());
        session.setAttribute("uupdatedate", uvo.getUupdatedate());
        session.setAttribute("uprofile", uvo.getUprofile());
        session.setAttribute("usaveprofile", "./resources/UPLOAD/" + uvo.getUsaveprofile());        
    }

    // JWT ��ū ����
    public static String generateJwt(String uid) {
        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(new Date()) // ��ū �߱� �ð�
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // ��ū ���� �ð� (24�ð�)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // ����
                .compact();
    }

    // JWT ��ū�� �����ϰ� ����� ������ ��ȯ
    public static String getUserFromJwt(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // ��ū���� ����� ID ��������
        } catch (Exception e) {
            return null; // ���ܰ� �߻��ϸ� null ��ȯ
        }
    }

    // ���ǿ��� ����� ���� ���� (�α׾ƿ�)
    public static void removeSession(HttpSession session) {
        session.invalidate();
        System.out.println("�α׾ƿ� ����");
    }
}