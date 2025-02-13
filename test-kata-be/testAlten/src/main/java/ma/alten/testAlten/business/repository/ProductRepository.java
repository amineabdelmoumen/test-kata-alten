package ma.alten.testAlten.business.repository;

import ma.alten.testAlten.business.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}