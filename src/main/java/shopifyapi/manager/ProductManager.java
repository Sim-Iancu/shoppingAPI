package shopifyapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopifyapi.model.Product;
import shopifyapi.model.dao.ProductRepository;

@Service
public class ProductManager implements Manager<Product> {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long productCode) {
        productRepository.deleteById(productCode);
    }
}
