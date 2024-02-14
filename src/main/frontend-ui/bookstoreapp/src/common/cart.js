import React from "react";
import "./cart.css";

function cart() {
  return (
    <div class="shopping-cart-container">
      <div data-testid="shopping-cart" className="cart">
        <div className="cart-img">
          <img
            data-testid="coverImageUrl"
            src={`https://res.cloudinary.com/www-thepencilapp-com/image/upload/q_auto,f_auto/books/covers/1586_f1601742134.png`}
            alt="abc"
            height={240}
            width={180}
          />
          <div className="cart-details">
            <span data-testid="title">Cosmic urban connect</span>
            <span data-testid="author" className="author">
              by Sundaran
            </span>
          </div>
        </div>

        <div className="price">
          <span data-testid="price">RS 400.0</span>
        </div>
      </div>
    </div>
  );
}

export default cart;
