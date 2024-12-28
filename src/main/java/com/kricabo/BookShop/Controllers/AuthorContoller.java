
package com.kricabo.BookShop.Controllers;

import com.kricabo.BookShop.Models.Author;
import com.kricabo.BookShop.Services.AuthorServiceImpl;
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
@RequestMapping("/api/author")
@CrossOrigin(origins ="*")
public class AuthorContoller {
    
    
    @Autowired
    AuthorServiceImpl authorServiceImpl;
            
    
    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestBody Author author){ //@RequestBody como se recibe la peticion mediante el body
        
        Author authorSave = authorServiceImpl.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorSave);
    }
    
    @GetMapping("/list")
    public ResponseEntity<Page<Author>> getAuthors(Pageable pageable){
    
        Page<Author> authors = authorServiceImpl.getAuthors(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Author> update(@PathVariable Integer id, @RequestBody Author author){
        Optional<Author> authorFind = authorServiceImpl.getAuthorById(id);
        
        if(!authorFind.isPresent()){return ResponseEntity.notFound().build();}
            
        Author authorUpdate = authorFind.get();
        authorUpdate.setName(author.getName());
        authorUpdate.setLastName(author.getLastName());
        authorUpdate.setBiography(author.getBiography());
        authorServiceImpl.save(authorUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(authorUpdate);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<Author> authorFind = authorServiceImpl.getAuthorById(id);
        if(!authorFind.isPresent()){return ResponseEntity.notFound().build();}
        
        authorServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("{'mensaje': 'Autor eliminado correctamente'}");
    
    }
    
    @GetMapping("/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        List<Author> authors = authorServiceImpl.searchName(name);
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }
}
