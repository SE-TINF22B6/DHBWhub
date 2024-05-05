import React from 'react';
import './SavedPosts.css';
import {Link} from "react-router-dom";
import {dummyPosts} from "./dummyPosts";

export const SavedPosts = () => {

  return (
      <div className="saved-posts">
        <div className="component-headline">Saved posts</div>
        <div className="saved-posts-list">
          {dummyPosts.slice(0,5).map((tag: string, index: number) => (
              <Link key={index} to={`/post/?id=${tag.toLowerCase().replace(' ', '-')}`} className="saved-posts-link">
                <div className="saved-post">
                  {tag}
                </div>
              </Link>
          ))}
        </div>
      </div>
  );
};