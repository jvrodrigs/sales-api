package com.app.salesapi.model;

import javax.persistence.*;

@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Request requestId;
    @ManyToOne
    private Product productId;
    private Integer countProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Request getRequestId() {
        return requestId;
    }

    public void setRequestId(Request requestId) {
        this.requestId = requestId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }
}
