//package com.vitmvit.smarteducation.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @see "baeldung.com/spring-security-registration-password-encoding-bcrypt"
// */
//@Configuration
//public class PasswordConfig {
//
////    @Bean
////    public PasswordEncoder getPasswordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}