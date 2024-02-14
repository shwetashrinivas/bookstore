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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

@Testcontainers
@TestPropertySource(properties = {"spring.flyway.enabled=true"})


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest
{
     @Container
     public static PostgreSQLContainer postgreSQLContainer = PostgresTestContainer.getInstance();

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
        bookRepository.deleteAll();
        Book book1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book book2 = new Book("Little women",   "Louisa May Alcott", 450.0,
                "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
                "1586_f1601742134.png", 5.0, 4);
        bookRepository.saveAll(Arrays.asList(book1,book2));

        List<Book> bookList = restTemplate.exchange(
                baseUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }).getBody();

        assertEquals(2,bookList.size());
    }

    @Test
    @DisplayName("should return books for search term")
    void shouldReturnBooksForSearchTerm() {
        bookRepository.deleteAll();
        Book book1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book book2 = new Book("Little women",   "Louisa May Alcott", 450.0,
                "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
                "1586_f1601742134.png", 5.0, 4);
        bookRepository.saveAll(Arrays.asList(book1, book2));
        List<Book> bookList = restTemplate.exchange(
                baseUrl + "/books/search?searchTerm=Tale",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }).getBody();
        assertEquals(1, bookList.size());

    }

    @Test
    @DisplayName("should return book for given search id ")
    void shouldReturnBookForGivenSearchId() {
        bookRepository.deleteAll();
        Book book1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book book2 = new Book("Little women",   "Louisa May Alcott", 450.0,
                "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
                "1586_f1601742134.png", 5.0, 4);
        bookRepository.saveAll(Arrays.asList(book1, book2));
        Book bookList = restTemplate.exchange(
                baseUrl + "/books/details/" + book1.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Book>() {
                }).getBody();
        assertEquals("Tale of two cities", bookList.getTitle());
    }
}
