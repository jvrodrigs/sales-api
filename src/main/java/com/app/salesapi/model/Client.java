package com.app.salesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 100)
    @NotEmpty(message = "Name field is required.")
    private String name;

    @Column(length = 11)
    @NotEmpty(message = "CPF field is required.")
    @CPF(message = "Please enter a valid CPF.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "clientId", fetch = FetchType.LAZY)
    private Set<Request> requests;
}
