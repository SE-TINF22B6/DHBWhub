import React, {useEffect, useState} from "react";
import "./Posts.css";
import { Post } from "./Post";
import {PostModel} from "./models/PostModel";
// @ts-ignore
import {dummyPosts} from "./dummyPosts";

interface PostsProps {
  sortOption: string;
}

export const Posts: React.FC<PostsProps> = ({ sortOption }) => {
  const [posts, setPosts] = useState<PostModel[]>(dummyPosts);
  const [followingPosts, setFollowingPosts] = useState<PostModel[]>(dummyPosts);

  useEffect((): void => {
    const fetchPosts = async (): Promise<void> => {
      try {
        const response: Response = await fetch(process.env.API_URL + "post/homepage-preview-posts", {
          headers: {
            'Authorization': '',
            'Access-Control-Allow-Origin': 'http://localhost:3000'
          }
        });
        if (response.ok) {
          const data = await response.json();
          console.log(data);
          setPosts(data);
        } else {
          throw new Error("Failed to fetch posts");
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
        const response: Response = await fetch(process.env.API_URL + "post/following-posts", {
          headers: {
            'Authorization': '',
          }
        });
        if (response.ok) {
          const data = await response.json();
          console.log(data);
          setFollowingPosts(data);
        } else {
          throw new Error("Failed to fetch following posts");
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