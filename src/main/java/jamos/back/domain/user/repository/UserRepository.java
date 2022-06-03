package jamos.back.domain.user.repository;


import jamos.back.domain.instance.Instance;
import jamos.back.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String loginId);

    boolean existsByEmail(String email);
}
