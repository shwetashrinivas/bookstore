package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("books")
    public ResponseEntity<?> listBooks() {
        List<Book> books = bookService.getBooks();
        if (books.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No data found");
        }
        return ResponseEntity.ok(books);
    }




}
