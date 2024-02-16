import React from "react";
import { FaStar } from "react-icons/fa";
import { AiFillMinusSquare, AiFillPlusSquare } from "react-icons/ai";
import "./cart.css";

function Cart({ book, updateCart }) {
  const GRADES = ["Poor", "Fair", "Good", "Very good", "Excellent"];

  return (
    <div data-testid="shopping-cart" className="cart">
      <div className="details">
        <img
          data-testid="coverImageUrl"
          src={`https://res.cloudinary.com/www-thepencilapp-com/image/upload/q_auto,f_auto/books/covers/${book?.coverImageUrl}`}
          alt="abc"
          height={200}
          width={120}
        />
        <div className="cart-details">
          <span data-testid="title">{book?.title}</span>
          <span data-testid="author" className="author">
            by {book?.author}
          </span>
          <div className="price">
            <span data-testid="price"> ₹ {book?.price}</span>
          </div>
          <div className="stars">
            {GRADES.map((grade, index) =>
              index + 1 <= book?.rating ? (
                <FaStar color="#ffe802" />
              ) : (
                <FaStar />
              )
            )}
          </div>
        </div>
      </div>

      <div className="increment-Count">
        <button
          className="btn"
          onClick={() => {
            if (book?.bookCount >= 1) {
              updateCart(book, book?.bookCount - 1);
            }
          }}
        >
          <AiFillMinusSquare size={30} />
        </button>
        <div className="Count">{book?.bookCount}</div>
        <button
          className="btn"
          onClick={() => {
            updateCart(book, book?.bookCount + 1);
          }}
        >
          <AiFillPlusSquare size={30} />
        </button>
      </div>
    </div>
  );
}

export default Cart;
