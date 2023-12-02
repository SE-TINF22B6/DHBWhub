import React, {useEffect, useState} from 'react';
import './Post.css';
import {Share} from "./Share";
import {Link, useLocation} from "react-router-dom";
import {ReportPost} from "./ReportPost";
import {PostMenu} from "./PostMenu";

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
  let {
    postId,
    title,
    tags,
    authorUsername,
    authorId,
    initialLikes,
    initialComments,
  } = props;

  const timeDifference = (timestamp: string): string => {
    const postTime: number = new Date(timestamp).getTime();
    const currentTime: number = new Date().getTime();
    const difference: number = currentTime - postTime;

    const seconds: number = Math.floor(difference / 1000);
    const minutes: number = Math.floor(seconds / 60);
    const hours: number = Math.floor(minutes / 60);
    const days: number = Math.floor(hours / 24);

    if (days > 0) {
      return `${days} day${days > 1 ? 's' : ''} ago`;
    } else if (hours > 0) {
      return `${hours} hour${hours > 1 ? 's' : ''} ago`;
    } else if (minutes > 0) {
      return `${minutes} minute${minutes > 1 ? 's' : ''} ago`;
    } else {
      return 'Just now';
    }
  };

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

  const formattedTime = timeDifference(props.postCreatedTimestamp);
  const [likes, setLikes] = useState(initialLikes);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(initialComments);
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL = window.location.href;
  const [currentLocation, setCurrentLocation] = useState('');
  const location = useLocation();
  const shortDescription = shortenDescription(props.description, 190);

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  function sendReportToBackend(reportReason: string, reportDescription: string, postId: number, authorId: number, userId: number): void {
    const report = {
      reportReason: reportReason,
      reportDescription: reportDescription,
      postId: postId,
      authorId: authorId,
      userId: userId,
    };

    fetch("http://localhost:8080/report", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(report),
      credentials: 'include',
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      console.log(data);
    })
    .catch(error => {
      console.error('Fetch error:', error);
    });
  }

  const handleReportSubmit = (): void => {
    sendReportToBackend(reportReason, reportDescription, props.postId, props.authorId, 187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    setCurrentLocation(location.pathname);
    const userLikedPost = localStorage.getItem(`liked_${postId}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, postId]);

  useEffect((): void => {
    setCurrentLocation(location.pathname);
  }, [location]);

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
      alert('Post has been saved!');
    })
    .catch(err => {
      console.error('Error saving the post: ', err);
      alert('Error saving the post');
    });
  };

  const handleLike = (): void => {
    if (!userLiked) {
      setLikes(likes + 1);
      setUserLiked(true);
      setHeartClass('heart-filled');
      localStorage.setItem(`liked_${postId}`, 'true');
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);
    }
  };

  return (
      <div className="post-container">
        <div className="post">
          <div className="picture" style={{backgroundImage: `url(${props.imageSrc})`}}></div>
          <button className="post-menu" onClick={handleMenuClick}>...</button>

          <div className="post-infos">
            <Link to={`/post/?id=${postId}`} className="post-button">
              <p className="post-title">{title}</p>
            </Link>
            <div className="tags">
              {tags.length > 0 &&
                  <Link to={`/tag/?name=${tags[0].toLowerCase().replace(' ', '-')}`} className="post-tag-button">
                    <div className="post-tag">{tags[0]}</div>
                  </Link>
              }
              {tags.length > 1 &&
                  <Link to={`/tag/?name=${tags[1].toLowerCase().replace(' ', '-')}`} className="post-tag-button">
                    <div className="post-tag">{tags[1]}</div>
                  </Link>
              }
              {tags.length > 2 &&
                  <Link to={`/tag/?name=${tags[2].toLowerCase().replace(' ', '-')}`} className="post-tag-button">
                    <div className="post-tag">{tags[2]}</div>
                  </Link>
              }
            </div>
            <p className="short-description">{shortDescription}</p>
            <div className="seperator"/>

            <Link to={`/user/?name=${authorUsername.toLowerCase().replace(' ', '-')}`} className="author-link">
              <div className="author-date">{authorUsername} · {formattedTime}</div>
            </Link>

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

          <div className="interaction-stats">
            <div className="stats">
              <div className="likes">{likes} likes</div>
              <Link to={`/post/?id=${postId}`} className="post-button">
                <div className="comments">{comments} comments</div>
              </Link>
            </div>
          </div>
          <div className="interaction-symbols">
            <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike}>
              <div className={`heart-symbol ${heartClass}`} />
            </button>
            <Link to={`/post/?id=${postId}`} className="post-button">
              <div className="comment-symbol"/>
            </Link>
          </div>
        </div>
        {menuOpen && (
            <PostMenu handleShareClick={handleShareClick} handleSaveClick={handleSaveClick} handleReportClick={handleReportClick}/>
        )}
      </div>
  );
};
