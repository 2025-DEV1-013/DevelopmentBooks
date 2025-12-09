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

    private static final double BOOK_PRICE = 50.0;

    private final BookMapper mapper;

    public List<BookResponse> getAllBooks() {
        log.info("Retrieving all available books");
        return Arrays.stream(BookEnum.values())
                .map(mapper::toResponse)
                .toList();
    }

    public double calculateBookPrice(List<Book> bookList) {
        int reqBookCount = bookList.size();
        double totalPrice = 0.0;
        if(reqBookCount == 1)
            totalPrice = BOOK_PRICE * reqBookCount;
        else if(reqBookCount == 2){
            totalPrice = (BOOK_PRICE * reqBookCount) * (1 - 0.05);
        } else if(reqBookCount == 3){
            totalPrice = (BOOK_PRICE * reqBookCount) * (1 - 0.10);
        }
        return totalPrice;
    }
}