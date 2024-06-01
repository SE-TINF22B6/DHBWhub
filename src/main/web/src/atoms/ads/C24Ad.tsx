import React from "react";
import "./Ads.css";
import config from "../../config/config";

export const C24Ad = () => {
  return (
      <div className="ad-wrapper" style={!config.adsOn ? {display: 'none'} : {}}>
        <div className="ad-info">Ad</div>
        {/*eslint-disable-next-line*/}
        <a href="https://a.check24.net/misc/click.php?pid=638229&aid=338&deep=c24bank&cat=14" target="_blank">
          <img src="https://a.check24.net/misc/view.php?pid=638229&aid=338&cat=14" width="160" height="600"
               className="ad" alt="Ad"/>
        </a>
      </div>
  );
}