package shopifyapi.model;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.*;

@Entity
public abstract class Party {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    private String name;
    private String address;
    private String phone;

    protected Party() {}

    public Party(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
