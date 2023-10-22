import React from 'react';
import './SortOptions.css';
export const SortOptions = () => {

  return (
      <div className="sort-options">
        <div className="div-wrapper">
          <div className="popular">
            <div className="popular-picture"/>
            <div className="popular-label">Popular</div>
            <div className="popular-text">View the most liked discussions</div>
          </div>
          <div className="following">
            <div className="following-picture"/>
            <div className="following-label">Following</div>
            <div className="following-text">Explore from your favourites</div>
          </div>
          <div className="newest">
            <div className="newest-picture"/>
            <div className="newest-label">Newest</div>
            <div className="newest-text">Find the latest update</div>
          </div>
        </div>
      </div>
  );
};
