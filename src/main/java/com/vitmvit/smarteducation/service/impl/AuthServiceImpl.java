package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.config.jwt.JwtUtil;
import com.vitmvit.smarteducation.model.dto.auth.SignInDto;
import com.vitmvit.smarteducation.model.dto.auth.SignUpDto;
import com.vitmvit.smarteducation.model.dto.auth.TokenDto;
import com.vitmvit.smarteducation.model.entity.CabinetStudent;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.service.AuthService;
import com.vitmvit.smarteducation.service.CabinetStudentService;
import com.vitmvit.smarteducation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CabinetStudentService cabinetStudentService;
    private final JwtUtil jwtUtil;
    @Value("${jwt.sessionTime}")
    private Long sessionTime;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * установливается роль USER
     * так как, регистрируется самостоятельно и чтобы не лазил где попало
     * потом, администратор сменит ему роль при очном появлении
     * дабы избежать инсинуаций с лишними людьми
     */
    @Override
    public TokenDto signUp(SignUpDto dto) {
        User user = new User();
        user.setAvatarUuid("none");
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPasswords().getPassword());
        user.setRoleList(List.of());
        user.setCabinetStudent(cabinetStudentService.save(new CabinetStudent()));
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setActive(0);
        user.setDescription("");
        user = userService.save(user);
        return signIn(user.getLogin(), dto.getPasswords().getPassword());
    }

    @Override
    public TokenDto signIn(SignInDto dto) {
        return signIn(dto.getUsername(), dto.getPassword());
    }

    private TokenDto signIn(String login, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            return new TokenDto(
                    login,
                    jwtUtil.generateToken((UserDetails) authentication.getPrincipal()),
                    sessionTime
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверный логин или пароль", ex);
        }
    }
}
