package jamos.back.domain.user.repository;

import jamos.back.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User("brett", "123");
        User saveUser = userRepository.save(user);

        assertThat(user.getId()).isEqualTo(saveUser.getId());
    }

    @Test
    void findUser() {
        User user = new User("brett", "123");
        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(findUser.getId());
        assertThat(user.getLoginId()).isEqualTo(findUser.getLoginId());
    }

}