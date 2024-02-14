import { render, screen, fireEvent } from "@testing-library/react";
import Search from "./search.js";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";
import { BooksContext } from "../bookcontext/BooksProvider.js";

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
const mockAxios = new MockAdapter(axios);

const renderBooks = () => {
  return (
    <BooksContext.Provider
      value={{
        books: mockBookList,
        setBooks: () => {},
      }}
    >
      <Search />
    </BooksContext.Provider>
  );
};

describe("search bar", () => {
  beforeEach(() => {
    jest.clearAllMocks();
    jest.useFakeTimers("legacy");
    mockAxios
      .onGet("http://localhost:8090/books/search")
      .reply(200, mockBookList);
  });
  it("should rendering search bar", () => {
    const { container } = render(renderBooks());
    jest.runAllTimers();
    expect(container).toMatchSnapshot();
  });

  it("should present input search box", () => {
    render(renderBooks());
    jest.runAllTimers();
    expect(screen.getByTestId("search-bar")).toBeInTheDocument();
  });

  it("enter characters to input box", () => {
    render(renderBooks());
    jest.runAllTimers();
    fireEvent.change(screen.getByTestId("search-bar"), {
      target: { value: "abc" },
    });
    expect(screen.getByTestId("search-bar")).toHaveValue("abc");
  });

  it("when /search end point fails", () => {
    mockAxios.onGet("http://localhost:8090/books/search").reply(500, {});
    render(renderBooks());
    jest.runAllTimers();
    fireEvent.change(screen.getByTestId("search-bar"), {
      target: { value: "abc" },
    });
    expect(screen.getByTestId("search-bar")).toHaveValue("abc");
  });
});
