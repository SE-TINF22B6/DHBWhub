import React from 'react';
import './Interaction.css';
import {Link} from "react-router-dom";

interface PostInteractionProps {
  id: number;
  likes: number;
  userLiked: boolean;
  heartClass: string;
  comments: number;
  handleLike: () => void;
  isHomepage?: boolean;
}

export const Interaction : React.FC<PostInteractionProps> = (props: PostInteractionProps) => {
  const {
    id,
    likes,
    userLiked,
    heartClass,
    comments,
    handleLike,
    isHomepage
  } = props;

  const isHeartFilled = heartClass === 'heart-filled';

  return (
      <div className="interaction">
        <div className="like-container">
          <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike} aria-label="Like-Button">
            {isHeartFilled ?
                <img src={process.env.PUBLIC_URL + '/assets/home/post/heart-active.svg'} alt="Red heart"/>
                : <img src={process.env.PUBLIC_URL + '/assets/home/post/heart.svg'} alt="White heart"/>
            }
          </button>
          <div className="likes">{likes}</div>
        </div>
        <div className="comment-container">
          {isHomepage && (
              <>
                <Link to={`/post/?id=${id}`} className="post-button" aria-label="To the post">
                  <img className="comment-icon" src={process.env.PUBLIC_URL + '/assets/home/post/comment.svg'} alt="Comment"/>
                </Link>
                <Link to={`/post/?id=${id}`} className="post-button">
                  <div className="comments">{comments}</div>
                </Link>
              </>
          )}
          {!isHomepage && (
              <>
                <img src={process.env.PUBLIC_URL + '/assets/home/post/comment.svg'} alt="Comment"/>
                <div className="comments">{comments}</div>
              </>
          )}
        </div>
      </div>
  );
};