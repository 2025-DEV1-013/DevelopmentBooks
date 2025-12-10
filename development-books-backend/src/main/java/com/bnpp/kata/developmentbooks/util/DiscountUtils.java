package com.bnpp.kata.developmentbooks.util;

import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public final class DiscountUtils {

    private static final double BOOK_UNIT_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNT_RATE_BY_GROUP_SIZE  = Map.of(
            1, 0.00,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    private DiscountUtils() {}


    public static double computeOptimalPrice(List<Integer> counts) {
        return calculateOptimalPrice(counts, new HashMap<>());
    }

    private static double calculateOptimalPrice(List<Integer> counts, Map<String, Double> memoCache) {

        List<Integer> normalizedCounts  = normalizeCounts(counts);

        if (normalizedCounts .isEmpty()) {
            return 0.0;
        }

        String cacheKey  = normalizedCounts.toString();

        if (memoCache.containsKey(cacheKey)) {
            return memoCache.get(cacheKey);
        }

        int maxPossibleGroupSize = normalizedCounts.size();

        double optimalPrice  =
                IntStream.rangeClosed(1, maxPossibleGroupSize)
                        .mapToDouble(size -> computeCost(size, normalizedCounts , memoCache))
                        .min()
                        .orElse(Double.MAX_VALUE);

        memoCache.put(cacheKey, optimalPrice);
        return optimalPrice;
    }

    private static List<Integer> normalizeCounts(List<Integer> counts) {
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
        return groupCost + calculateOptimalPrice(nextState, cache);
    }

    private static double calculateGroupCost(int groupSize) {
        double discount = DISCOUNT_RATE_BY_GROUP_SIZE.getOrDefault(groupSize, 0.0);
        return groupSize * BOOK_UNIT_PRICE * (1 - discount);
    }
}
