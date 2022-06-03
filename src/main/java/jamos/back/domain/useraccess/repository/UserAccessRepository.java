package jamos.back.domain.useraccess.repository;

import jamos.back.domain.useraccess.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
}
