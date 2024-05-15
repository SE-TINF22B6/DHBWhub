import axios from "axios";
import config from "../config/config.ts";

export const register = (username, email, password) => {
    return axios.post(config.apiUrl + "api/auth/signup", {
        username,
        email,
        password,
    });
};

export const login = (username, password, rememberMe) => {
    return axios
        .post(config.apiUrl + "api/auth/login", {
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
        .post(config.apiUrl + "api/auth/email-verification", {
            email
        })
        .then((response) => {
            return response.data;
        });
};

export const tokenValidation = (token) => {
    return axios
        .post(config.apiUrl + "api/auth/token-validation", {
            token
        })
        .then((response) => {
            return response.data;
        });
}

export const logout = () => {
    localStorage.removeItem("user");
};

export const getCurrentUser = () => {
    const userStr = localStorage.getItem("user");
    if (userStr) return JSON.parse(userStr);

    return null;
};

export const isUserLoggedIn = () => {
    const userDataString = localStorage.getItem("user");

    if (userDataString) {
        const userData = JSON.parse(userDataString);
        const jwtToken = userData.accessToken;
        return !!jwtToken;
    } else {
        return false;
    }
};