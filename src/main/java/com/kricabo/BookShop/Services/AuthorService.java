
package com.kricabo.BookShop.Services;

import com.kricabo.BookShop.Models.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthorService {
    Author save(Author author);
    
    Page<Author> getAuthors(Pageable pageable);
   
    Optional<Author> getAuthorById(int id);
    
    void delete(int id);
    
    List<Author> searchName (String name);
}
