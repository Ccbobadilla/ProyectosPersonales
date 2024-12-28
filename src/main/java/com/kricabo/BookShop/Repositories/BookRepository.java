
package com.kricabo.BookShop.Repositories;


import com.kricabo.BookShop.Models.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cristian
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{
    
    List<Book> findByTitleContaining(String title); //Realiza automáticamente una búsqueda de libros cuyos títulos contengan la cadena proporcionada en title.
    
    @Query(value= "select b from Book b where b.title like %?1%")
    List<Book> searchTitle(String title);
}

