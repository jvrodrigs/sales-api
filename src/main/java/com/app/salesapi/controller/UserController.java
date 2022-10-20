package com.app.salesapi.controller;

import com.app.salesapi.config.security.token.JwtService;
import com.app.salesapi.exception.PasswordIsInvalidException;
import com.app.salesapi.model.User;
import com.app.salesapi.model.dto.CredencialDto;
import com.app.salesapi.model.dto.TokenDto;
import com.app.salesapi.model.dto.UserDto;
import com.app.salesapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid User user){
        String criptorPassword = encoder.encode(user.getPassword());
        user.setPassword(criptorPassword);
        User usedRegister = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToDto(usedRegister));
    }
    @PostMapping("/auth")
    public TokenDto auth(@RequestBody CredencialDto credencial){
        try{
            User user = User.builder()
                    .username(credencial.getUsername())
                    .password(credencial.getPassword())
                    .build();
            UserDetails userAuthentication = service.isAuthentication(user);

            String token = jwtService.generatedToken(user);
            return new TokenDto(user.getUsername(), token);
        } catch (PasswordIsInvalidException | UsernameNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,ex.getMessage());
        }
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
