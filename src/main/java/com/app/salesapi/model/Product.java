package com.app.salesapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 100)
    @NotEmpty(message = "Product field is required.")
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @NotNull(message = "Price field is required.")
    private BigDecimal priceUnit;

    @CreationTimestamp
    private Date createAt;
}
