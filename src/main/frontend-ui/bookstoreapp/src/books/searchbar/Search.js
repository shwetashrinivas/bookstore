import React, { useState } from "react";
import "./search.css";

export default function Search() {
  const [searchValue, setsearchValue] = useState("");

  return (
    <input
      placeholder="Search book"
      data-testid="search-bar"
      className="input-style"
    />
  );
}
