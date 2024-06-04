import React, {useEffect, useState} from 'react';
import './EventDetail.css';
import {Share} from "../../../organisms/share/Share";
import {useLocation} from "react-router-dom";
import {Report} from "../../../organisms/report/Report";
import {PostMenu} from "../../../organisms/post-menu/PostMenu";
import {Tag} from "../../../atoms/Tag";
import LikeService from "../../../services/LikeService";
import ReportService from "../../../services/ReportService";
import { Map } from './Map';
import {LatLngExpression} from "leaflet";
import {Interaction} from "../../../organisms/interaction/Interaction";
import config from "../../../config/config";
import {EventDetailModel} from "../model/EventDetailModel";

export const EventDetail: React.FC<EventDetailModel> = (props: EventDetailModel) => {
  const {
    id,
    title,
    description,
    tags,
    locationProposal,
    likeAmount,
    commentAmount,
    startDate,
    endDate,
  } = props;

  const [likes, setLikes] = useState(likeAmount);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [menuOpen, setMenuOpen] = useState(false);
  const [shareWindowOpen, setShareWindowOpen] = useState(false);
  const currentPageURL = window.location.href;
  const location = useLocation();
  const dateStart: Date = new Date(startDate * 1000);
  const dateEnd: Date = new Date(endDate * 1000);
  const allDay: boolean = dateStart === dateEnd;

  const formatTime = (date: Date): string => {
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const ampm = hours >= 12 ? 'pm' : 'am';
    const formattedHours = hours % 12 || 12;
    const formattedMinutes = minutes.toString().padStart(2, '0');
    return `${formattedHours}:${formattedMinutes} ${ampm}`;
  };

  const formattedStartTime: string = formatTime(dateStart);
  const formattedEndTime: string = formatTime(dateEnd);
  const formattedTime: string = allDay ? 'All day' : `${formattedStartTime} - ${formattedEndTime}`;

  const position: LatLngExpression = [locationProposal.latitude, locationProposal.longitude];

  const [reportOpen, setReportOpen] = useState(false);
  const [reportReason, setReportReason] = useState('');
  const [reportDescription, setReportDescription] = useState('');

  const handleReportClick = (): void => {
    setReportOpen(true);
  };

  const handleReportSubmit = (): void => {
    ReportService.sendReportToBackend(reportReason, reportDescription, id, null,187);
    setReportOpen(!reportOpen);
    setReportReason('');
    setReportDescription('');
  };

  useEffect((): void => {
    const userLikedEvent: string | null = localStorage.getItem(`liked_${id}`);
    if (userLikedEvent) {
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
    fetch(config.apiUrl + `/saveEvent?eventId=${id}`, {
      method: 'POST',
      credentials: 'include',
    })
    .then((): void => {
      alert('EventDetail has been saved!');
    })
    .catch(err => {
      console.error('Error saving the event: ', err);
      alert('Error saving the event');
    });
  };

  const handleUnsaveClick = (): void => {
    fetch(config.apiUrl + `/unsaveEvent?eventId=${id}`, {
      method: 'POST',
      credentials: 'include',
    })
    .then((): void => {
      alert('EventDetail has been unsaved!');
    })
    .catch(err => {
      console.error('Error unsaving the event: ', err);
      alert('Error unsaving the event');
    });
  };

  const handleLike = (): void => {
    LikeService.handleLike(id, userLiked, likes, setLikes, setUserLiked, setHeartClass);
  };

  return (
      <div className="event-detail">
        <div className="event-detail-content">
          <div className="event-detail-left-sidebar">
            <div className="event-detail-data">
              {dateStart.getDate()}.{dateStart.getMonth() + 1}.{dateStart.getFullYear()}
              <br/>
              {allDay ? 'All day' : formattedTime}
            </div>
            <div className="event-detail-tags">
              {tags.map((_tag: string, index: number) => (
                  index < 3 &&
                  <Tag key={index} name={tags[index]} index={index} isEventTag={false}/>
              ))}
            </div>
            <Interaction
                likes={likes}
                userLiked={userLiked}
                heartClass={heartClass}
                comments={commentAmount}
                handleLike={handleLike}
                id={id}
                isHomepage={false}
            />
          </div>
          <div className="event-detail-infos">
              <p className="event-detail-title">{title}</p>
              <p className="event-detail-description">{description}</p>
          </div>
          <div className="event-map">
            <Map position={position} address={locationProposal.location}/>
          </div>
        </div>
        <button className="event-detail-menu-button" onClick={handleMenuClick}>
          <img alt="Menu" src={process.env.PUBLIC_URL + '/assets/menu-dots.svg'}/>
        </button>
        {menuOpen && (
            <PostMenu
                handleShareClick={handleShareClick}
                handleSaveClick={handleSaveClick}
                handleUnsaveClick={handleUnsaveClick}
                handleReportClick={handleReportClick}/>
        )}
        {shareWindowOpen && (
            <div className="event-share-window">
              <Share postId={id} currentPageURL={currentPageURL}></Share>
            </div>
        )}
        <div className="report-window">
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
      </div>
  );
};