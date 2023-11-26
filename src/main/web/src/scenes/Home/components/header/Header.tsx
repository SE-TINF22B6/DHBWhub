import React, {useEffect, useState} from 'react';
import {SearchBar} from './SearchBar';
import {Link, useLocation} from 'react-router-dom';
import "./Header.css";
import {Logo} from "./Logo";
import Login from '../login/Login';
import SignUp from "../signup/SignUp";

export const Header = () => {

    const [newNotifications, setNewNotifications] = useState(true);
    const [currentLocation, setCurrentLocation] = useState('');
    const location = useLocation();
    const [openLogin, setOpenLogin] = useState(false)

    useEffect((): void => {
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

                <div className="login">
                    <button className="login" onClick={() => setOpenLogin(!openLogin)}>
                        <div className="log-in-wrapper">
                            <div className="log-in-text">LOGIN</div>
                        </div>
                    </button>
                    {openLogin ? <Login/> : null}
                </div>
                <div className="sign-up">
                    <SignUp/>
                </div>
            </div>
        </div>
    );
};
