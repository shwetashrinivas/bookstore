import React, { useContext } from "react";
import Modal from "react-modal";
import { useNavigate } from "react-router-dom";
import Success from "./order-received.gif";
import "./popup.css";
import { BooksContext } from "../books/bookcontext/BooksProvider.js";

const customStyles = {
  content: {
    top: "50%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
    padding: "40px",
    border: "none",
    backgroundColor: "#FBFBFB",
    boxShadow: "0 2px 5px 1px rgba(64,60,67,.16)",
  },
};
export default function Popup({ isModal, setIsModal }) {
  const { setCartItems } = useContext(BooksContext);
  const navigate = useNavigate();
  function closeModal() {
    setIsModal(false);
  }
  const goToHome = () => {
    setCartItems([]);
    navigate("/");
  };
  return (
    <div>
      <Modal
        isOpen={isModal}
        onRequestClose={closeModal}
        style={customStyles}
        shouldCloseOnOverlayClick={false}
      >
        <div className="card">
          <p className="orderrecieved">Your order has been received</p>
          <div className="imgstyle">
            <img
              data-testid="coverImageUrl"
              src={Success}
              alt="Success"
              height={110}
              width={190}
            />
          </div>

          <p className="purchase-msg">Thank you for purchase !</p>
          <div className="btn-container">
            <button
              className="continue-shopping"
              onClick={() => {
                goToHome();
              }}
            >
              Continue shopping
            </button>
          </div>
        </div>
      </Modal>
    </div>
  );
}
