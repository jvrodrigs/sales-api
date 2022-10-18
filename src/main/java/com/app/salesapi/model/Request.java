package com.app.salesapi.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Client clientId;
    @Column(length = 100)
    private String name;
    @Column(length = 250)
    private String description;
    @Column(precision = 20, scale = 2)
    private BigDecimal valueTotal;
    @CreationTimestamp
    private Date createAt;
    @OneToMany(mappedBy = "requestId")
    private Set<ItemOrder> itemOrderSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(BigDecimal valueTotal) {
        this.valueTotal = valueTotal;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Set<ItemOrder> getItemOrderSet() {
        return itemOrderSet;
    }

    public void setItemOrderSet(Set<ItemOrder> itemOrderSet) {
        this.itemOrderSet = itemOrderSet;
    }
}
