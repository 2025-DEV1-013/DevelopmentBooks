package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.mapper.BookMapper;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookMapper mapper;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("getAllBooks() → returns exactly 5 books from enum")
    void testGetAllBooksCount() {
        for (BookEnum bookEnum : BookEnum.values()) {
            when(mapper.toResponse(bookEnum))
                    .thenReturn(new BookResponse(
                            bookEnum.id,
                            bookEnum.title,
                            bookEnum.author,
                            bookEnum.year,
                            bookEnum.price
                    ));
        }
        List<BookResponse> result = bookService.getAllBooks();
        assertThat(result).hasSize(BookEnum.values().length);
        assertEquals(5, result.size());
        verify(mapper, times(BookEnum.values().length)).toResponse(any());
    }

    @Test
    @DisplayName("Calculate price for a single book with no discount")
    void testSingleBookWithNoDiscount() {
        List<Book> items = List.of(
                new Book("Clean Code", 1)
        );
        assertEquals(50.0, bookService.calculateBookPrice(items), 0.01);
    }

    @Test
    @DisplayName("Apply 5% discount for two different books")
    void testTwoDifferentBooksFor5PercentDiscount() {
        List<Book> items = List.of(
                new Book("Clean Code", 1),
                new Book("The Clean Coder", 1)
        );
        assertEquals(95.0, bookService.calculateBookPrice(items), 0.01);
    }

    @Test
    @DisplayName("Apply 10% discount for three different books")
    void testThreeDifferentBooksFor10PercentDiscount() {
        List<Book> items = List.of(
                new Book("Clean Code", 1),
                new Book("The Clean Coder", 1),
                new Book("Clean Architecture", 1)
        );
        assertEquals(135.0, bookService.calculateBookPrice(items), 0.01);
    }

    @Test
    @DisplayName("Apply 20% discount for four different books")
    void testFourDifferentBooksFor20PercentDiscount() {
        List<Book> items = List.of(
                new Book("Clean Code", 1),
                new Book("The Clean Coder", 1),
                new Book("Clean Architecture", 1),
                new Book("TDD", 1)
        );
        assertEquals(160.0, bookService.calculateBookPrice(items), 0.01);
    }
}