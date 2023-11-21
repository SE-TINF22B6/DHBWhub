import React, {useState} from 'react';
import './Post.css';
import {Link} from "react-router-dom";

interface PostProps {
  postId: number;
  title: string;
  description: string;
  tags: string[];
  authorUsername: string;
  authorId: number;
  postCreatedTimestamp: string;
  initialLikes: number;
  initialComments: number;
  imageSrc: string;
}

export const Post: React.FC<PostProps> = (props: PostProps) => {
  let {
    postId,
    title,
    tags,
    authorUsername,
    authorId,
    initialLikes,
    initialComments,
  } = props;

  const timeDifference = (timestamp: string): string => {
    const postTime: number = new Date(timestamp).getTime();
    const currentTime: number = new Date().getTime();
    const difference: number = currentTime - postTime;

    const seconds: number = Math.floor(difference / 1000);
    const minutes: number = Math.floor(seconds / 60);
    const hours: number = Math.floor(minutes / 60);
    const days: number = Math.floor(hours / 24);

    if (days > 0) {
      return `${days} day${days > 1 ? 's' : ''} ago`;
    } else if (hours > 0) {
      return `${hours} hour${hours > 1 ? 's' : ''} ago`;
    } else if (minutes > 0) {
      return `${minutes} minute${minutes > 1 ? 's' : ''} ago`;
    } else {
      return 'Just now';
    }
  };

  const shortenDescription = (text: string, maxLength: number): string => {
    if (text.length <= maxLength) {
      return text;
    }
    const shortenedText: string = text.slice(0, maxLength);
    const lastSpace: number = shortenedText.lastIndexOf(' ');
    if (lastSpace === -1 || (text[maxLength] !== ' ')) {
      return shortenedText + '...';
    } else {
      return text.slice(0, lastSpace) + '...';
    }
  };

  const formattedTime = timeDifference(props.postCreatedTimestamp);
  const [likes, setLikes] = useState(initialLikes);
  const [userLiked, setUserLiked] = useState(false);
  const [heartClass, setHeartClass] = useState('heart-empty');
  const [comments] = useState(initialComments);
  const shortDescription = shortenDescription(props.description, 190);

  const handleLike = (): void => {
    if (!userLiked) {
      setLikes(likes + 1);
      setUserLiked(true);
      setHeartClass('heart-filled');
      localStorage.setItem(`liked_${postId}`, 'true');
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);
    }
  };

  return (
      <div className="post-container">
        <div className="post">
          <div className="picture" style={{backgroundImage: `url(${props.imageSrc})`}}></div>
          <div className="post-infos">
            <Link to={`/post/?id=${postId}`} className="post-button">
              <p className="post-title">{title}</p>
            </Link>
            <div className="tags">
              {tags.length > 0 && <div className="post-tag">{tags[0]}</div>}
              {tags.length > 1 && <div className="post-tag">{tags[1]}</div>}
              {tags.length > 2 && <div className="post-tag">{tags[2]}</div>}
            </div>
            <p className="short-description">{shortDescription}</p>
            <div className="seperator"/>
            <p className="author-date">{authorUsername} · {formattedTime}</p>
          </div>

          <div className="interaction-stats">
            <div className="stats">
              <div className="likes">{likes} likes</div>
              <div className="comments">{comments} comments</div>
            </div>
          </div>
          <div className="interaction-symbols">
            <button className={`like-button ${userLiked ? 'liked' : ''}`} onClick={handleLike}>
              <div className={`heart-symbol ${heartClass}`} />
            </button>
            <div className="comment-symbol"/>
          </div>
        </div>
      </div>
  );
};