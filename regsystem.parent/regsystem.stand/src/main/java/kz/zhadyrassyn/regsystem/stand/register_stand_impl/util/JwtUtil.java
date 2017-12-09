package kz.zhadyrassyn.regsystem.stand.register_stand_impl.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Bean
public class JwtUtil {
    private String secret = "mySecret";

    private Long expiration = (long)604800;

    public BeanGetter<TimeProvider> timeProvider;

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, subject);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = timeProvider.get().now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

//    public boolean validateToken(String token, UserDetails userDetails) {
//        JwtUser user = (JwtUser)userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getIssuedAtDateFromToken(token);
//
//        return (
//                username.equals(user.getUsername()) && !isTokenExpired(token)
//        );
//    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return timeProvider.get().now().before(expiration);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

}
