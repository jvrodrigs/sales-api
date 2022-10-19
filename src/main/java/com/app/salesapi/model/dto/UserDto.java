package com.app.salesapi.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String name;
    private String email;
    private boolean admin;
}
