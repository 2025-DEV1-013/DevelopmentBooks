import { render, screen, fireEvent } from "@testing-library/react";
import BookCard from "../BookCard";
import { describe, it, expect, vi } from "vitest";

const mockBook = {
  title: "Clean Code",
  author: "Robert Martin",
  price: 50,
  imageUrl: "test-image.png",
};

describe("BookCard Component", () => {
  it("renders book details", () => {
    render(
      <BookCard
        book={mockBook}
        quantity={2}
        increment={() => {}}
        decrement={() => {}}
      />
    );

    expect(screen.getByText("Clean Code")).toBeInTheDocument();
    expect(screen.getByText("Author: Robert Martin")).toBeInTheDocument();
    expect(screen.getByText("€50")).toBeInTheDocument();
    expect(screen.getByRole("img")).toHaveAttribute("src", "test-image.png");
  });

  it("calls increment and decrement when buttons are clicked", () => {
    const inc = vi.fn();
    const dec = vi.fn();

    render(
      <BookCard
        book={mockBook}
        quantity={1}
        increment={inc}
        decrement={dec}
      />
    );

    fireEvent.click(screen.getByLabelText("increment"));
    expect(inc).toHaveBeenCalledWith("Clean Code");

    fireEvent.click(screen.getByLabelText("decrement"));
    expect(dec).toHaveBeenCalledWith("Clean Code");
  });

  it("displays the quantity", () => {
    render(
      <BookCard
        book={mockBook}
        quantity={5}
        increment={() => {}}
        decrement={() => {}}
      />
    );

    expect(screen.getByText("5")).toBeInTheDocument();
  });
});
