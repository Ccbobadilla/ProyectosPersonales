
package com.kricabo.BookShop.Services;

import com.kricabo.BookShop.Models.User;
import com.kricabo.BookShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User save(User usuario) {
        passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return userRepository.save(usuario);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
