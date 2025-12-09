package com.bnpp.kata.developmentbooks.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("handleIllegalArgumentException → should return BAD_REQUEST")
    void testIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Bad arg");

        ResponseEntity<Map<String, String>> response =
                handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad arg", response.getBody().get("error"));
    }

}