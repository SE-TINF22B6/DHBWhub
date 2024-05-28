import React from 'react';
import './CommentMenu.css';

interface CommentMenuProps {
  handleShareClick: () => void;
  handleReportClick: () => void;
}

export const CommentMenu: React.FC<CommentMenuProps> = ({handleShareClick, handleReportClick,}: CommentMenuProps) => {
  return (
      <div className="comment-menu">
        <div className="comment-menu-text">
          <button className="comment-menu-button" onClick={handleShareClick}>
            <div className="comment-share-image"></div>
            <div className="comment-share-text">Share comment</div>
          </button>
          <button className="comment-menu-button" onClick={handleReportClick}>
            <div className="comment-report-image"></div>
            <div className="comment-report-text">Report comment</div>
          </button>
        </div>
      </div>
  );
};
