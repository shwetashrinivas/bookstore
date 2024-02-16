import BookDetails from "./bookDetails";

it("should rendering book component", () => {
    const { container } = <BookDetails />;
    expect(container).toMatchSnapshot();
  });