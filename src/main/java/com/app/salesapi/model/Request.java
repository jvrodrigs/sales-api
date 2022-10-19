package com.app.salesapi.model;

import com.app.salesapi.model.enums.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Client clientId;

    @Column(precision = 20, scale = 2)
    private BigDecimal valueTotal;

    @CreationTimestamp
    private Date createAt;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;

    @OneToMany(mappedBy = "requestId")
    private List<ItemOrder> itemOrders;
}
