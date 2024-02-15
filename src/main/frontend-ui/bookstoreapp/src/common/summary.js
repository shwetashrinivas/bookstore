import React from "react";
import "./summary.css";

export default function summary() {
  return (
    <div className="summary">
      <span className="summary-text">Summary</span>
      <sapn className="value">Order value: RS 121200:00</sapn>
      <div className="button-container">
        <button className="button">Proceed to buy</button>
      </div>
    </div>
  );
}
