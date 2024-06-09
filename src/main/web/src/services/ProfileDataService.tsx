import {getJWT, getUserId} from "./AuthService";
import config from "../config/config";

interface UserData {
    username: string;
    email: string;
    picture: {
        id: number;
        name: string;
        imageData: string;
    };
    amountFollower: number;
    age: string;
    description: string;
    course: string;
}

export const fetchUserData = async (): Promise<UserData | null> => {
    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };
    const userId: number | null = getUserId();

    try {
        const response: Response = await fetch(`${config.apiUrl}user/${userId}`, {
            headers: headersWithJwt
        });

        if (response.ok) {
            const data = await response.json();
            const userData: UserData = {
                username: data.account.username,
                email: data.account.email,
                picture: data.account.picture ? data.account.picture : { id: 0, name: '', imageData: '' },
                amountFollower: data.amountFollower ?? 0,
                age: data.age ?? '',
                description: data.description ?? '',
                course: data.course ?? ''
            };
            console.log("Successful fetching of userdata", userData);
            return userData;
        } else {
            console.error("Failed to fetch Userdata");
            return null;
        }
    } catch (error) {
        console.error("Error fetching Data:", error);
        return null;
    }
};

export const updateUserProfile = async (updatedUserData: UserData): Promise<boolean> => {
    const jwt: string | null = getJWT();
    const headersWithJwt = {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };

    try {
        const response: Response = await fetch(`${config.apiUrl}/user/${getUserId()}`, {
            method: 'PUT',
            headers: headersWithJwt,
            body: JSON.stringify(updatedUserData)
        });

        if (response.ok) {
            console.log("User data updated successfully");
            return true;
        } else {
            console.error("Failed to update user data");
            return false;
        }
    } catch (error) {
        console.error("Error updating user data:", error);
        return false;
    }
};

