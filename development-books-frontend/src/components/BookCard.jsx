import "../styles/BookCard.css";

export default function BookCard({ book }) {
  return (
    <div className="book-card">
      <img
        src={book.imageUrl}
        alt={book.title}
        className="book-image"
      />

      <h2 className="book-title">{book.title}</h2>
      <p className="book-author">{book.author}</p>
      <p className="book-price">€{book.price}</p>
    </div>
  );
}
