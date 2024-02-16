import React, { useContext } from "react";
import "./book.css";
import { useNavigate } from "react-router-dom";
import { BooksContext } from "../books/bookcontext/BooksProvider.js";

export default function Book({ book }) {
  const navigate = useNavigate();
  const { cartItems, setCartItems } = useContext(BooksContext);

  const updateCart = () => {
    if (cartItems?.length) {
      const obj = cartItems?.find((b) => b?.id === book?.id);
      if (obj?.id) {
        let a = [];
        for (var item = 0; item < cartItems?.length; item++) {
          if (cartItems[item]?.id === obj?.id) {
            a.push({
              ...cartItems[item],
              bookCount: cartItems[item]?.bookCount + 1,
            });
          } else {
            a.push(cartItems[item]);
          }
        }
        setCartItems([...a]);
      } else {
        setCartItems((prev) => [...prev, { ...book, bookCount: 1 }]);
      }
    } else {
      setCartItems((prev) => [...prev, { ...book, bookCount: 1 }]);
    }
    navigate("/cart");
  };

  const goToBookDetails = () => {
    navigate("/bookDetails", { state: { id: book?.id } });
  };

  return (
    <div className="book-component" data-testid="book">
      <div className="book-style-div" onClick={goToBookDetails}>
        <img
          data-testid="coverImageUrl"
          src={`https://res.cloudinary.com/www-thepencilapp-com/image/upload/q_auto,f_auto/books/covers/${book?.coverImageUrl}`}
          alt="abc"
          height={240}
          width={180}
        />
        <p className="title" data-testid="title">
          {book?.title}
        </p>
        <p className="author" data-testid="author">
          by {book?.author}
        </p>
        <p className="price" data-testid="price">
          ₹ {book?.price}
        </p>
      </div>
      <button
        data-testid="add-to-card"
        className="button-style"
        onClick={() => {
          updateCart();
        }}
      >
        Add to cart
      </button>
    </div>
  );
}
