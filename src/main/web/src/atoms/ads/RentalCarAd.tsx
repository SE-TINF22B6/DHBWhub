import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const RentalCarAd = () => {
  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://a.check24.net/misc/click.php?pid=638229&aid=213&deep=mietwagen-preisvergleich&cat=10" target="_blank">
          <img
              src="https://a.check24.net/misc/view.php?pid=638229&aid=213&cat=10" width="280" height="250"
              style={{maxWidth: '100%', height: 'auto', borderRadius: 'var(--border-radius)'}} alt="Ad"/>
        </a>
      </div>
  );
}