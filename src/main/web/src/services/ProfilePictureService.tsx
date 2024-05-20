import config from "../config/config";

const fetchUserImage = async (setUserImage: (data: any) => void): Promise<void> => {
  try {
    const response: Response = await fetch(config.apiUrl + "user-image", {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
      }
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