import React, {useEffect, useRef, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {Events} from "./components/events/Events";
import {CreatePost} from "./components/create-post/CreatePost";
import {Posts} from "./components/post/Posts";
import {SortOptions} from "./components/sort-options/SortOptions";
import {PopularTags} from "./components/popular-tags/PopularTags";

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('popular');

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
