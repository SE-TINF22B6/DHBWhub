import React, {useState} from 'react';
import './Interaction.css';
import {Link} from "react-router-dom";

interface PostInteractionProps {
  id: number;
  likes: number;
  userLiked: boolean;
  heartClass: string;
  comments: number | null;
  handleLike: () => void;
  isHomepage?: boolean;
}

export const Interaction : React.FC<PostInteractionProps> = (props: PostInteractionProps) => {
  const {id, likes, userLiked, heartClass, comments, handleLike, isHomepage} = props;

  const isHeartFilled = heartClass === 'heart-filled';
  const [loggedIn, setLoggedIn] = useState(false);
  const [commentsNull, setCommentsNull] = useState(false);

  return (
      <div className="interaction">
        <div className="like-container">
          <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike} aria-label="Like-Button" disabled={!loggedIn}>
            {isHeartFilled ?
                <img style={isHomepage ? {marginBottom: '5px'} : {}} src={process.env.PUBLIC_URL + '/assets/home/post/heart-active.svg'} alt="Red heart"/>
                : <img style={isHomepage ? {marginBottom: '5px'} : {}} src={process.env.PUBLIC_URL + '/assets/home/post/heart.svg'} alt="White heart"/>
            }
          </button>
          <div className="likes">{likes}</div>
        </div>
        <div className="comment-container">
          {comments !== null && (
              <>
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
                      <img className="comment-icon" src={process.env.PUBLIC_URL + '/assets/home/post/comment.svg'} alt="Comment"/>
                      <div className="comments">{comments}</div>
                    </>
                )}
              </>
          )}
        </div>
      </div>
  );
};