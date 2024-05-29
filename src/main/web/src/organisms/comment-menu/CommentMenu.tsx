import React from 'react';
import './CommentMenu.css';
import {isUserLoggedIn} from "../../services/AuthService";
import {Tooltip} from "react-tooltip";
import config from "../../config/config";

interface CommentMenuProps {
  handleReportClick: () => void;
}

export const CommentMenu: React.FC<CommentMenuProps> = ({handleReportClick}: CommentMenuProps) => {

  return (
      <div className="comment-menu">
        <button className="comment-report-button" onClick={handleReportClick} data-tooltip-id="report"
                  data-tooltip-content={config.tooltipMessage}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/report.svg'} alt="Report" className="comment-report-image"></img>
            <div className="comment-report-text">Report comment</div>
        </button>
        {!isUserLoggedIn() && (
            <Tooltip variant={"light"} id="report" place="top" style={{ marginLeft: "-50px", marginTop: "20px", zIndex: 999 }}/>
        )}
      </div>
  );
};
