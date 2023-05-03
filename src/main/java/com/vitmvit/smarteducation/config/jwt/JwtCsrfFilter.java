//package com.vitmvit.smarteducation.config.jwt;
//
//import com.vitmvit.smarteducation.util.StringUtils;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import lombok.AllArgsConstructor;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.InvalidCsrfTokenException;
//import org.springframework.security.web.csrf.MissingCsrfTokenException;
//import org.springframework.security.web.util.UrlUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@AllArgsConstructor
//@Component
//public class JwtCsrfFilter extends OncePerRequestFilter {
//
//    private final CsrfTokenRepository csrfTokenRepository;
//    private final HandlerExceptionResolver handlerExceptionResolver;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        request.setAttribute(HttpServletResponse.class.getName(), response);
//        CsrfToken csrfToken = this.csrfTokenRepository.loadToken(request);
//        boolean missingToken = csrfToken == null;
//        if (missingToken) {
//            csrfToken = this.csrfTokenRepository.generateToken(request);
//            this.csrfTokenRepository.saveToken(csrfToken, request, response);
//        }
//        request.setAttribute(CsrfToken.class.getName(), csrfToken);
//        request.setAttribute(csrfToken.getParameterName(), csrfToken);
//        if (request.getServletPath().equals("/api/open/login")) {
//            try {
//                filterChain.doFilter(request, response);
//            } catch (Exception e) {
//                handlerExceptionResolver.resolveException(request, response, null, new MissingCsrfTokenException(csrfToken.getToken()));
//            }
//        } else {
//            String actualToken = request.getHeader(csrfToken.getHeaderName());
//            if (actualToken == null) {
//                actualToken = request.getParameter(csrfToken.getParameterName());
//            }
//            try {
//                if (StringUtils.isNotEmpty(actualToken)) {
//                    Jwts.parser()
//                            .setSigningKey(((JwtTokenRepository) csrfTokenRepository).getSecret())
//                            .parseClaimsJws(actualToken);
//                    filterChain.doFilter(request, response);
//                } else {
//                    handlerExceptionResolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
//                }
//            } catch (JwtException e) {
//                if (this.logger.isDebugEnabled()) {
//                    this.logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request));
//                }
//                if (missingToken) {
//                    handlerExceptionResolver.resolveException(request, response, null, new MissingCsrfTokenException(actualToken));
//                } else {
//                    handlerExceptionResolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
//                }
//            }
//        }
//    }
//}