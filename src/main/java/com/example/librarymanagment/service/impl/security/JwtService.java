package com.example.librarymanagment.service.impl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value("${application.security.secret-key}")
    String secretKey;

    @Value("${application.security.access-token-expiration}")
    Long accessTokenExpiration;

    @Value("${application.security.refresh-token-expiration}")
    Long refreshTokenExpiration;

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails,
            long accessTokenExpiration
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ accessTokenExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails,
            long refreshTokenExpiration
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ refreshTokenExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails,accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails,refreshTokenExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token,UserDetails userDetails){////////////
        final String username=extractUsername(token);
        log.info("Token username -> {}",username);
        log.info("Username -> {}",userDetails.getUsername());
        log.info("Token expired ->{}",isTokenExpired(token));
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


}
