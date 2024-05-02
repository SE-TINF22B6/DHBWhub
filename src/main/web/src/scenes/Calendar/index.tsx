import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";

export const Calendar = (): JSX.Element => {
  return (
      <div className="calendar">
        <Header/>
        <Footer/>
      </div>
  );
};
