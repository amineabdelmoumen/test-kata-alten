package ma.alten.testAlten.security.repository;

import ma.alten.testAlten.security.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
}