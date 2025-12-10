const BASE_URL = "/api/books";

export async function fetchBooks() {
  const response = await fetch(`${BASE_URL}/getbooks`);

  if (!response.ok) {
    throw new Error("Failed to fetch books");
  }

  return response.json();
}
