import "../styles/BookListPage.css";

import { useEffect, useState } from "react";
import { fetchBooks } from "../api/bookApi";
import BookCard from "../components/BookCard";
import BasketSummary from "../components/BasketSummary";
import { useNavigate } from "react-router-dom";

import Header from "../components/Header";

import {
  Container,
  Grid,
  Typography,
  Button,
  Alert,
  Box,
  Paper,
} from "@mui/material";

export default function BookListPage() {
  const [books, setBooks] = useState([]);
  const [basket, setBasket] = useState({});
  const [error, setError] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetchBooks()
      .then(setBooks)
      .catch((err) => setError(err.message));
  }, []);

  const increment = (title) =>
    setBasket((prev) => ({ ...prev, [title]: (prev[title] || 0) + 1 }));

  const decrement = (title) =>
    setBasket((prev) => {
      const copy = { ...prev };
      if (copy[title] > 1) copy[title]--;
      else delete copy[title];
      return copy;
    });

  return (
    <>
      <Header />

      <Container
      sx={{
      mt: 4,
      maxWidth: "1350px !important"
      }}
      >

        <Typography variant="h3" className="page-title">
          📚 Development Books
        </Typography>

        {error && <Alert severity="error">{error}</Alert>}

        <Grid container spacing={3} className="books-grid">
          {books.map((book) => (
            <Grid item xs={12} sm={6} md={4} key={book.id}>
              <BookCard
                book={book}
                quantity={basket[book.title] || 0}
                increment={increment}
                decrement={decrement}
              />
            </Grid>
          ))}
        </Grid>

        <Paper elevation={3} sx={{ mt: 4, p: 3 }}>
          <Typography variant="h5">🧺 Basket Summary</Typography>

          <BasketSummary basket={basket} />

          <Box className="checkout-btn">
            <Button
              variant="contained"
              size="large"
              disabled={Object.keys(basket).length === 0}
              onClick={() => navigate("/summary", { state: { basket } })}
            >
              Proceed to Checkout →
            </Button>
          </Box>
        </Paper>
      </Container>
    </>
  );
}
