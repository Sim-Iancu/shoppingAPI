package shopifyapi.model;

import javax.persistence.Entity;

@Entity
public class Customer extends Party {

    protected Customer() {
    }

    public Customer(Long id, String name, String address, String phone) {
        super(id, name, address, phone);
    }
}
