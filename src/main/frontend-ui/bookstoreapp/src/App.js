import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BookList from "./books/BookList.js";
import Search from "./books/searchbar/search.js";
import Navbar from "./books/navbar/navbar.js";
import Shopcart from "./books/cart/shopcart.js";

function MainComponent() {
  return (
    <div className="App">
      <Search />
      <BookList />
    </div>
  );
}

function App() {
  return (
    <Router>
      <Navbar />
      <div className="background">
        <Routes>
          <Route path="/" element={<MainComponent />} />
          <Route path="/cart" element={<Shopcart />} />
          <Route path="*" element={<MainComponent />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
