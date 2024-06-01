import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const SmallC24Ad = () => {
  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://a.check24.net/misc/click.php?pid=638229&aid=342&deep=c24bank&cat=14" target="_blank">
          <img src="https://a.check24.net/misc/view.php?pid=638229&aid=342&cat=14" width="330" height="250"
               className="ad-banner-c24" alt="Ad"/>
        </a>
      </div>
  );
}