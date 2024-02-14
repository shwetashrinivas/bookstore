import React from "react";
import Summary from "../../common/summary.js";
import Cart from "../../common/cart.js";
import "./shopcart.css";

export default function shopcart() {
  return (
    <div className="shopping-cart">
      <div class="shopping-cart-header">
        <p className="header">Shopping Cart</p>
        <p className="price-header">Price</p>
      </div>
      <hr class="separator" />
      <div className="cart-summary">
        <Cart />
        <Summary />
      </div>
    </div>
  );
}
