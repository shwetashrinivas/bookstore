import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { IoIosCart } from "react-icons/io";
import { BooksContext } from "../bookcontext/BooksProvider.js";
import "./navbar.css";

export default function Navbar() {
  const { cartItems } = useContext(BooksContext);
  let totalBookCount = 0;
  cartItems.forEach((element) => {
    totalBookCount = totalBookCount + element?.bookCount;
  });
  return (
    <nav className="nav-main">
      <div className="nav-submain">
        <Link to="/" className="link">
          <span className="home">Phantom Book Store</span>
        </Link>
        <div className="basket">
          <Link to="/cart" className="link cart-img">
            <IoIosCart size={26} color="#2286Be" />
          </Link>
          <span>{`(${totalBookCount || 0})`}</span>
        </div>
      </div>
    </nav>
  );
}
