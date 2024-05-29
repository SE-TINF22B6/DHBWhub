import React, { useEffect } from "react";
import { useDetectAdBlock } from "adblock-detect-react";
import "./AdBlockOverlay.css";

const AdBlockOverlay = () => {
  const adBlockDetected = useDetectAdBlock();

  useEffect(() => {
    if (adBlockDetected) {
      // Abgedunkeltes Overlay anzeigen
      document.body.classList.add("adblock-overlay-active");
    }
  }, [adBlockDetected]);

  return (
      <div className="adblock-overlay">
        <img className="adblock-overlay-icon" alt="Warning" src={process.env.PUBLIC_URL + '/assets/warn.svg'}/>
        <p>Bitte deaktiviere deinen AdBlocker, um DHBWhub weiterhin nutzen zu können.</p>
      </div>
  );
};

export default AdBlockOverlay;
