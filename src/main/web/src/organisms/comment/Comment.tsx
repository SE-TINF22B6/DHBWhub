import React, {useEffect, useState} from "react";
import "./Comment.css";
import {Link, useLocation} from "react-router-dom";
import {Share} from "../share/Share";
import {Report} from "../report/Report";
import {CommentMenu} from "../comment-menu/CommentMenu";
import ReportService from "../../services/ReportService";
import TimeService from "../../services/TimeService";
import LikeService from "../../services/LikeService";
import {Interaction} from "../interaction/Interaction";
import {CommentModel} from "./CommentModel";

export const Comment: React.FC<CommentModel> = (props: CommentModel) => {
  const {
    postId,
    commentId,
    text,
    authorUsername,
    accountId,
    authorImage,
    timestamp,
    likeAmount,
  } = props;

  const formattedTime = TimeService.timeDifference(new Date(timestamp).toISOString());
  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL = window.location.href;

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  const handleReportSubmit = (): void => {
    ReportService.sendReportToBackend(reportReason, reportDescription, postId, accountId, 187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  const handleMenuClick = (): void => {
    setMenuOpen(!menuOpen);
    setShareWindowOpen(false);
  };

  const handleShareClick = (): void => {
    setShareWindowOpen(!shareWindowOpen);
  };

  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const location = useLocation();

  useEffect((): void => {
    const userLikedPost = localStorage.getItem(`liked_${postId}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, postId]);

  const handleLike = (): void => {
    LikeService.handleLike(postId, userLiked, likes, setLikes, setUserLiked, setHeartClass);
  };

  return (
      <div className="comment">
        <div className="comment-content">
          {authorUsername && (
              <Link to={`/user/?name=${authorUsername.toLowerCase().replace(' ', '-')}`} className="author-link"
                    aria-label="To the author">
                {authorImage && <img className="profile-picture" alt="Comment profile" src={authorImage}/>}
              </Link>
          )}
          <div>
            {authorUsername && (
                <Link to={`/user/?name=${authorUsername.toLowerCase().replace(' ', '-')}`} className="author-link"
                      aria-label="To the author">
                  {authorImage && <img className="profile-picture" alt="Comment profile" src={authorImage}/>}
                </Link>
            )}
            <br/>
            <div className="comment-author-date">{formattedTime}</div>
          </div>
          <div className="comment-text">{text}</div>
        </div>

        <div className="comment-interaction">
          <Interaction likes={likes} userLiked={userLiked} heartClass={heartClass} comments={null} handleLike={handleLike} id={0}
                       isHomepage={false}/>
        </div>

        <button className="comment-menu-button" onClick={handleMenuClick}>
          <img alt="Menu" src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
        </button>

        {shareWindowOpen && (
            <div className="comment-share-window">
              <Share postId={postId} commentId={commentId} currentPageURL={currentPageURL}></Share>
            </div>
        )}
        {menuOpen && (
            <CommentMenu handleShareClick={handleShareClick} handleReportClick={handleReportClick}/>
        )}
        {reportOpen && (
            <div className="comment-report-window">
              <Report
                  reportOpen={reportOpen}
                  reportReason={reportReason}
                  reportDescription={reportDescription}
                  setReportReason={setReportReason}
                  setReportDescription={setReportDescription}
                  handleReportSubmit={handleReportSubmit}
              />
            </div>
        )}
      </div>
  );
};