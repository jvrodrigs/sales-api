package com.app.salesapi.config.token;

import com.app.salesapi.SalesApiApplication;
import com.app.salesapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.key-signature}")
    private String keySignature;

    public String generatedToken(User user){
        long expString = Long.valueOf(expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(dateHourExpiration
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, keySignature)
                .compact();
    }

    private Claims getClaimsToken(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(keySignature)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValid(String token){
        try{
            Claims claims = getClaimsToken(token);
            Date dateExpirationToken = claims.getExpiration();
            LocalDateTime localDateTime = LocalDateTime.from(dateExpirationToken.
                    toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate());
            return !LocalDateTime.now().isAfter(localDateTime);
        }catch (Exception ex){
            return false;
        }
    }

    public String getIsLoginUser(String token) throws ExpiredJwtException{
        return (String) getClaimsToken(token).getSubject();
    }

    //Verificando o token
    public static void main(String[] args) {
        ConfigurableApplicationContext context  = SpringApplication.run(SalesApiApplication.class);
        JwtService service = context.getBean(JwtService.class);
        User user = User.builder().username("jvrodrigs").build();
        String token = service.generatedToken(user);
        System.out.println(token);


        boolean isTokenValid = service.tokenValid(token);
        System.out.println("O token está:" + isTokenValid);

        System.out.println(service.getIsLoginUser(token));
    }
}
