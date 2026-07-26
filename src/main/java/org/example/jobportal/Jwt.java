package org.example.jobportal;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Jwt {
    private final SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean isExpired(String token){
        Date e= Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return e.before(new Date());
    }
    public boolean validateToken(String token,String username){
        String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username)
                && !isExpired(token);
    }
}
