import { describe, it, expect } from "vitest";
import { fetchBooks, calculatePrice } from "../bookApi";

describe("bookApi - incremental tests", () => {
  it("fetchBooks returns a list of books", async () => {
    const books = await fetchBooks();

    expect(Array.isArray(books)).toBe(true);
    expect(books.length).toBeGreaterThan(0);
    expect(books[0]).toHaveProperty("title");
  });

  it("calculatePrice returns totalPrice and discountPrice", async () => {
    const result = await calculatePrice([
      { title: "Clean Code", quantity: 2 }
    ]);

    expect(result).toHaveProperty("bookList");
    expect(result.bookList.length).toBe(1);

    expect(result).toHaveProperty("totalPrice");
    expect(result).toHaveProperty("discountPrice");

    expect(result.totalPrice).toBeGreaterThan(0);
    expect(result.discountPrice).toBeLessThan(result.totalPrice);
  });
});
