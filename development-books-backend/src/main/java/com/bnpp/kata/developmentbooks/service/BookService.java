package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.dto.BookPriceResponse;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.exception.InvalidBookException;
import com.bnpp.kata.developmentbooks.mapper.BookMapper;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import com.bnpp.kata.developmentbooks.util.BookUtils;
import com.bnpp.kata.developmentbooks.util.DiscountUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private static final double BOOK_PRICE = 50.0;

    private final BookMapper mapper;

    /**
     * Fetch all available books.
     */
    public List<BookResponse> getAllBooks() {
        log.info("Retrieving all available books");
        return Arrays.stream(BookEnum.values())
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * Calculate the total and discounted price for a given book list.
     */
    public BookPriceResponse calculateBookPrice(List<Book> books) {
        log.info("Calculate Book Price");
        BookUtils.validateBasket(books);

        Map<String, Integer> mergedBooks = BookUtils.mergeDuplicateTitles(books);
        List<Integer> sortedCounts = BookUtils.extractSortedCounts(mergedBooks);

        validateCounts(sortedCounts);

        double discountedPrice = DiscountUtils.computeOptimalPrice(sortedCounts);
        double originalPrice = calculateOriginalPrice(mergedBooks);

        List<Book> finalMergedBooks = convertToBookList(mergedBooks);

        return new BookPriceResponse(finalMergedBooks, originalPrice, discountedPrice);
    }

    private void validateCounts(List<Integer> sortedCounts) {
        if (sortedCounts.isEmpty()) {
            throw new InvalidBookException("Basket must contain at least one book with quantity > 0");
        }
    }

    private double calculateOriginalPrice(Map<String, Integer> mergedBooks) {
        return mergedBooks.values().stream()
                .mapToInt(Integer::intValue)
                .sum() * BOOK_PRICE;
    }

    private List<Book> convertToBookList(Map<String, Integer> mergedBooks) {
        return mergedBooks.entrySet().stream()
                .map(entry -> new Book(entry.getKey(), entry.getValue()))
                .toList();
    }
}