import "../styles/Header.css";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";

export default function Header() {
  return (
    <AppBar position="static" className="header-bar" elevation={2}>
      <Toolbar className="header-toolbar">
        <Typography variant="h5" className="header-title">
          Book Price Calculator
        </Typography>

      </Toolbar>
    </AppBar>
  );
}
