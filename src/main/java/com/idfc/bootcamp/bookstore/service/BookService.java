package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchProductsByName(String searchTerm) {
        return bookRepository.findByTitleContainingIgnoreCase(searchTerm);
    }
}
