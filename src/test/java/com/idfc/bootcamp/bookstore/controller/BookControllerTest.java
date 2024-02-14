package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("should return two books when invoked")
    void shouldReturnTwoBooksWhenInvoked() throws Exception {
        Book b1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book b2 = new Book("Little women",   "Louisa May Alcott", 450.0,
                "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
                "1586_f1601742134.png", 5.0, 4);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));
        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should return titles when the endpoint is invoked")
    void shouldReturnTitlesWhenTheEndpointIsInvoked() throws Exception {
        Book b1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book b2 = new Book("Little women",   "Louisa May Alcott", 450.0,
                "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
                "1586_f1601742134.png", 5.0, 4);


        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));
        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].title").value("Tale of two cities"))
                .andExpect(jsonPath("$[1].title").value("Little women"));
    }

    @Test
    @DisplayName("should return no books available when no data is available")
    void shouldReturnNoBooksAvailableWhenNoDataIsAvailable() throws Exception {
        List<Book> emptyList = Collections.emptyList();
        when(bookRepository.findAll()).thenReturn(emptyList);
        mockMvc.perform(get("/books")).
                andExpect(status().isNoContent()).
                andExpect(jsonPath("$[0].title").doesNotExist()).
                andExpect(content().string("No data found"));
    }

    @Test
    @DisplayName("should return matching books for search term")
    void shouldReturnMatchingBooksForSearchTerm() throws Exception {
        Book b1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        when(bookRepository.findByTitleContainingIgnoreCase("Tale")).thenReturn(List.of(b1));
        mockMvc.perform(get("/books/search?searchTerm=Tale"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Tale of two cities"))
                .andExpect(jsonPath("$[1].title").doesNotExist())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("should return all books when no search term is passed")
    void shouldReturnAllBooksWhenNoSearchTermIsPassed() throws Exception{
        Book b1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        Book b2 = new Book("bale of 5 cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 4);
        when(bookRepository.findByTitleContainingIgnoreCase("")).thenReturn(Arrays.asList(b1, b2));
        mockMvc.perform(get("/books/search?searchTerm="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Tale of two cities"))
                .andExpect(jsonPath("$[1].title").value("bale of 5 cities"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("should return book details when book id is passed")
    void shouldReturnBookDetailsWhenBookIdIsPassed() throws Exception {
        Book b1 = new Book("Tale of two cities",  "Charles Dickens",  350.0,
                "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
                "1586_f1601742134.png", 5.0, 3);
        when(bookRepository.findById(4L)).thenReturn(Optional.of(b1));
        mockMvc.perform(get("/books/details/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @DisplayName("should return 204 with no book found when invalid id is passed")
    void shouldReturn204WithNoBookFoundWhenInvalidIdIsPassed() throws Exception {
        when(bookRepository.findById(4L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/books/details/12"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("No book found"));

    }

}
