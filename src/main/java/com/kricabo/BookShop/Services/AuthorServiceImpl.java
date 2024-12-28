
package com.kricabo.BookShop.Services;

import com.kricabo.BookShop.Models.Author;

import com.kricabo.BookShop.Repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    
    @Autowired
    AuthorRepository authorRepository;
    
    /** 
    * Metododo para guardar un autor
    * @param author modelo del author
    * @return El author guardado enla base de datos
    */
    @Override
    public Author save(Author author) {
        
        return authorRepository.save(author);
 
    }

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Author> getAuthors(Pageable pageable) {
        
        return authorRepository.findAll(pageable);
        
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        
        return authorRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        
        authorRepository.deleteById(id);
        
    }

    @Override
    public List<Author> searchName(String name) {
        return authorRepository.searchName(name);
    
    }

    
    
}
