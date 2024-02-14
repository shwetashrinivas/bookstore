import React from "react";
import { Link } from "react-router-dom";
import { IoIosCart } from "react-icons/io";
import "./navbar.css";

export default function navbar() {
  return (
    <nav className="nav-main">
      <div className="nav-submain">
        <Link to="/" className="link">
          <span className="home">Phantom Book Store</span>
        </Link>
        <Link to="/about" className="link cart">
          <IoIosCart size={26} color="#2286Be" />
        </Link>
      </div>
    </nav>
  );
}
