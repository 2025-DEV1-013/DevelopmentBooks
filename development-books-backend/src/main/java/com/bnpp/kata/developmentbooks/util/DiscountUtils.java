package com.bnpp.kata.developmentbooks.util;

import java.util.*;
import java.util.stream.IntStream;


public final class DiscountUtils {

    private static final double BOOK_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.00,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    private DiscountUtils() {}


    public static double computeOptimalPrice(List<Integer> counts) {
        return compute(counts, new HashMap<>());
    }

    private static double compute(List<Integer> counts, Map<String, Double> cache) {

        List<Integer> normalized = normalize(counts);

        if (normalized.isEmpty()) {
            return 0.0;
        }

        String key = normalized.toString();

        Double cached = cache.get(key);
        if (Objects.nonNull(cached)) {
            return cached;
        }

        int maxGroupSize = normalized.size();

        double bestPrice =
                IntStream.rangeClosed(1, maxGroupSize)
                        .mapToDouble(size -> computeCost(size, normalized, cache))
                        .min()
                        .orElse(Double.MAX_VALUE);

        cache.put(key, bestPrice);
        return bestPrice;
    }

    private static List<Integer> normalize(List<Integer> counts) {
        return counts.stream()
                .filter(count -> count > 0)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    private static double computeCost(int groupSize, List<Integer> state, Map<String, Double> cache) {

        // Reduce quantities for the first 'groupSize' books
        List<Integer> nextState = IntStream.range(0, state.size())
                .map(i -> i < groupSize ? state.get(i) - 1 : state.get(i))
                .boxed()
                .toList();

        double groupCost = calculateGroupCost(groupSize);
        return groupCost + compute(nextState, cache);
    }

    private static double calculateGroupCost(int groupSize) {
        double discount = DISCOUNTS.getOrDefault(groupSize, 0.0);
        return groupSize * BOOK_PRICE * (1 - discount);
    }
}
