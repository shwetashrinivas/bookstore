import ShoppingCart from "../books/cart/shoppingCart";

it("should rendering book list component", () => {
  const { container } = <ShoppingCart />;
  expect(container).toMatchSnapshot();
});
