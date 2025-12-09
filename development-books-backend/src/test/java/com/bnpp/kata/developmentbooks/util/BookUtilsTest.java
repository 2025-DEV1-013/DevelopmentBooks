package com.bnpp.kata.developmentbooks.util;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.exception.InvalidBookException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class BookUtilsTest {

    @Test
    @DisplayName("extractSortedCounts() → filters out null values")
    void testExtractSortedCounts_nullValue() {
        Map<String, Integer> input = new HashMap<>();
        input.put("clean code", null);
        List<Integer> result = BookUtils.extractSortedCounts(input);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("extractSortedCounts() → filters out zero or negative values")
    void testExtractSortedCounts_zeroOrNegative() {
        Map<String, Integer> input = new HashMap<>();
        input.put("clean code", 0);
        input.put("tdd", -3);
        List<Integer> result = BookUtils.extractSortedCounts(input);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("extractSortedCounts() → returns sorted positive values")
    void testExtractSortedCounts_positiveValues() {
        Map<String, Integer> input = new HashMap<>();
        input.put("a", 1);
        input.put("b", 4);
        input.put("c", 2);
        List<Integer> result = BookUtils.extractSortedCounts(input);
        assertEquals(List.of(4, 2, 1), result);
    }

    // ------------------------------
    // normalizeTitle()
    // ------------------------------
    @Test
    @DisplayName("normalizeTitle() → trims and lowercases valid text")
    void testNormalizeTitle_valid() {
        assertEquals("clean code", BookUtils.normalizeTitle("  Clean Code "));
    }

    @Test
    @DisplayName("normalizeTitle() → returns empty string for null or blank")
    void testNormalizeTitle_blank() {
        assertEquals("", BookUtils.normalizeTitle(null));
        assertEquals("", BookUtils.normalizeTitle(""));
        assertEquals("", BookUtils.normalizeTitle("   "));
    }

    // ------------------------------
    // mergeDuplicateTitles()
    // ------------------------------

    @Test
    @DisplayName("mergeDuplicateTitles() → merges and sums duplicate titles (case-insensitive)")
    void testMergeDuplicateTitles() {
        List<Book> list = List.of(
                new Book("Clean Code", 1),
                new Book("clean code", 2),
                new Book(" CLEAN CODE ", 3)
        );
        Map<String, Integer> map = BookUtils.mergeDuplicateTitles(list);
        assertEquals(1, map.size());
        assertEquals(6, map.get("clean code"));
    }

    // ------------------------------
    // extractSortedCounts()
    // ------------------------------
    @Test
    @DisplayName("extractSortedCounts() → filters nulls and non-positive numbers and sorts descending")
    void testExtractSortedCounts_valid() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.put("b", 0);
        map.put("c", -1);
        map.put("d", 5);
        map.put("e", null);
        List<Integer> result = BookUtils.extractSortedCounts(map);
        assertEquals(List.of(5, 3), result);
    }

    @Test
    @DisplayName("extractSortedCounts() → throws when map is null (100% branch coverage)")
    void testExtractSortedCounts_nullMap() {
        InvalidBookException ex =
                assertThrows(InvalidBookException.class, () -> BookUtils.extractSortedCounts(null));
        assertEquals("Merged books map must not be null", ex.getMessage());
    }

    // ------------------------------
    // validateBasket()
    // ------------------------------
    @Test
    @DisplayName("validateBasket() → throws when list is null")
    void testValidateBasket_null() {
        assertThrows(InvalidBookException.class, () -> BookUtils.validateBasket(null));
    }

    @Test
    @DisplayName("validateBasket() → throws when list is empty")
    void testValidateBasket_Empty() {
        List<Book> emptyList = List.of();
        assertThrows(InvalidBookException.class,
                () -> BookUtils.validateBasket(emptyList));
    }

    @Test
    @DisplayName("validateBasket() → throws when no book has quantity > 0")
    void testValidateBasket_noPositiveQuantity() {
        List<Book> items = List.of(
                new Book("A", 0),
                new Book("B", 0)
        );
        assertThrows(InvalidBookException.class, () -> BookUtils.validateBasket(items));
    }

    @Test
    @DisplayName("validateBasket() → succeeds when valid and contains at least one positive quantity")
    void testValidateBasket_success() {
        List<Book> items = List.of(
                new Book("A", 0),
                new Book("B", 2)
        );
        assertDoesNotThrow(() -> BookUtils.validateBasket(items));
    }

    // ------------------------------
    // validateBook() — private method via validateBasket()
    // ------------------------------

    @Test
    @DisplayName("validateBook() → throws when book is null (100% coverage of missing branch)")
    void testValidateBook_null() {
        List<Book> list = new ArrayList<>();
        list.add(null);

        InvalidBookException ex =
                assertThrows(InvalidBookException.class, () -> BookUtils.validateBasket(list));
        assertEquals("Book entry must not be null", ex.getMessage());
    }

    @Test
    @DisplayName("validateBook() → throws when title is null or empty")
    void testValidateBook_invalidTitle() {
        List<Book> list = List.of(new Book("   ", 1));

        InvalidBookException ex =
                assertThrows(InvalidBookException.class, () -> BookUtils.validateBasket(list));
        assertEquals("Book title must not be null or empty", ex.getMessage());
    }

    @Test
    @DisplayName("validateBook() → throws when quantity is negative")
    void testValidateBook_negativeQty() {
        List<Book> list = List.of(new Book("Clean Code", -5));

        InvalidBookException ex =
                assertThrows(InvalidBookException.class, () -> BookUtils.validateBasket(list));
        assertTrue(ex.getMessage().contains("must not be negative"));
    }

}