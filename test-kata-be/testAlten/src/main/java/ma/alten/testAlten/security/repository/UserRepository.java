package ma.alten.testAlten.security.repository;

import ma.alten.testAlten.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

User findByUsername(String username);
User findByEmail(String email);
boolean existsByEmail(String email);
}