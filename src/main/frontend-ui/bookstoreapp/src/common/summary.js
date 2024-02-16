import React, { useContext } from "react";
import "./summary.css";
import { BooksContext } from "../books/bookcontext/BooksProvider.js";

export default function Summary() {
  const { cartItems } = useContext(BooksContext);
  const items = [];
  let finalPrice = 0;
  for (const book of cartItems) {
    finalPrice = finalPrice + book?.bookCount * book?.price;
    items.push(
      <tr key={book?.id}>
        <td> {book?.title} </td>
        <td> {book?.bookCount}</td>
        <td> {book?.bookCount * book?.price}</td>
      </tr>
    );
  }
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
        <button className="button">Proceed to buy</button>
      </div>
    </div>
  );
}
