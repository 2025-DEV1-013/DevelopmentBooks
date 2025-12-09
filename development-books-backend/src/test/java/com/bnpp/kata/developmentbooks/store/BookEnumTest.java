package com.bnpp.kata.developmentbooks.store;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEnumTest {

    @Test
    @DisplayName("BookEnum should contain exactly 5 books")
    void testEnumToCount5Books() {
        assertEquals(5, BookEnum.values().length,
                "Enum must contain exactly 5 predefined books");
    }

    @Test
    @DisplayName("Record-based BookEnum should expose correct book metadata")
    void testBookEnumRecordMapping() {
        BookEnum book = BookEnum.CLEAN_CODE;

        assertEquals(1, book.id);
        assertEquals("Clean Code", book.title);
        assertEquals("Robert Martin", book.author);
        assertEquals(2008, book.year);
        assertEquals(50.0, book.price);
        assertEquals("https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCode.png",
                book.imageUrl);
    }

}