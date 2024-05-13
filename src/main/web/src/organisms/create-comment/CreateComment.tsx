import React, { useState } from "react";
import "./CreateComment.css";
import { Link } from "react-router-dom";

interface CreateCommentProps {
  onReplyClick: (newCommentText: string) => void;
}

export const CreateComment: React.FC<CreateCommentProps> = ({ onReplyClick }) => {
  const [commentText, setCommentText] = useState("");

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    setCommentText(event.target.value);
  };

  const handleReplyClick = (): void => {
    if (commentText.trim() !== "") {
      onReplyClick(commentText);
      setCommentText("");
    }
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>): void => {
    if (event.key === "Enter") {
      handleReplyClick();
    }
  };

  return (
      <div className="create-comment">
        <Link to="/profile" aria-label="To the profile">
          <img style={{top: '13px'}} className="profile-picture" alt="Profile" src={process.env.PUBLIC_URL + '/assets/profile.svg'}/>
        </Link>
        <div className="comment-text-area">
          <input
              className="comment-input-field"
              placeholder="Post your answer"
              value={commentText}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
          />
        </div>
        <button className="reply-button" onClick={handleReplyClick}>
          <div className="reply-button-label">Reply</div>
        </button>
      </div>
  );
};