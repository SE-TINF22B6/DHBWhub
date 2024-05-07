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

      await fetch(`http://193.196.38.232:8080/post-liked/${postId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': "Basic ZGhid2h1YjpQZEA1ZmtaQ3dTMjRUNFclY3ZxYkdONXZqVXVuRUAmcWRONEpLdnM0amdlSiZmKndY"
        },
        body: JSON.stringify({ liked: true }),
      });
    } else {
      setLikes(likes - 1);
      setUserLiked(false);
      setHeartClass('heart-empty');
      localStorage.removeItem(`liked_${postId}`);

      await fetch(`http://193.196.38.232:8080/post-liked/${postId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': "Basic ZGhid2h1YjpQZEA1ZmtaQ3dTMjRUNFclY3ZxYkdONXZqVXVuRUAmcWRONEpLdnM0amdlSiZmKndY"
        },
        body: JSON.stringify({ liked: false }),
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