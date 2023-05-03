package com.vitmvit.smarteducation.util;

import org.springframework.stereotype.Component;

@Component
public class CookieUtils {

//    public void setCookie(ServerHttpResponse serverHttpResponse, AccessPairDto accessPairDto) {
//        ResponseCookie accessCookie = ResponseCookie.from("accessCookie", accessPairDto.accessToken())
//                .maxAge(30 * 24 * 60 * 60)
//                .httpOnly(true)
//                .path("/")
//                .secure(true).build();
//        ResponseCookie refreshCookie = ResponseCookie.from("refreshCookie", accessPairDto.refreshToken())
//                .maxAge(30 * 24 * 60 * 60)
//                .httpOnly(true)
//                .path("/")
//                .secure(true).build();
//        serverHttpResponse.addCookie(accessCookie);
//        serverHttpResponse.addCookie(refreshCookie);
//    }
}