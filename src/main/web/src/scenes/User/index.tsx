import React, {useEffect, useState} from "react";
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
        imageData: string | null;
    }>({id: null, name: null, imageData: null});
    const [userPosts, setUserPosts] = useState<any[]>([]);
    const [amountFollower, setAmountFollower] = useState<number | null>(null);
    const [age, setAge] = useState<number | null>(null);
    const [description, setDescription] = useState<string | null>(null);
    const [course, setCourse] = useState<string | null>(null);
    const [loading, setLoading] = useState(true);
    const [followLoading, setFollowLoading] = useState(true);
    const [notFound, setNotFound] = useState(false);
    const [follow, setFollow] = useState(false);
    const [postLoading, setPostsLoading] = useState(true);
    const [postsNotFound, setPostsNotFound] = useState(false);
    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    useEffect(() => {
        setFollowLoading(true);
        setNotFound(false);
        const fetchUserData = async () => {
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
                    console.log("Successful fetching of userdata");
                    setNotFound(false);
                    setLoading(false);
                } else {
                    console.log(new Error("Failed to fetch userdata"));
                    setNotFound(true);
                    setLoading(false);
                }
            } catch (error) {
                console.error("Error fetching data:", error);
                setNotFound(true);
                setLoading(false);
            }
        };

        fetchUserData();

        const fetchUserPosts = async () => {
            setPostsLoading(true);
            setPostsNotFound(false);

            try {
                const response = await fetch(config.apiUrl + `post/user-posts/${id}`, {
                    headers: headersWithJwt
                });
                if (response.ok) {
                    const data = await response.json();
                    if (data.length === 0) {
                        setPostsNotFound(true);
                    } else {
                        console.log("Successful fetching of user posts");
                        setUserPosts(data);
                        setPostsLoading(false);
                        setPostsNotFound(false);
                    }
                } else {
                    console.log(new Error("Failed to fetch user posts"));
                    setPostsLoading(false);
                    setPostsNotFound(true);
                }
            } catch (error) {
                console.error("Error when retrieving the search data:", error);
                setPostsLoading(false);
                setPostsNotFound(true);
            }
        };

        fetchUserPosts();

        const checkIfFollowing = async () => {
            try {
                const response = await fetch(config.apiUrl + `friendship/is-following-user`, {
                    method: 'POST',
                    headers: headersWithJwt,
                    body: JSON.stringify({
                        requesterId: senderId,
                        receiverId: id
                    })
                });

                if (response.ok) {
                    const isFollowing = await response.json();
                    setFollow(isFollowing);
                    setFollowLoading(false);
                    console.log("Successfully checked following status");
                } else {
                    setFollow(false);
                    setFollowLoading(false);
                    console.error("Failed to check following status");
                }
            } catch (error) {
                setFollow(false);
                setFollowLoading(false);
                console.error("Failed to check following status: ", error);
            }
        };
        checkIfFollowing();
    }, [id, senderId]);

    const handleFollow = async (following: boolean) => {
        if (userId === null) return;
        if (!following) {
            try {
                const response = await fetch(config.apiUrl + `friendship/follow-user`, {
                    method: 'POST',
                    headers: headersWithJwt,
                    body: JSON.stringify({
                        requesterId: senderId,
                        receiverId: id
                    })
                });

                if (response.ok) {
                    console.log("Successfully followed the user");
                    setAmountFollower(prev => (prev !== null ? prev + 1 : 1));
                    setFollow(true);
                } else {
                    console.error("Failed to follow the user");
                }
            } catch (error) {
                console.error("Failed to follow the user: ", error);
            }
        } else {
            try {
                const response = await fetch(config.apiUrl + `friendship/unfollow-user`, {
                    method: 'POST',
                    headers: headersWithJwt,
                    body: JSON.stringify({
                        requesterId: senderId,
                        receiverId: id
                    })
                });

                if (response.ok) {
                    console.log("Successfully unfollowed the user");
                    setAmountFollower(prev => (prev !== null ? prev - 1 : 0));
                    setFollow(false);
                } else {
                    console.error("Failed to unfollow the user");
                }
            } catch (error) {
                console.error("Failed to unfollow the user: ", error);
            }
        }
    };

    const FollowButton = () => {
        if (userId === senderId) {
            return <div></div>;
        } else if (followLoading) {
            return (
                <div className="animation">
                    <div className="loading-animation">
                        <Lottie animationData={animationData}/>
                    </div>
                </div>
            );
        } else if (follow) {
            return (
                <button className="unfollow-button" onClick={() => handleFollow(true)}>
                    Unfollow
                </button>
            );
        } else {
            return (
                <button className="follow-button" onClick={() => handleFollow(false)}>
                    Follow
                </button>
            );
        }
    };

    const DisplayPosts = () => {
        if (postsNotFound) {
            return (
                <div className="user-not-found">
                    <h1 className="error">This User doesn´t have Posts</h1>
                </div>
            );
        }
        if (postLoading) {
            return (
                <div className="user-post-component">
                    <div className="animation">
                        <div className="loading-animation">
                            <Lottie animationData={animationData}/>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <div className="user-posts-header">Posts by {username}:</div>
                    <div className="user-posts">
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
                    </div>
                </div>
            );
        }
    };

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

    return (
        <div className="page">
            <Header/>
            <div className="user-component">
                <div className="user-page-picture">
                    {picture.id !== null && picture.imageData ? (
                        <img className="user-image" alt="user" src={picture.imageData} loading="lazy"/>
                    ) : (
                        <img className="custom-image" alt="random-image" src={getDefaultOrRandomPicture(false)}/>
                    )}
                </div>
                <div className="user-page-name">
                    <span>{username}</span>
                </div>
                <div className="user-followers">
                    <span>Followers: {amountFollower}</span>
                </div>
                {course !== null && (
                    <div className="description-field">
                        <span>Course: {course}</span>
                    </div>)
                }

                {age !== null && (
                    <div className="description-field">
                        <span>Age: {age}</span>
                    </div>
                )}
                {description !== null && (
                    <div className="description-field">
                        <span>{description}</span>
                    </div>
                )
                }

                <FollowButton/>
            </div>
            <div>
                <div className="user-posts">
                    <DisplayPosts/>
                </div>
            </div>
            <Footer/>
        </div>
    );
};
