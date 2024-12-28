
package com.kricabo.BookShop.Services;

import com.kricabo.BookShop.Models.User;


public interface UserService {
    User save(User usuario);
    
    User findByEmail(String email);
    
    
}
