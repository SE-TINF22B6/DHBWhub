import React, { useEffect, useRef, useState } from "react";
import DOMPurify from "dompurify";
import "./index.css";
import { Header } from "../../organisms/header/Header";
import { Footer } from "../../organisms/footer/Footer";
import { getUserId, logout } from "../../services/AuthService";
import { NavigateFunction, useNavigate } from "react-router-dom";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import { useDetectAdBlock } from "adblock-detect-react";
import { usePreventScrolling } from "../../organisms/ad-block-overlay/preventScrolling";
import {fetchUserData, updateUserProfile} from "../../services/ProfileDataService";
import { fetchUserImage } from "../../services/ProfilePictureService";  // Import the function for fetching user image

interface UserData {
    username: string;
    email: string;
    picture: {
        id: number;
        name: string;
        imageData: string;
    };
    amountFollower: number;
    age: string;
    description: string;
    course: string;
}

export const ProfilePage = () => {
    let navigate: NavigateFunction = useNavigate();

    const [userData, setUserData] = useState<UserData>({
        username: "",
        email: "",
        picture: {
            id: 0,
            name: "",
            imageData: "",
        },
        amountFollower: 0,
        age: "",
        description: "",
        course: "",
    });

    const [isEditing, setIsEditing] = useState<{ username: boolean, email: boolean, course: boolean, age: boolean, description: boolean }>({ username: false, email: false, course: false, age: false, description: false });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        const sanitizedValue = DOMPurify.sanitize(value);
        setUserData({ ...userData, [name]: sanitizedValue });
    };

    const handleEdit = (field: 'username' | 'email' | 'course' | 'age' | 'description') => {
        setIsEditing({ ...isEditing, [field]: !isEditing[field] });
    };

    const handlePasswordChange = (): void => {
        //navigate("/");
    };

    const handleSaveChanges = async () => {
        const success = await updateUserProfile(userData);
        if (success) {
            console.log("Changes saved successfully");
        } else {
            console.error("Failed to save changes");
        }
    };

    const handleLogout = (): void => {
        logout();
        navigate("/");
        window.location.reload();
    }

    useEffect((): void => {
        const fetchData = async (): Promise<void> => {
            const id = getUserId();
            if (id) {
                const [data, image] = await Promise.all([fetchUserData(), fetchUserImage()]);
                if (data) {
                    console.log("Fetched user data:", data);
                    if (image) {
                        data.picture.imageData = image;
                    }
                    setUserData(data);
                } else {
                    console.error("Failed to set user data");
                }
            } else {
                console.error("No user ID found");
            }
        };
        fetchData();
    }, []);

    const adBlockDetected = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);
    const scrollUpRef = useRef<HTMLDivElement>(null);

    return (
        <div className="page">
            {adBlockDetected && <AdBlockOverlay />}
            <div ref={scrollUpRef} />
            <Header />
            <div className="profile-container">
                <div className="profile-page-picture">
                    <img src={userData.picture.imageData || "https://via.placeholder.com/150"} alt="Profile" />
                    <input type="file" />
                </div>
                <div className="followers">
                    <button className="followers-btn">{userData.amountFollower} Followers</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Username</label>
                    {isEditing.username ? (
                        <input type="text" name="username" value={userData.username} onChange={handleChange} />
                    ) : (
                        <span>{userData.username}</span>
                    )}
                    <button onClick={() => handleEdit('username')}>{isEditing.username ? 'Save' : 'Edit'}</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Email Address</label>
                    {isEditing.email ? (
                        <input type="email" name="email" value={userData.email} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.email) }}></span>
                    )}
                    <button onClick={() => handleEdit('email')}>{isEditing.email ? 'Save' : 'Edit'}</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Course</label>
                    {isEditing.course ? (
                        <input type="text" name="course" value={userData.course} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.course) }}></span>
                    )}
                    <button onClick={() => handleEdit('course')}>{isEditing.course ? 'Save' : 'Edit'}</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Age</label>
                    {isEditing.age ? (
                        <input type="number" name="age" value={userData.age} onChange={handleChange} />
                    ) : (
                        <span>{userData.age}</span>
                    )}
                    <button onClick={() => handleEdit('age')}>{isEditing.age ? 'Save' : 'Edit'}</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Description</label>
                    {isEditing.description ? (
                        <input type="text" name="description" value={userData.description} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.description) }}></span>
                    )}
                    <button onClick={() => handleEdit('description')}>{isEditing.description ? 'Save' : 'Edit'}</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Password</label>
                    <button onClick={handlePasswordChange}>Change Password</button>
                </div>
                <button className="logout-btn" onClick={handleLogout}>Logout</button>
            </div>
            <ScrollUpButton scrollUpRef={scrollUpRef} />
            <Footer />
        </div>
    );
};