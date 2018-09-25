package shopifyapi.model;

import javax.persistence.Entity;

@Entity
public class Shop extends Party {

    protected Shop() {
    }

    public Shop(Long id, String name, String address, String phone) {
        super(id, name, address, phone);
    }
}
