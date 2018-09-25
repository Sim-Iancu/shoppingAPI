package shopifyapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import shopifyapi.model.Customer;
import shopifyapi.model.Shop;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}