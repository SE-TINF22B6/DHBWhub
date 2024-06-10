import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { getUserId } from "./AuthService";
import config from "../config/config";

const ProfileDataService = () => {
    const [userData, setUserData] = useState({
        username: null,
        courseName: null,
        age: null,
        email: null,
        imageData: null
    });
    const userId = getUserId();

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axios.get(`${config.apiUrl}/users/${userId}`);
                const user = response.data;
                const { username, age, email, course, account } = user;
                const { name: courseName } = course;
                const { picture, username: accountUsername, email: accountEmail } = account;
                const { imageData } = picture;

                setUserData({
                    username: accountUsername,
                    courseName,
                    age,
                    email: accountEmail,
                    imageData
                });
            } catch (error) {
                console.error('Fehler beim Abrufen der Benutzerdaten:', error);
            }
        };

        fetchUserData();
    }, [userId]);

    return userData;
};

export default ProfileDataService;