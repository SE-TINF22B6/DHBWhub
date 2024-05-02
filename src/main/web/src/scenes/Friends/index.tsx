import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";

export const Friends = () => {

  return (
      <div className="friends">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <h1 className="loading">Work in progress</h1>
        <Footer/>
      </div>
  );
};
