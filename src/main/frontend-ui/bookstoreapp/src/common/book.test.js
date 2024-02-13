import { render, screen } from "@testing-library/react";
import Book from "./book";

const book = {
  id: 1,
  title: "Tale of two cities",
  author: "Charles Dickens",
  price: 350.0,
  description:
    "The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.",
  coverImageUrl: "1586_f1601742134.png",
  rating: 5.0,
  quantity: 3,
};

describe("Book", () => {
  test("should rendering book component", () => {
    const { container } = render(<Book book={book} />);
    expect(container).toMatchSnapshot();
  });

  test("should have elements title, price, coverImageUrl, author", () => {
    render(<Book book={book} />);
    expect(screen.getByTestId("title")).toBeInTheDocument();
    expect(screen.getByTestId("price")).toBeInTheDocument();
    expect(screen.getByTestId("coverImageUrl")).toBeInTheDocument();
    expect(screen.getByTestId("author")).toBeInTheDocument();
  });
});
