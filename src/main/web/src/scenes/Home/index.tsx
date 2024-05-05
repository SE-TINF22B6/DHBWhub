import React, {useEffect, useRef, useState} from "react";
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

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('popular');
  const scrollUpRef = useRef<HTMLDivElement>(null);

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
              <SavedPosts/>
          </div>
          <div className="middle-content">
            <CreatePost/>
            <Posts sortOption={sortOption}/>
            {!showMobileScrollUpButton && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
          </div>
          <div className="sidebar-right">
            <Events/>
            <Infos/>
          </div>
        </div>
        {showMobileScrollUpButton && <ScrollUpButton scrollUpRef={scrollUpRef}/>}
        <Footer/>
      </div>
  );
};
