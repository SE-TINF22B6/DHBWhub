import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

export const register = (username, password) => {
    return axios.post(API_URL + "signup", {
        username,
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

export const tokenValidation = (token) => {
    return axios
        .post(API_URL + "token-validation", {
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
