package com.mstramohz.BankingApp.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRETE_KEY = "dGFrZW1vb25wcm91ZGFsbGChanRoY3JlYXkRlcVtYXJrYWJsZWNvbW11bml0eWHelmU=";
    private Key getSigningKey(){
        byte[] keyByes = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyByes);
    }

    public String createToken(UserDetails userDetails){
        return createFreshToken(new HashMap<>(), userDetails);
    }

    private String createFreshToken(Map<String, Object> mapOfClaim, UserDetails userDetails){
        return Jwts.builder()
                .addClaims(mapOfClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                .setIssuer("Banking App 1.0")
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
       return Jwts.parserBuilder()
               .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractDateIssued(String token){
        return extractClaims(token, Claims::getIssuedAt);
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public boolean isTokenGeneratedFromServer(String token){
        return extractClaims(token, Claims::getIssuer).equals("Banking App 1.0");
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractClaims(token, Claims::getSubject);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenGeneratedFromServer(token);
    }
}
