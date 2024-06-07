import config from "../config/config";
import { getJWT, getUserId } from "./AuthService";
import { PostModel } from "../scenes/Home/components/post/models/PostModel";

export const fetchPostsByTag = async (tagName: string | null): Promise<PostModel[] | null> => {
  try {
    const response: Response = await fetch(config.apiUrl + `post/posts-by-tag/${tagName}`, {
      headers: config.headers,
    });
    if (response.ok) {
      return await response.json();
    } else {
      console.log(response);
      return null;
    }
  } catch (error) {
    console.error(`Error retrieving the posts for tag ${tagName}: ` + error);
    return null;
  }
};

export const fetchFriendPostsByTag = async (tagName: string | null): Promise<PostModel[] | null> => {
  try {
    const jwt: string | null = getJWT();
    const headersWithJwt = {
      ...config.headers,
      'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    const response: Response = await fetch(config.apiUrl + `post/friend-posts-by-tag`, {
      headers: headersWithJwt,
      method: 'POST',
      body: JSON.stringify({
        userId: getUserId(),
        tag: tagName,
      }),
    });
    if (response.ok) {
      return await response.json();
    } else {
      console.log(response);
      return null;
    }
  } catch (error) {
    console.error(`Error retrieving the posts of your followers with tag ${tagName}: ` + error);
    return null;
  }
};
