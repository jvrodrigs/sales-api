package com.app.salesapi.config.security;

import com.app.salesapi.config.security.token.JwtService;
import com.app.salesapi.model.User;
import com.app.salesapi.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserService userService;

    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValidToken = jwtService.tokenValid(token);

            if (isValidToken){
                String lgUser = jwtService.getIsLoginUser(token);
                UserDetails user = userService.loadUserByUsername(lgUser);

                UsernamePasswordAuthenticationToken  userDetails = new
                        UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                userDetails.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }
}
