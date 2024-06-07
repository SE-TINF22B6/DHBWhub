import React, {useEffect, useMemo, useState} from "react";
import "./Posts.css";
import { Post } from "./Post";
import {PostModel} from "./models/PostModel";
import config from "../../../../config/config";
import {getJWT, getUserId, isUserLoggedIn} from "../../../../services/AuthService";
import Lottie from "lottie-react";
import animationData from "../../../../assets/loading.json";

interface PostsProps {
  sortOption: string;
}

export const Posts: React.FC<PostsProps> = ({ sortOption }) => {
  const [posts, setPosts] = useState<PostModel[]>();
  const [followingPosts, setFollowingPosts] = useState<PostModel[]>([]);
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);
  const userId: number | null = getUserId();
  const [loading, setLoading] = useState(true);

  useEffect((): void => {
    const fetchPosts = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "post/homepage-preview-posts", {
          headers: config.headers
        });
        if (response.ok) {
          const data = await response.json();
          setPosts(data);
          setLoading(false);
        } else {
          console.error("Failed to fetch posts");
        }
      } catch (error) {
        console.error("Error fetching posts:", error);
      }
    };
    fetchPosts();
  }, []);

  useEffect((): void => {
    const fetchFollowingPosts = async (): Promise<void> => {
      if (isUserLoggedIn()) {
        try {
          const response: Response = await fetch(config.apiUrl + `post/friend-posts/${userId}`, {
            headers: headersWithJwt
          });
          if (response.ok) {
            const followingPosts = await response.json();
            setFollowingPosts(followingPosts);
          } else {
            console.error("Failed to fetch following posts");
          }
        } catch (error) {
          console.error("Error fetching following posts:", error);
        }
      }
    };
    fetchFollowingPosts();
  }, [headersWithJwt, userId]);

  const sortedPosts = (): PostModel[] => {
    if (posts === undefined) return [];
    switch (sortOption) {
      case "newest":
        return posts.sort((a: PostModel, b: PostModel) => Date.parse(new Date(b.timestamp).toISOString()) -
            Date.parse(new Date(a.timestamp).toISOString()));
      case "following":
        return followingPosts;
      case "popular":
        return posts.sort((a: PostModel, b: PostModel) => b.likeAmount - a.likeAmount);
      default:
        return posts;
    }
  };

  if (loading) {
    return (
        <div className="posts">
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
        </div>
    );
  }

  return (
      <div className="posts">
        {sortedPosts().map(post => (
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