import React, {Dispatch, SetStateAction, useMemo} from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import "./Notifications.css";
import config from "../../config/config";
import {getJWT} from "../../services/AuthService";
import {NotificationModel} from "./model/NotificationModel";
import {DeleteNotificationModel} from "./model/DeleteNotificationModel";

interface NotificationsProps {
  showNotifications: boolean;
  notifications: NotificationModel[];
  setNotifications: Dispatch<SetStateAction<NotificationModel[]>>;
}

export const Notifications: React.FC<NotificationsProps> = ({ notifications, setNotifications}) => {
  let navigate: NavigateFunction = useNavigate();
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);

  const handleClick = (link: string): void => {
    navigate(link);
    window.location.reload();
  };

  const handleRemoveNotification = async (notification: NotificationModel): Promise<void> => {
    const confirmed: boolean = window.confirm("Are you sure you want to delete this notification?");
    if (confirmed) {
      try {
        const deleteNotification: DeleteNotificationModel = {
          notificationId: notification.notificationId,
          groupId: notification.groupId,
          type: notification.type
        };

        const response: Response = await fetch(config.apiUrl + `notification`, {
          method: 'DELETE',
          headers: headersWithJwt,
          body: JSON.stringify(deleteNotification)
        });

        if (response.ok) {
          const updatedNotifications: NotificationModel[] = notifications.filter(n => n.notificationId !== notification.notificationId);
          setNotifications(updatedNotifications);
        } else {
          console.error("Failed to delete notification:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching notifications:", error);
      }
    }
  };

  return (
      <div className="notifications">
        <span className="notifications-title"> Notifications </span>
        <ul>
          {notifications.map((notification: NotificationModel) => (
              <li key={notification.notificationId} className="notification-item">
                <a onClick={() => handleClick(notification.link)}>{notification.text}</a>
                <button className="remove-notification" onClick={() => handleRemoveNotification(notification)}>
                  ×
                </button>
              </li>
          ))}
        </ul>
      </div>
  );
};