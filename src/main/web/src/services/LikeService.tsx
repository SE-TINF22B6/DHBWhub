import config from "../config/config";
import {getJWT, getUserId} from "./AuthService";

const jwt: string | null = getJWT();
const headersWithJwt = {
  ...config.headers,
  'Authorization': jwt ? `Bearer ${jwt}` : ''
};
const userId: number | null = getUserId();

const handleLike = async (
    postId: number,
    userLiked: boolean,
    likes: number,
    setLikes: (likes: number) => void,
    setUserLiked: (userLiked: boolean) => void,
    setHeartClass: (heartClass: string) => void
): Promise<void> => {
  try {
    if (!userLiked) {
      setLikes(likes + 1);
      setUserLiked(true);
      setHeartClass('heart-filled');
      localStorage.setItem(`liked_${postId}`, 'true');

      await fetch(config.apiUrl + `post/increase-likes`, {
        method: 'PUT',
        headers: headersWithJwt,
        body: JSON.stringify({
          userId: userId,
          postId: postId
        }),
      });
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);

      await fetch(config.apiUrl + `post/decrease-likes`, {
        method: 'PUT',
        headers: headersWithJwt,
        body: JSON.stringify({
          userId: userId,
          postId: postId
        }),
      });
    }
  } catch (error) {
    console.error('Error handling like:', error);
  }
};

const LikeService = {
  handleLike,
};

export default LikeService;