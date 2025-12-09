package com.bnpp.kata.developmentbooks.service;

import com.bnpp.kata.developmentbooks.dto.Book;
import com.bnpp.kata.developmentbooks.dto.BookResponse;
import com.bnpp.kata.developmentbooks.mapper.BookMapper;
import com.bnpp.kata.developmentbooks.store.BookEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private static final double BOOK_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.00,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    private final BookMapper mapper;

    public List<BookResponse> getAllBooks() {
        log.info("Retrieving all available books");
        return Arrays.stream(BookEnum.values())
                .map(mapper::toResponse)
                .toList();
    }

    public double calculateBookPrice(List<Book> bookList) {
        List<String> allCopies = new ArrayList<>();
        for (Book book : bookList) {
            for (int i = 0; i < book.quantity(); i++) {
                allCopies.add(book.title());
            }
        }
        List<String> limitedCopies = allCopies.stream()
                .limit(8)
                .toList();

        Map<String, Long> counts = limitedCopies.stream()
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));

        List<Integer> groupSizes = new ArrayList<>();
        while (counts.values().stream().anyMatch(q -> q > 0)) {
            int uniqueTitles = 0;
            for (Map.Entry<String, Long> titleMap : counts.entrySet()) {
                long qty = counts.get(titleMap.getKey());
                if (qty > 0) {
                    uniqueTitles++;
                    counts.put(titleMap.getKey(), qty - 1);
                }
            }
            groupSizes.add(uniqueTitles);
        }
        optimizeGroupsWithBooks(groupSizes);
        double totalPrice = 0.0;
        for (int size : groupSizes) {
            double discount = DISCOUNTS.getOrDefault(size, 0.0);
            totalPrice += size * BOOK_PRICE * (1 - discount);
        }

        return totalPrice;
    }

    private void optimizeGroupsWithBooks(List<Integer> groups) {
        while (groups.contains(5) && groups.contains(3)) {
            groups.remove(Integer.valueOf(5));
            groups.remove(Integer.valueOf(3));
            groups.add(4);
            groups.add(4);
        }
    }
}