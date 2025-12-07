package com.bnpp.kata.developmentbooks.store;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEnumTest {

    @Test
    @DisplayName("BookEnum should contain exactly 5 books")
    void testEnumCount() {
        assertEquals(5, BookEnum.values().length,
                "Enum must contain exactly 5 predefined books");
    }

}