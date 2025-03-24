package com.ajthack.netgeardemo.controller;

import com.ajthack.netgeardemo.model.Book;
import com.ajthack.netgeardemo.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        bookRepository.save(new Book(null, "Book 1", "Author 1", ZonedDateTime.now(), "Genre 1", null, null, false));
        bookRepository.save(new Book(null, "Book 2", "Author 2", ZonedDateTime.now(), "Genre 2", null, null, false));
        bookRepository.save(new Book(null, "Book 3", "Author 3", ZonedDateTime.now(), "Genre 3", null, null, false));
        bookRepository.save(new Book(null, "Book 4", "Author 4", ZonedDateTime.now(), "Genre 4", null, null, false));
        bookRepository.save(new Book(null, "Book 5", "Author 5", ZonedDateTime.now(), "Genre 5", null, null, false));
        bookRepository.save(new Book(null, "Book 6", "Author 6", ZonedDateTime.now(), "Genre 6", null, null, false));
    }

    @Test
    void shouldCreateBook() throws Exception {
        Book book = new Book(null, "Test Book", "Test Author",
                ZonedDateTime.now(), "Test Genre", null, null, false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.genre").value("Test Genre"))
                .andExpect(jsonPath("$.deleted").value(false));

    }

    @Test
    void shouldGetPaginatedBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.content[0].title").value("Book 1"))
                .andExpect(jsonPath("$.content[1].title").value("Book 2"));
    }

    @Test
    void shouldGetBookById() throws Exception {
        Book savedBook = bookRepository.save(
                new Book(null, "Find Me", "Author", ZonedDateTime.now(), "Genre", null, null, false));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/" + savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Find Me"))
                .andExpect(jsonPath("$.id").value(savedBook.getId()));
    }

    @Test
    void shouldSoftDeleteBook_marksDeletedFieldTrue() throws Exception {
        Book book = bookRepository.save(
                new Book(null, "Delete Me", "Author", ZonedDateTime.now(), "Genre", null, null, false));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/" + book.getId()))
                .andExpect(status().isNoContent());

        Book deletedBook = bookRepository.findById(book.getId()).orElseThrow();
        assertTrue(deletedBook.isDeleted());
    }
}