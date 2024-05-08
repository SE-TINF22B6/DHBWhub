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
            <img src={process.env.PUBLIC_URL + '/assets/home/post/share.svg'} alt="Share" className="post-share-image"></img>
            <div className="post-share-text">Share post</div>
          </button>
          <button className="post-menu-button" onClick={handleSaveClick}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/save.svg'} alt="Save" className="post-save-image"></img>
            <div className="post-save-text">Save post</div>
          </button>
          <button className="post-menu-button" onClick={handleReportClick}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/report.svg'} alt="Report" className="post-report-image"></img>
            <div className="post-report-text">Report post</div>
          </button>
        </div>
      </div>
  );
};
