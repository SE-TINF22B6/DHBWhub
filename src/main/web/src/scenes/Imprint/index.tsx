import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {imprint} from "./ImprintInterface";

export const Imprint = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  return (
      <div className="imprint-component">
        <Header/>
        <div className="imprint-content">
            <div>
                <h2>Imprint</h2>
                <p><strong>{imprint.informationAccordingTo}</strong></p>
                <p>{imprint.address}</p>
                <p><strong>Represented by:</strong> <br /> {imprint.representedBy}</p>
                <p><strong>Contact:</strong> <br /> E-Mail: {imprint.contact.email}</p>
                <h3>Disclaimer of liability:</h3>
                <p>{imprint.disclaimerOfLiability.content}</p>
                <h3>Liability for links</h3>
                <p>{imprint.liabilityForLinks.content}</p>
                <h3>Copyright</h3>
                <p>{imprint.copyright}</p>
                <h3>Data protection</h3>
                <p>{imprint.dataProtection}</p>
            </div>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
}
