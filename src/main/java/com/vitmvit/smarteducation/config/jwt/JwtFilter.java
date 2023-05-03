//package com.vitmvit.smarteducation.config.jwt;
//
//import com.vitmvit.smarteducation.config.model.UserDetails;
//import com.vitmvit.smarteducation.converter.UserDetailsConverter;
//import com.vitmvit.smarteducation.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//import static org.springframework.util.StringUtils.hasText;
//
///**
// * @see "https://www.baeldung.com/spring-security-oauth-jwt"
// */
//@AllArgsConstructor
//@Component
//public class JwtFilter extends GenericFilterBean {
//
//    public static final String AUTHORIZATION = "Authorization";
//    public static final String BEARER = "Bearer ";
//
//    private final UserService userService;
//    private final JwtProvider jwtProvider;
//    private final UserDetailsConverter userDetailsConverter;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//        if (token != null && jwtProvider.isValidToken(token)) {
//            String login = jwtProvider.getLoginFromToken(token);
//            UserDetails user = userDetailsConverter.convert(userService.findByLogin(login));
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
//        String bearer = httpServletRequest.getHeader(AUTHORIZATION);
//        return hasText(bearer) && bearer.startsWith(BEARER) ? bearer.substring(BEARER.length()) : null;
//    }
//}