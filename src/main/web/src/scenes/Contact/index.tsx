import React, {useEffect, useRef, useState} from "react";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {MobileAd} from "../../atoms/ads/MobileAd";
import {C24Ad} from "../../atoms/ads/C24Ad";
import {useMediaQuery} from "@mui/system";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import "./index.css";

export const Contact = () => {
  const adBlockDetected = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');

  const scrollUpRef = useRef<HTMLDivElement>(null);
  const [showMobileScrollUpButton, setShowMobileScrollUpButton] = useState(false);

  useEffect(() => {
    const handleResize = () => {
      setShowMobileScrollUpButton(window.innerWidth <= 1200);
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <div ref={scrollUpRef}/>
        <Header/>
        <div className="contact-content">
          <MobileAd/>
          <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSd54-d4TIZ1PIcXOBJg3CE3V9_mTqWmavMKi1EhrkZQ8BQ3iA/viewform?embedded=true"
                  className="google-form"
                  title="contact-form">Wird geladen…
          </iframe>
          <C24Ad/>
          {showMobileScrollUpButton && (<ScrollUpButton scrollUpRef={scrollUpRef}/>)}
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};