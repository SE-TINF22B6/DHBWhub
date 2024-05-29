import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const TermsOfService = () => {
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  return (
      <div className="page">
        <Header/>
        <div className="tos-content">
          <h1 className="loading">Work in progress</h1>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};