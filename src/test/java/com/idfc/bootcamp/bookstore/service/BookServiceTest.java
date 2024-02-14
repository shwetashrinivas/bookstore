package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookOrderRepository;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookOrderRepository bookOrderRepository;

    @BeforeEach
    void setUp() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        when(bookRepository.findByTitleContainingIgnoreCase("book")).thenReturn(Arrays.asList(book1, book2));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());
    }

    @Test
    void testGetBooks() {
        List<Book> books = bookService.getBooks();
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Book 2", books.get(1).getTitle());
    }

    @Test
    void testSearchProductsByName() {
        List<Book> books = bookService.searchProductsByName("book");
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Book 2", books.get(1).getTitle());
    }

}
