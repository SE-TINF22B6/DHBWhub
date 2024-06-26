import config from "../config/config";
import { getJWT, getUserId, isUserLoggedIn } from "./AuthService";

export const fetchUserImage = async (): Promise<string | null> => {
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };
  const userId: number | null = getUserId();

  if (isUserLoggedIn()) {
    try {
      const response: Response = await fetch(config.apiUrl + `picture/find/${userId}`, {
        headers: headersWithJwt
      });
      if (response.ok) {
        const data = await response.json();
        localStorage.setItem('userImage', data.imageData);
        return data.imageData;
      } else {
        console.error("Failed to fetch user image");
        return null;
      }
    } catch (error) {
      console.error("Error fetching user image:", error);
      return null;
    }
  } else {
    return null;
  }
};