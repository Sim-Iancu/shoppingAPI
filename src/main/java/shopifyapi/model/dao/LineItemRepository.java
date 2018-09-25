package shopifyapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import shopifyapi.model.Customer;
import shopifyapi.model.LineItem;

import java.util.List;

public interface LineItemRepository extends CrudRepository<LineItem, Long> {
}