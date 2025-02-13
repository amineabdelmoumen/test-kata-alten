package ma.alten.testAlten.business.repository;

import ma.alten.testAlten.business.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}