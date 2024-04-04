import React, {useState} from "react";
import "./index.css";
import {Header} from "./components/header/Header";
import {Posts} from "./components/post/Posts";
import {SortOptions} from "./components/sort-options/SortOptions";

export const Home = () => {
  const [sortOption, setSortOption] = useState<string>('popular');

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  return (
      <div className="homepage">
        <Header></Header>
        <div className="body">
          <div className="sidebar-left">

            <SortOptions onSortChange={handleSortChange}/>
          </div>
          <div className="middle-content">
            <Posts sortOption={sortOption} />
          </div>
          <div className="sidebar-right">

          </div>
        </div>
      </div>
  );
};
