import React, {useEffect, useState} from 'react';
import {SearchBar} from './SearchBar';
import {Link, useLocation} from 'react-router-dom';
import ModalLoginContainer from '../login/ModalLoginContainer';
import {Notifications} from "./Notifications";
import SignUp from "../signup/SignUp";
import {isUserLoggedIn, isTokenValid, logout} from "../../services/AuthService";
import "./Header.css";
import {Tooltip} from "react-tooltip";
import config from "../../config/config";
import {fetchUserImage} from "../../services/ProfilePictureService";
import {fetchNotifications} from "../../services/NotificationsService";
import {NotificationModel} from "./model/NotificationModel";

export const Header = () => {
    const [notifications, setNotifications] = useState<NotificationModel[]>([]);
    const [showNotifications, setShowNotifications] = useState(false);
    const [currentLocation, setCurrentLocation] = useState('');
    const [userImage, setUserImage] = useState(localStorage.getItem('userImage') || "data:image/svg+xml;base64,PHg==");
    const location = useLocation();

    useEffect((): void => {
        setCurrentLocation(location.pathname);
    }, [location]);

    useEffect((): void => {
        const fetchUserProfileImage = async (): Promise<void> => {
            const image: string | null = await fetchUserImage();
            if (image) {
                setUserImage(image);
            }
        };
        fetchUserProfileImage();
    }, []);

    useEffect( () => {
        const fetchAndSetNotifications = async () => {
            const fetchedNotifications = await fetchNotifications();
            setNotifications(fetchedNotifications);
        };
        if (isUserLoggedIn()){
            fetchAndSetNotifications();
        }
    }, []);

    useEffect((): void => {
      if (isUserLoggedIn() && !isTokenValid()) {
        logout();
        alert("Your session has expired. Please log in again to continue.")
        window.location.href = "/";
      }
    }, []);

    useEffect((): void => {
        setCurrentLocation(location.pathname);
    }, [location]);

    const handleNotificationsButtonClick = async (): Promise<void> => {
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
                <img alt="Home" src={process.env.PUBLIC_URL + '/assets/header/home.svg'} style={{height: "26px", width: "26px"}}/>
            </Link>
            <Link className={`friends-background ${currentLocation === '/friends' ? 'active' : ''}`} to={isUserLoggedIn() ? '/friends' : '#'}
                  aria-label="To the friends page" data-tooltip-id="like" data-tooltip-content={config.tooltipMessage}>
                <img alt="Friends" src={process.env.PUBLIC_URL + '/assets/header/friends.svg'} style={{height: "26px", width: "26px"}}/>
            </Link>
            {!isUserLoggedIn() && (
                <Tooltip variant={"light"} id="friends" place="bottom"/>
            )}
            <Link className={`calendar-background ${currentLocation === '/calendar' ? 'active' : ''}`} to="/calendar"
                  aria-label="To the calendar page">
                <img alt="Calendar" src={process.env.PUBLIC_URL + '/assets/header/calendar.svg'} style={{height: "26px", width: "24px"}}/>
            </Link>
            <SearchBar/>
            <div className="notifications-button-container" data-tooltip-id="notifications" data-tooltip-content={config.tooltipMessage}>
                { notifications.length > 0 ? (
                    <button className="notifications-button-new" onClick={handleNotificationsButtonClick} disabled={!isUserLoggedIn()}>
                        <img alt="New notifications"
                             src={process.env.PUBLIC_URL + '/assets/header/notifications.svg'} style={{height: "25px", width: "26px"}}/>
                        <img className="notifications-dot" alt="New notifications"
                             src={process.env.PUBLIC_URL + '/assets/header/notifications-dot.svg'} style={{height: "7px", width: "8px"}}/>
                    </button>
                ) : (
                    <button className="notifications-button" onClick={handleNotificationsButtonClick}
                            aria-label="Notifications-Button" disabled={!isUserLoggedIn()}>
                        <img alt="New notifications" src={process.env.PUBLIC_URL + '/assets/header/notifications.svg'}
                             style={{height: "25px", width: "26px"}}/>
                    </button>
                )}
            </div>
            {!isUserLoggedIn() && (
                <Tooltip variant={"light"} id="notifications" place="bottom" style={{zIndex: 999}}/>
            )}
            {showNotifications && <Notifications showNotifications={showNotifications} notifications={notifications} setNotifications={setNotifications}/>}
            {isUserLoggedIn() ? (
                <div className="profile-component">
                    <Link to="/profile" aria-label="To the profile">
                        <img className="profile-picture-header" alt="Profile" src={userImage}/>
                    </Link>
                </div>
            ) : (
                <div className="profile-component">
                    <ModalLoginContainer/>
                    <SignUp/>
                </div>
            )}
        </div>
    );
};