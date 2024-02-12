package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.model.Book;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
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
}
