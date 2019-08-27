package com.tk.service.apigateway.util.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTokenHelper {
    private static JWTokenHelper jwTokenHelper = null;

    public JWTokenHelper() {
    }

    public static JWTokenHelper getInstance() {
        if (jwTokenHelper == null) {
            return new JWTokenHelper();
        }
        return jwTokenHelper;
    }

    public void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        Jwts.parser()
                .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .parseClaimsJws(privateKey);
    }
}
