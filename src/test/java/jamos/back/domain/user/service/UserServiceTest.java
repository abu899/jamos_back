package jamos.back.domain.user.service;

import jamos.back.domain.user.User;
import jamos.back.global.exception.SameLoginIdException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void join() {
        User user = new User("brett@abc.com", "brett", "123");
        Long joinUserId = userService.join(user);

        assertThat(user.getId()).isEqualTo(joinUserId);
    }

    @Test
    void samIdJoin() {
        User user = new User("brett@abc.com", "brett", "123");
        User user2 = new User("brett@abc.com", "brett", "123");
        userService.join(user);
        org.junit.jupiter.api.Assertions.assertThrows(SameLoginIdException.class,
                () -> userService.join(user2));
    }
}