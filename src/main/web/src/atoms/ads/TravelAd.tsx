import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const TravelAd = () => {
  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://a.check24.net/misc/click.php?pid=638229&aid=256&deep=pauschalreisen-vergleich&cat=9"
           target="_blank">
          <img
              src="https://a.check24.net/misc/view.php?pid=638229&aid=256&cat=9" width="820" height="100"
              className="ad-banner-pauschalreisen" alt="Ad"/>
        </a>
      </div>
  );
}