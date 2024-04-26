import React, {useEffect, useRef, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Events} from "./components/events/Events";
import {CreatePost} from "./components/create-post/CreatePost";
import {Posts} from "./components/post/Posts";
import {SortOptions} from "./components/sort-options/SortOptions";
import {PopularTags} from "./components/popular-tags/PopularTags";

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('popular');

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  return (
      <div className="homepage">
        <Header/>
        <div className="homepage-content">
          <div className="sidebar-left">
            <SortOptions onSortChange={handleSortChange}/>
              <PopularTags/>
          </div>
          <div className="middle-content">
            <CreatePost/>
            <Posts sortOption={sortOption}/>
          </div>
          <div className="sidebar-right">
            <Events/>
          </div>
        </div>
      </div>
  );
};