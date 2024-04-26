import React, {useEffect, useState} from 'react';
import './Post.css';
import {Share} from "../../../../organisms/share/Share";
import {Link, useLocation} from "react-router-dom";
import {ReportPost} from "../../../../organisms/report-post/ReportPost";
import {PostMenu} from "../../../../organisms/post-menu/PostMenu";
import {Tag} from "../../../../atoms/Tag";
import ReportService from '../../../../services/ReportService';
import LikeService from '../../../../services/LikeService';
import TimeService from "../../../../services/TimeService";

interface PostProps {
  postId: number;
  title: string;
  description: string;
  tags: string[];
  authorUsername: string;
  authorId: number;
  postCreatedTimestamp: string;
  initialLikes: number;
  initialComments: number;
  imageSrc: string;
}

export const Post: React.FC<PostProps> = (props: PostProps) => {
  const {
    postId,
    title,
    description,
    tags,
    authorUsername,
    authorId,
    postCreatedTimestamp,
    initialLikes,
    initialComments,
    imageSrc,
  } = props;
  const [] = useState(false);

  const shortenDescription = (text: string, maxLength: number): string => {
    if (text.length <= maxLength) {
      return text;
    }
    const shortenedText: string = text.slice(0, maxLength);
    const lastSpace: number = shortenedText.lastIndexOf(' ');
    if (lastSpace === -1 || (text[maxLength] !== ' ')) {
      return shortenedText + '...';
    } else {
      return text.slice(0, lastSpace) + '...';
    }
  };

  const formattedTime = TimeService.timeDifference(postCreatedTimestamp);
  const [likes, setLikes] = useState(initialLikes);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(initialComments);
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL = window.location.href;
  const location = useLocation();
  const shortDescription = shortenDescription(description, 190);

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  const handleReportSubmit = (): void => {
    ReportService.sendReportToBackend(reportReason, reportDescription, postId, authorId, 187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    const userLikedPost = localStorage.getItem(`liked_${postId}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, postId]);

  const handleMenuClick = (): void => {
    setMenuOpen(!menuOpen);
    setShareWindowOpen(false);
  };

  const handleShareClick = (): void => {
    setShareWindowOpen(!shareWindowOpen);
  };

  const handleSaveClick = (): void => {
    fetch(`http://localhost:8080/savePost?postId=${postId}`, {
      method: 'POST',
      credentials: 'include',
    })
    .then((): void => {
      alert('PostDetail has been saved!');
    })
    .catch(err => {
      console.error('Error saving the post: ', err);
      alert('Error saving the post');
    });
  };

  const handleLike = (): void => {
    LikeService.handleLike(postId, userLiked, likes, setLikes, setUserLiked, setHeartClass);
  };

  return (
      <div className="post-container">
        <div className="post">
          <Link to={`/post/?id=${postId}`} className="post-button">
            <img className="post-image" alt="Post" src={process.env.PUBLIC_URL + imageSrc}/>
          </Link>
          <img className="post-menu-points" onClick={handleMenuClick} alt="Menu dots" src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
          <div className="post-infos">
            <Link to={`/post/?id=${postId}`} className="post-button">
              <p className="post-title">{title}</p>
            </Link>
            <div className="post-tags">
              {tags.slice(0, 3).map((tag, index) => (
                  <Tag name={tag} key={index} index={index} isEventTag={false}/>
              ))}
            </div>
            <p className="short-description">{shortDescription}</p>
            <div className="footer">
            <Link to={`/user/?name=${authorUsername.toLowerCase().replace(' ', '-')}`} className="author-link" aria-label="To the author">
                {authorUsername}
            </Link>
              &nbsp;· {formattedTime}
            </div>
          </div>
          <div className="interaction-stats">
            <div className="likes">{likes} likes</div>
            <Link to={`/post/?id=${postId}`} className="post-button">
              <div className="comments">{comments} comments</div>
            </Link>
          </div>
          <div className="interaction-symbols">
            <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike} aria-label="Like-Button">
              <div className={`heart-symbol ${heartClass}`}/>
            </button>
            <Link to={`/post/?id=${postId}`} className="post-button" aria-label="To the post">
              <div className="comment-symbol"/>
            </Link>
          </div>
        </div>
        {menuOpen && (
            <PostMenu handleShareClick={handleShareClick} handleSaveClick={handleSaveClick} handleReportClick={handleReportClick}/>
        )}
        {shareWindowOpen && (<Share postId={postId} currentPageURL={currentPageURL}></Share>)}
        {reportOpen && (
            <ReportPost
                reportOpen={reportOpen}
                reportReason={reportReason}
                reportDescription={reportDescription}
                setReportReason={setReportReason}
                setReportDescription={setReportDescription}
                handleReportSubmit={handleReportSubmit}
            />
        )}
      </div>
  );
};