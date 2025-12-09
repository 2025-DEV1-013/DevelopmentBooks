package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.dto.BookPriceResponse;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.exception.InvalidBookException;
import com.bnpp.kata.developmentbooks.mapper.BookMapper;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import com.bnpp.kata.developmentbooks.util.BookUtils;
import com.bnpp.kata.developmentbooks.util.DiscountUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookMapper mapper;

    @InjectMocks
    private BookService bookService;

    // ------------------------------------------------------
    // 1. getAllBooks()
    // ------------------------------------------------------
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
                            bookEnum.price,
                            bookEnum.imageUrl
                    ));
        }
        List<BookResponse> result = bookService.getAllBooks();
        assertThat(result).hasSize(BookEnum.values().length);
        assertEquals(5, result.size());
        verify(mapper, times(BookEnum.values().length)).toResponse(any());
    }

    // ------------------------------------------------------
    // 2. calculatePrice() – Valid basket
    // ------------------------------------------------------
    @Test
    @DisplayName("calculatePrice() → successful flow returns mergedBooks, totalPrice, discountPrice")
    void testCalculatePriceForSuccess() {

        List<Book> input = List.of(
                new Book("Clean Code", 1),
                new Book("The Clean Coder", 1)
        );

        Map<String, Integer> merged = Map.of(
                "clean code", 1,
                "the clean coder", 1
        );

        List<Integer> sortedCounts = List.of(1, 1);

        try (MockedStatic<BookUtils> bookUtils = mockStatic(BookUtils.class);
             MockedStatic<DiscountUtils> discountUtils = mockStatic(DiscountUtils.class)) {

            bookUtils.when(() -> BookUtils.validateBasket(input))
                    .thenAnswer(inv -> null);

            bookUtils.when(() -> BookUtils.mergeDuplicateTitles(input))
                    .thenReturn(merged);

            bookUtils.when(() -> BookUtils.extractSortedCounts(merged))
                    .thenReturn(sortedCounts);

            discountUtils.when(() -> DiscountUtils.computeOptimalPrice(sortedCounts))
                    .thenReturn(95.0);

            BookPriceResponse response = bookService.calculateBookPrice(input);

            assertEquals(100.0, response.totalPrice());
            assertEquals(95.0, response.discountPrice());
            assertEquals(2, response.bookList().size());
        }
    }

    // ------------------------------------------------------
    // 3. calculatePrice() – sortedCounts is empty
    // ------------------------------------------------------
    @Test
    @DisplayName("calculatePrice() → throws InvalidBookException when sortedCounts is empty")
    void testCalculatePriceForEmptySortedCounts() {

        List<Book> input = List.of(new Book("Clean Code", 1));

        try (MockedStatic<BookUtils> bookUtils = mockStatic(BookUtils.class)) {

            bookUtils.when(() -> BookUtils.validateBasket(input))
                    .thenAnswer(inv -> null);

            bookUtils.when(() -> BookUtils.mergeDuplicateTitles(input))
                    .thenReturn(Map.of("clean code", 1));

            bookUtils.when(() -> BookUtils.extractSortedCounts(anyMap()))
                    .thenReturn(List.of());

            assertThrows(
                    InvalidBookException.class,
                    () -> bookService.calculateBookPrice(input)
            );
        }
    }


    // ------------------------------------------------------
    // 4. calculatePrice() → handles BookUtils validation exception correctly
    // ------------------------------------------------------
    @Test
    @DisplayName("calculatePrice() → propagates validation exceptions thrown by BookUtils")
    void testCalculatePriceToValidationError() {

        List<Book> input = List.of(new Book("", -1));

        try (MockedStatic<BookUtils> bookUtils = mockStatic(BookUtils.class)) {

            bookUtils.when(() -> BookUtils.validateBasket(input))
                    .thenThrow(new InvalidBookException("Invalid basket"));

            assertThrows(
                    InvalidBookException.class,
                    () -> bookService.calculateBookPrice(input)
            );
        }
    }
}