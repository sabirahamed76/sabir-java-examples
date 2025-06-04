package comhome.sabir.microservices.SpringCloudOrderService.repository;

import comhome.sabir.microservices.SpringCloudOrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
