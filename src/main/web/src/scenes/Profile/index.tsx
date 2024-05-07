import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";

export const Profile = () => {
  const adBlockDetected = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  return (
      <div className="profile">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <img style={{position: "relative", marginTop: "20px", marginBottom: "20px"}} className="profile-picture" alt="Profile" src={process.env.PUBLIC_URL + '/assets/profile.svg'}/>
        <Footer/>
        <MobileFooter/>
      </div>
  );
};