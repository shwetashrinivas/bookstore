import "./App.css";
import BooksProvider from "./books/bookcontext/BooksProvider.js";
import BookList from "./books/BookList.js";
import Search from "./books/searchbar/search.js";

function App() {
  return (
    <BooksProvider>
      <div className="App">
        <Search />
        <BookList />
      </div>
    </BooksProvider>
  );
}

export default App;
