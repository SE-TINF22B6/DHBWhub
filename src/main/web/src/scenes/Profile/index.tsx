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
import {fetchUserData, updatePassword} from "../../services/ProfileDataService";
import { fetchUserImage } from "../../services/ProfilePictureService";
import { updateAge, updateDescription, updateCourse, updateEmail, updateUsername, updatePicture } from "../../services/ProfileDataService";

interface UserData {
    username: string;
    email: string;
    picture: {
        id: number | null;
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
            id: null,
            name: "",
            imageData: "",
        },
        amountFollower: 0,
        age: "",
        description: "",
        course: "",
    });

    const [isEditing, setIsEditing] = useState<{ [key: string]: boolean }>({
        username: false,
        email: false,
        course: false,
        age: false,
        description: false
    });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        const sanitizedValue = DOMPurify.sanitize(value);
        setUserData({ ...userData, [name]: sanitizedValue });
    };

    const handleEdit = (field: string) => {
        setIsEditing({ ...isEditing, [field]: !isEditing[field] });
    };

    const handleSaveChanges = async (field: string) => {
        let success = false;
        switch (field) {
            case 'username':
                success = await updateUsername(userData.username);
                break;
            case 'email':
                success = await updateEmail(userData.email);
                break;
            case 'course':
                success = await updateCourse(userData.course);
                break;
            case 'age':
                success = await updateAge(userData.age);
                break;
            case 'description':
                success = await updateDescription(userData.description);
                break;
            default:
                break;
        }

        if (success) {
            console.log(`${field} updated successfully`);
            setIsEditing({ ...isEditing, [field]: false });
        } else {
            console.error(`Failed to update ${field}`);
        }
    };

    const handlePasswordChange = async () => {
        const newPassword = prompt("Enter your new password:");
        if (newPassword) {
            const success = await updatePassword(newPassword);
            if (success) {
                console.log("Password updated successfully");
            } else {
                console.error("Failed to update password");
            }
        }
    };

    const handlePictureChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.files);
        if (event.target.files && event.target.files.length > 0) {
            const reader = new FileReader();
            reader.onloadend = async () => {
                if (reader.result && event.target.files) {
                    const imageData = reader.result as string;
                    const success = await updatePicture(imageData);
                    if (success) {
                        setUserData(prevState => ({
                            ...prevState,
                            picture: {
                                ...prevState.picture,
                                imageData
                            }
                        }));
                        console.log("Picture updated successfully");
                    } else {
                        console.error("Failed to update the picture");
                    }
                }
            };
            reader.readAsDataURL(event.target.files[0]);
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
                    <img
                        src={userData.picture.imageData || "https://via.placeholder.com/150"}
                        alt="Profile"
                        onClick={() => document.getElementById("fileInput")?.click()}
                    />
                    <input
                        id="fileInput"
                        type="file"
                        style={{display: "none"}}
                        onChange={handlePictureChange}
                    />
                </div>
                <div className="followers">
                    <button className="followers-btn">{userData.amountFollower} Followers</button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Username</label>
                    {isEditing.username ? (
                        <input type="text" name="username" value={

                            userData.username} onChange={handleChange} />
                    ) : (
                        <span>{userData.username}</span>
                    )}
                    <button onClick={() => isEditing.username ? handleSaveChanges('username') : handleEdit('username')}>
                        {isEditing.username ? 'Save' : 'Edit'}
                    </button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Email Address</label>
                    {isEditing.email ? (
                        <input type="email" name="email" value={userData.email} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.email) }}></span>
                    )}
                    <button onClick={() => isEditing.email ? handleSaveChanges('email') : handleEdit('email')}>
                        {isEditing.email ? 'Save' : 'Edit'}
                    </button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Course</label>
                    {isEditing.course ? (
                        <input type="text" name="course" value={userData.course} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.course) }}></span>
                    )}
                    <button onClick={() => isEditing.course ? handleSaveChanges('course') : handleEdit('course')}>
                        {isEditing.course ? 'Save' : 'Edit'}
                    </button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Age</label>
                    {isEditing.age ? (
                        <input type="number" name="age" value={userData.age} onChange={handleChange} />
                    ) : (
                        <span>{userData.age}</span>
                    )}
                    <button onClick={() => isEditing.age ? handleSaveChanges('age') : handleEdit('age')}>
                        {isEditing.age ? 'Save' : 'Edit'}
                    </button>
                </div>
                <div className="profile-field">
                    <label className="label-profile-page-text">Description</label>
                    {isEditing.description ? (
                        <input type="text" name="description" value={userData.description} onChange={handleChange} />
                    ) : (
                        <span dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(userData.description) }}></span>
                    )}
                    <button onClick={() => isEditing.description ? handleSaveChanges('description') : handleEdit('description')}>
                        {isEditing.description ? 'Save' : 'Edit'}
                    </button>
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
