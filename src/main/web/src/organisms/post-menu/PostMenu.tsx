import React from 'react';
import './PostMenu.css';

interface PostMenuProps {
  handleShareClick: () => void;
  handleSaveClick: () => void;
  handleReportClick: () => void;
}

export const PostMenu: React.FC<PostMenuProps> = ({handleShareClick, handleSaveClick, handleReportClick,}: PostMenuProps) => {
  return (
      <div className="post-menu">
        <div className="post-menu-text">
          <button className="post-menu-button" onClick={handleShareClick}>
            <div className="post-share-image"></div>
            <div className="post-share-text">Share post</div>
          </button>
          <button className="post-menu-button" onClick={handleSaveClick}>
            <div className="post-save-image"></div>
            <div className="post-save-text">Save post</div>
          </button>
          <button className="post-menu-button" onClick={handleReportClick}>
            <div className="post-report-image"></div>
            <div className="post-report-text">Report post</div>
          </button>
        </div>
      </div>
  );
};
