import React from "react";
import "./CreatePost.css";

export const CreatePost = () => {
  return (
      <div className="create-post">
        <div className="profile-picture"></div>
        <div className="text-area">
          <div className="overlap-group-7">
            <div className="default-text">Write something...</div>
          </div>
        </div>
        <div className="button-create-post">
          <div className="overlap-16">
            <div className="create-post-text">+ Create post</div>
          </div>
        </div>
      </div>
  );
}