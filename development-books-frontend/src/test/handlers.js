import { http, HttpResponse } from "msw";

// Temporary placeholder mock responses for TDD
export const handlers = [
  // GET /api/books/getbooks
  http.get("/api/books/getbooks", () => {
    return HttpResponse.json([
      {
        id: 1,
        title: "Clean Code",
        author: "Robert C. Martin",
        price: 50,
        imageUrl: "clean-code.jpg"
      },
      {
        id: 2,
        title: "Refactoring",
        author: "Martin Fowler",
        price: 45,
        imageUrl: "refactoring.jpg"
      }
    ]);
  }),

  // POST /api/books/price/calculate
  http.post("/api/books/price/calculate", async ({ request }) => {
    const { bookList } = await request.json();

    // Simple fake calculation for now
    const totalPrice = bookList.reduce(
      (sum, item) => sum + item.quantity * 50,
      0
    );
    const discountPrice = totalPrice * 0.9;

    return HttpResponse.json({
      bookList,
      totalPrice,
      discountPrice
    });
  }),
];
