import React, {useRef} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {imprint} from "./ImprintInterface";
import {Link} from "react-router-dom";
import {DigitalOceanAd} from "../../atoms/ads/DigitalOceanAd";
import ScrollUpButton from "../../atoms/ScrollUpButton";

export const Imprint = () => {
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
  const scrollUpRef = useRef<HTMLDivElement>(null);

  return (
      <div className="page">
        <div ref={scrollUpRef}></div>
        <Header/>
        <div className="imprint-content">
          <div>
            <h2>Imprint</h2>
            <p><h4>{imprint.informationAccordingTo}</h4></p>
            <p>{imprint.addressLine1}</p>
            <p>{imprint.addressLine2}</p>
            <p>{imprint.addressLine3}</p>
            <p><h4>Represented by:</h4></p>
            <p>{imprint.representedBy}</p>
            <p><h4>Contact:</h4></p>
            <p>E-Mail: {imprint.contact.email}</p>
            <p>Or use our <Link style={{color: "var(--white)"}} to="/contact">contact form</Link></p>
            <h3>Disclaimer of liability:</h3>
            <p>{imprint.disclaimerOfLiability.content}</p>
            <h3>Liability for links</h3>
            <p>{imprint.liabilityForLinks.content}</p>
            <h3>Copyright</h3>
            <p>{imprint.copyright}</p>
            <h3>Data protection</h3>
            <p>{imprint.dataProtection}</p>
          </div>
          <br/>
          <DigitalOceanAd/>
        </div>
        <ScrollUpButton scrollUpRef={scrollUpRef}/>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
}
