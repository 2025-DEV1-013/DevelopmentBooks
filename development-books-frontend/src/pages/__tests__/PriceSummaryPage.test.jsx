import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import PriceSummaryPage from "../PriceSummaryPage";

describe("PriceSummaryPage - single test", () => {
  it("renders the page title", () => {
    render(<PriceSummaryPage />);

    expect(screen.getByText("Price Summary")).toBeInTheDocument();
  });
});
