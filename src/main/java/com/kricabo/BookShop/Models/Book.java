/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kricabo.BookShop.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author cristian
 */
@Entity
@Table(name="book")
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    
    @NotNull
    @NotBlank
    private String title;
    
    
    private String description;
    private String category;
    private Double price;
    private String isbn;
    private int pages;
    private LocalDate publication_date;
    private String image;
    
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_author")//Relacion con la tabla author
    private Author author;
    
}
