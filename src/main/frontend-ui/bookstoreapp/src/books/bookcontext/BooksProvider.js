import React, { createContext, useState, useEffect } from "react";
import axios from "axios";

export const BooksContext = createContext();
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
  {
    id: 3,
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
    id: 4,
    title: "Little women",
    author: "Louisa May Alcott",
    price: 450.0,
    description:
      "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
    coverImageUrl: "1586_f1601742134.png",
    rating: 5.0,
    quantity: 4,
  },
  {
    id: 5,
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
    id: 6,
    title: "Little women",
    author: "Louisa May Alcott",
    price: 450.0,
    description:
      "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
    coverImageUrl: "1586_f1601742134.png",
    rating: 5.0,
    quantity: 4,
  },
  {
    id: 7,
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
    id: 8,
    title: "Little women",
    author: "Louisa May Alcott",
    price: 450.0,
    description:
      "It is set during and after the Civil War and tells the story of the March family, principally the four girls: Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.",
    coverImageUrl: "1586_f1601742134.png",
    rating: 5.0,
    quantity: 4,
  },
  {
    id: 9,
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
    id: 10,
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
const BooksProvider = ({ children }) => {
  const [books, setBooks] = useState({
    books: [],
  });

  useEffect(() => {
    const getBooks = async () => await axios.get("http://localhost:8090/books");
    getBooks()
      .then(({ data }) => {
        setBooks([...mockBookList]);
      })
      .catch(() => {
        setBooks([...mockBookList]);
      });
  }, []);

  return (
    <BooksContext.Provider
      value={{
        setBooks,
        books,
      }}
    >
      {children}
    </BooksContext.Provider>
  );
};

export default BooksProvider;
