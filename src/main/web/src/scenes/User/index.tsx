import React, { useEffect, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import config from "../../config/config";
import {getJWT, getUserId} from "../../services/AuthService";
import {getDefaultOrRandomPicture} from "../../atoms/Pictures/PicturesComponent";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";

import {UserPost} from "./UserPost";

export const UserPage = () => {
    const searchParams: URLSearchParams = new URLSearchParams(window.location.search);
    const id: string | null = searchParams.get('id');
    const senderId: number | null = getUserId();

    const [userId, setUserId] = useState<number | null>(null);
    const [username, setUserName] = useState<string | null>(null);
    const [picture, setPicture] = useState<{
        id: number | null;
        name: string | null;
        imageData: string | null
    }>({id: null, name: null, imageData: null});
    const [userPosts, setUserPosts] = useState<any[]>([]);
    const [amountFollower, setAmountFollower] = useState<number | null>(null);
    const [age, setAge] = useState<number | null>(null);
    const [description, setDescription] = useState<string | null>(null);
    const [course, setCourse] = useState<string | null>(null);
    const [loading, setLoading] = useState(true);
    const [notFound, setNotFound] = useState(true);
    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    useEffect(() => {

        const fetchUserData = async () => {
            setUserPosts([]);
            setLoading(true);
            setNotFound(false);

            try {
                const response = await fetch(config.apiUrl + `user/user-information/${id}`, {
                    headers: config.headers
                });
                if (response.ok) {
                    const data = await response.json();
                    setUserId(data.userId);
                    setUserName(data.username);
                    if (data.picture !== null) {
                        setPicture(data.picture);
                    }
                    setAmountFollower(data.amountFollower);
                    setAge(data.age);
                    setDescription(data.description);
                    setCourse(data.course);
                    console.log("successful fetching of userdata");
                    setLoading(false);
                    setNotFound(false);
                } else {
                    console.log(new Error("Failed to fetch Userdata"));
                    setLoading(false);
                    setNotFound(true);
                }
            } catch (error) {
                console.error("Error fetching Data:", error);
                setLoading(false);
                setNotFound(true);
            }
        };

        fetchUserData();

        const fetchUserPosts = async () => {
            try {
                const response = await fetch(config.apiUrl + `post/user-posts/${userId}`, {
                    headers: headersWithJwt
                });
                if (response.ok) {
                    const data = await response.json();
                    if (data.length === 0) {
                        // handle empty posts
                    } else {
                        setUserPosts(data);
                    }
                } else {
                    console.log(new Error("Failed to fetch User Posts"));
                 //   setLoading(false);
               //     setNotFound(true);
                    // handle error
                }
            } catch (error) {
                console.error("Error when retrieving the search data:", error)
             //   setLoading(false);
             //   setNotFound(true);
                // handle error
            }
        };

           fetchUserPosts();


    }, [id, userId, config.headers, config.apiUrl]);


    const handleFollow = async () => {
        if (userId === null) return;

        try {
            const response = await fetch(config.apiUrl + `friendship/follow-user`, {
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


    if (notFound) {
        return (
            <div className="page">
                <Header/>
                <div className="user-not-found">
                    <h1 className="error">This User doesn´t exist</h1>
                </div>
                <Footer/>
            </div>
        );
    }
    if (loading) {
        return (
            <div className="page">
                <Header/>
                <div className="user-component">
                    <div className="animation">
                        <div className="loading-animation">
                            <Lottie animationData={animationData}/>
                        </div>
                    </div>
                </div>
                <Footer/>
            </div>
        );
    }

    class FollowButton extends React.Component<{ userId: any, senderId: any, handleFollow: any }> {
        render() {
            let {userId, senderId, handleFollow} = this.props;
            if (userId === senderId) {
                return (
                    <div></div>
                );
            } else {
                return (
                    <button className="follow-button" onClick={handleFollow}>
                        Follow
                    </button>
                );
            }
        }
    }

    class DisplayPosts extends React.Component {

        render() {
            return (
                <div className="search-results">
                    {userPosts.map(post => (
                        <UserPost
                            key={post.id}
                            id={post.id}
                            title={post.title}
                            description={post.description || ""}
                            tags={post.tags}
                            likeAmount={post.likeAmount}
                            commentAmount={post.commentAmount}
                            timestamp={post.timestamp}
                            postImage={post.postImage}
                            accountId={post.accountId}
                            username={post.username}
                        />
                    ))}
                </div>
            );
        }
    }

    return (
        <div className="page">
            <Header/>
            <div className="user-component">
                <div className="user-page-picture">
                    {picture.id !== null && picture.imageData ? (
                        <img className="user-image" alt={"user"} src={picture.imageData} loading="lazy"/>
                    ) : (
                        <img className="custom-image" alt={"random-image"} src={getDefaultOrRandomPicture(false)}/>
                    )}
                </div>
                <div className="user-page-name">
                    <span>{username}</span>
                </div>
                <div className="user-followers">
                    <span>Followers: {amountFollower}</span>
                </div>
                <div className="description-field">
                    {course !== null ? (
                        <span>Course: {course}</span>
                    ) : (
                        <span>Course: Not set</span>
                    )}
                </div>
                <div className="description-field">
                    {age !== null ? (
                        <span>Age: {age}</span>
                    ) : (
                        <span>Age: Not set</span>
                    )}
                </div>
                <div className="description-field">
                    {description !== null ? (
                        <span>Description: {description}</span>
                    ) : (
                        <span>Description: Not set</span>
                    )}
                </div>
                <FollowButton userId={userId} senderId={senderId} handleFollow={handleFollow}/>

            </div>
            {userPosts.length >0 ?
                <div>
                    <div className="user-posts-header">Posts by {username}:</div>
                    <div className="user-posts">
                        <DisplayPosts/>
                    </div>
                </div> : <div></div>}
            <Footer/>
        </div>
    );

};
