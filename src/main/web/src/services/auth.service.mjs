import { useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

export const register = (username, email, password) => {
    return axios.post(API_URL + "signup", {
        username,
        email,
        password,
    });
};

export const login = (username, password, rememberMe) => {
    return axios
        .post(API_URL + "login", {
            username,
            password,
            rememberMe
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }

            return response.data;
        });
};

export const emailVerification = (email) => {
    return axios
        .post(API_URL + "email-verification", {
            email
        })
        .then((response) => {
            return response.data;
        });
};

export const logout = () => {
    localStorage.removeItem("user");
};

export const getCurrentUser = () => {
    const userStr = localStorage.getItem("user");
    if (userStr) return JSON.parse(userStr);

    return null;
};

export const EmailVerificationSuccess = () => {
    const history = useHistory();
    const location = useLocation();

    useEffect(() => {
        const token = new URLSearchParams(location.search).get('token');

        const verifyEmail = async () => {
            try {
                const response = await axios.get('/api/verify-email', {
                    params: { token },
                });

                console.log('Email verified successfully!', response.data);

                // Redirect the user to a success page or perform other actions
                history.push('/verification-success');
            } catch (error) {
                console.error('Failed to verify email:', error);

                // Redirect the user to an error page or perform other actions
                history.push('/verification-error');
            }
        };

        if (token) {
            verifyEmail();
        } else {
            console.error('Email verification token not found!');
            history.push('/verification-error');
        }
    }, [history, location.search]);

    return null; // or display a loading spinner
};
