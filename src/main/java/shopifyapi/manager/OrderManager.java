package shopifyapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopifyapi.model.OrderObject;
import shopifyapi.model.dao.OrderRepository;

@Service
public class OrderManager implements Manager<OrderObject> {

    @Autowired
    private OrderRepository orderRepository;

    public Iterable<OrderObject> getAll() {
        return orderRepository.findAll();
    }

    public OrderObject get(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderObject save(OrderObject orderObject) {
        return orderRepository.save(orderObject);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
