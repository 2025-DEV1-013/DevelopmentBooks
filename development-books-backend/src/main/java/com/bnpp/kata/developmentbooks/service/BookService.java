package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.mapper.BookMapper;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper mapper;

    public List<BookResponse> getAllBooks() {
        log.info("Retrieving all available books");
        return Arrays.stream(BookEnum.values())
                .map(mapper::toResponse)
                .toList();
    }

    public double calculateBookPrice(List<Book> bookList) {
        return 50.0;
    }
}