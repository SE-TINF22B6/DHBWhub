import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";

export const FAQ = () => {
  return (
      <div className="faq-component">
        <Header/>
        <Footer/>
        <MobileFooter/>
      </div>
  );
};
