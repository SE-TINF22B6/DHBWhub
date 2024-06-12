import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import * as authService from "../AuthService";
import config from '../../config/config';

const mock = new MockAdapter(axios);

describe('AuthService', () => {
    afterEach(() => {
        mock.reset();
        localStorage.clear();
    });

    describe('register', () => {
        it('should make a POST request to register a user', async () => {
            const username = 'testuser';
            const email = 'test@example.com';
            const password = 'password';
            const rememberMe = false;
            const responseData = {accountId: 1, userId: 2, username: 'testuser', accessToken: 'jwt-token'};
            mock.onPost(config.apiUrl + 'api/auth/signup').reply(200, responseData);

            const response = await authService.register(username, email, password);

            expect(response).toEqual(responseData);
            expect(localStorage.getItem('accountId')).toEqual('1');
            expect(localStorage.getItem('userId')).toEqual('2');
            expect(localStorage.getItem('username')).toEqual('testuser');
            expect(localStorage.getItem('token')).toEqual('jwt-token');
        });
    });

    describe('saveUserDataToLocalStorage', () => {
        it('should make a POST request to register a user', async () => {
            const userData = { accountId: 1, userId: 2, username: 'testUser', accessToken: 'jwt-token' }

            authService.saveUserDataToLocalStorage(userData);

            expect(localStorage.getItem('accountId')).toEqual('1');
            expect(localStorage.getItem('userId')).toEqual('2');
            expect(localStorage.getItem('username')).toEqual('testUser');
            expect(localStorage.getItem('token')).toEqual('jwt-token');
        });
    });

    describe('login', () => {
        it('should make a POST request to log in a user and save data to localStorage', async () => {
            const username = 'testuser';
            const password = 'password';
            const rememberMe = true;
            const responseData = {accountId: 1, userId: 2, username: 'testuser', accessToken: 'jwt-token'};
            mock.onPost(config.apiUrl + 'api/auth/login').reply(200, responseData);

            const response = await authService.login(username, password, rememberMe);

            expect(response).toEqual(responseData);
            expect(localStorage.getItem('accountId')).toEqual('1');
            expect(localStorage.getItem('userId')).toEqual('2');
            expect(localStorage.getItem('username')).toEqual('testuser');
            expect(localStorage.getItem('token')).toEqual('jwt-token');
        });
    });

    describe('emailVerification', () => {
        it('should make a POST request for email verification', async () => {
            const email = 'test@example.com';
            const responseData = {message: 'Verification email sent'};
            mock.onPost(config.apiUrl + 'api/auth/email-verification').reply(200, responseData);

            const response = await authService.emailVerification(email);

            expect(response).toEqual(responseData);
        });
    });

    describe('tokenValidation', () => {
        it('should make a POST request for token validation', async () => {
            const token = '123456';
            const responseData = {message: 'Token valid'};
            mock.onPost(config.apiUrl + 'api/auth/token-validation').reply(200, responseData);

            const response = await authService.tokenValidation(token);

            expect(response).toEqual(responseData);
        });
    });

    describe('logout', () => {
        it('should remove user from localStorage', () => {
            localStorage.setItem('user', JSON.stringify({id: 1, username: 'testuser', accessToken: 'jwt-token'}));

            authService.logout();

            expect(localStorage.getItem('accountId')).toBeNull();
            expect(localStorage.getItem('userId')).toBeNull();
            expect(localStorage.getItem('username')).toBeNull();
            expect(localStorage.getItem('token')).toBeNull();
        });
    });

    describe('getAccountId', () => {
        it('should return the accountId of the current user from localStorage', () => {
            const userAccountIdString = '1';
            localStorage.setItem('accountId', userAccountIdString);

            const currentUserAccountId = authService.getAccountId();

            expect(currentUserAccountId).toEqual(parseInt(userAccountIdString));
        });

        it('should return null if no accountId is in localStorage', () => {
            const currentUserAccountId = authService.getAccountId();

            expect(currentUserAccountId).toBeNull();
        });

        it('should return null if accountId is not a number', () => {
            localStorage.setItem('accountId', 'j');
            const currentUserAccountId = authService.getAccountId();

            expect(currentUserAccountId).toBeNull();
        });
    });

    describe('getUserId', () => {
        it('should return the userId of the current user from localStorage', () => {
            const userIdString = '2';
            localStorage.setItem('userId', userIdString);

            const currentUserId = authService.getUserId();

            expect(currentUserId).toEqual(parseInt(userIdString));
        });

        it('should return null if no userId is in localStorage', () => {
            const currentUserId = authService.getUserId();

            expect(currentUserId).toBeNull();
        });

        it('should return null if userId is not a number', () => {
            localStorage.setItem('userId', 'j');
            const currentUserId = authService.getUserId();

            expect(currentUserId).toBeNull();
        });
    });

    describe('getUsername', () => {
        it('should return the username of the current user from localStorage', () => {
            const userUsernameString = 'testUser';
            localStorage.setItem('username', userUsernameString);

            const currentUserUsername = authService.getUsername();

            expect(currentUserUsername).toEqual(userUsernameString);
        });

        it('should return null if no username is in localStorage', () => {
            const currentUserUsername = authService.getUsername();

            expect(currentUserUsername).toBeNull();
        });
    });

    describe('getJWT', () => {
        it('should return the JWT-Token of the current user from localStorage', () => {
            const userJWTTokenString = 'jwt-token';
            localStorage.setItem('token', userJWTTokenString);

            const currentUserJWTToken = authService.getJWT();

            expect(currentUserJWTToken).toEqual(userJWTTokenString);
        });

        it('should return null if no JWT-Token is in localStorage', () => {
            const currentUserJWTToken = authService.getJWT();

            expect(currentUserJWTToken).toBeNull();
        });
    });

    describe('isUserLoggedIn', () => {
        it('should return true if user is logged in', () => {
            const token = 'jwt-token';
            localStorage.setItem('token', token);

            const isLoggedIn = authService.isUserLoggedIn();

            expect(isLoggedIn).toBe(true);
        });

        it('should return false if user is not logged in', () => {
            const isLoggedIn = authService.isUserLoggedIn();

            expect(isLoggedIn).toBe(false);
        });
    });
});

