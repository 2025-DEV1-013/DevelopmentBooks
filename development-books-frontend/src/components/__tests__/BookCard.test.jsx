import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import BookCard from "../BookCard";

describe("BookCard Component - single test", () => {
  it("renders book details", () => {
    const book = {
      title: "Clean Code",
      author: "Robert C. Martin",
      price: 50,
      imageUrl: "clean-code.jpg"
    };

    render(
      <BookCard
        book={book}
        quantity={0}
        increment={() => {}}
        decrement={() => {}}
      />
    );

    expect(screen.getByText("Clean Code")).toBeInTheDocument();
    expect(screen.getByText("Robert C. Martin")).toBeInTheDocument();
    expect(screen.getByText("€50")).toBeInTheDocument();

    const img = screen.getByAltText("Clean Code");
    expect(img).toBeInTheDocument();
    expect(img).toHaveAttribute("src", "clean-code.jpg");
  });

it("calls increment handler when + button is clicked", () => {
  const book = {
    title: "Clean Code",
    author: "Robert C. Martin",
    price: 50,
    imageUrl: "clean-code.jpg"
  };

  const increment = vi.fn();

  render(
    <BookCard
      book={book}
      quantity={0}
      increment={increment}
      decrement={() => {}}
    />
  );

  const plusBtn = screen.getByText("+");
  plusBtn.click();

  expect(increment).toHaveBeenCalledWith("Clean Code");
});

});
