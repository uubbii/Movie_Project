package com.kmove.app.comm;

import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpSession;
import com.kmove.app.user.vo.UserVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Security {
    private static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY"); // 환경 변수에서 비밀 키 가져오기

    private static Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // 비밀 키 바이트 배열
        return Keys.hmacShaKeyFor(keyBytes); // Key 객체 생성
    }

    // 세션에 사용자 정보 저장
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

    // JWT 토큰 생성
    public static String generateJwt(String uid) {
        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 토큰 만료 시간 (24시간)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    // JWT 토큰을 검증하고 사용자 정보를 반환
    public static String getUserFromJwt(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // 토큰에서 사용자 ID 가져오기
        } catch (Exception e) {
            return null; // 예외가 발생하면 null 반환
        }
    }

    // 세션에서 사용자 정보 제거 (로그아웃)
    public static void removeSession(HttpSession session) {
        session.invalidate();
        System.out.println("로그아웃 성공");
    }
}