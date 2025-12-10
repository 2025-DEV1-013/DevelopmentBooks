const BASE_URL = "/api/books";

export async function fetchBooks() {
  const response = await fetch(`${BASE_URL}/getbooks`);

  if (!response.ok) {
    throw new Error("Failed to fetch books");
  }

  return response.json();
}

export async function calculatePrice(bookList) {
  const response = await fetch(`${BASE_URL}/price/calculate`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ bookList })
  });

  if (!response.ok) {
    throw new Error("Failed to calculate price");
  }

  return response.json();
}
