import React from "react";
import "./SearchBar.css";

export const SearchBar = (): JSX.Element => {
  return (
      <div className="searchbar">
        <input className="input-field" placeholder="Type here to search..." />
        <div className="search-icon"/>
      </div>
  );
};
