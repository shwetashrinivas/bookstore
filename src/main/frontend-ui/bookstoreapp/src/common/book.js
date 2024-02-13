import React from "react";
import "./book.css";

export default function Book({ book }) {
  return (
    <div className="book-component" data-testid="book">
      <img
        data-testid="coverImageUrl"
        src="https://res.cloudinary.com/www-thepencilapp-com/image/upload/q_auto,f_auto/books/covers/1586_f1601742134.png"
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
    </div>
  );
}
