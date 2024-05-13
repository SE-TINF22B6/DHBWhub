import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const FAQ = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  return (
      <div className="faq-component">
        <Header/>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};
