import { render, screen } from "@testing-library/react";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";
import BooksProvider from "./BooksProvider.js";

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
];
const mockAxios = new MockAdapter(axios);

const renderTestComponent = () => {
  return (
    <BooksProvider
      value={{
        books: [],
        setBooks: () => {},
      }}
    >
      <div>Test Component</div>
    </BooksProvider>
  );
};

describe("Book Provider", () => {
  beforeEach(() => {
    jest.clearAllMocks();
    mockAxios
      .onGet("http://localhost:8090/books")
      .reply(200, { data: mockBookList });
  });

  it("Success api call", async () => {
    await render(renderTestComponent());
    expect(mockAxios.history.get[0].url).toStrictEqual(
      "http://localhost:8090/books"
    );
  });
});
