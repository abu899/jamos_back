package jamos.back.domain.login.service;

import jamos.back.domain.user.User;
import jamos.back.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final UserRepository userRepository;

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .stream()
                .filter(user -> user.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}
