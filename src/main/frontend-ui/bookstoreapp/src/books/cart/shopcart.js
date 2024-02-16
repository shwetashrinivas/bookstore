import React, { useContext, useState } from "react";
import Summary from "../../common/summary.js";
import Cart from "../../common/cart.js";
import { BooksContext } from "../bookcontext/BooksProvider.js";
import "./shopcart.css";
import Popup from "../../common/popup.js";

export default function Shopcart() {
  const { cartItems, setCartItems } = useContext(BooksContext);
  const [isModal, setIsModal] = useState(false);
  const updateCart = (book, count) => {
    if (count <= 0) {
      setCartItems([...cartItems?.filter((item) => item?.id !== book?.id)]);
    } else {
      let items = [];
      for (var i = 0; i < cartItems?.length; i++) {
        if (cartItems[i]?.id === book?.id) {
          items.push({
            ...cartItems[i],
            bookCount: count,
          });
        } else {
          items.push(cartItems[i]);
        }
      }
      setCartItems([...items]);
    }
  };

  return (
    <div className="shopping-cart">
      <div className="sub-container">
        <div class="shopping-cart-header">
          <p className="header">Shopping Cart</p>
        </div>
        <hr className="separator" />

        {cartItems.length ? (
          <>
            <div data-testid="list-books" className="cartbooks">
              {cartItems.map((book) => {
                return (
                  <div className="cart-summary">
                    <Cart book={book} updateCart={updateCart} />
                  </div>
                );
              })}
            </div>
          </>
        ) : (
          <p data-testid="no-book">No books are in cart!</p>
        )}
      </div>
      {cartItems.length ? <Summary setIsModal={setIsModal} /> : null}
      {isModal ? <Popup isModal={isModal} setIsModal={setIsModal} /> : null}
    </div>
  );
}
