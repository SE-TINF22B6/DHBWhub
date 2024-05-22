import axios from "axios";
import config from "../config/config";

export const register = (username: string, email: string, password: string) => {
    return axios.post(config.apiUrl + "api/auth/signup", {
        username,
        email,
        password,
    });
};

const saveUserDataToLocalStorage = (data: {
    id: number;
    username: string;
    accessToken: string;
}) => {
    localStorage.setItem('id', data.id.toString());
    localStorage.setItem('username', data.username);
    localStorage.setItem('token', data.accessToken);
};

export const login = async (username: string, password: string, rememberMe: boolean) => {
    const response = await axios
    .post(config.apiUrl + "api/auth/login", {
        username,
        password,
        rememberMe
    });
    if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
        saveUserDataToLocalStorage(response.data);
    }
    return response.data;
};

export const emailVerification = async (email: string) => {
    const response = await axios
    .post(config.apiUrl + "api/auth/email-verification", {
        email
    });
    return response.data;
};

export const tokenValidation = async (token: string) => {
    const response = await axios
    .post(config.apiUrl + "api/auth/token-validation", {
        token
    });
    return response.data;
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