import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const PrimeAd = () => {
  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://www.amazon.de/joinstudent?tag=cashbackde-21" target="_blank">
          <img src={process.env.PUBLIC_URL + '/assets/prime.webp'} width="1240" height="100"
               className="ad" alt="Ad"/>
        </a>
      </div>
  );
}