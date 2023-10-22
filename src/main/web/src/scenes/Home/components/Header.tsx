import React from 'react';
import {SearchBar} from "./SearchBar";
import "./Header.css";
import {Logo} from "./Logo";

export const Header = () => {

  return (
      <div className="header">
        <div className="header-content">
          <Logo/>
          <div className="home-button"></div>
          <div className="friends-button">
            <div className="friends-picture"/>
          </div>
          <div className="calendar-button">
            <div className="calendar-icon"/>
          </div>
          <div className="searchbar">
            <SearchBar/>
          </div>
          <div className="notifications-button"/>
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
            <div className="profile-picture"></div>
          </div>
        </div>
      </div>
  );
};
