import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import BookListPage from "../BookListPage";
import { BrowserRouter } from "react-router-dom";
import * as api from "../../api/bookApi";
import { describe, it, expect, vi } from "vitest";

vi.spyOn(api, "fetchBooks").mockResolvedValue([
  {
    id: 1,
    title: "Clean Code",
    author: "Robert Martin",
    price: 50,
    imageUrl: "img.png",
  },
]);

function renderPage() {
  return render(
    <BrowserRouter>
      <BookListPage />
    </BrowserRouter>
  );
}

describe("BookListPage.jsx", () => {
  it("loads and displays books", async () => {
    renderPage();
    expect(await screen.findByText("Clean Code")).toBeInTheDocument();
  });

  it("increments and decrements quantity", async () => {
    renderPage();

    await screen.findByText("Clean Code");

    // ---- Click increment ----
    const addBtn = screen.getByLabelText("increment");
    fireEvent.click(addBtn);

    expect(screen.getByText("1")).toBeInTheDocument();

    // ---- Click decrement ----
    const minusBtn = screen.getByLabelText("decrement");
    fireEvent.click(minusBtn);

    await waitFor(() =>
      expect(screen.queryByText("1")).not.toBeInTheDocument()
    );
  });

  it("checkout button is disabled when basket empty", async () => {
    renderPage();

    const checkoutBtn = await screen.findByText("Proceed to Checkout →");

    expect(checkoutBtn).toBeDisabled();
  });

  it("checkout button becomes enabled after adding a book", async () => {
    renderPage();

    await screen.findByText("Clean Code");

    // Add one book
    const addBtn = screen.getByLabelText("increment");
    fireEvent.click(addBtn);

    const checkoutBtn = screen.getByText("Proceed to Checkout →");
    expect(checkoutBtn).toBeEnabled();
  });
});
