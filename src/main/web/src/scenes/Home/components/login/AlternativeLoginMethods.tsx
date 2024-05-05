import React from 'react';
import Box from '@mui/material/Box';
import './GoogleLogo.css';

function GoogleLogin() {
    return (
        <Box className="login-box">
            <img src={`${process.env.PUBLIC_URL}/googleLogo.png`} alt="Google Logo" className="login-image" />
            <span className="login-text">Continue with Google</span>
        </Box>
    );
}

function MicrosoftLogin() {
    return (
        <Box className="login-box">
            <img src={`${process.env.PUBLIC_URL}/microsoftLogo.png`} alt="Microsoft Logo" className="login-image" />
            <span className="login-text">Continue with Microsoft</span>
        </Box>
    );
}

function AlternativeLoginMethods() {
    return (
        <>
            <GoogleLogin/>
            <MicrosoftLogin />
        </>
    );
}

export default AlternativeLoginMethods;
