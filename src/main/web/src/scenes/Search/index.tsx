import React, {useEffect, useMemo, useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {useLocation} from "react-router-dom";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";
import {Footer} from "../../organisms/footer/Footer";
import {SearchSortOptions} from "./components/SearchSortOptions";
import {FindByOptions} from "./components/FindByOptions";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {C24Ad} from "../../atoms/ads/C24Ad";
import {getJWT, getUserId, isUserLoggedIn} from "../../services/AuthService";
import config from "../../config/config";
import {PostModel} from "./components/PostModel";
import {Post} from "./components/Post";

export const Search = () => {
  const location = useLocation();
  const searchParams: URLSearchParams = new URLSearchParams(location.search);
  const searchTerm: string | null = searchParams.get('query');
  const [searchResults, setSearchResults] = useState([]);
  const jwt: string | null = getJWT();
  const userId: number | null = getUserId();
  const [sortOption, setSortOption] = useState<string>('popular');
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
        const response: Response = await fetch(config.apiUrl + `/post/posts-by-keyword/${searchTerm}`, {
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
  }, [searchTerm, headersWithJwt]);

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
            console.log("Fetching ok" );
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


  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  const handleFindByChange = (option: string): void => {
    console.log("option"+ option)
  };

  if (loading) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
          <Footer/>
        </div>
    );
  }
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
  if (notFound) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <div className="search-content">
            <div className="search-sidebar">
              <SearchSortOptions onSortChange={handleSortChange}/>
              <FindByOptions onSortChange={handleFindByChange}/>
            </div>
            <div className="search-results">
              <h1 className="error">No results for search term: {searchTerm}</h1>
            </div>
            <C24Ad/>
          </div>
          <Footer/>
        </div>
    );
  }

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <div className="search-content">
        <div className="search-sidebar">
          <SearchSortOptions onSortChange={handleSortChange}/>
          <FindByOptions onSortChange={handleFindByChange}/>
        </div>
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
        </div>
        <Footer/>
      </div>
  );
};