import axios from "axios";
import config from "../config/config";
import {CredentialResponse} from "@react-oauth/google";

export const register = (username: string, email: string, password: string) => {
    return axios.post(config.apiUrl + "api/auth/signup", {
        username,
        email,
        password,
    });
};

export const saveUserDataToLocalStorage = (data: { accountId: number; userId: number; username: string; accessToken: string; }): void => {
    localStorage.setItem('accountId', data.accountId.toString());
    localStorage.setItem('userId', data.userId.toString());
    localStorage.setItem('username', data.username);
    localStorage.setItem('token', data.accessToken);
};

export const login = async (username: string, password: string, rememberMe: boolean): Promise<any> => {
    const response = await axios
    .post(config.apiUrl + "api/auth/login", {
        username,
        password,
        rememberMe
    });
    saveUserDataToLocalStorage(response.data);
    return response.data;
};

export const googleLogin = async (credentialResponse: String): Promise<any> => {
    const response = await axios
        .post(config.apiUrl + "api/auth/google", credentialResponse,
            {
                headers:{
                    'Content-Type': 'application/json'
                }
            });
    saveUserDataToLocalStorage(response.data);
    return response.data
}

export const emailVerification = async (email: string): Promise<any> => {
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

export const logout = (): void => {
    localStorage.removeItem("accountId");
    localStorage.removeItem("userId");
    localStorage.removeItem("username");
    localStorage.removeItem("token");
};

export const getAccountId = (): number | null => {
    const accountIdString: string | null = localStorage.getItem("accountId");
    if (accountIdString === null) {
        return null;
    }
    const accountId: number = parseInt(accountIdString, 10);
    return isNaN(accountId) ? null : accountId;
};

export const getUserId = (): number | null => {
    const userIdString: string | null = localStorage.getItem("userId");
    if (userIdString === null) {
        return null;
    }
    const userId: number = parseInt(userIdString, 10);
    return isNaN(userId) ? null : userId;
};

export const getUsername = (): string | null => {
    const username: string | null = localStorage.getItem("username");
    if (username) return username;

    return null;
};

export const getJWT = (): string | null => {
    const jwtToken: string | null = localStorage.getItem("token");

    if (jwtToken) {
        return jwtToken;
    } else {
        return null;
    }
};

export const isUserLoggedIn = (): boolean => {
    const jwtToken: string | null = localStorage.getItem("token");

    if (jwtToken) {
        return !!jwtToken;
    } else {
        return false;
    }
};