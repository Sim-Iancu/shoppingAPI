package shopifyapi.model;

import javax.persistence.*;
import javax.sound.sampled.Line;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
public class OrderObject {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long orderNumber;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Shop shop;
    private String date;
    @OneToMany(targetEntity = LineItem.class, cascade = ALL)
    private List<LineItem> lineItems;
    private BigDecimal total = new BigDecimal("0");

    protected OrderObject() {
    }

    public OrderObject(Long orderNumber, Customer customer, Shop shop, String date, List<LineItem> lineItems) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.shop = shop;
        this.date = date;
        this.lineItems = lineItems;
        BigDecimal orderTotal = new BigDecimal("0");
        for (LineItem lineItem : lineItems) {
            orderTotal = orderTotal.add(lineItem.getLineItemTotal());
        }
        this.total = orderTotal;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        BigDecimal orderTotal = new BigDecimal("0");
        for (LineItem lineItem : lineItems) {
            orderTotal = orderTotal.add(lineItem.getLineItemTotal());
        }
        return orderTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
