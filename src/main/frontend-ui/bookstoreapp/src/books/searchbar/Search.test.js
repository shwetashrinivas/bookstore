import { render, screen } from "@testing-library/react";
import Search from "./search.js";

describe("search bar", () => {
  test("should rendering search bar", () => {
    const { container } = render(<Search />);
    expect(container).toMatchSnapshot();
  });

  test("should present input search box", () => {
    render(<Search />);
    expect(screen.getByTestId("search-bar")).toBeInTheDocument();
  });
});
