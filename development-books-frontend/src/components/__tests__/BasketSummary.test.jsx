import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import BasketSummary from "../BasketSummary";

describe("BasketSummary Component - single test", () => {
  it("shows empty message when no books selected", () => {
    render(<BasketSummary basket={{}} />);

    expect(screen.getByText("No books selected.")).toBeInTheDocument();
  });
});
