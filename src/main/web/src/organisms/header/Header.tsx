import React, {useEffect, useState} from 'react';
import {SearchBar} from './SearchBar';
import {Link, useLocation} from 'react-router-dom';
import ModalLoginContainer from '../../scenes/Home/components/login/ModalLoginContainer';
import SignUp from "../../scenes/Home/components/signup/SignUp";
import {Notifications} from "./Notifications";
import "./Header.css";
import ModalSignUpContainer from "../../scenes/Home/components/signup/ModalSignUpContainer";

export const Header = () => {

    const [showNotifications, setShowNotifications] = useState(false);
    const [loggedIn, setLoggedIn] = useState(false);
    const [currentLocation, setCurrentLocation] = useState('');
    const location = useLocation();

    useEffect((): void => {
        setCurrentLocation(location.pathname);
    }, [location]);

    const handleNotificationsButtonClick = (): void => {
        setShowNotifications(!showNotifications);
    };
    return (
        <div className="header">
            <Link to="/" aria-label="To the homepage">
                <div className='logo'>
                    <span>DHBW </span>
                    <span>hub</span>
                </div>
            </Link>
            <Link className={`home-background ${currentLocation === '/' ? 'active' : ''}`} to="/"
                  aria-label="To the homepage">
                <img alt="Home" src={process.env.PUBLIC_URL + '/assets/header/home.svg'}/>
            </Link>
            <Link className={`friends-background ${currentLocation === '/friends' ? 'active' : ''}`} to="/friends"
                  aria-label="To the friends page">
                <img alt="Friends" src={process.env.PUBLIC_URL + '/assets/header/friends.svg'}/>
            </Link>
            <Link className={`calendar-background ${currentLocation === '/calendar' ? 'active' : ''}`} to="/calendar"
                  aria-label="To the calendar page">
                <img alt="Calendar" src={process.env.PUBLIC_URL + '/assets/header/calendar.svg'}/>
            </Link>
            <SearchBar/>
            <div className="notifications-button-container">
                {showNotifications ? (
                    <button className="notifications-button-new" onClick={handleNotificationsButtonClick}>
                        <img alt="New notifications"
                             src={process.env.PUBLIC_URL + '/assets/home/header/notifications.svg'}/>
                        <img className="notifications-dot" alt="New notifications"
                             src={process.env.PUBLIC_URL + '/assets/header/notifications-dot.svg'}/>
                    </button>
                ) : (
                    <button className="notifications-button" onClick={handleNotificationsButtonClick}
                            aria-label="Notifications-Button">
                        <img alt="New notifications" src={process.env.PUBLIC_URL + '/assets/header/notifications.svg'}/>
                    </button>
                )}
            </div>
            {showNotifications && <Notifications showNotifications={showNotifications}/>}
            {loggedIn ? (
                <div className="profile-component">
                    <Link to="/profile" aria-label="To the profile" onClick={() => setLoggedIn(false)}>
                        <img className="profile-picture-header" alt="Profile"
                             src={process.env.PUBLIC_URL + '/assets/profile.svg'}/>
                    </Link>
                </div>
            ) : (
                <div className="profile-component">
                    <button className="header-menu" onClick={() => setLoggedIn(true)} aria-label="ArrowDown-Button">
                        <img src={process.env.PUBLIC_URL + '/assets/header/arrow-down.svg'} alt="Arrow Down"
                             className="arrow-down"/>
                    </button>
                    <ModalLoginContainer/>
                    <ModalSignUpContainer/>
                </div>
            )}
        </div>
    );
};