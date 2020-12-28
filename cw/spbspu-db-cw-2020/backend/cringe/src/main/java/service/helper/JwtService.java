package service.helper;

import data.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtService {
  private static final String SECRET_KEY = "babyyodaissith";
  private static final String ISSUER = "tourapp";

  public static Claims decodeJWT(String jwt) {
    return Jwts.parser()
        .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
        .parseClaimsJws(jwt).getBody();
  }

  public static String createJWT(String username, Role role) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET_KEY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    JwtBuilder builder = Jwts.builder()
        .setId(username)
        .setIssuedAt(now)
        .setSubject(role.toString())
        .setIssuer(ISSUER)
        .signWith(signatureAlgorithm, signingKey);
    long expMillis = nowMillis + TimeUnit.DAYS.toMillis(10);
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }
}
