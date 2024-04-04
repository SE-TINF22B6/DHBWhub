import React, {useState} from "react";
import "./index.css";
import {Header} from "./components/header/Header";
import {Posts} from "./components/post/Posts";
import {SortOptions} from "./components/sort-options/SortOptions";

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  return (
      <div className="homepage">
        <Header></Header>
        <div className="body">
          <div className="sidebar-left">

          </div>
          <div className="middle-content">
            <Posts></Posts>
            <Posts sortOption={sortOption} />
          </div>
          <div className="sidebar-right">

          </div>
        </div>
      </div>
  );
};
