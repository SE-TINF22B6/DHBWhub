import React, {useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {useLocation} from "react-router-dom";

import {Footer} from "../../organisms/footer/Footer";
import {SearchSortOptions} from "./components/SearchSortOptions";
import {FindByOptions} from "./components/FindByOptions";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {SearchedPosts} from "./components/SearchedPosts";

import {SearchedTags} from "./components/SearchedTags";
import {SearchedUsers} from "./components/SearchedUsers";

export const Search = () => {
  const location = useLocation();
  const searchParams: URLSearchParams = new URLSearchParams(location.search);
  const searchTerm: string | null = searchParams.get('query');
  const [sortOption, setSortOption] = useState<string>('popular');
  const [FindOption, setFindOption] = useState<string>('title');



  const adBlockDetected = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);


  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  const handleFindByChange = (option: string): void => {
      setFindOption(option);
    };

  const FindByOption = (option: string): React.JSX.Element =>{
      switch (option) {
          case "title":
              return (
                  <SearchedPosts sortOption={sortOption} query={searchTerm}/>);
          case "user":
              return (<SearchedUsers sortOption={sortOption} query={searchTerm}/>);
          case "tags":
              return (<SearchedTags sortOption={sortOption} query={searchTerm}/>);
          default:
              return(<SearchedPosts sortOption={sortOption} query={searchTerm}/>);
      }
  }
  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <div className="search-content">
        <div className="search-sidebar">
          <SearchSortOptions onSortChange={handleSortChange}/>
          <FindByOptions onSortChange={handleFindByChange}/>
        </div>
            <div>
                {FindByOption(FindOption)}
            </div>
        </div>
        <Footer/>
      </div>
  );
};