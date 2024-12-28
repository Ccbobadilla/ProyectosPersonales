
package com.kricabo.BookShop.Services;


import com.kricabo.BookShop.Models.Book;
import com.kricabo.BookShop.Repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book save(Book book) throws Exception {
        
        try{
            return bookRepository.save(book);
        }catch(Exception e){
            throw new Exception("Error guardando el libro "+ e.getCause());
        }
        
    }
    

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        
        return bookRepository.findAll(pageable);
        
    }

    @Override
    public Optional<Book> getBookById(int id) {
        
        return bookRepository.findById(id);
    
    }
    @Override
    public void delete(int id) throws Exception {
        
        try{
            bookRepository.deleteById(id);
        }catch(Exception e){
            throw new Exception("Error ELIMINANDO el libro "+ e.getCause());
        }
    }

    @Override
    public List<Book> findByTitleContaining(String title) throws Exception {
        
        try{
            return bookRepository.findByTitleContaining(title);
        }catch(Exception e){
            throw new Exception("Error buscando el titulo del libro el libro "+ e.getCause());
        }
    }

    @Override
    public List<Book> searchTitle(String name) throws Exception {
        
        
        
        try{ 
            return bookRepository.searchTitle(name);
        }catch(Exception e){
            throw new Exception("Error buscando libros con este match: "+ e.getCause());
        }
    
    }

    
    
    
    
    
}
