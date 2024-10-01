package by.mikhalachkin.service;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

  public static final String SECRET = "secret";

  public void validateToken(final String token) {
    Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
  }

  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  private Key getSignKey() {
    byte[] keyBytes = Base64.getDecoder().decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
