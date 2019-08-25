package com.tk.service.apigateway.util.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTokenHelper {
    private static JWTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 30;

    public JWTokenHelper() {
    }

    public static JWTokenHelper getInstance() {
        if (jwTokenHelper == null) {
            return new JWTokenHelper();
        }
        return jwTokenHelper;
    }

    public String generatePrivateKey(String username, String password) {
        return Jwts.builder()
                .setSubject(username)
                .setSubject(password)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.ES512, Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }

    public void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        Jwts.parser()
                .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .parseClaimsJws(privateKey);
    }

    private Date getExpirationDate() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
