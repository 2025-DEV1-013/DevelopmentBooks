package com.bnpp.kata.developmentbooks.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscountUtilsTest {

    @Test
    @DisplayName("computeOptimalPrice() → returns 0.0 for empty list")
    void testComputeOptimalPrice_emptyList() {
        double result = DiscountUtils.computeOptimalPrice(List.of());
        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("computeOptimalPrice() → single count (1 book) → no discount")
    void testComputeOptimalPrice_singleBook() {
        double result = DiscountUtils.computeOptimalPrice(List.of(1));
        assertEquals(50.0, result);
    }

    @Test
    @DisplayName("computeOptimalPrice() → two distinct books → 5% discount")
    void testComputeOptimalPrice_twoBooks() {
        double result = DiscountUtils.computeOptimalPrice(List.of(1, 1));
        assertEquals(95.0, result);
    }

    @Test
    @DisplayName("computeOptimalPrice() → three distinct books → 10% discount")
    void testComputeOptimalPrice_threeBooks() {
        double result = DiscountUtils.computeOptimalPrice(List.of(1, 1, 1));
        assertEquals(135.0, result);
    }

    @Test
    @DisplayName("computeOptimalPrice() → complex scenario tests recursion (2 copies of 3 titles)")
    void testComputeOptimalPrice_recursive() {
        double result = DiscountUtils.computeOptimalPrice(List.of(2, 2, 2));
        assertEquals(270.0, result);
    }

    @Test
    @DisplayName("computeOptimalPrice() → normalize filters out non-positive numbers")
    void testNormalizationFilters() {
        double result = DiscountUtils.computeOptimalPrice(List.of(3, -1, 0, 2));
        assertTrue(result > 200 && result < 260);
    }

    @Test
    @DisplayName("computeOptimalPrice() → ensures caching is used (same input twice)")
    void testCacheHit() {
        double first = DiscountUtils.computeOptimalPrice(List.of(1, 1, 1));
        double second = DiscountUtils.computeOptimalPrice(List.of(1, 1, 1));
        assertEquals(first, second);
    }

}