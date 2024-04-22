const handleLike = (postId: number, userLiked: boolean, likes: number, setLikes: (likes: number) => void, setUserLiked: (userLiked: boolean) => void, setHeartClass: (heartClass: string) => void): void => {
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

const LikeService = {
  handleLike,
};

export default LikeService;