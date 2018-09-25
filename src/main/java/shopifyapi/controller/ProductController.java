package shopifyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopifyapi.manager.CustomerManager;
import shopifyapi.manager.ProductManager;
import shopifyapi.model.Customer;
import shopifyapi.model.Product;

@RestController
@RequestMapping(value = "/products")
public class ProductController extends BaseController<Product, ProductManager> {

    @Autowired
    public ProductController(ProductManager manager) {
        super(manager);
    }
}
