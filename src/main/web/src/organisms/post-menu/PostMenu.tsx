import React, {useEffect, useMemo, useState} from 'react';
import './PostMenu.css';
import {getJWT, getUserId, isUserLoggedIn} from "../../services/AuthService";
import {Tooltip} from "react-tooltip";
import config from "../../config/config";

interface PostMenuProps {
  handleShareClick: () => void;
  handleSaveClick: () => void;
  handleUnsaveClick: () => void;
  handleReportClick: () => void;
  id: number;
}

export const PostMenu: React.FC<PostMenuProps> = ({handleShareClick, handleSaveClick, handleUnsaveClick, handleReportClick, id}: PostMenuProps) => {
  const [postSaved, setPostSaved] = useState(false);
  const userId: number | null = getUserId();
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);

  useEffect((): void => {
    const fetchIsPostSaved = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `saved-post/is-saved`, {
          method: 'POST',
          headers: headersWithJwt,
          body: JSON.stringify({
            postId: id,
            userId: userId,
          }),
        });
        if (response.ok) {
          const isSaved = await response.json();
          setPostSaved(isSaved);
        }
      } catch (error) {
        console.error("Failed to fetch if post with id " + id + " is saved.");
      }
    };
    fetchIsPostSaved();
  }, [headersWithJwt, id, userId]);


  const handleSaveButtonClick = (): void => {
    if (postSaved) {
      handleUnsaveClick();
    } else {
      handleSaveClick();
    }
    setPostSaved(!postSaved);
  };

  return (
      <div className="post-menu">
        <div className="post-menu-text">
          <button className="post-menu-button" onClick={handleShareClick}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/share.svg'} alt="Share" className="post-share-image"></img>
            <div className="post-share-text">Share post</div>
          </button>
          <button className="post-menu-button" onClick={handleSaveButtonClick} data-tooltip-id="save-post"
                  data-tooltip-content={config.tooltipMessage}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/save.svg'} alt="Save" className="post-save-image"></img>
            {!postSaved && (
                <div className="post-save-text">Save post</div>
            )}
            {postSaved && (
                <div className="post-unsave-text">Unsave post</div>
            )}
          </button>
          {!isUserLoggedIn() && (
              <Tooltip variant={"light"} id="save-post" place="top" style={{ marginLeft: "-50px", marginTop: "20px", zIndex: 999 }}/>
          )}
          <button className="post-menu-button" onClick={handleReportClick} data-tooltip-id="report"
                  data-tooltip-content={config.tooltipMessage}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/report.svg'} alt="Report" className="post-report-image"></img>
            <div className="post-report-text">Report post</div>
          </button>
          {!isUserLoggedIn() && (
              <Tooltip variant={"light"} id="report" place="top" style={{ marginLeft: "-50px", marginTop: "20px", zIndex: 999 }}/>
          )}
        </div>
      </div>
  );
};
