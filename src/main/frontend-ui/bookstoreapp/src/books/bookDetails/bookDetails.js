/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useContext } from "react";
import "./bookDetails.css";
import axios from "axios";
import { BooksContext } from "../bookcontext/BooksProvider.js";
import { useLocation } from "react-router-dom";

export default function BookDetails() {
  const { bookDetails, setBookDetails } = useContext(BooksContext);
  const location = useLocation();

  useEffect(() => {
    const getBooksDetails = async () =>
      await axios.get(
        `http://localhost:8090/books/details/${location?.state?.id}`
      );
    getBooksDetails()
      .then(({ data }) => {
        setBookDetails(data);
      })
      .catch(() => {});
  }, []);

  return (
    <div class="BookDetails-container">
      <div data-testid="BookDetails-cart" className="BookDetails-cart">
        <div className="cart-img">
          <img
            data-testid="coverImageUrl"
            src={`https://res.cloudinary.com/www-thepencilapp-com/image/upload/q_auto,f_auto/books/covers/${bookDetails?.coverImageUrl}`}
            alt="abc"
            height={250}
            width={200}
          />
        </div>
        <div className="Book-details">
          <span data-testid="title" className="book-title">
            {bookDetails?.title}
          </span>
          <span data-testid="author" className="book-author">
            by {bookDetails?.author}
          </span>
          <span data-testid="description" className="description">
            {bookDetails?.description}
          </span>
          <span data-testid="rating" className="rating">
            Rating: {bookDetails?.rating}/5
          </span>
          <span data-testid="price" className="book-price">
            ₹ {bookDetails?.price}
          </span>
        </div>
      </div>
    </div>
  );
}
