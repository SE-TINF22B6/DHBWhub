import React, {useEffect, useState} from 'react';
import {Link, useLocation} from 'react-router-dom';
import {Notifications} from "./Notifications";
import "./MobileFooter.css";

export const MobileFooter = () => {

  const [showNotifications, setShowNotifications] = useState(false);
  const [currentLocation, setCurrentLocation] = useState('');
  const location = useLocation();

  useEffect((): void => {
    setCurrentLocation(location.pathname);
  }, [location]);

  const handleNotificationsButtonClick = (): void => {
    setShowNotifications(!showNotifications);
  };

  return (
      <div className="mobile-footer">
        <Link className={`mobile-footer-home-background ${currentLocation === '/' ? 'active' : ''}`} to="/"
              aria-label="To the homepage">
          <img alt="Home" src={process.env.PUBLIC_URL + '/assets/header/home.svg'}/>
        </Link>
        <Link className={`mobile-footer-friends-background ${currentLocation === '/friends' ? 'active' : ''}`} to="/friends"
              aria-label="To the friends page">
          <img alt="Friends" src={process.env.PUBLIC_URL + '/assets/header/friends.svg'}/>
        </Link>
        <Link className={`mobile-footer-calendar-background ${currentLocation === '/calendar' ? 'active' : ''}`} to="/calendar"
              aria-label="To the calendar page">
          <img alt="Calendar" src={process.env.PUBLIC_URL + '/assets/header/calendar.svg'}/>
        </Link>
        <div className="mobile-footer-notifications-button-container">
          {showNotifications ? (
              <button className="mobile-footer-notifications-button-new" onClick={handleNotificationsButtonClick}>
                <img alt="New notifications"
                     src={process.env.PUBLIC_URL + '/assets/header/notifications.svg'}/>
                <img className="mobile-footer-notifications-dot" alt="New notifications"
                     src={process.env.PUBLIC_URL + '/assets/header/notifications-dot.svg'}/>
              </button>
          ) : (
              <button className="mobile-footer-notifications-button" onClick={handleNotificationsButtonClick}
                      aria-label="Notifications-Button">
                <img alt="New notifications" src={process.env.PUBLIC_URL + '/assets/header/notifications.svg'}/>
              </button>
          )}
        </div>
        {showNotifications && <Notifications showNotifications={showNotifications}/>}
      </div>
  );
};