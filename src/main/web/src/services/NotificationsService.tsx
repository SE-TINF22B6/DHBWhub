import {NotificationModel} from "../organisms/header/Notifications";
import {getJWT} from "./AuthService";
import {useMemo} from "react";
import config from "../config/config";

export const fetchNotifications = async (): Promise<NotificationModel[]> => {
    try {
        const jwt: string | null = getJWT();
        const headersWithJwt = {
            ...config.headers,
            'Authorization': jwt ? `Bearer ${jwt}` : ''
        };

        const response: Response = await fetch(    `https://localhost:8443/notification/unseen/` + 7, {
            headers: headersWithJwt
        });

        if (response.ok) {
            const data: NotificationModel[] = await response.json();
            return data;
        } else {
            console.error("Failed to fetch notifications");
            return [];
        }
    } catch (error) {
        console.error("Error fetching notifications:", error);
        return [];
    }
};