import React, {useEffect, useState} from 'react';
import {SearchBar} from "./SearchBar";
import {Link, useLocation} from 'react-router-dom';
import "./Header.css";
import {Logo} from "./Logo";

export const Header = () => {

  const [newNotifications, setNewNotifications] = useState(true);
  const [loggedIn, setLoggedIn] = useState(false);
  const [currentLocation, setCurrentLocation] = useState('');

  const location = useLocation();

  useEffect(() => {
    setCurrentLocation(location.pathname);
  }, [location]);

  return (
      <div className="header">
        <div className="header-content">
          <Link to="/" className="home-button">
            <Logo/>
          </Link>
          <Link to="/" className="home-button">
            <div className={`home-background ${currentLocation === '/' ? 'active' : ''}`}></div>
          </Link>
          <Link to="/friends" className="friends-button">
            <div className={`friends-background ${currentLocation === '/friends' ? 'active' : ''}`}></div>
          </Link>
          <Link to="/calendar" className="calendar-button">
            <div className={`calendar-background ${currentLocation === '/calendar' ? 'active' : ''}`}/>
          </Link>

          <SearchBar/>

          <div style={{display: 'flex', alignItems: 'center'}}>
            {newNotifications ? (
                <button className="notifications-button-new" onClick={() => setNewNotifications(false)}>
                  <div className="notifications-new-background"/>
                </button>
            ) : (
                <button className="notifications-button" onClick={() => setNewNotifications(true)}>
                  <div className="notifications-background"/>
                </button>
            )}
          </div>

          {loggedIn ? (
              <div className="profile-component">
                <div className="profile-picture-header"></div>
                <div className="username">Test user</div>
                <button className="header-menu" onClick={() => setLoggedIn(false)}>
                  <div className="arrow-down"/>
                </button>
              </div>
          ) : (
              <div className="profile-component">
                <button className="login" onClick={() => setLoggedIn(true)}>
                  <div className="log-in-wrapper">
                    <div className="log-in-text">LOGIN</div>
                  </div>
                </button>
                <button className="sign-up" onClick={() => setLoggedIn(true)}>
                  <div className="sign-up-wrapper">
                    <div className="sign-up-text">SIGN UP</div>
                  </div>
                </button>
              </div>
          )}
        </div>
      </div>
  );
};
