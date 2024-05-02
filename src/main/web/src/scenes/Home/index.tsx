import React, {useEffect, useRef, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Events} from "./components/events/Events";
import {CreatePost} from "./components/create-post/CreatePost";
import {Posts} from "./components/post/Posts";
import {SortOptions} from "./components/sort-options/SortOptions";
import {PopularTags} from "./components/popular-tags/PopularTags";
import {Footer} from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
// @ts-ignore
import {useDetectAdBlock} from "adblock-detect-react";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('popular');

  const adBlockDetected = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  const [showMobileScrollUpButton, setShowMobileScrollUpButton] = useState(false);
  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  useEffect(() => {
    const handleResize = () => {
      setShowMobileScrollUpButton(window.innerWidth <= 1200);
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return (
      <div className="homepage">
        {adBlockDetected && <AdBlockOverlay/>}
        <div ref={scrollUpRef}/>
        <Header/>
        <div className="homepage-content">
          <div className="sidebar-left">
            <SortOptions onSortChange={handleSortChange}/>
              <PopularTags/>
          </div>
          <div className="middle-content">
            <CreatePost/>
            <Posts sortOption={sortOption}/>
            <div className="ad-info">Anzeige</div>
            <a href="https://a.check24.net/misc/click.php?pid=638229&aid=256&deep=pauschalreisen-vergleich&cat=9"
               target="_blank">
              <img
                  src="https://a.check24.net/misc/view.php?pid=638229&aid=256&cat=9" width="860" height="100"
                  className="ad-banner-pauschalreisen" alt="Ad"/>
            </a>
            {!showMobileScrollUpButton && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
          </div>
          <div className="sidebar-right">
            <Events/>
          </div>
        </div>
        {showMobileScrollUpButton && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
        <Footer/>
      </div>
  );
};