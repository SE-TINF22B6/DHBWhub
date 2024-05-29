import config from "../config/config";
import {getJWT, getUserId, isUserLoggedIn} from "./AuthService";

const fetchUserImage = async (setUserImage: (data: any) => void): Promise<void> => {
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
        setUserImage(data);
      } else {
        console.log(new Error("Failed to fetch user image"));
      }
    } catch (error) {
      console.error("Error fetching user image:", error);
    }
  } else {
    console.log("User is not logged in: cannot fetch user image.");
  }
};

const ProfilePictureService = {
  fetchUserImage,
};

export default ProfilePictureService;