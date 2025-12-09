package com.bnpp.kata.developmentbooks.dto;

import java.util.List;

public record BookPriceResponse(
        List<Book> bookList,
        double totalPrice,
        double discountPrice
) {
}
