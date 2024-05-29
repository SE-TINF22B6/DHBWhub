import React, {useEffect, useState} from "react";
import "./index.css";
import axios from 'axios';
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {isUserLoggedIn, logout} from "../../services/AuthService";
import "./index.css";
import ProfilePictureService from "../../services/ProfilePictureService";

interface ProfileData {
    username: string;
    email: string;
    followers: number;
    profilePicture: string;
}

export const Profile: React.FC = () => {
    const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
    const defaultProfilePicture = process.env.PUBLIC_URL + '/assets/profile.svg';
    const [profileData, setProfileData] = useState<ProfileData>({
        username: '',
        email: '',
        followers: 0,
        profilePicture: ''
    });
    const [password, setPassword] = useState<string>('');
    const [isEditing, setIsEditing] = useState<{
        username: boolean,
        email: boolean,
        password: boolean
    }>({username: false, email: false, password: false});
    let userProfilePicture;

    useEffect(() => {
        // Fetch profile data from backend
        axios.get('/api/profile')
            .then(response => setProfileData(response.data))
            .catch(error => console.error('Error fetching profile data', error));
    }, []);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setProfileData({...profileData, [name]: value});
    };

    const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    };

    const handleEdit = (field: 'username' | 'email' | 'password') => {
        setIsEditing({...isEditing, [field]: !isEditing[field]});
    };

    const handleSubmit = () => {
        // Update profile data in backend
        axios.put('/api/profile', profileData)
            .then(response => {
                setProfileData(response.data);
                setIsEditing({username: false, email: false, password: false});
            })
            .catch(error => console.error('Error updating profile data', error));

        // Update password in backend
        if (password) {
            axios.put('/api/profile/password', {password})
                .then(() => setPassword(''))
                .catch(error => console.error('Error updating password', error));
        }
    };

    const handleProfilePictureChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files[0]) {
            const formData = new FormData();
            formData.append('profilePicture', event.target.files[0]);

            axios.post('/api/profile/upload', formData)
                .then(response => setProfileData({...profileData, profilePicture: response.data.profilePicture}))
                .catch(error => console.error('Error uploading profile picture', error));
        }
    };

    return (
        <>
            <Header/>
            {isUserLoggedIn() && (
                <button className="back-button" onClick={logout}>Logout</button>
            )}
            <div className="profile-page">
                <div className="profile-container">
                    <div className="profile-picture">
                        <img src={profileData.profilePicture} alt="Profile"/>
                        <input type="file" onChange={handleProfilePictureChange}/>
                    </div>
                    <div className="followers">
                        <button className="followers-btn">{profileData.followers} Followers</button>
                    </div>
                    <div className="profile-field">
                        <label>Username</label>
                        {isEditing.username ? (
                            <input type="text" name="username" value={profileData.username} onChange={handleChange}/>
                        ) : (
                            <span>{profileData.username}</span>
                        )}
                        <button onClick={() => handleEdit('username')}>{isEditing.username ? 'Save' : 'Edit'}</button>
                    </div>
                    <div className="profile-field">
                        <label>Email Address</label>
                        {isEditing.email ? (
                            <input type="email" name="email" value={profileData.email} onChange={handleChange}/>
                        ) : (
                            <span>{profileData.email}</span>
                        )}
                        <button onClick={() => handleEdit('email')}>{isEditing.email ? 'Save' : 'Edit'}</button>
                    </div>
                    <div className="profile-field">
                        <label>Password</label>
                        {isEditing.password ? (
                            <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
                        ) : (
                            <span>********</span>
                        )}
                        <button onClick={() => handleEdit('password')}>{isEditing.password ? 'Save' : 'Edit'}</button>
                    </div>
                    <button onClick={handleSubmit}>Update Profile</button>
                </div>
            </div>
            <div className="footer-profilepage">
                <Footer/>
                {isSmartphoneSize && <MobileFooter/>}
            </div>
        </>
    );
};
