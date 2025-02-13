package ma.alten.testAlten.business.repository;

import ma.alten.testAlten.business.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}