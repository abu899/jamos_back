package jamos.back.domain.jamdata.repository;

import jamos.back.domain.jamdata.JamData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JamDataRepository extends JpaRepository<JamData, Long> {
}
