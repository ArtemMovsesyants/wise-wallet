package ua.dp.amovsesyants.wisewallet.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final long SECONDS_PER_MINUTE = 60L;
    private static final String ALGORITHM = "HmacSHA256";

    @Value("${secret.key}")
    private String secretKey;

    @Value("${token.validity.minutes}")
    private int tokenValidityMinutes;

    public String generateToken(final UserDetails userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("authorities", authorities);

        return generateToken(claims, userDetails);
    }

    public String generateToken(final Map<String, ?> claims, final UserDetails userDetails) {
        Instant currentTime = Instant.now();
        Instant expirationTime = currentTime.plusSeconds(tokenValidityMinutes * SECONDS_PER_MINUTE);

        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(expirationTime))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        String usernameFromToken = extractUsername(token);
        return usernameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(final String token, final Function<Claims, T> extractor) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return extractor.apply(claims);

    }

    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }
}
