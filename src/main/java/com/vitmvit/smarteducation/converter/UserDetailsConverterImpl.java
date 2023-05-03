//package com.vitmvit.smarteducation.converter;
//
//import com.vitmvit.smarteducation.config.model.UserDetails;
//import com.vitmvit.smarteducation.model.entity.User;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Component
//public class UserDetailsConverterImpl implements UserDetailsConverter {
//
//    @Override
//    public UserDetails convert(User user) {
//        UserDetails userDetails = new UserDetails();
//        userDetails.setLogin(user.getLogin());
//        userDetails.setPassword(user.getPassword());
//        userDetails.setAuthorities(
//                user
//                        .getRoleList()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                        .collect(Collectors.toCollection(() -> new ArrayList<>(user.getRoleList().size())))
//        );
//        return userDetails;
//    }
//}
