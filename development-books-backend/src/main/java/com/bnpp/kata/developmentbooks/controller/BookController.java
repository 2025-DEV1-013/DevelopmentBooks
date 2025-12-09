package com.bnpp.kata.developmentbooks.controller;


import com.bnpp.kata.developmentbooks.dto.BookBasketRequest;
import com.bnpp.kata.developmentbooks.dto.BookPriceResponse;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "APIs for fetching development books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Fetch all books", description = "Returns all development books stored in enum")
    @GetMapping("/getbooks")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        log.info("Hit endpoint : GET /api/books/getbooks");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(
            summary = "Calculate total price for the book basket",
            description = "Evaluates the provided list of books, applies discounts and pricing rules, and returns the final computed price."
    )
    @PostMapping("/price/calculate")
    public ResponseEntity<BookPriceResponse> calculatePrice(@RequestBody BookBasketRequest request) {
        log.info("Hit endpoint: POST /api/books/price/calculate  body={}", request);
        BookPriceResponse bookPriceResponse = bookService.calculateBookPrice(request.bookList());
        return ResponseEntity.ok(bookPriceResponse);
    }
}