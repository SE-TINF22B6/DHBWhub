import React from "react";
import "./SearchBar.css";

export const SearchBar = (): JSX.Element => {
  return (
      <div className="searchbar">
        <div className="text-wrapper">Type here to search...</div>
        <div className="search-icon"/>
      </div>
  );
};
