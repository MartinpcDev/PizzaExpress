package com.martin.ecommerce.application.utils;

import com.martin.ecommerce.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${jwt.security.secret-key}")
  private String SECRET_KEY;
  @Value("${jwt.security.access-expiration-time}")
  private Long ACCESS_TOKEN_EXPIRATION;
  @Value("${jwt.security.refresh-expiration-time}")
  private Long REFRESH_TOKEN_EXPIRATION;

  public String generateAccessToken(User user) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expirationAt = new Date(issueAt.getTime() + ACCESS_TOKEN_EXPIRATION);
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(issueAt)
        .expiration(expirationAt)
        .signWith(generateKey())
        .compact();
  }

  public String generateRefreshToken(User user) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expirationAt = new Date(issueAt.getTime() + REFRESH_TOKEN_EXPIRATION);
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(issueAt)
        .expiration(expirationAt)
        .signWith(generateKey())
        .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, User user) {
    final String username = extractUsername(token);
    return username.equalsIgnoreCase(user.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return claimsResolver.apply(claims);
  }

  private SecretKey generateKey() {
    final byte[] secretBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(secretBytes);
  }
}
