import config from "../config/config";
import {getJWT} from "./AuthService";

const fetchUserImage = async (setUserImage: (data: any) => void): Promise<void> => {
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  try {
    const response: Response = await fetch(config.apiUrl + "user-image", {
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
};

const ProfilePictureService = {
  fetchUserImage,
};

export default ProfilePictureService;