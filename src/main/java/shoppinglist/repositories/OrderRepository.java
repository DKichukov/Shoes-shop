package shoppinglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppinglist.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
