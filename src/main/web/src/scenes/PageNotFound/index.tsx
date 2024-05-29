import React from "react";
import { Header } from "../../organisms/header/Header";
import Lottie from "lottie-react";
import errorAnimationData from "../../assets/error.json";
import {Footer} from "../../organisms/footer/Footer";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const PageNotFound = () => {
  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <div className="error-animation">
          <Lottie animationData={errorAnimationData}/>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
}
