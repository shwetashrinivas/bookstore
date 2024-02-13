import { render, screen } from "@testing-library/react";
import BookList from "./BookList.js";
import { BooksContext } from "./bookcontext/BooksProvider.js";

const mockBookList = [
  {
    id: 1,
    title: "Tale of two cities",
    author: "Charles Dickens",
    price: 350.0,
    description:
      "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
    coverImageUrl: "1586_f1601742134.png",
    rating: 5.0,
    quantity: 3,
  },
  {
    id: 2,
    title: "Little women",
    author: "Louisa May Alcott",
    price: 450.0,
    description:
      "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
    coverImageUrl: "1586_f1601742134.png",
    rating: 5.0,
    quantity: 4,
  },
];

const renderBooks = () => {
  return (
    <BooksContext.Provider
      value={{
        books: mockBookList,
        setBooks: () => {},
      }}
    >
      <BookList />
    </BooksContext.Provider>
  );
};

describe("BookList", () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });
  it("should rendering book list component", () => {
    const { container } = render(renderBooks());
    expect(container).toMatchSnapshot();
  });

  it("should rendering book list component with list of books", () => {
    render(renderBooks());
    expect(screen.getByTestId("list-books")).toBeInTheDocument();
  });

  it("should check book list is empty", () => {
    render(
      <BooksContext.Provider
        value={{
          books: [],
          setBooks: () => {},
        }}
      >
        <BookList />
      </BooksContext.Provider>
    );
    expect(screen.getByTestId("no-book")).toBeInTheDocument();
    expect(screen.getByTestId("no-book")).toHaveTextContent(
      "No books available!"
    );
  });
});
