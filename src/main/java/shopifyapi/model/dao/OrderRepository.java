package shopifyapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import shopifyapi.model.OrderObject;

public interface OrderRepository extends CrudRepository<OrderObject, Long> {
}