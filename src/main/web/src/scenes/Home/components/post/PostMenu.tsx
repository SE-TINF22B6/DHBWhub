import React from 'react';
import './PostMenu.css';

interface PostMenuProps {
  handleShareClick: () => void;
  handleSaveClick: () => void;
  handleReportClick: () => void;
}

export const PostMenu: React.FC<PostMenuProps> = ({handleShareClick, handleSaveClick, handleReportClick,}: PostMenuProps) => {
  return (
      <div className="menu">
        <div className="menu-text">
          <button className="menu-button" onClick={handleShareClick}>
            <div className="share-image"></div>
            <div className="share-text">Share post</div>
          </button>
          <button className="menu-button" onClick={handleSaveClick}>
            <div className="save-image"></div>
            <div className="save-text">Save post</div>
          </button>
          <button className="menu-button" onClick={handleReportClick}>
            <div className="report-image"></div>
            <div className="report-text">Report post</div>
          </button>
        </div>
      </div>
  );
};
