import React from 'react';
import './Interaction.css';
import {Link} from "react-router-dom";
import {isUserLoggedIn} from "../../services/AuthService";
import {InteractionModel} from "./models/InteractionModel";
import {Tooltip} from "react-tooltip";
import config from "../../config/config";

export const Interaction : React.FC<InteractionModel> = (props: InteractionModel) => {
  const {id, likes, userLiked, heartClass, comments, handleLike,
    isHomepage} = props;

  const isHeartFilled: boolean = heartClass === 'heart-filled';

  return (
      <div className="interaction">
        <div className="like-container" data-tooltip-id="like" data-tooltip-content={config.tooltipMessage}>
          <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike} aria-label="Like-Button"
                  disabled={!isUserLoggedIn()}>
            {isHeartFilled ?
                <img style={isHomepage ? {marginBottom: '5px', height: "26px", width: "26px"} : {height: "26px", width: "26px"}} src={process.env.PUBLIC_URL + '/assets/home/post/heart-active.svg'}
                     alt="Red heart"/>
                : <img style={isHomepage ? {marginBottom: '5px', height: "26px", width: "26px"} : {height: "26px", width: "26px"}} src={process.env.PUBLIC_URL + '/assets/home/post/heart.svg'}
                       alt="White heart"/>
            }
          </button>
          <div className="likes">{likes}</div>
        </div>
        {!isUserLoggedIn() && (
            <Tooltip variant={"light"} id="like" place="top" />
        )}
        <div className="comment-container">
          {comments !== null && (
              <>
                {isHomepage && (
                    <>
                      <Link to={`/post/?id=${id}`} className="post-button" aria-label="To the post">
                        <img className="comment-icon" src={process.env.PUBLIC_URL + '/assets/home/post/comment.svg'} alt="Comment"
                             style={{height: "26px", width: "26px"}}/>
                      </Link>
                      <Link to={`/post/?id=${id}`} className="post-button">
                        <div className="comments">{comments}</div>
                      </Link>
                    </>
                )}
                {!isHomepage && (
                    <>
                      <img className="comment-icon" src={process.env.PUBLIC_URL + '/assets/home/post/comment.svg'} alt="Comment"
                           style={{height: "26px", width: "26px"}}/>
                      <div className="comments">{comments}</div>
                    </>
                )}
              </>
          )}
        </div>
      </div>
  );
};