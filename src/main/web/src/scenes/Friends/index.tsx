import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const Friends = () => {
  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <h1 className="loading">Work in progress</h1>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};