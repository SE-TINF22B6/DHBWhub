import React, {useRef, useEffect, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Events} from "./components/events/Events";
import {CreatePost} from "./components/create-post/CreatePost";
import {Posts} from "./components/post/Posts";
import {Infos} from "./components/infos/Infos";
import {SavedPosts} from "./components/saved-posts/SavedPosts";
import {SortOptions} from "./components/sort-options/SortOptions";
import {PopularTags} from "./components/popular-tags/PopularTags";
import {Footer} from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
// @ts-ignore
import {useDetectAdBlock} from "adblock-detect-react";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {SmallC24Ad} from "../../atoms/ads/SmallC24Ad";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {TravelAd} from "../../atoms/ads/TravelAd";
import {useMediaQuery} from "@mui/system";

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('newest');
  const scrollUpRef = useRef<HTMLDivElement>(null);

  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
  const isTabletSize: boolean = useMediaQuery('(max-width: 1024px)');

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <div ref={scrollUpRef}/>
        <Header/>
        <div className="homepage-content">
          <div className="sidebar-left">
            {!isTabletSize && (
                <>
                  <SortOptions onSortChange={handleSortChange}/>
                  <PopularTags/>
                  <SavedPosts/>
                </>
            )}
          </div>
          <div className="middle-content">
            {isTabletSize && (
                <SortOptions onSortChange={handleSortChange}/>
            )}
            <CreatePost/>
            <Posts sortOption={sortOption}/>
            {isTabletSize && (
                <div className="mobile-bar">
                  <PopularTags/>
                  <SavedPosts/>
                </div>
            )}
            <TravelAd/>
            {!isSmartphoneSize && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
            {!isSmartphoneSize && <Footer/>}
          </div>
          <div className="sidebar-right">
            <Events/>
            <Infos/>
            <SmallC24Ad/>
          </div>
        </div>
        {isSmartphoneSize && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
        {isSmartphoneSize && <Footer/>}
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};