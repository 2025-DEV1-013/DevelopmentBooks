package com.bnpp.kata.developmentbooks.util;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.exception.InvalidBookException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BookUtils {

    private BookUtils() {
    }

    public static String normalizeTitle(String title) {
        return StringUtils.hasText(title) ? title.trim().toLowerCase() : "";
    }

    public static Map<String, Integer> mergeDuplicateTitles(List<Book> items) {
        validateBasket(items);

        return items.stream()
                .collect(Collectors.toMap(
                        item -> normalizeTitle(item.title()),
                        Book::quantity,
                        Integer::sum
                ));
    }


    public static List<Integer> extractSortedCounts(Map<String, Integer> merged) {
        if (merged == null) {
            throw new InvalidBookException("Merged books map must not be null");
        }

        return merged.values().stream()
                .filter(qty -> qty != null && qty > 0)
                .sorted(Comparator.reverseOrder())
                .toList();
    }


    public static void validateBasket(List<Book> items) {
        if (CollectionUtils.isEmpty(items)) {
            throw new InvalidBookException("Basket must not be null or empty");
        }

        boolean containsPositiveQuantity = false;

        for (Book item : items) {
            validateBook(item);

            if (item.quantity() > 0) {
                containsPositiveQuantity = true;
            }
        }

        if (!containsPositiveQuantity) {
            throw new InvalidBookException("Basket must contain at least one book with quantity > 0");
        }
    }

    private static void validateBook(Book book) {
        if (Objects.isNull(book)) {
            throw new InvalidBookException("Book entry must not be null");
        }

        if (!StringUtils.hasText(book.title())) {
            throw new InvalidBookException("Book title must not be null or empty");
        }

        if (book.quantity() < 0) {
            throw new InvalidBookException(
                    "Quantity for book '%s' must not be negative".formatted(book.title().trim())
            );
        }
    }
}
