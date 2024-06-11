import React, {useEffect, useMemo, useState} from "react";
import "./SearchService.css";
import Lottie from "lottie-react";
import animationData from "../../../assets/loading.json";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../../organisms/ad-block-overlay/preventScrolling";
import {getJWT} from "../../../services/AuthService";
import config from "../../../config/config";
import {PostModel} from "../../Home/components/post/models/PostModel";
import {User} from "./User";
import {Post} from "../../Home/components/post/Post";
import {UserModel} from "./models/UserModel";


interface SearchedPostsProps {
    sortOption: string;
    query: string | null;
    findByOption: string;
}

export const SearchService: React.FC<SearchedPostsProps> = ({sortOption, query, findByOption}) => {
    const [searchResults, setSearchResults] = useState<any[]>([]);
    const jwt: string | null = getJWT();
    const headersWithJwt = useMemo(() => ({
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    }), [jwt]);

    const [notFound, setNotFound] = useState(false);
    const [loading, setLoading] = useState(true);
    const adBlockDetected = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);

    useEffect(() => {
        setSearchResults([]);
        setNotFound(false);
        setLoading(true);

        const fetchResults = async (url: string): Promise<void> => {
            try {
                const response: Response = await fetch(url, {
                    headers: headersWithJwt
                });
                if (response.ok) {
                    const data = await response.json();
                    if (data.length === 0) {
                        setNotFound(true);
                    } else {
                        setSearchResults(data);
                        setNotFound(false);
                    }

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

        switch (findByOption) {
            case "title":
                fetchResults(config.apiUrl + `post/posts-by-keyword/${query}`);
                break;
            case "tags":
                fetchResults(config.apiUrl + `post/posts-by-tag-keyword/${query}`);
                break;
            case "user":
                fetchResults(config.apiUrl + `user/user-by-keyword/${query}`);
                break;
            default:
                setNotFound(true);
                setLoading(true);
                break;
        }
    }, [findByOption, query, headersWithJwt]);

    const sortedResults = (): PostModel[] => {
        if (!searchResults || searchResults.length === 0) setNotFound(true);
        if (findByOption !== "user") {
            switch (sortOption) {
                case "newest":
                    return searchResults.sort((a: PostModel, b: PostModel) =>
                        Date.parse(new Date(b.timestamp).toISOString()) - Date.parse(new Date(a.timestamp).toISOString()));
                case "following":
                    return searchResults;
                case "popular":
                    return searchResults.sort((a: PostModel, b: PostModel) => b.likeAmount - a.likeAmount);
                default:
                    return searchResults;
            }
        }
        return searchResults;
    };

    if (loading) {
        return (
            <div className="animation">
                <div className="loading-animation">
                    <Lottie animationData={animationData}/>
                </div>
            </div>

        );
    }

    if (notFound) {
        return (
            <div className="search-results">
                <h1 className="error">No results for search term: {query}</h1>
            </div>
        );
    }

    if (findByOption === "user") {

        return (
            <div className="user-search-results">
                {searchResults.map((user: UserModel) => (
                    <User
                        key={user.userId}
                        userId={user.userId}
                        username={user.username}
                        picture={user.picture}
                    />))
                }
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
};