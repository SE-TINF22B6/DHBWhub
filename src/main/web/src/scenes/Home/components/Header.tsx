import React, {useState} from 'react';
import {SearchBar} from "./SearchBar";
import { Link } from 'react-router-dom';
import "./Header.css";
import {Logo} from "./Logo";

export const Header = () => {

  const [newNotifications, setNewNotifications] = useState(true);
  const [loggedIn, setLoggedIn] = useState(true);

  return (
      <div className="header">
        <div className="header-content">
          <Link to="/" className="home-button">
            <Logo/>
          </Link>
          <Link to="/" className="home-button">
            <div className="home-background"></div>
          </Link>
          <Link to="/friends" className="friends-button">
            <div className="friends-background">
              <div className="friends-picture" />
            </div>
          </Link>
          <Link to="/calendar" className="calendar-button">
            <div className="calendar-background">
              <div className="calendar-icon" />
            </div>
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
                <div className="arrow-down"/>
              </div>
          ) : (
              <div className="profile-component">
                <div className="login">
                  <div className="log-in-wrapper">
                    <div className="log-in-text">LOGIN</div>
                  </div>
                </div>
                <div className="sign-up">
                  <div className="sign-up-wrapper">
                    <div className="sign-up-text">SIGN UP</div>
                  </div>
                </div>
              </div>
          )}
        </div>
      </div>
  );
};
