package jamos.back.domain.login.controller;

import jamos.back.domain.SessionConst;
import jamos.back.domain.login.controller.form.LoginRequestForm;
import jamos.back.domain.login.controller.form.LoginResponseForm;
import jamos.back.domain.login.service.LoginService;
import jamos.back.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

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

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER_ID, loginUser.getId());
        log.info("session.getId() : {} ", session.getId());

        return ResponseEntity.ok(new LoginResponseForm(true));
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
}
