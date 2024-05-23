import config from "../config/config";
import {getJWT} from "./AuthService";

const jwt: string | null = getJWT();
const headersWithJwt = {
  ...config.headers,
  'Authorization': jwt ? `Bearer ${jwt}` : ''
};

const handleLike = async (
    postId: number,
    userLiked: boolean,
    likes: number,
    setLikes: (likes: number) => void,
    setUserLiked: (userLiked: boolean) => void,
    setHeartClass: (heartClass: string) => void
): Promise<void> => {
  try {
    const jwt: string | null = getJWT();
    if (!userLiked) {
      setLikes(likes + 1);
      setUserLiked(true);
      setHeartClass('heart-filled');
      localStorage.setItem(`liked_${postId}`, 'true');

      await fetch(config.apiUrl + `increase-likes/${postId}`, {
        method: 'POST',
        headers: headersWithJwt
      });
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);

      await fetch(config.apiUrl + `decrease-likes/${postId}`, {
        method: 'POST',
        headers: headersWithJwt
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