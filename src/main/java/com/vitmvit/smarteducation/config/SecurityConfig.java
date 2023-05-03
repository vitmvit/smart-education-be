//package com.vitmvit.smarteducation.config;
//
//import com.vitmvit.smarteducation.constant.RoleEnum;
//import com.vitmvit.smarteducation.service.UserService;
//import com.vitmvit.smarteducation.util.RoleEnumUtils;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @see "https://www.baeldung.com/spring-security-oauth-jwt"
// */
//@AllArgsConstructor
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //    private final JwtTokenRepository jwtTokenRepository;
////    private final JwtCsrfFilter jwtCsrfFilter;
//
//    private final HandlerExceptionResolver handlerExceptionResolver;
//    //private final UserService userService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.parentAuthenticationManager(authenticationManagerBean()).userDetailsService(userDetailsService);
//    }
//
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/api/open/**").allowedMethods("*");
////                registry.addMapping("/swagger-resources/**").allowedMethods("*");
////                registry.addMapping("/swagger-ui.html").allowedMethods("*");
////                registry.addMapping("/v2/api-docs").allowedMethods("*");
////                registry.addMapping("/v3/api-docs").allowedMethods("*");
////                registry.addMapping("/webjars/**").allowedMethods("*");
////            }
////        };
////    }
//
////    @Bean
////    public WebSecurityCustomizer getWebSecurityCustomizer() {
////        return (web) -> web.ignoring().antMatchers(
////                "/api/open/**",
////                "/swagger-resources/**",
////                "/swagger-ui.html",
////                "/v2/api-docs",
////                "/v3/api-docs",
////                "/webjars/**"
////        );
////    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                //.sessionManagement()
//                //.sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                //.and()
////                .addFilterAt(new JwtCsrfFilter(jwtTokenRepository, handlerExceptionResolver), CsrfFilter.class)
////                .csrf().ignoringAntMatchers("/**")
////                .httpBasic().disable()
////                .csrf().disable()
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
////                .and()
////                .authorizeRequests()
////                .antMatchers("/auth/login")
////                .authenticated()
//
////                .and()
//                .authorizeRequests()
//                .antMatchers("/api/root/**").hasRole(RoleEnum.ROOT.getName())
//                .antMatchers("/api/admin/**").hasAnyRole(RoleEnumUtils.allAdminRoles())
//                .antMatchers("/api/auth/**").hasAnyRole(RoleEnumUtils.allAuthRoles())
//                .antMatchers("/api/user/**").hasAnyRole(RoleEnumUtils.allRoles())
//                .antMatchers("/api/open/**", "/api/open/registration", "/api/open/login", "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/v3/api-docs", "/webjars/**").permitAll()
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(((request, response, e) -> handlerExceptionResolver.resolveException(request, response, null, e)));
////                .addFilterBefore(jwtCsrfFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}