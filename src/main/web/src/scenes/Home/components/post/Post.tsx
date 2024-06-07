import React, {useCallback, useEffect, useRef, useState} from 'react';
import './Post.css';
import {Share} from "../../../../organisms/share/Share";
import {Link, useLocation} from "react-router-dom";
import {Report} from "../../../../organisms/report/Report";
import {PostMenu} from "../../../../organisms/post-menu/PostMenu";
import {Tag} from "../../../../atoms/Tag";
import {handleLike} from '../../../../services/LikeService';
import {timeDifference} from "../../../../services/TimeService";
import {PostModel} from "./models/PostModel";
import {Interaction} from "../../../../organisms/interaction/Interaction";
import {shortenDescription} from "../../../../services/DescriptionService";
import {useMediaQuery} from "@mui/system";
import config from "../../../../config/config";
import {getJWT, getUserId} from "../../../../services/AuthService";
import {sendReportToBackend} from "../../../../services/ReportService";

export const Post: React.FC<PostModel> = (props: PostModel) => {
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
    username
  } = props;

  const matches: boolean = useMediaQuery('(max-width: 412px)')
  const formattedTime: string = timeDifference(new Date(timestamp));
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
  const [imageWidth, setImageWidth] = useState<number | null>(null);

  const handleReportClick = (): void => {
    setReportOpen(!reportOpen);
  };

  const handleReportSubmit = (): void => {
    sendReportToBackend(reportReason, reportDescription, id, accountId, "post");
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');

  useEffect((): void => {
    const userLikedPost: string | null = localStorage.getItem(`post_liked_${id}`);
    if (userLikedPost) {
      setUserLiked(true);
      setHeartClass('heart-filled');
    }
  }, [location, id]);

  useEffect((): void => {
    setShortDescription(shortenDescription(description, postImage ? 190 : matches ? 190 : 280));
  }, [description, postImage, matches]);

  const handleMenuClick = (): void => {
    setMenuOpen(!menuOpen);
    setShareWindowOpen(false);
  };

  const handleShareClick = (): void => {
    setShareWindowOpen(!shareWindowOpen);
  };

  const imageRef = useRef(null);

  useEffect((): void => {
    const imageElement = imageRef.current;
    if (imageElement) {
      const computedStyle: CSSStyleDeclaration = window.getComputedStyle(imageElement);
      const width: number = parseInt(computedStyle.getPropertyValue('width'));
      setImageWidth(width);
    }
  }, []);

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

  function getMarginLeft() {
    if (matches) {
      return tags ? '110px' : '0';
    } else {
      return postImage ? (imageWidth ? `${imageWidth + 20}px` : '280px') : '10px';
    }
  }

  function getWidth() {
    if (matches) {
      return postImage ? '240px' : '260px';
    } else {
      return postImage ? '310px' : '600px';
    }
  }

  function getMarginTop() {
    if (matches) {
      return postImage ? '140px' : '5px';
    } else {
      return '0';
    }
  }

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
      <div className="post-container">
        <div className="post">
          <Link to={`/post/?id=${id}`} aria-label="To the post" className="post-button">
            {postImage && <img className="post-image" ref={imageRef} alt="Post" src={postImage} loading="lazy" />}
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

            <p className="short-description" style={{ width: getWidth() }}>
              {postImage ? shortenDescription(shortDescription, 150) : shortDescription}
            </p>

            <div className="footer">
              <Link to={`/user/?id=${accountId}`} className="author-link" aria-label="To the author">
                {username}
              </Link>
              &nbsp;· {formattedTime}
            </div>
          </div>
          <div className="home-post-interaction">
            <Interaction
                likes={likes}
                userLiked={userLiked}
                heartClass={heartClass}
                comments={comments}
                handleLike={() => handleLike(id, "post", likes, setLikes, setUserLiked, setHeartClass)}
                id={id}
                isHomepage={true}/>
          </div>
        </div>

        {menuOpen && (
            <div className="post-menu-container">
              <PostMenu
                  handleShareClick={handleShareClick}
                  handleSaveClick={handleSaveClick}
                  handleUnsaveClick={handleUnsaveClick}
                  handleReportClick={handleReportClick}
                  id={id}
              />
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
                setReportReason={setReportReason}
                setReportDescription={setReportDescription}
                handleReportSubmit={handleReportSubmit}
                handleClose={handleClose}
            />
        )}
      </div>
  );
};