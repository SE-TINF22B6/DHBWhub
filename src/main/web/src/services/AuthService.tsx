import axios from "axios";
import config from "../config/config";
import {jwtDecode} from "jwt-decode";

export const register = async (username: string, email: string, password: string): Promise<any> => {
    const response = await axios
        .post(config.apiUrl + "api/auth/signup", {
            username,
            email,
            password,
        });
    saveUserDataToLocalStorage(response.data);
    localStorage.setItem('oAuthUser', 'false');

    return response.data;
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
    localStorage.setItem('oAuthUser', 'false');

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
    localStorage.removeItem('userImage');
    localStorage.removeItem('oAuthUser');

    Object.keys(localStorage)
      .filter(x =>
        x.includes("_liked_")
      ).map(
        x => localStorage.removeItem(x));
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

export const isTokenValid = (): boolean => {
    const jwtToken: string | null = localStorage.getItem("token");

    if (jwtToken) {
        const decodedToken = jwtDecode(jwtToken);
        if (decodedToken && decodedToken.exp) {
            return decodedToken.exp * 1000 > Date.now();
        } else {
            return false;
        }
    } else {
        return false;
    }
};
