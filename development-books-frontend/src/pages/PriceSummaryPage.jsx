import { useLocation, useNavigate } from "react-router-dom";
import { calculatePrice } from "../api/bookApi";
import { useEffect, useState } from "react";

import Header from "../components/Header";

import {
  Container,
  Typography,
  List,
  ListItem,
  ListItemText,
  Button,
  Alert,
  Divider,
  Paper
} from "@mui/material";

import ReceiptLongIcon from "@mui/icons-material/ReceiptLongOutlined";
import CheckCircleIcon from "@mui/icons-material/CheckCircleOutline";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";

// Import external CSS
import "../styles/PriceSummaryPage.css";

export default function PriceSummaryPage() {
  const { state } = useLocation();
  const navigate = useNavigate();
  const basket = state?.basket || {};

  const [result, setResult] = useState(null);
  const [error, setError] = useState("");

  function toCamelCase(text) {
    if (!text) return "";
    return text
      .toLowerCase()
      .replaceAll(/\b\w/g, (char) => char.toUpperCase());
  }

  useEffect(() => {
    const bookList = Object.entries(basket).map(([title, quantity]) => ({
      title,
      quantity,
    }));

    calculatePrice(bookList)
      .then(setResult)
      .catch(err => setError(err.message));
  }, []);

  if (error) return <Alert severity="error">{error}</Alert>;
  if (!result) return <Typography>Calculating price...</Typography>;

  return (
      <>
        {/* ---- BNP Header ---- */}
        <Header />

        {/* ---- Main Page Content ---- */}
        <Container sx={{ mt: 4 }}>
      <Paper elevation={6} className="summary-container">

        {/* Header */}
        <div className="summary-header">
          <ReceiptLongIcon className="summary-header-icon" />
          <Typography variant="h3" fontWeight="bold">
            Price Summary
          </Typography>
        </div>

        <Divider sx={{ my: 2 }} />

        <List>
          {result.bookList.map((b, i) => (
            <ListItem key={i}>
              <ListItemText
                primary={
                  <Typography variant="h6">
                    <b>{toCamelCase(b.title)}</b>
                  </Typography>
                }
                secondary={`Quantity: ${b.quantity}`}
              />
            </ListItem>
          ))}
        </List>

        <Divider sx={{ my: 2 }} />

        {/* Total Price */}
        <Typography className="total-price">
          Total Price: €{result.totalPrice}
        </Typography>

        {/* Highlighted Discount Price */}
        <div className="discount-box">
          <CheckCircleIcon className="discount-icon" />
          <Typography className="discount-price">
            Discounted Price: €{result.discountPrice}
          </Typography>
        </div>

        <Button
          variant="contained"
          sx={{ mt: 3 }}
          startIcon={<ArrowBackIcon />}
          onClick={() => navigate("/")}
        >
          Back to Book List
        </Button>

      </Paper>
    </Container>
    </>
  );
}
