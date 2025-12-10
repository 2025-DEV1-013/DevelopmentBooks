import "../styles/BasketSummary.css";

import { List, ListItem, ListItemText, Typography } from "@mui/material";

export default function BasketSummary({ basket }) {
  const entries = Object.entries(basket);

  if (entries.length === 0) {
    return <Typography>No books selected.</Typography>;
  }

  return (
    <List className="basket-list">
      {entries.map(([title, quantity]) => (
        <ListItem key={title}>
          <ListItemText
            primary={
              <Typography className="basket-item-text">
                <b>{title}</b> — Quantity: {quantity}
              </Typography>
            }
          />
        </ListItem>
      ))}
    </List>
  );
}
