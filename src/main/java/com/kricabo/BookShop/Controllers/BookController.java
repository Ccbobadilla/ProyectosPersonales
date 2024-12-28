
package com.kricabo.BookShop.Controllers;


import com.kricabo.BookShop.Models.Author;
import com.kricabo.BookShop.Models.Book;
import com.kricabo.BookShop.Services.AuthorServiceImpl;
import com.kricabo.BookShop.Services.BookServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins ="*")
public class BookController {
    
    @Autowired
    BookServiceImpl bookServiceImpl;
    @Autowired
    AuthorServiceImpl authorServiceImpl;
            
    
    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody Book book) throws Exception{ //@RequestBody como se recibe la peticion mediante el body
        
        Book bookSave = bookServiceImpl.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSave);
    }
    
    @GetMapping("/list")
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable){
    
        Page<Book> books = bookServiceImpl.getBooks(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) throws Exception{
        Optional<Book> bookFind = bookServiceImpl.getBookById(id);
        
        if(!bookFind.isPresent()){return ResponseEntity.notFound().build();}
            
        Book bookUpdate = bookFind.get();
        bookUpdate.setTitle(book.getTitle());
        bookUpdate.setDescription(book.getDescription());
        bookUpdate.setCategory(book.getCategory());
        bookUpdate.setImage(book.getImage());
        bookUpdate.setIsbn(book.getIsbn());
        bookUpdate.setPages(book.getPages());
        bookUpdate.setPrice(book.getPrice());
        bookUpdate.setPublication_date(book.getPublication_date());
        
        bookServiceImpl.save(bookUpdate);
        
        Optional<Author> author = authorServiceImpl.getAuthorById(book.getAuthor().getId());
        if (author.isPresent()) {
            bookUpdate.setAuthor(author.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookUpdate);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception{
        Optional<Book> authorFind = bookServiceImpl.getBookById(id);
        if(!authorFind.isPresent()){return ResponseEntity.notFound().build();}
        
        bookServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("{'mensaje': 'Autor eliminado correctamente'}");
    
    }
    
    @GetMapping("/search/{title}")
    public ResponseEntity<?> search(@PathVariable String title) throws Exception{
        List<Book> books = bookServiceImpl.searchTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    
    
}

