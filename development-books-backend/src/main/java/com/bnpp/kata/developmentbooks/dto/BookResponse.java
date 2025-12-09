package com.bnpp.kata.developmentbooks.dto;

public record BookResponse(int id,
                           String title,
                           String author,
                           int year,
                           double price) {
}
