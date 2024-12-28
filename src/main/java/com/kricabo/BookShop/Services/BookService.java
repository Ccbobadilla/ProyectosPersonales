
package com.kricabo.BookShop.Services;



import com.kricabo.BookShop.Models.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface BookService {
    
    Book save(Book book) throws Exception;
    
    Page<Book> getBooks(Pageable pageable);
   
    Optional<Book> getBookById(int id);
    
    void delete(int id) throws Exception;
    
    List<Book> findByTitleContaining(String title) throws Exception;
    
    List<Book> searchTitle (String name) throws Exception;
    
}
