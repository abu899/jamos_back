package jamos.back.domain.user.repository;


import jamos.back.domain.instance.Instance;
import jamos.back.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String loginId);

    boolean existsByEmail(String email);
}
