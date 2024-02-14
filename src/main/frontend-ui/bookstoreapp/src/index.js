import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App.js";
import BooksProvider from "./books/bookcontext/BooksProvider.js";
import reportWebVitals from "./reportWebVitals.js";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BooksProvider>
      <App />
    </BooksProvider>
  </React.StrictMode>
);
reportWebVitals();
