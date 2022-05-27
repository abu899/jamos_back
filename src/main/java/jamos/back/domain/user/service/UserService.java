package jamos.back.domain.user.service;

import jamos.back.domain.user.User;
import jamos.back.domain.user.repository.UserRepository;
import jamos.back.global.exception.SameLoginIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        if(userRepository.existsByLoginId(user.getEmail())) {
            throw new SameLoginIdException("Same login id");
        }
        userRepository.save(user);
        return user.getId();
    }

    public User findUserById(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        return findUser.orElse(null);
    }
}
