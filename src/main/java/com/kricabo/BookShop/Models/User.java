
package com.kricabo.BookShop.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @NotBlank
    @Column(unique=true)
    private String email;
    
    @NotNull
    @NotBlank
    private String nombre;
    
    @NotNull
    @NotBlank
    private String password;
}
