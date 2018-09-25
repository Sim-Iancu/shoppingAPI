package shopifyapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import shopifyapi.model.Customer;
import shopifyapi.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}