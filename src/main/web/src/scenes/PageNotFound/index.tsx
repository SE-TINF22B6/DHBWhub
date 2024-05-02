import React from "react";
import { Header } from "../../organisms/header/Header";
import "./index.css";
import Lottie from "lottie-react";
import errorAnimationData from "../../assets/error.json";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";

export const PageNotFound = () => {
  return (
      <div className="page-not-found">
        <Header/>
        <div className="error-animation">
          <Lottie animationData={errorAnimationData}/>
        </div>
        <Footer/>
      </div>
  );
}