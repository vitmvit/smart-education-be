//package com.vitmvit.smarteducation.config.jwt;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @see "https://www.baeldung.com/spring-security-oauth-jwt"
// */
//@Slf4j
//@Getter
//@Component
//@PropertySource("classpath:/jwt.properties")
//public class JwtProvider {
//
//    @Value("$(jwt.secret)")
//    private String jwtSecret;
//
//    @Value("${jwt.token.expiration}")
//    private Long jwtExpiration;
//
//    public String generateToken(String login) {
//        return Jwts
//                .builder()
//                .setSubject(login)
//                .setExpiration(new Date(jwtExpiration))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public boolean isValidToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException ex) {
//            log.error("Token expired: " + ex.getMessage());
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported jwt: " + ex.getMessage());
//        } catch (MalformedJwtException ex) {
//            log.error("Malformed jwt: " + ex.getMessage());
//        } catch (SignatureException ex) {
//            log.error("Invalid signature: " + ex.getMessage());
//        } catch (Exception ex) {
//            log.error("Invalid token: " + ex.getMessage());
//        }
//        return false;
//    }
//
//    public String getLoginFromToken(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}