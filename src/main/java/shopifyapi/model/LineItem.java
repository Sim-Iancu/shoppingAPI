package shopifyapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class LineItem {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity;
    private BigDecimal lineItemTotal = new BigDecimal("0");

    protected LineItem() {
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.lineItemTotal = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (this.product != null) {
            this.lineItemTotal = this.product.getPrice().multiply(new BigDecimal(quantity));
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.lineItemTotal = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal getLineItemTotal() {
        return lineItemTotal;
    }

    public void setLineItemTotal(BigDecimal lineItemTotal) {
        this.lineItemTotal = lineItemTotal;
    }
}
