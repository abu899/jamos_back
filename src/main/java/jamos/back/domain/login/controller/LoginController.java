package jamos.back.domain.login.controller;

import jamos.back.domain.login.controller.form.LoginRequestForm;
import jamos.back.domain.login.controller.form.LoginResponseForm;
import jamos.back.domain.login.service.LoginService;
import jamos.back.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("")
    public LoginResponseForm login(@RequestBody @Validated LoginRequestForm requestForm
            , BindingResult bindingResult
            , HttpServletRequest servletRequest) {

        if (bindingResult.hasErrors()) {
            return new LoginResponseForm(false);
        }

        User loginUser = loginService.login(requestForm.getLoginId(), requestForm.getPassword());
        if (null == loginUser) {
            return new LoginResponseForm(false);
        }

        return new LoginResponseForm(true);
    }
}
