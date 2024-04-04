import React, { useState } from 'react';
import './SortOptions.css';

interface SortOptionsProps {
  onSortChange: (option: string) => void;
}

export const SortOptions: React.FC<SortOptionsProps> = ({ onSortChange }) => {
  const [activeOption, setActiveOption] = useState('newest');

  const handleSortClick = (option: string): void => {
    onSortChange(option);
    setActiveOption(option);
  };

  return (
      <div className="sort-options">
        <button
            className={`newest ${activeOption === 'newest' ? 'active' : ''}`}
            onClick={() => handleSortClick('newest')}>
          <img className="newest-picture" src={process.env.PUBLIC_URL + '/assets/home/sort-options/newest.svg'} alt="Newest"/>
          <div className="newest-label">Newest</div>
          <div className="newest-text">Find the latest update</div>
        </button>
        <button
            className={`following ${activeOption === 'following' ? 'active' : ''}`}
            onClick={() => handleSortClick('following')}>
          <img className="following-picture" src={process.env.PUBLIC_URL + '/assets/home/sort-options/following.svg'} alt="Following"/>
          <div className="following-label">Following</div>
          <div className="following-text">Explore from your favourites</div>
        </button>
        <button
            className={`popular ${activeOption === 'popular' ? 'active' : ''}`}
            onClick={() => handleSortClick('popular')}>
          <img className="popular-picture" src={process.env.PUBLIC_URL + '/assets/home/sort-options/popular.svg'} alt="Popular"/>
          <div className="popular-label">Popular</div>
          <div className="popular-text">View the most liked discussions</div>
        </button>
      </div>
  );
};
