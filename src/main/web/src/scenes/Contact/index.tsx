import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";

export const Contact = () => {
  return (
      <div className="contact-component">
        <Header/>
        <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSd54-d4TIZ1PIcXOBJg3CE3V9_mTqWmavMKi1EhrkZQ8BQ3iA/viewform?embedded=true"
                className="google-form"
                title = "contact-form">Wird geladen…
        </iframe>
      </div>
  );
};
