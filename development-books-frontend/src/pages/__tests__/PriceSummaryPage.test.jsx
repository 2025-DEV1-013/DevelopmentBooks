import { render, screen } from "@testing-library/react";
import PriceSummaryPage from "../PriceSummaryPage";
import { BrowserRouter } from "react-router-dom";
import * as api from "../../api/bookApi";
import { vi } from "vitest";

// Mock price API
vi.spyOn(api, "calculatePrice").mockResolvedValue({
  bookList: [{ title: "clean code", quantity: 2 }],
  totalPrice: 100,
  discountPrice: 90,
});

// Mock location state
vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual("react-router-dom");
  return {
    ...actual,
    useLocation: () => ({ state: { basket: { "Clean Code": 2 } } }),
    useNavigate: () => vi.fn(),
  };
});

describe("PriceSummaryPage", () => {
  it("renders price summary correctly", async () => {
    render(
      <BrowserRouter>
        <PriceSummaryPage />
      </BrowserRouter>
    );

    expect(await screen.findByText("Price Summary")).toBeInTheDocument();
    expect(await screen.findByText("Clean Code")).toBeInTheDocument();
    expect(screen.getByText("Total Price: €100")).toBeInTheDocument();
    expect(screen.getByText("Discounted Price: €90")).toBeInTheDocument();
  });
});
