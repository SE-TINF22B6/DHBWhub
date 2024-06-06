import React from 'react';
import './EventMenu.css';

interface EventMenuProps {
  handleShareClick: () => void;
}

export const EventMenu: React.FC<EventMenuProps> = ({handleShareClick}: EventMenuProps) => {
  return (
      <div className="event-menu">
        <div className="event-menu-text">
          <button className="event-menu-button" onClick={handleShareClick}>
            <img src={process.env.PUBLIC_URL + '/assets/home/post/share.svg'} alt="Share" className="event-share-image"></img>
            <div className="event-share-text">Share event</div>
          </button>
        </div>
      </div>
  );
};
