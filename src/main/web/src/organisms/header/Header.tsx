import React, {useEffect, useState} from 'react';
import {SearchBar} from './SearchBar';
import {Link, useLocation} from 'react-router-dom';
import Login from '../../scenes/Home/components/login/Login';
import SignUp from "../../scenes/Home/components/signup/SignUp";
import {Notifications} from "../../scenes/Home/components/Notifications";
import "./Header.css";

export const Header = () => {

  const [loggedIn, setLoggedIn] = useState(false);
  const [currentLocation, setCurrentLocation] = useState('');
  const location = useLocation();

  useEffect((): void => {
    setCurrentLocation(location.pathname);
  }, [location]);

  return (
      <div className="header">
        <Link to="/" aria-label="To the homepage">
          <div className='logo'>
            <span>DHBW </span>
            <span>hub</span>
          </div>
        </Link>
        <Link to="/" aria-label="To the homepage">
          <div className={`home-background ${currentLocation === '/' ? 'active' : ''}`}></div>
        </Link>
        <Link to="/friends" aria-label="To the friends page">
          <div className={`friends-background ${currentLocation === '/friends' ? 'active' : ''}`}></div>
        </Link>
        <Link to="/calendar" aria-label="To the calendar page">
          <div className={`calendar-background ${currentLocation === '/calendar' ? 'active' : ''}`}/>
        </Link>
        <SearchBar/>
        <div className="notifications-button-container">
          <button className="notifications-button" aria-label="Notifications-Button">
            <div className="notifications-background" />
          </button>
        </div>
        {loggedIn ? (
            <div className="profile-component">
              <Link to="/profile" aria-label="To the profile" onClick={() => setLoggedIn(false)}>
                <img className="profile-picture-header" alt="Profile" src={process.env.PUBLIC_URL + '/assets/home/header/profile-header.svg'}/>
              </Link>
            </div>
        ) : (
            <div className="profile-component">
              <button className="header-menu" onClick={() => setLoggedIn(true)} aria-label="ArrowDown-Button">
                <img src={process.env.PUBLIC_URL + '/assets/home/header/arrow-down.svg'} alt="Arrow Down" className="arrow-down"/>
              </button>
              <Login/>
              <SignUp/>
            </div>
        )}
      </div>
  );
};
