
package com.kricabo.BookShop.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

//Anotaciones o decoradores
@Entity //Entidad que representa una tabla en una base de datos
@Table(name="author") //ASigna el nombre de la tabla
@Data //Contiene todo de los gettters y setters entre otros
public class Author {
    
    @Id // para llave primaria
    @GeneratedValue(strategy=GenerationType.IDENTITY)//Indica que el dato es autogenerado
    private Integer id;
    
    @NotNull //Es para que el campo del nombre no quede basio
    @NotBlank //No se admiten espacios basios
    private String name;
    
    @NotNull
    @NotBlank
    private String lastName;
    
    private String biography; 
    
    @OneToMany(mappedBy="author",fetch = FetchType.EAGER)//OneToMAny: uno a muchos, mappedBy: Quien va a ser mapeado? ("author"), FetchType.EAGER: carga el listado de libros
    @JsonBackReference // Es para evitar referencias circulares
    private List<Book> books = new ArrayList<>();
    
}
