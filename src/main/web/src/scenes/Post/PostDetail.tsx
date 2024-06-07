import React, {useCallback, useEffect, useState} from 'react';
import {Share} from "../../organisms/share/Share";
import {Link, useLocation} from "react-router-dom";
import {Report} from "../../organisms/report/Report";
import {PostMenu} from "../../organisms/post-menu/PostMenu";
import {Tag} from "../../atoms/Tag";
import {handleLike} from "../../services/LikeService";
import {timeDifference} from "../../services/TimeService";
import {PostDetailModel} from "./models/PostDetailModel";
import {Interaction} from "../../organisms/interaction/Interaction";
import {getJWT, getUserId} from "../../services/AuthService";
import config from "../../config/config";
import './PostDetail.css';
import {sendReportToBackend} from "../../services/ReportService";

export const PostDetail: React.FC<PostDetailModel> = (props: PostDetailModel) => {
  const {
    id,
    title,
    description,
    tags,
    likeAmount,
    commentAmount,
    timestamp,
    postImage,
    accountId,
    username,
    userImage,
  } = props;

  const formattedTime = timeDifference(new Date(timestamp));
  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(commentAmount);
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL: string = window.location.href;
  const location = useLocation();
  const userId: number | null = getUserId();
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  const handleReportSubmit = (): void => {
    sendReportToBackend(reportReason, reportDescription, id, accountId, "post");
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    const userLikedPost: string | null = localStorage.getItem(`post_liked_${id}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, id]);

  const handleMenuClick = (): void => {
    setMenuOpen(!menuOpen);
    setShareWindowOpen(false);
  };

  const handleShareClick = (): void => {
    setShareWindowOpen(!shareWindowOpen);
  };

  const handleSaveClick = async (): Promise<void> => {
    try {
      const response: Response = await fetch(config.apiUrl + "saved-post", {
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify({
          postId: id,
          userId: userId,
        }),
        headers: headersWithJwt
      });
      if (response.ok) {
        console.log('Post has been saved!');
        alert('Post has been saved!');
      } else {
        console.error('Error saving the post: ', response.statusText);
        alert('Error saving the post');
      }
    } catch (err) {
      console.error('Error saving the post: ', err);
      alert('Error saving the post');
    }
  };

  const handleUnsaveClick = async (): Promise<void> => {
    try {
      const response: Response = await fetch(config.apiUrl + `saved-post`, {
        method: 'DELETE',
        credentials: 'include',
        body: JSON.stringify({
          postId: id,
          userId: userId,
        }),
        headers: {
          'Authorization': 'Bearer ' + jwt,
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': "*",
          'Accept': 'application/json'
        }
      });
      if (response.ok) {
        alert('Post has been unsaved!');
      } else {
        console.error('Error unsaving the post: ', response.statusText);
        alert('Error unsaving the post');
      }
    } catch (err) {
      console.error('Error unsaving the post: ', err);
    }
  };

  const handleClose = useCallback((): void => {
    setReportOpen(false);
  }, [setReportOpen]);

  useEffect(() => {
    const handleEsc = (event: KeyboardEvent): void => {
      if (event.key === 'Escape' && reportOpen) {
        handleClose();
      }
    };
    document.addEventListener('keydown', handleEsc);
    return (): void => {
      document.removeEventListener('keydown', handleEsc);
    };
  }, [reportOpen, handleClose]);

  return (
      <div className="post-detail">
        <div className="post-detail-content">
          <div className="post-detail-sidebar">
            <div className="post-detail-data">
              {userImage && <img className="profile-picture" alt="Profile" src={userImage}/>}
              <div>
                <Link
                    to={`/user/?name=${username.toLowerCase().replace(' ', '-')}`}
                    className="author-link" aria-label="To the author">{username}
                </Link>
                <br/>
                {formattedTime}
              </div>
            </div>
            <div className="post-detail-tags">
              {tags.map((_tag: string, index: number) => (
                  index < 3 &&
                  <Tag name={tags[index]} key={index} index={index} isEventTag={false}/>
              ))}
            </div>
            <Interaction
                likes={likes}
                userLiked={userLiked}
                heartClass={heartClass}
                comments={comments}
                handleLike={() => handleLike(id, "post", likes, setLikes, setUserLiked, setHeartClass)}
                id={id}
                isHomepage={false}
            />
          </div>

          <div className="post-detail-infos">
            <p className="post-detail-title">{title}</p>
            <p className="post-detail-description">{description}</p>
          </div>
          {postImage ? (
              <img className="post-detail-picture" alt="Post" src={postImage} />
          ) : (
              <div className="post-detail-placeholder"></div>
          )}
        </div>

        <button className="post-detail-menu-button" onClick={handleMenuClick}>
          <img alt="Menu" src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
        </button>

        {menuOpen && (
            <div className="post-detail-menu">
              <PostMenu
                  handleShareClick={handleShareClick}
                  handleSaveClick={handleSaveClick}
                  handleUnsaveClick={handleUnsaveClick}
                  handleReportClick={handleReportClick}
              />
            </div>
        )}
        {shareWindowOpen && (
            <div className="post-detail-share">
              <Share postId={id} currentPageURL={currentPageURL}></Share>
            </div>
        )}
        {reportOpen && (
            <div className="post-detail-report-window">
              <Report
                  reportOpen={reportOpen}
                  setReportReason={setReportReason}
                  setReportDescription={setReportDescription}
                  handleReportSubmit={handleReportSubmit}
                  handleClose={handleClose}
              />
            </div>
        )}
      </div>
  );
};