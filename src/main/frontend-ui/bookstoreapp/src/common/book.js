import React from "react";
import "./book.css";
import { Link } from "react-router-dom";

export default function Book({ book }) {
  return (
    <div className="book-component" data-testid="book">
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
        by ${book?.author}
      </p>
      <p className="price" data-testid="price">
        RS ${book?.price}
      </p>
      <Link data-testid="add-to-card" className="button-style" to="/cart">
        Add to cart
      </Link>
    </div>
  );
}
