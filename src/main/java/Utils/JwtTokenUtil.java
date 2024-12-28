
package Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    
    private final String secretKey = "JavaKricabo2024";
    //private final SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    private final long expirationTime = 1000*60*20;
    private final String userGenerator = "AUTHOJWT-BACKEND";
    
    // Metodo para crear el token
    public String createToken (Authentication auth){
    
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        User username = (User)auth.getPrincipal();
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        return JWT.create().withIssuer(userGenerator)
                .withSubject(username.getUsername())
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
                
                
    }
    
    
    //Medoto para validar el token
    public DecodedJWT validateToken(String token){
    
            try {
            
                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(userGenerator)
                        .build();
                return verifier.verify(token);
            
            
            }catch(JWTVerificationException e){
                throw new JWTVerificationException("Token Invalido");
                        
            
            }
    
    
    }
    
    
    public String extractUsername(DecodedJWT decodedJWT){
    
        return decodedJWT.getSubject();
    
    }
    
    public Claim getSpecificClaim (DecodedJWT decodedJWT, String claimName){
    
        return decodedJWT.getClaim(claimName);
    
    }
    
    public UserDetails getByUsername(String username, String password){
    
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new User(username,password,true,true,true,true,authorityList);
    
    }
    
    /*public String generateToken (Authentication auth){
    
        return  Jwts
                .builder()
                .claims()
                .subject(auth.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expirationTime))
                .and()
                .signWith(secret)
                .compact();
    
    }*/
    
}
