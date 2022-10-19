package com.app.salesapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Username field is required")
    private String username;
    @NotEmpty(message = "Name field is required")
    private String name;
    @NotEmpty(message = "Email field is required")
    private String email;
    @NotEmpty(message = "Password field is required")
    private String password;

    private boolean admin;

}

