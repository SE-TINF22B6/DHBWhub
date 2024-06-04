import React, {useEffect, useState} from 'react';
import './Post.css';
import {Share} from "../../../../organisms/share/Share";
import {Link, useLocation} from "react-router-dom";
import {Report} from "../../../../organisms/report/Report";
import {PostMenu} from "../../../../organisms/post-menu/PostMenu";
import {Tag} from "../../../../atoms/Tag";
import ReportService from '../../../../services/ReportService';
import LikeService from '../../../../services/LikeService';
import TimeService from "../../../../services/TimeService";
import {PostModel} from "./models/PostModel";
import {Interaction} from "../../../../organisms/interaction/Interaction";
import DescriptionService from "../../../../services/DescriptionService";
import {useMediaQuery} from "@mui/system";
import config from "../../../../config/config";
import {getJWT, getUserId} from "../../../../services/AuthService";

export const Post: React.FC<PostModel> = (props: PostModel) => {
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
    username
  } = props;

  const matches: boolean = useMediaQuery('(max-width: 412px)')
  const formattedTime: string = TimeService.timeDifference(new Date(timestamp));
  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(commentAmount);
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL: string = window.location.href;
  const location = useLocation();
  const [shortDescription, setShortDescription] = useState('');
  const userId: number | null =  getUserId();
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(!reportOpen);
  };

  const handleReportSubmit = (): void => {
    ReportService.sendReportToBackend(reportReason, reportDescription, id, accountId, 187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    const userLikedPost: string | null = localStorage.getItem(`liked_${id}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, id]);

  useEffect(() => {
    setShortDescription(DescriptionService.shortenDescription(description, image ? 190 : matches ? 190 : 280));
  }, [description, image, matches]);

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
        headers: headersWithJwt
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

  const handleLike = (): void => {
    LikeService.handleLike(id, userLiked, likes, setLikes, setUserLiked, setHeartClass);
  };

  function getMarginLeft() {
    if (matches) {
      return tags ? '110px' : '0';
    } else {
      return image ? '180px' : '10px';
    }
  }

  function getWidth() {
    if (matches) {
      return image ? '240px' : '260px';
    } else {
      return image ? '440px' : '600px';
    }
  }

  function getMarginTop() {
    if (matches) {
      return image ? '140px' : '5px';
    } else {
      return '0';
    }
  }

  return (
      <div className="post-container">
        <div className="post">
          <Link to={`/post/?id=${id}`} aria-label="To the post" className="post-button">
            {image && <img className="post-image" alt="Post" src={image} loading="lazy" />}
          </Link>
          <img className="post-menu-points" onClick={handleMenuClick} alt="Menu dots"
               src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
          <div className="post-infos" style={{marginLeft: getMarginLeft()}}>
            <Link to={`/post/?id=${id}`} className="post-button">
              <p className="post-title">{title}</p>
            </Link>
            <div className="post-tags" style={{marginTop: getMarginTop()}}>
              {tags && tags.slice(0, 3).map((tag: string, index: number) => (
                  <Tag name={tag} key={index} index={index} isEventTag={false}/>
              ))}
            </div>
            <p className="short-description" style={{width: getWidth()}}>{shortDescription}</p>
            <div className="footer">
              <Link to={`/user/?id=${accountId}`} className="author-link" aria-label="To the author">
                {username}
              </Link>
              &nbsp;· {formattedTime}
            </div>
          </div>
          <div className="home-post-interaction">
            <Interaction likes={likes} userLiked={userLiked} heartClass={heartClass} comments={comments} handleLike={handleLike} id={id}
                         isHomepage={true}/>
          </div>
        </div>

        {menuOpen && (
            <div className="post-menu-container">
              <PostMenu handleShareClick={handleShareClick} handleSaveClick={handleSaveClick} handleUnsaveClick={handleUnsaveClick} handleReportClick={handleReportClick}/>
            </div>
        )}
        {shareWindowOpen && (
            <div className="post-share-container">
              <Share postId={id} currentPageURL={currentPageURL}></Share>
            </div>
        )}
        {reportOpen && (
            <Report
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