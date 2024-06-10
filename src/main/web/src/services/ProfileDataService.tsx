import {getJWT, getUserId, getUsername} from "./AuthService";
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

const getHeaders = () => {
    const jwt: string | null = getJWT();
    return {
        ...config.headers,
        'Authorization': jwt ? `Bearer ${jwt}` : ''
    };
};

export const fetchUserData = async (): Promise<UserData | null> => {
    const userId: number | null = getUserId();

    try {
        const response: Response = await fetch(`${config.apiUrl}user/${userId}`, {
            headers: getHeaders()
        });

        if (response.ok) {
            const data = await response.json();
            const userData: UserData = {
                username: data.account.username ?? "",
                email: data.account.email ?? "",
                picture: data.account.picture ? data.account.picture : { id: 0, name: '', imageData: '' },
                amountFollower: data.amountFollower ?? 0,
                age: data.age ?? '',
                description: data.description ?? '',
                course: data.course?.name ?? ''
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

export const updateAge = async (age: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-age`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, age })
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating age:", error);
        return false;
    }
};

export const updateDescription = async (description: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-description`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, description })
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating description:", error);
        return false;
    }
};

export const updateCourse = async (course: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-course`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, course })
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating course:", error);
        return false;
    }
};

export const updateEmail = async (email: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-email`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, email })
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating email:", error);
        return false;
    }
};

export const updateUsername = async (username: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-username`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, username })
        });

        return response.ok;
    } catch (error) {
        console.error("Error updating username:", error);
        return false;
    }
};

export const updatePassword = async (password: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();
    try {
        const response = await fetch(`${config.apiUrl}user/update-password`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, password })
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating password:", error);
        return false;
    }
};

export const fetchNewToken = async (): Promise<void> => {
    const headers = getHeaders();
    const username = getUsername();
    try {
        const response = await fetch(`${config.apiUrl}/api/auth/new-token`, {
            method: 'POST',
            headers,
            body: JSON.stringify({ username })
        });

        if (response.ok) {
            const data = await response.json();
            const newToken = data.token; // Annahme: Die Antwort enthält den neuen Token im Feld "token"
            localStorage.setItem('token', newToken); // Speichern Sie den Token im localStorage
            console.log('New token fetched and saved successfully');
        } else {
            console.error('Failed to fetch new token:', response.statusText);
        }
    } catch (error) {
        console.error('Error fetching new token:', error);
    }
};


export const updatePicture = async (imageData: string): Promise<boolean> => {
    const headers = getHeaders();
    const userId = getUserId();

    try {
        const response = await fetch(`${config.apiUrl}user/update-picture`, {
            method: 'PUT',
            headers,
            body: JSON.stringify({ userId, imageData }),
        });
        return response.ok;
    } catch (error) {
        console.error("Error updating picture:", error);
        return false;
    }
};


