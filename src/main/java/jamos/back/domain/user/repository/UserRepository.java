package jamos.back.domain.user.repository;


import jamos.back.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}
