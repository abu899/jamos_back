package jamos.back.domain.login.service;

import jamos.back.domain.user.User;
import jamos.back.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .stream()
                .filter(user -> user.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}