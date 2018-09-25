package shopifyapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import shopifyapi.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}