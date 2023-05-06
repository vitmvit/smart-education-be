package com.vitmvit.smarteducation.config;

import com.vitmvit.smarteducation.config.jwt.JwtFilter;
import com.vitmvit.smarteducation.config.jwt.JwtUserService;
import com.vitmvit.smarteducation.util.RoleEnumUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserService jwtUserService;
    private final JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserService);
    }

//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/api/open/**");
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable();
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/pulse", "/api/open/login", "/api/open/registration", "/api/open/**")
                .permitAll();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/api/user/**").access(RoleEnumUtils.allUserRoles())
                .antMatchers("/api/auth/**").access(RoleEnumUtils.allAuthRoles())
                .antMatchers("/api/admin/**").access(RoleEnumUtils.allRootRoles())
                .antMatchers("/api/root/**").access("hasRole('ROOT')");
        httpSecurity
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}