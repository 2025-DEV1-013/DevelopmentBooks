import "../styles/BasketSummary.css";

export default function BasketSummary({ basket }) {
  const entries = Object.entries(basket);

  if (entries.length === 0) {
    return <p>No books selected.</p>;
  }

  // More functionality will be added in later TDD commits
  return <div></div>;
}
