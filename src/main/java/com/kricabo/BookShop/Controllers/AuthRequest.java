
package com.kricabo.BookShop.Controllers;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class AuthRequest {
    
    private String username;
    private String password;
    
    
}
