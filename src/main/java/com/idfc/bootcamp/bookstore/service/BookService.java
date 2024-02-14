package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookOrderRepository;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookOrderRepository bookOrderRepository;
    BookRepository bookRepository;

    @Autowired
    public BookService(BookOrderRepository bookOrderRepository, BookRepository bookRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookRepository = bookRepository;
    }
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchProductsByName(String searchTerm) {
        return bookRepository.findByTitleContainingIgnoreCase(searchTerm);
    }

    public Optional<Book> getById(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
