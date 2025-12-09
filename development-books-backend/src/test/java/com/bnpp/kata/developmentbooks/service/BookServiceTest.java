package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookServiceTest {

    private BookService service;

    @BeforeEach
    void setup() {
        service = new BookService();
    }

    @Test
    @DisplayName("getAllBooks() → returns exactly 5 books from enum")
    void testGetAllBooksCount() {
        List<BookResponse> books = service.getAllBooks();
        assertEquals(5, books.size());
    }
}