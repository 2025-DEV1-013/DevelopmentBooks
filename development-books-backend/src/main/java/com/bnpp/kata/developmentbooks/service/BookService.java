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
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private static final double BOOK_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.00,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    private final BookMapper mapper;

    public List<BookResponse> getAllBooks() {
        log.info("Retrieving all available books");
        return Arrays.stream(BookEnum.values())
                .map(mapper::toResponse)
                .toList();
    }

    public double calculateBookPrice(List<Book> bookList) {
        double totalPrice = 0.0;
        Map<String, Integer> quantities = bookList.stream()
                .collect(Collectors.toMap(Book::title, Book::quantity));

        while (quantities.values().stream().anyMatch(count -> count > 0)) {
            int uniqueCount = 0;
            for (Map.Entry<String, Integer> title : quantities.entrySet()) {
                int qty = quantities.get(title.getKey());
                if (qty > 0) {
                    uniqueCount++;
                    quantities.put(title.getKey(), qty - 1);
                }
            }

            // Apply discount based on the number of unique titles in this group
            double discount = DISCOUNTS.getOrDefault(uniqueCount, 0.0);
            totalPrice += (uniqueCount * BOOK_PRICE) * (1 - discount);
        }
        return totalPrice;
    }
}