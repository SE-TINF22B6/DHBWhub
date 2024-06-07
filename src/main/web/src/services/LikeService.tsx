import config from "../config/config";
import {getJWT, getUserId} from "./AuthService";

const jwt: string | null = getJWT();
const headersWithJwt = {
  ...config.headers,
  'Authorization': jwt ? `Bearer ${jwt}` : ''
};
const userId: number | null = getUserId();

export const checkUserLiked = async (id: number, type: string): Promise<boolean> => {
  let endpoint: string = '';
  if (type === 'post') {
    endpoint = 'post/is-liked';
  } else if (type === 'comment') {
    endpoint = 'comment/is-liked';
  } else if (type === 'event') {
    endpoint = 'event/post-is-liked';
  } else if (type === 'eventComment') {
    endpoint = 'event/comment-is-liked';
  } else {
    console.log(new Error('Invalid type provided: ' +  type));
  }

  const requestOptions = {
    method: 'POST',
    headers: headersWithJwt,
    body: JSON.stringify({
      userId: userId,
      [type + "Id"]: id
    }),
  };

  const response: Response = await fetch(config.apiUrl + endpoint, requestOptions);

  if (!response.ok) {
    throw new Error(`Failed to check if user liked ${type}: ${response.statusText}`);
  }

  return await response.json();
};

export const handleLike = async (
    id: number,
    type: string,
    likes: number,
    setLikes: (likes: number) => void,
    setUserLiked: (userLiked: boolean) => void,
    setHeartClass: (heartClass: string) => void
): Promise<void> => {

  try {
    const userLiked: boolean = await checkUserLiked(id, type);

    let endpoint: string = '';
    if (type === 'post') {
      endpoint = userLiked ? 'post/decrease-likes' : 'post/increase-likes';
    } else if (type === 'comment') {
      endpoint = userLiked ? 'comment/decrease-likes' : 'comment/increase-likes';
    } else if (type === 'event') {
      endpoint = userLiked ? 'event/post-decrease-likes' : 'event/post-increase-likes';
    } else if (type === 'eventComment') {
      endpoint = userLiked ? 'event/comment-decrease-likes' : 'event/comment-increase-likes';
    } else {
      console.log(new Error('Invalid type provided: ' +  type));
    }

    const requestOptions = {
      method: 'PUT',
      headers: headersWithJwt,
      body: JSON.stringify({
        userId: userId,
        [type + "Id"]: id
      }),
    };

    const response: Response = await fetch(config.apiUrl + endpoint, requestOptions);

    if (!response.ok) {
      console.log("Response:", response);
      console.log(new Error(`Failed to ${userLiked ? 'unlike' : 'like'} ${type}: ${response.statusText}`));
    } else {
      if (!userLiked) {
        setLikes(likes + 1);
        setUserLiked(true);
        setHeartClass('heart-filled');
        localStorage.setItem(`${type}_liked_${id}`, 'true');
      } else {
        setLikes(likes - 1);
        setUserLiked(false);
        setHeartClass('heart-empty');
        localStorage.removeItem(`${type}_liked_${id}`);
      }
    }

  } catch (error) {
    console.error('Error handling like:', error);
  }
};