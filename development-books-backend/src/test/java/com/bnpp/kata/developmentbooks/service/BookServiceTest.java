package com.bnpp.kata.developmentbooks.service;

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

        // Prepare mock mapping for each enum value
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
}