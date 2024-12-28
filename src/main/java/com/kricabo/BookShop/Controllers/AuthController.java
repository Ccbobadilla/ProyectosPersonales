
package com.kricabo.BookShop.Controllers;

import Utils.JwtTokenUtil;
import com.kricabo.BookShop.Models.User;
import com.kricabo.BookShop.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins ="*")
public class AuthController {
    
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    //signin
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){
        
        if (authRequest != null && !authRequest.getUsername().isEmpty() && !authRequest.getPassword().isEmpty()){
        
            User user = userServiceImpl.findByEmail(authRequest.getUsername());
            
            if(user ==null){
            
                return "Usurio o contraseña invalidos";
                
            }
            
            passwordEncoder = new BCryptPasswordEncoder();
            if(!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())){
            
                return "Usurio o contraseña invalidos";
                
            }
            
            //createToken
            UserDetails userDetails = jwtTokenUtil.getByUsername(authRequest.getUsername(), authRequest.getPassword());
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
            return jwtTokenUtil.createToken(auth);
            
            
            
            
        
        }
        
        return "";
    }
    //singup
    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user){ //@RequestBody como se recibe la peticion mediante el body
        
        User userSave = userServiceImpl.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }
    
}
