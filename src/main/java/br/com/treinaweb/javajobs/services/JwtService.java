package br.com.treinaweb.javajobs.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    //Nao colocar em producao como hardcoded
    private static final String SIGNIN_KEY = "oDX8sJ0z86K49NTSbcYu06Ctnl0Z4BFQ";

    private static final int EXPIRATION_TIME = 300;

    public String generateToken(Authentication authentication) {
        return generateToken(SIGNIN_KEY, authentication.getName(), EXPIRATION_TIME);
    }

    public Date getExpirationFromToken(String token) {
        Claims claims = getClaims(token, SIGNIN_KEY);

        return claims.getExpiration();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token, SIGNIN_KEY);

        return claims.getSubject();
    }

    private Claims getClaims(String token, String signinKey) {
        return Jwts.parser()
                .setSigningKey(signinKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(String signinKey, String subject, int expirationTime) {
        Map<String, Object> claims = new HashMap<>();

        Instant currentDate = Instant.now();
        Instant expirationDate = currentDate.plusSeconds(expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentDate.toEpochMilli()))
                .setExpiration(new Date(expirationDate.toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, signinKey)
                .compact();
    }
}
