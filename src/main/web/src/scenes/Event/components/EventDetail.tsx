import React, {useEffect, useState} from 'react';
import './EventDetail.css';
import {Share} from "../../../organisms/share/Share";
import {useLocation} from "react-router-dom";
import {Tag} from "../../../atoms/Tag";
import LikeService from "../../../services/LikeService";
import { Map } from './Map';
import {LatLngExpression} from "leaflet";
import {Interaction} from "../../../organisms/interaction/Interaction";
import {EventDetailModel} from "../model/EventDetailModel";
import {EventMenu} from "./EventMenu";

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
  const currentPageURL: string = window.location.href;
  const location = useLocation();
  const dateStart: Date = new Date(startDate * 1000);
  const dateEnd: Date = new Date(endDate * 1000);
  const allDay: boolean = dateStart === dateEnd;

  const formatTime = (date: Date): string => {
    const hours: number = date.getHours();
    const minutes: number = date.getMinutes();
    const ampm = hours >= 12 ? 'pm' : 'am';
    const formattedHours: number = hours % 12 || 12;
    const formattedMinutes: string = minutes.toString().padStart(2, '0');
    return `${formattedHours}:${formattedMinutes} ${ampm}`;
  };

  const formattedStartTime: string = formatTime(dateStart);
  const formattedEndTime: string = formatTime(dateEnd);
  const formattedTime: string = allDay ? 'All day' : `${formattedStartTime} - ${formattedEndTime}`;

  const position: LatLngExpression = [locationProposal.latitude, locationProposal.longitude];

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
            <EventMenu handleShareClick={handleShareClick}/>
        )}
        {shareWindowOpen && (
            <div className="event-share-window">
              <Share eventId={id} currentPageURL={currentPageURL}></Share>
            </div>
        )}
      </div>
  );
};