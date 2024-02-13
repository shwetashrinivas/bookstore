import React, { useContext } from "react";
import Book from "../common/book.js";
import "./BookList.css";
import { BooksContext } from "./bookcontext/BooksProvider.js";

export default function BookList() {
  const { books } = useContext(BooksContext);

  return (
    <div className="books">
      {books.length ? (
        <div data-testid="list-books" className="listbooks">
          {books.map((book) => {
            return <Book book={book} key={book?.id} />;
          })}
        </div>
      ) : (
        <p data-testid="no-book">No books available!</p>
      )}
    </div>
  );
}
