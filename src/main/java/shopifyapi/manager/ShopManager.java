package shopifyapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopifyapi.model.Shop;
import shopifyapi.model.dao.ShopRepository;

import java.util.Optional;

@Service
public class ShopManager implements Manager<Shop> {

    @Autowired
    private ShopRepository shopRepository;

    public Iterable<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop get(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}
