package com.bnpp.kata.developmentbooks.dto;

import java.util.List;

public record BookBasketRequest(
        List<Book> bookList
) {
}
