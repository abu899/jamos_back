package jamos.back.domain.user.controller;

import jamos.back.domain.user.User;
import jamos.back.domain.user.controller.form.UserRequestForm;
import jamos.back.domain.user.controller.form.UserResponseForm;
import jamos.back.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserResponseForm saveUser(@RequestBody @Validated UserRequestForm userRequestForm
            , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new UserResponseForm();
        }

        Long joinId = userService.join(userRequestForm);
        return new UserResponseForm(joinId, userRequestForm.getEmail());
    }

    @GetMapping("/{id}")
    public UserResponseForm findUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new UserResponseForm(user.getId(), user.getEmail());
    }
}
