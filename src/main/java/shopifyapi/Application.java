package shopifyapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import shopifyapi.model.Customer;
import shopifyapi.model.Product;
import shopifyapi.model.Shop;
import shopifyapi.model.dao.CustomerRepository;
import shopifyapi.model.dao.ProductRepository;
import shopifyapi.model.dao.ShopRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@EntityScan
@SpringBootApplication
public class Application {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void populateDatabase() {
        customerRepository.save(new Customer(1L, "Michael", "308 King St.", "416-970-5473"));
        shopRepository.save(new Shop(2L, "Simona's Grocery Store", "273 Lester St.", "905-555-4907"));
        productRepository.save(new Product(51419L, "Banana", new BigDecimal("0.45")));
    }
}
