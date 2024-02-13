import "./App.css";
import BooksProvider from "./books/bookcontext/BooksProvider.js";
import BookList from "./books/BookList.js";

function App() {
  return (
    <BooksProvider>
      <div className="App">
        <BookList />
      </div>
    </BooksProvider>
  );
}

export default App;
