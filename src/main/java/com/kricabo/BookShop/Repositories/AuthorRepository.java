/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kricabo.BookShop.Repositories;

import com.kricabo.BookShop.Models.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //marca la clase como un componente de acceso a datos 
public interface AuthorRepository extends JpaRepository<Author,Integer>{ //JpaRepository, que es una clase genérica proporcionada por Spring Data JPA para operaciones CRUD (Create, Read, Update, Delete) y paginación/sorting.
    
    @Query(value= "select a from Author a where a.name like %?1%")
    List<Author> searchName(String name); // devuelve una lista de autores, name es el parametro para buscar estos autores
    
     
}
