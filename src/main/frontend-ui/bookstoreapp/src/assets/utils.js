import axios from "axios";
export const BASE_URL = "http://localhost:8090";
export const GET_BOOKS_URL = `${BASE_URL}/books`;

export const getBooks = () => {
  axios
    .get(GET_BOOKS_URL)
    .then((response) => {
      return response;
    })
    .catch((error) => {
      return error;
    });
};
