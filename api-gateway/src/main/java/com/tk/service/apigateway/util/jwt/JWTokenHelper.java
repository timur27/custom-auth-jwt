package com.tk.service.apigateway.util.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public final class JWTokenHelper {
    public static void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        Jwts.parser()
                .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .parseClaimsJws(privateKey);
    }
}
