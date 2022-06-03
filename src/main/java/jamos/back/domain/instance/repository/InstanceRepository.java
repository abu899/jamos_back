package jamos.back.domain.instance.repository;

import jamos.back.domain.instance.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {

    @Query("select i from Instance i join fetch i.userAccesses ua where ua.id=:user_id")
    List<Instance> findInstanceList(@Param("user_id") Long userId);

}
