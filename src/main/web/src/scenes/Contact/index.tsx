import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import {MobileAd} from "../../atoms/ads/MobileAd";
import {C24Ad} from "../../atoms/ads/C24Ad";
import {DigitalOceanAd} from "../../atoms/ads/DigitalOceanAd";

export const Contact = () => {
  return (
      <div className="contact-component">
        <Header/>
        <div className="contact-content">
          <MobileAd/>
          <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSd54-d4TIZ1PIcXOBJg3CE3V9_mTqWmavMKi1EhrkZQ8BQ3iA/viewform?embedded=true"
                  className="google-form"
                  title="contact-form">Wird geladen…
          </iframe>
          <C24Ad/>
          {showMobileScrollUpButton && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
        </div>
        <DigitalOceanAd/>
        <Footer/>
      </div>
  );
};
