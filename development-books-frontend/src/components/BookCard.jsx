import "./../styles/BookCard.css";

import {
  Card,
  CardMedia,
  CardContent,
  Typography,
  IconButton,
  Stack
} from "@mui/material";

import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

export default function BookCard({ book, quantity, increment, decrement }) {
  return (
    <Card className="book-card" elevation={4}>
      <CardMedia
        component="img"
        image={book.imageUrl}
        alt={book.title}
        className="book-card-img"
      />

      <CardContent>
        <Typography variant="h6" fontWeight="bold">
          {book.title}
        </Typography>

        <Typography variant="body2" color="text.secondary">
          Author: {book.author}
        </Typography>

        <Typography variant="body1" mt={1} fontWeight="bold">
          €{book.price}
        </Typography>

        <Stack direction="row" spacing={2} alignItems="center" mt={2}>
          <IconButton
            aria-label="decrement"
            onClick={() => decrement(book.title)}
            className="btn-minus"
            tooltip="Remove from Basket"
          >
            <RemoveCircleOutlineIcon fontSize="large" />
          </IconButton>


          <Typography className="book-qty">{quantity}</Typography>

          <IconButton
            aria-label="increment"
            onClick={() => increment(book.title)}
            className="btn-plus"
            tooltip="Add to Basket"
          >
            <AddCircleOutlineIcon fontSize="large" />
          </IconButton>
        </Stack>
      </CardContent>
    </Card>
  );
}
