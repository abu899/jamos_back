package jamos.back.domain.login.controller;

import jamos.back.domain.SessionConst;
import jamos.back.domain.login.UserAuthentication;
import jamos.back.domain.login.controller.form.LoginRequestForm;
import jamos.back.domain.login.controller.form.LoginResponseForm;
import jamos.back.domain.login.service.LoginService;
import jamos.back.domain.user.User;
import jwt.tutorial.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseForm> login(@RequestBody @Validated LoginRequestForm requestForm
            , BindingResult bindingResult
            , HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.error("login bindingResult error");
            return ResponseEntity.badRequest().body(new LoginResponseForm(false));
        }

        User loginUser = loginService.login(requestForm.getEmail(), requestForm.getPassword());
        if (null == loginUser) {
            log.error("loginUser null");
            return ResponseEntity.badRequest().body(new LoginResponseForm(false));
        }

        return ResponseEntity.ok(jwtProcess(request, requestForm));
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ( null != session ){
            log.info("session.getId() : {} ", session.getId());
            session.invalidate();

            return "logout";
        }

        return "invalid logout";
    }

    private void sessionProcess(HttpServletRequest request, User loginUser) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_USER_ID, loginUser.getId());
        Long attribute = (Long)session.getAttribute(SessionConst.LOGIN_USER_ID);
        log.info("session.getId() : {} ", session.getId());
    }

    private LoginResponseForm jwtProcess(HttpServletRequest request, LoginRequestForm requestForm) {

        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(requestForm.getEmail(), requestForm.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = JwtTokenProvider.createToken(authenticate);

        return new LoginResponseForm(true, token);
    }
}
