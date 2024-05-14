import React, {useEffect, useState} from 'react';
import './PostDetail.css';
import {Share} from "../../organisms/share/Share";
import {Link, useLocation} from "react-router-dom";
import {Report} from "../../organisms/report/Report";
import {PostMenu} from "../../organisms/post-menu/PostMenu";
import {Tag} from "../../atoms/Tag";
import LikeService from "../../services/LikeService";
import TimeService from "../../services/TimeService";
import ReportService from "../../services/ReportService";
import {PostDetailModel} from "./models/PostDetailModel";
import {Interaction} from "../../organisms/interaction/Interaction";
import config from "../../config/config";

export const PostDetail: React.FC<PostDetailModel> = (props: PostDetailModel) => {
  const {
    id,
    title,
    description,
    tags,
    likeAmount,
    commentAmount,
    timestamp,
    image,
    accountId,
    username,
    userImage,
    setScrollToComments
  } = props;

  const formattedTime = TimeService.timeDifference(new Date(timestamp).toISOString());
  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(commentAmount);
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL = window.location.href;
  const location = useLocation();

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  const handleReportSubmit = (): void => {
    ReportService.sendReportToBackend(reportReason, reportDescription, id, accountId, 187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    const userLikedPost = localStorage.getItem(`liked_${id}`);
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

  const handleSaveClick = (): void => {
    fetch(config.apiUrl + 'savePost?postId=${id}', {
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
    LikeService.handleLike(props.id, userLiked, likes, setLikes, setUserLiked, setHeartClass);
  };

  const handleCommentClick = () => {
    setScrollToComments(true);
  };

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
            <Interaction likes={likes} userLiked={userLiked} heartClass={heartClass} comments={comments} handleLike={handleLike} id={id} isHomepage={false}/>
          </div>

          <div className="post-detail-infos">
            <p className="post-detail-title">{title}</p>
            <p className="post-detail-description">{description}</p>
          </div>

          {image && <img className="post-detail-picture" alt="Post" src={image}></img>}
        </div>

        <button className="post-detail-menu-button" onClick={handleMenuClick}>
          <img alt="Menu" src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
        </button>

        {menuOpen && (
            <div className="post-detail-menu">
              <PostMenu handleShareClick={handleShareClick} handleSaveClick={handleSaveClick} handleReportClick={handleReportClick}/>
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