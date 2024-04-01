import React from "react";
import "./index.css";
import {Header} from "../Home/components/header/Header";

export const Contact = (): JSX.Element => {
  return (
      <div className="contact">
        <Header></Header>
        <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSd54-d4TIZ1PIcXOBJg3CE3V9_mTqWmavMKi1EhrkZQ8BQ3iA/viewform?embedded=true"
                width="800"
                height="600"
                className="google-form"
                title = "contact-form">Wird geladen…
        </iframe>
      </div>
  );
};
