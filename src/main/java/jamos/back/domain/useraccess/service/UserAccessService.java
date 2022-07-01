package jamos.back.domain.useraccess.service;

import jamos.back.domain.instance.Instance;
import jamos.back.domain.user.Rights;
import jamos.back.domain.user.User;
import jamos.back.domain.user.repository.UserRepository;
import jamos.back.domain.useraccess.UserAccess;
import jamos.back.domain.useraccess.repository.UserAccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccessService {

    private final UserAccessRepository userAccessRepository;
    private final UserRepository userRepository;

    public UserAccess create(String userEmail, Instance instance) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not exists");
        }

        log.info("userAccess Created");

        UserAccess userAccess = UserAccess.createUserAccess(user, instance, Rights.OWNER);
        return userAccessRepository.save(userAccess);
    }
}
