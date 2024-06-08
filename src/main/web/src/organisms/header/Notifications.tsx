import React, {useEffect, useMemo, useState} from 'react';
import { NavigateFunction, useNavigate } from "react-router-dom";
import "./Notifications.css";
import config from "../../config/config";
import {getJWT, getUserId} from "../../services/AuthService"; // Ensure you import the CSS for styling

interface NotificationsProps {
  showNotifications: boolean;
}

interface Notification {
  notificationId: number;
  groupId: number;
  text: string;
  link: string;
  type: string;
}

interface DeleteNotification {
  notificationId: number;
  groupId: number;
  type: string
}

export const Notifications: React.FC<NotificationsProps> = ({ showNotifications }) => {
  const [notificationShown, setNotificationShown] = useState(false);
  const [notifications, setNotifications] = useState<Notification[]>([]);
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

  const handleRemoveNotification = async (notification: Notification): Promise<void> => {
    const confirmed = window.confirm("Are you sure you want to delete this notification?");
    if (confirmed) {
      try {
        const deleteNotification: DeleteNotification = {
          notificationId: notification.notificationId,
          groupId: notification.groupId,
          type: notification.type
        };

        const response: Response = await fetch(  config.apiUrl + `notification`, {
          method: 'DELETE',
          headers: headersWithJwt,
          body: JSON.stringify(deleteNotification)
        });

        if (response.ok) {
          const updatedNotifications = notifications.filter(n => n.notificationId !== notification.notificationId);
          setNotifications(updatedNotifications);
        } else {
          console.error("Failed to delete notification:", response.statusText);
        }
      } catch (error) {
        console.error("Error fetching notifications:", error);
      }
    }
  };

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const response: Response = await fetch(  config.apiUrl + `/notification/unseen/` + 8, {
          headers: headersWithJwt
        });
        if (response.ok) {
          const data: Notification[] = await response.json();
          setNotifications(data);
        } else {
          console.error("Failed to fetch notifications");
        }
      } catch (error) {
        console.error("Error fetching notifications:", error);
      }
    };

    if (showNotifications) {
      fetchNotifications();
    }
  }, [showNotifications]);

  useEffect(() => {
    const showNotification = async (): Promise<void> => {
      try {
        if ('Notification' in window && showNotifications && !notificationShown) {
          const permission: NotificationPermission = await Notification.requestPermission();
          if (permission === 'granted') {
            /*new Notification('DHBWhub', {
              body: 'Neue Benachrichtigung von DHBWhub.',
              icon: '/logo192.png',
            });*/
            setNotificationShown(true);
          }
        }
      } catch (error) {
        console.error('Fehler beim Anzeigen der Benachrichtigung:', error);
      }
    };

    showNotification();
  }, [showNotifications, notificationShown]);

  return (
      <div className="notifications">
        <span className="notifications-title"> Notifications </span>
        <ul>
          {notifications.map((notification) => (
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
