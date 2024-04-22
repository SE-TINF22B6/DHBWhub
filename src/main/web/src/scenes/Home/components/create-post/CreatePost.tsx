import React, { useState } from "react";
import "./CreatePost.css";
import {Link} from "react-router-dom";

export const CreatePost = () => {
  const [draftOpen, setDraftOpen] = useState(false);
  const [postText, setPostText] = useState("");

  const handleCreatePostClick = (): void => setDraftOpen(!draftOpen);

  const createPost = (): void => {
    console.log("Post created:", postText);
    // TODO: Logik hinzufügen, um den Post zu erstellen
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
      </div>
  );
};
