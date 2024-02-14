package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found");
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("books/search")
    public ResponseEntity<List<Book>> searchProductsByName(@RequestParam String searchTerm) {
        List<Book> books = bookService.searchProductsByName(searchTerm);
        return ResponseEntity.ok(books);
    }

    @GetMapping("books/details/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        Optional<Book> book = bookService.getById(bookId);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No book found");
        }
        return ResponseEntity.ok(book);
    }


}
