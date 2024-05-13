import config from "../config/config";

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

      await fetch(config.apiUrl + 'increase-likes/${postId}', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
      });
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);

      await fetch(config.apiUrl + 'decrease-likes/${postId}', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
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