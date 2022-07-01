package jamos.back.domain.user.service;

import jamos.back.domain.user.User;
import jamos.back.domain.user.controller.form.UserRequestForm;
import jamos.back.domain.user.repository.UserRepository;
import jamos.back.global.exception.SameLoginIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(UserRequestForm userRequestForm) {
        if (userRepository.existsByEmail(userRequestForm.getEmail())) {
            throw new SameLoginIdException("Same login id");
        }

        User user = new User(userRequestForm.getEmail(),
                userRequestForm.getName(),
                passwordEncoder.encode(userRequestForm.getPassword()));

        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public Long join(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
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
