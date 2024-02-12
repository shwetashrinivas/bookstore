package com.idfc.bootcamp.bookstore.integration;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    BookRepository bookRepository;

    @LocalServerPort
    private int randomServerPort;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        baseUrl = "http://localhost:"+ randomServerPort + "/";
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("should return list of books")
    void shouldReturnListOfBooks() {
        Book book1 = new Book("Refactoring");
        Book book2 = new Book("TDD");

        bookRepository.saveAll(Arrays.asList(book1,book2));

        List<Book> bookList = restTemplate.exchange(
                baseUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }).getBody();

        assertEquals(2,bookList.size());
    }

}
