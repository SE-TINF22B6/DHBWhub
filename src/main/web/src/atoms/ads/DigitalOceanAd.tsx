import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const DigitalOceanAd = () => {

  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://www.digitalocean.com/?refcode=edd29efbe7cc&utm_campaign=Referral_Invite&utm_medium=Referral_Program&utm_source=badge">
          <img src="https://web-platforms.sfo2.cdn.digitaloceanspaces.com/WWW/Badge%202.svg" alt="DigitalOcean Referral Badge"/>
        </a>
      </div>
  );
}