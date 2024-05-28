import React, { useState } from 'react';
import './FindByOptions.css';

interface FindByOptionsProps {
  onSortChange: (option: string) => void;
}

export const FindByOptions: React.FC<FindByOptionsProps> = ({ onSortChange }) => {
  const [activeOption, setActiveOption] = useState('newest');

  const handleSortClick = (option: string): void => {
    onSortChange(option);
    setActiveOption(option);
  };

  return (
      <div className="find-by-options">
        Find by
        <button className={`title ${activeOption === 'title' ? 'active' : ''}`} onClick={() => handleSortClick('title')}>
          <img className="title-picture" src={process.env.PUBLIC_URL + '/assets/search/title.svg'} alt="Title"/>
          <div className="title-label">Title</div>
        </button>
        <button className={`user ${activeOption === 'user' ? 'active' : ''}`} onClick={() => handleSortClick('user')}>
          <img className="user-picture" src={process.env.PUBLIC_URL + '/assets/search/user.svg'} alt="User"/>
          <div className="user-label">User</div>
        </button>
        <button className={`tags ${activeOption === 'tags' ? 'active' : ''}`} onClick={() => handleSortClick('tags')}>
          <img className="tags-picture" src={process.env.PUBLIC_URL + '/assets/search/tags.svg'} alt="Tags"/>
          <div className="tags-label">Tags</div>
        </button>
      </div>
  );
};