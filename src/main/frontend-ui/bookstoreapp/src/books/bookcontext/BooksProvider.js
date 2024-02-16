import React, { createContext, useState, useEffect } from "react";
import axios from "axios";

export const BooksContext = createContext();

const BooksProvider = ({ children }) => {
  const [books, setBooks] = useState({
    books: [],
  });

  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    const getBooks = async () => await axios.get("http://localhost:8090/books");
    getBooks()
      .then(({ data }) => {
        setBooks([...data]);
      })
      .catch(() => {});
  }, []);

  return (
    <BooksContext.Provider
      value={{
        setBooks,
        books,
        cartItems,
        setCartItems,
      }}
    >
      {children}
    </BooksContext.Provider>
  );
};

export default BooksProvider;
