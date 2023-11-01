import React from 'react';
import Box from '@mui/material/Box';
import './GoogleLogo.css';

function GoogleLogin() {
    return (
        <Box className="google-login" sx={{mt: 2}}>
            <img src="src/main/web/public/googleLogo.png" alt="Google Logo"/>
            <span>Continue with Google</span>
        </Box>
    );
}

function MicrosoftLogin() {
    return (
        <Box className="google-login" sx={{mt: 2}}>
            <img src="src/main/web/public/microsoftLogo.png" alt="Microsoft Logo"/>
            <span>Continue with Microsoft</span>
        </Box>
    );
}

function AppleLogin() {
    return (
        <Box className="google-login" sx={{mt: 2}}>
            <img src="src/main/web/public/appleLogo.png" alt="Apple Logo"/>
            <span>Continue with Apple</span>
        </Box>
    );
}

function AlternativeLoginMethods() {
    return (
        <>
            <GoogleLogin/>
            <MicrosoftLogin />
            <AppleLogin />
        </>
    );
}

export default AlternativeLoginMethods;
