import React, {useEffect, useState} from 'react';
import './SavedPosts.css';
import { Link } from "react-router-dom";
import { dummyPosts } from "./dummyPosts";
import { SavedPostModel } from "./model/SavedPostModel";
import config from "../../../../config/config";
import {DSLAd} from "../../../../atoms/ads/DSLAd";
import {getJWT, getUserId, isUserLoggedIn} from "../../../../services/AuthService";

export const SavedPosts = () => {
  const [savedPosts, setSavedPosts] = useState<SavedPostModel[]>(dummyPosts);
  const userId: number | null = getUserId();
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  useEffect((): void => {
    const fetchSavedPosts = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `saved-post/homepage-saved-posts/${userId}`, {
          method: 'GET',
          headers: headersWithJwt
        });
        if (response.ok) {
          const data = await response.json();
          console.log('Saved posts:' + data);
          setSavedPosts(data);
        } else {
          console.log(new Error("Failed to fetch saved posts"));
        }
      } catch (error) {
        console.error("Error fetching saved posts:", error);
      }
    };
    fetchSavedPosts();
  }, []);

  if (!isUserLoggedIn() || savedPosts === null || savedPosts.length === 0) {
    return <DSLAd/>;
  }

  const uniqueSavedPosts: SavedPostModel[] = savedPosts.filter(
      (post: SavedPostModel, index: number, self: SavedPostModel[]): boolean => self.findIndex(p => p.postId === post.postId) === index
  );

  return (
      <div className="saved-posts">
        <div className="component-headline">Saved posts</div>
        <div className="saved-posts-list">
          {uniqueSavedPosts.slice(0, 10).map((post: SavedPostModel) => (
              <Link key={post.postId} to={`/post/?id=${post.postId}`} className="saved-posts-link">
                <div className="saved-post">
                  {post.title}
                </div>
              </Link>
          ))}
        </div>
      </div>
  );
};