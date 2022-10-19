package com.app.salesapi.controller;

import com.app.salesapi.model.User;
import com.app.salesapi.model.dto.UserDto;
import com.app.salesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid User user){
        String criptorPassword = encoder.encode(user.getPassword());
        user.setPassword(criptorPassword);
        User usedRegister = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToDto(usedRegister));
    }


    private UserDto convertEntityToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .admin(user.isAdmin())
                .build();
    }

}
