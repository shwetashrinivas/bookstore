package com.idfc.bootcamp.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    public Book(){}

    public String getTitle() {return title;}
}