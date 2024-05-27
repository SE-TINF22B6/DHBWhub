import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import * as authService from '@services/AuthService';
import config from '@config/config';

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
            const responseData = { message: 'User registered successfully' };
            mock.onPost(config.apiUrl + 'api/auth/signup').reply(200, responseData);

            const response = await authService.register(username, email, password);

            expect(response.data).toEqual(responseData);
        });
    });

    describe('login', () => {
        it('should make a POST request to log in a user and save data to localStorage', async () => {
            const username = 'testuser';
            const password = 'password';
            const rememberMe = true;
            const responseData = { id: 1, username: 'testuser', accessToken: 'jwt-token' };
            mock.onPost(config.apiUrl + 'api/auth/login').reply(200, responseData);

            const response = await authService.login(username, password, rememberMe);

            expect(response).toEqual(responseData);
            expect(localStorage.getItem('user')).toEqual(JSON.stringify(responseData));
            expect(localStorage.getItem('id')).toEqual('1');
            expect(localStorage.getItem('username')).toEqual('testuser');
            expect(localStorage.getItem('token')).toEqual('jwt-token');
        });
    });

    describe('emailVerification', () => {
        it('should make a POST request for email verification', async () => {
            const email = 'test@example.com';
            const responseData = { message: 'Verification email sent' };
            mock.onPost(config.apiUrl + 'api/auth/email-verification').reply(200, responseData);

            const response = await authService.emailVerification(email);

            expect(response).toEqual(responseData);
        });
    });

    describe('tokenValidation', () => {
        it('should make a POST request for token validation', async () => {
            const token = '123456';
            const responseData = { message: 'Token valid' };
            mock.onPost(config.apiUrl + 'api/auth/token-validation').reply(200, responseData);

            const response = await authService.tokenValidation(token);

            expect(response).toEqual(responseData);
        });
    });

    describe('logout', () => {
        it('should remove user from localStorage', () => {
            localStorage.setItem('user', JSON.stringify({ id: 1, username: 'testuser', accessToken: 'jwt-token' }));

            authService.logout();

            expect(localStorage.getItem('user')).toBeNull();
        });
    });

    describe('getCurrentUser', () => {
        it('should return current user from localStorage', () => {
            const userData = { id: 1, username: 'testuser', accessToken: 'jwt-token' };
            localStorage.setItem('user', JSON.stringify(userData));

            const currentUser = authService.getCurrentUser();

            expect(currentUser).toEqual(userData);
        });

        it('should return null if no user is in localStorage', () => {
            const currentUser = authService.getCurrentUser();

            expect(currentUser).toBeNull();
        });
    });

    describe('isUserLoggedIn', () => {
        it('should return true if user is logged in', () => {
            const userData = { id: 1, username: 'testuser', accessToken: 'jwt-token' };
            localStorage.setItem('user', JSON.stringify(userData));

            const isLoggedIn = authService.isUserLoggedIn();

            expect(isLoggedIn).toBe(true);
        });

        it('should return false if user is not logged in', () => {
            const isLoggedIn = authService.isUserLoggedIn();

            expect(isLoggedIn).toBe(false);
        });
    });
});