import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import "./search.css";
import { BooksContext } from "../bookcontext/BooksProvider.js";

export default function Search() {
  const { setBooks } = useContext(BooksContext);
  const [searchValue, setSearchValue] = useState("");
  useEffect(() => {
    const getSereachedBooks = setTimeout(() => {
      axios
        .get("http://localhost:8090/books/search", {
          params: {
            searchTerm: searchValue,
          },
        })
        .then((response) => {
          const { data } = response;
          console.log("response", data);
          setBooks([...data]);
        })
        .catch(() => {});
    }, 500);

    return () => clearTimeout(getSereachedBooks);
  }, [searchValue, setBooks]);

  return (
    <input
      placeholder="Search book"
      data-testid="search-bar"
      className="input-style"
      onChange={(event) => {
        setSearchValue(event.target.value);
      }}
    />
  );
}
