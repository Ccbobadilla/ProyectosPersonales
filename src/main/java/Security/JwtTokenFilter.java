
package Security;

import Utils.JwtTokenUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableMethodSecurity
public class JwtTokenFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (jwtToken!=null){
            jwtToken = jwtToken.substring(7);
            
            DecodedJWT decodedJWT = jwtTokenUtil.validateToken(jwtToken);
            String username = jwtTokenUtil.extractUsername(decodedJWT);
            String stringRoles = jwtTokenUtil.getSpecificClaim(decodedJWT,"authorities").asString();
            
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringRoles);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication auth = new UsernamePasswordAuthenticationToken(username,null,authorities);
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            
        
        } 
        filterChain.doFilter(request, response);
    }
    
    /*
    
    private final String secretKey = "JavaKricabo2024";
    private final SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
        String token = getTokenFromRequest(request);
        if(token != null && validateToken(token)){
            Claims claims = Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    claims.getSubject()
                    ,null 
                    ,Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(request, response);
    
    }
    
    private String getTokenFromRequest(HttpServletRequest request){
        
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    
    }
    
    private boolean validateToken(String token){
        
        try{

            Jwts.parser().verifyWith(secret).build().parseSignedClaims(token);

            return true;
        }catch (Exception e){
            return false;
        }
    }*/

    
}
