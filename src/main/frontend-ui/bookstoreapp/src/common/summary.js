import React, { useContext } from "react";
import "./summary.css";
import axios from "axios";
import { BooksContext } from "../books/bookcontext/BooksProvider.js";

export default function Summary({ setIsModal }) {
  const { cartItems } = useContext(BooksContext);
  const items = [];
  const ids = [];
  let finalPrice = 0;
  for (const book of cartItems) {
    ids.push(book?.id);
    finalPrice = finalPrice + book?.bookCount * book?.price;
    items.push(
      <tr key={book?.id}>
        <td> {book?.title} </td>
        <td> {book?.bookCount}</td>
        <td> {book?.bookCount * book?.price}</td>
      </tr>
    );
  }
  const buyBooks = () => {
    axios
      .post("http://localhost:8090/orders/buy", [10])
      .then((response) => {
        if (true) {
          setIsModal(true);
        }
      })
      .catch(() => {});
  };
  return (
    <div className="summary">
      <span className="summary-text">Summary</span>
      {cartItems.length ? (
        <>
          <div data-testid="list-books" className="summary-details">
            <table>
              <thead>
                <tr>
                  <th> Title</th>
                  <th> Count</th>
                  <th> Total price</th>
                </tr>
              </thead>
              <tbody>{items}</tbody>
            </table>
          </div>
        </>
      ) : (
        <p data-testid="no-book">No books are in cart!</p>
      )}
      <sapn className="value">Total order value: ₹ {finalPrice}</sapn>
      <div className="button-container">
        <button
          className="button"
          onClick={() => {
            buyBooks();
          }}
        >
          Proceed to buy
        </button>
      </div>
    </div>
  );
}
