import React, {useEffect, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import config from "../../config/config";
import {Pictures} from "../../atoms/Pictures/Pictures";
import {getJWT, getUserId} from "../../services/AuthService";

export const UserPage = () => {
    const searchParams: URLSearchParams = new URLSearchParams(window.location.search);
    const id: string | null = searchParams.get('id');
    const senderId: number | null = getUserId();

    const [userId, setUserId] = useState<number | null>(null);
    const [username, setUserName] = useState<string | null>(null);
    const [picture, setPicture] = useState<{id: number | null; name: string | null; imageData: string | null}>({id: null, name: null, imageData: null});
    const [amountFollower, setAmountFollower] = useState<number | null>(null);
    const [age, setAge] = useState<number | null>(null);
    const [description, setDescription] = useState<string | null>(null);
    const [course, setCourse] = useState<string | null>(null);
    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    useEffect((): void => {
        const fetchUserData = async (): Promise<void> => {
            try {
                const response: Response = await fetch(config.apiUrl +`user/user-information/${id}`, {
                    headers: config.headers
                });
                if (response.ok) {
                    const data = await response.json();
                    setUserId(data.userId);
                    setUserName(data.username);
                    if(data.picture.id !== null){
                        setPicture(data.picture);
                    }
                    setAmountFollower(data.amountFollower);
                    setAge(data.age);
                    setDescription(data.description);
                    setCourse(data.course);
                    console.log("successful fetching of userdata")
                } else {
                    console.log(new Error("Failed to fetch Userdata"));
                }
            } catch (error) {
                console.error("Error fetching Data:", error);
            }
        };
        fetchUserData();
    }, [id]);

    const handleFollow = async () => {
        if (userId === null) return;

        try {
            const response = await fetch(config.apiUrl +`friendship/follow-user`, {
                method: 'POST',
                headers: headersWithJwt,
                body: JSON.stringify({
                    "requesterId": senderId,
                    "receiverId": id
                })
            });

            if (response.ok) {
                console.log("Successfully followed the user");

                setAmountFollower(prev => (prev !== null ? prev + 1 : 1));
            } else {
                console.error("Failed to follow the user");
            }
        } catch (error) {
            console.error("Error following the user:", error);
        }
    }

    return (
        <div className="page">
            <Header />
            <div className="user-component">
                <div className="user-page-picture">
                    {picture.id !== null && picture.imageData ? (
                        <img className="user-image" alt={"user"} src={picture.imageData} loading="lazy"/>
                    ) : (
                        <Pictures defaultPicture={false} className="custom-image"/>
                    )}
                </div>
                <div className="user-page-name">
                    <span>{username}</span>
                </div>
                <div className="user-followers">
                    <span> Followers: {amountFollower}</span>
                </div>
                <div className="description-field">
                    {course !== null ? (
                        <span>Course: {course}</span>
                    ) : (
                        <span>Course: Not set</span>
                    )}
                </div>
                <div className="description-field">
                    <span> Age: {age}</span>
                </div>
                <div className="description-field">
                    <span>Description: {description}</span>
                </div>
                <button className="follow-button" onClick={handleFollow}>Follow</button>
            </div>
            <Footer/>
        </div>
    );
};
