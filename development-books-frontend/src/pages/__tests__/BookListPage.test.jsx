import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import BookListPage from "../BookListPage";

describe("BookListPage - single test", () => {
  it("renders the page title", () => {
    render(<BookListPage />);

    expect(screen.getByText("Available Books")).toBeInTheDocument();
  });
});
