package com.bnpp.kata.developmentbooks.controller;


import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}