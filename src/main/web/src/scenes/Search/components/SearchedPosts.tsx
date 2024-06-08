import React, {useEffect, useMemo, useState} from "react";
import "./SearchedPosts.css";
import Lottie from "lottie-react";
import animationData from "../../../assets/loading.json";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../../organisms/ad-block-overlay/preventScrolling";
import {getJWT, getUserId, isUserLoggedIn} from "../../../services/AuthService";
import config from "../../../config/config";
import {PostModel} from "./PostModel";
import {Post} from "./Post";
interface SearchedPostsProps {
    sortOption: string;
    query: string| null;
}

export const SearchedPosts: React.FC<SearchedPostsProps> = ({ sortOption, query }) => {
    const [searchResults, setSearchResults] = useState([]);
    const jwt: string | null = getJWT();
    const userId: number | null = getUserId();
    const headersWithJwt = useMemo(() => ({
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    }), [jwt]);

    const [notFound, setNotFound] = useState(false);
    const [loading, setLoading] = useState(true);
    const adBlockDetected = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);

    useEffect((): void => {
        const fetchResults = async (): Promise<void> => {
            try {
                const response: Response = await fetch(config.apiUrl + `post/posts-by-keyword/${query}`, {
                    headers: headersWithJwt
                });
                if (response.ok) {
                    setSearchResults(await response.json());
                } else {
                    setNotFound(true);
                }
                setLoading(false);
            } catch (error) {
                console.error("Error when retrieving the search data:", error);
                setNotFound(true);
                setLoading(false);
            }
        };

        fetchResults();
    }, [query, headersWithJwt]);

    useEffect((): void => {
        const fetchTagResults = async (): Promise<void> => {
            try {
                const response: Response = await fetch(config.apiUrl + `post/posts-by-tag-keyword/${query}`, {
                    headers: headersWithJwt
                });
                if (response.ok) {
                    setSearchResults(await response.json());
                } else {
                    setNotFound(true);
                    console.error("Error while fetching tags"+ response.json())
                }
                setLoading(false);
            } catch (error) {
                console.error("Error when retrieving the search data:", error);
                setNotFound(true);
                setLoading(false);
            }
        };

        fetchTagResults();
    }, [query, headersWithJwt]);

    useEffect((): void => {
        const fetchFollowingPosts = async (): Promise<void> => {
            if (isUserLoggedIn()) {
                try {
                    const response: Response = await fetch(config.apiUrl + `post/friend-posts/${userId}`, {
                        headers: headersWithJwt
                    });
                    if (response.ok) {
                        const followingPosts = await response.json();
                        setSearchResults(followingPosts);
                        console.log("Fetching ok");
                    } else {
                        setNotFound(true);
                        console.log(new Error("Failed to fetch following posts"));
                    }
                } catch (error) {
                    console.error("Error fetching following posts:", error);

                }
            }
        };
        fetchFollowingPosts();
    }, [headersWithJwt, userId]);

    const sortedResults = (): PostModel[] => {
        if (searchResults === undefined) return [];
        switch (sortOption) {
            case "newest":
                return searchResults.sort((a: PostModel, b: PostModel) => Date.parse(new Date(b.timestamp).toISOString()) -
                    Date.parse(new Date(a.timestamp).toISOString()));
            case "following":
                return searchResults;
            case "popular":
                return searchResults.sort((a: PostModel, b: PostModel) => b.likeAmount - a.likeAmount);
            default:
                return searchResults;
        }
    };

    if (loading) {
        return (

            <div className="loading-animation">
                <Lottie animationData={animationData}/>
            </div>)

    }

    if (notFound) {
        return (

            <div className="search-results">
                <h1 className="error">No results for search term: {query}</h1>
            </div>

        );
    }

    return (
        <div className="search-results">
            {sortedResults().map(post => (
                <Post
                    key={post.id}
                    id={post.id}
                    title={post.title}
                    description={post.description}
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
};