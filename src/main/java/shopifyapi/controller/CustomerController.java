package shopifyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopifyapi.manager.CustomerManager;
import shopifyapi.manager.ShopManager;
import shopifyapi.model.Customer;
import shopifyapi.model.Shop;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController extends BaseController<Customer, CustomerManager> {

    @Autowired
    public CustomerController(CustomerManager manager) {
        super(manager);
    }
}
