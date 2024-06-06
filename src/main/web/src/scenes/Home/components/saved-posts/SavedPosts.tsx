import React, {useEffect, useMemo, useState} from 'react';
import './SavedPosts.css';
import { Link } from "react-router-dom";
import { SavedPostModel } from "./model/SavedPostModel";
import config from "../../../../config/config";
import {getJWT, getUserId, isUserLoggedIn} from "../../../../services/AuthService";
import {RentalCarAd} from "../../../../atoms/ads/RentalCarAd";
import {useMediaQuery} from "@mui/system";

export const SavedPosts = () => {
  const [savedPosts, setSavedPosts] = useState<SavedPostModel[]>();
  const userId: number | null = getUserId();
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  useEffect((): void => {
    const fetchSavedPosts = async (): Promise<void> => {
      if (isUserLoggedIn()) {
        try {
          const response: Response = await fetch(config.apiUrl + `saved-post/homepage-saved-posts/${userId}`, {
            method: 'GET',
            headers: headersWithJwt
          });
          if (response.ok) {
            const data = await response.json();
            setSavedPosts(data);
          } else {
            console.log(new Error("Failed to fetch saved posts"));
          }
        } catch (error) {
          console.error("Error fetching saved posts:", error);
        }
      } else {
        console.log("User is not logged in: cannot fetch saved posts.");
      }
    };
    fetchSavedPosts();
  }, [userId, headersWithJwt]);

  const uniqueSavedPosts: SavedPostModel[] = (savedPosts ?? []).filter(
      (post: SavedPostModel, index: number, self: SavedPostModel[]): boolean =>
          self.findIndex(p => p.postId === post.postId) === index
  );

  if (savedPosts) {
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
  } else {
    return (
        <div>
          {!isSmartphoneSize && <RentalCarAd/>}
        </div>
    );
  }

};