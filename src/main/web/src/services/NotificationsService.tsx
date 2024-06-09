import {NotificationModel} from "../organisms/header/Notifications";
import {getJWT, getUserId} from "./AuthService";
import config from "../config/config";

export const fetchNotifications = async (): Promise<NotificationModel[]> => {
    try {
        const userId = getUserId();
        const jwt: string | null = getJWT();
        const headersWithJwt = {
            ...config.headers,
            'Authorization': jwt ? `Bearer ${jwt}` : ''
        };

        const response: Response = await fetch(config.apiUrl + `notification/unseen/` + userId, {
            headers: headersWithJwt
        });

        if (response.ok) {
            return await response.json();
        } else {
            console.error("Failed to fetch notifications");
            return [];
        }
    } catch (error) {
        console.error("Error fetching notifications:", error);
        return [];
    }
};