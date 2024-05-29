import React, { useState } from "react";
import "./ProfilePage.css";
import { Header } from "../../organisms/header/Header";
import { Footer } from "../../organisms/footer/Footer";

interface ProfileData {
    username: string;
    email: string;
    followers: number;
    profilePicture: string;
}

export const ProfilePage: React.FC = () => {
    const [profileData, setProfileData] = useState<ProfileData>({
        username: "Dieter B.",
        email: "user@example.com",
        followers: 51,
        profilePicture: "path/to/profile-picture.jpg" // Verwende den tatsächlichen Pfad
    });

    const [isEditing, setIsEditing] = useState<{ username: boolean, email: boolean }>({ username: false, email: false });
    const [password, setPassword] = useState<string>("");

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;

        // Sanitize input
        const sanitizedValue = value.replace(/<script.*?>.*?<\/script>/gi, "");

        setProfileData({ ...profileData, [name]: sanitizedValue });
    };

    const handleEdit = (field: 'username' | 'email') => {
        setIsEditing({ ...isEditing, [field]: !isEditing[field] });
    };

    const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    };

    return (
        <div className="page">
            <Header/>
            <div className="profile-page">
                <div className="profile-container">
                    <div className="profile-picture">
                        <img src={profileData.profilePicture} alt="Profile"/>
                        <input type="file"/>
                    </div>
                    <div className="followers">
                        <button className="followers-btn">{profileData.followers} Followers</button>
                    </div>
                    <div className="profile-field">
                        <label className="label-profile-page-text">Username</label>
                        {isEditing.username ? (
                            <input type="text" name="username" value={profileData.username} onChange={handleChange}/>
                        ) : (
                            <span>{profileData.username}</span>
                        )}
                        <button onClick={() => handleEdit('username')}>{isEditing.username ? 'Save' : 'Edit'}</button>
                    </div>
                    <div className="profile-field">
                        <label className="label-profile-page-text">Email Address</label>
                        {isEditing.email ? (
                            <input type="email" name="email" value={profileData.email} onChange={handleChange}/>
                        ) : (
                            <span>{profileData.email}</span>
                        )}
                        <button onClick={() => handleEdit('email')}>{isEditing.email ? 'Save' : 'Edit'}</button>
                    </div>
                    <div className="profile-field">
                        <label className="label-profile-page-text">Password</label>
                        <input type="password" value={password} onChange={handlePasswordChange}/>
                        <button>Change Password</button>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
};
