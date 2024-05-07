import React, { useState } from "react";
import "./CreatePost.css";
import { PostDraft } from "./PostDraft";
import {Link} from "react-router-dom";

export const CreatePost = () => {
  const [draftOpen, setDraftOpen] = useState(false);

  const handleCreatePostClick = (): void => setDraftOpen(!draftOpen);
  const handleDraftSubmit = (): void => {
    setDraftOpen(!draftOpen);
  };

  return (
      <div className="create-post">
        <Link to="/profile" aria-label="To the profile">
          <img className="profile-picture-create-post" src={process.env.PUBLIC_URL + '/assets/profile.svg'} alt="Profile"/>
        </Link>
        <div className="post-text-area" onClick={handleCreatePostClick}>
          <input className="post-input-field" placeholder="Write something..."/>
        </div>
        <button className="create-post-button">
          <div onClick={handleCreatePostClick} className="button-label">
            + Create post
          </div>
        </button>
        <PostDraft
            draftOpen={draftOpen}
            handleDraftSubmit={handleDraftSubmit}
        ></PostDraft>
      </div>
  );
};
