package jamos.back.domain.user.controller;

import jamos.back.domain.user.User;
import jamos.back.domain.user.controller.dto.RequestUser;
import jamos.back.domain.user.controller.dto.ResponseUser;
import jamos.back.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseUser saveUser(@RequestBody @Valid RequestUser requestUser) {
        User user = new User(requestUser.getLoginId(), requestUser.getPassword());

        return new ResponseUser(user.getId(), user.getLoginId());
    }

    public ResponseUser findUser(Long id) {
        User user = userService.findUserById(id);
        return new ResponseUser(user.getId(), user.getLoginId());
    }
}
