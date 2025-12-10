import { describe, it, expect } from "vitest";
import { fetchBooks } from "../bookApi";

describe("bookApi - single test", () => {
  it("fetchBooks returns a list of books", async () => {
    const books = await fetchBooks();

    expect(Array.isArray(books)).toBe(true);
    expect(books.length).toBeGreaterThan(0);
    expect(books[0]).toHaveProperty("title");
  });
});

