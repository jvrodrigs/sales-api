package com.app.salesapi.service;

import com.app.salesapi.exception.PasswordIsInvalidException;
import com.app.salesapi.model.User;
import com.app.salesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public UserDetails isAuthentication(User user){
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean isPasswordMatcher = encoder.matches(user.getPassword(), userDetails.getPassword());
        if (isPasswordMatcher) return userDetails;
        throw new PasswordIsInvalidException();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userLogin = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database."));
        String[] roles = userLogin.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User.builder()
                .username(userLogin.getUsername())
                .password(userLogin.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public User create(User user){
        return this.repository.save(user);
    }
}
