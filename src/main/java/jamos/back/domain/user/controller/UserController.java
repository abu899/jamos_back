package jamos.back.domain.user.controller;

import jamos.back.domain.user.User;
import jamos.back.domain.user.controller.dto.RequestUser;
import jamos.back.domain.user.controller.dto.ResponseUser;
import jamos.back.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseUser saveUser(@RequestBody @Valid RequestUser requestUser) {
        User user = new User(requestUser.getLoginId(), requestUser.getPassword());
        Long joinId = userService.join(user);
        return new ResponseUser(joinId, user.getLoginId());
    }

    @GetMapping("/{id}")
    public ResponseUser findUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseUser(user.getId(), user.getLoginId());
    }
}
