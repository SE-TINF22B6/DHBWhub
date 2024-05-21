import React, {useEffect, useState} from "react";
import "./Posts.css";
import { Post } from "./Post";
import {PostModel} from "./models/PostModel";
import {dummyPosts} from "./dummyPosts";
import config from "../../../../config/config";

interface PostsProps {
  sortOption: string;
}

export const Posts: React.FC<PostsProps> = ({ sortOption }) => {
  const [posts, setPosts] = useState<PostModel[]>(dummyPosts);
  const [followingPosts, setFollowingPosts] = useState<PostModel[]>(dummyPosts);

  useEffect((): void => {
    const fetchPosts = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "post/homepage-preview-posts");
        if (response.ok) {
          const data = await response.json();
          setPosts(data);
        } else {
          console.log(new Error("Failed to fetch posts"));
        }
      } catch (error) {
        console.error("Error fetching posts:", error);
      }
    };
    fetchPosts();
  }, []);

  useEffect((): void => {
    const fetchFollowingPosts = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "post/following-posts", {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
          }
        });
        if (response.ok) {
          const data = await response.json();
          setFollowingPosts(data);
        } else {
          console.log(new Error("Failed to fetch following posts"));
        }
      } catch (error) {
        console.error("Error fetching following posts:", error);
      }
    };
    fetchFollowingPosts();
  }, []);

  const sortedPosts = (): PostModel[] => {
    switch (sortOption) {
      case "newest":
        return posts.sort((a, b) => Date.parse(new Date(b.timestamp).toISOString()) -
            Date.parse(new Date(a.timestamp).toISOString()));
      case "following":
        return followingPosts;
      case "popular":
        return posts.sort((a, b) => b.likeAmount - a.likeAmount);
      default:
        return posts;
    }
  };

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
                image={post.image}
                accountId={post.accountId}
                username={post.username}
            />
        ))}
      </div>
  );
};