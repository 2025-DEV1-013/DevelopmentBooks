import { render, screen } from "@testing-library/react";
import BasketSummary from "../BasketSummary";
import { describe, it, expect } from "vitest";

describe("BasketSummary Component", () => {
  it("shows 'No books selected' when empty", () => {
    render(<BasketSummary basket={{}} />);

    expect(screen.getByText("No books selected.")).toBeInTheDocument();
  });

  it("renders basket items", () => {
    const basket = { "Clean Code": 2, "TDD": 1 };

    render(<BasketSummary basket={basket} />);

    expect(screen.getByText(/Clean Code/i)).toBeInTheDocument();
    expect(screen.getByText(/Quantity: 2/i)).toBeInTheDocument();
    expect(screen.getByText(/TDD/i)).toBeInTheDocument();
    expect(screen.getByText(/Quantity: 1/i)).toBeInTheDocument();
  });
});
