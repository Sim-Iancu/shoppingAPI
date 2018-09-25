package shopifyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopifyapi.manager.OrderManager;
import shopifyapi.model.OrderObject;

@RestController
@RequestMapping(value = "/orders")
public class OrderController extends BaseController<OrderObject, OrderManager> {

    @Autowired
    public OrderController(OrderManager manager) {
        super(manager);
    }
}
