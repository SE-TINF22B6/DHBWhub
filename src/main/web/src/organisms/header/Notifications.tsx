import React, {Dispatch, SetStateAction, useEffect, useMemo, useState} from 'react';
import { NavigateFunction, useNavigate } from "react-router-dom";
import "./Notifications.css";
import config from "../../config/config";
import {getJWT} from "../../services/AuthService";

interface NotificationsProps {
  showNotifications: boolean;
  notifications: NotificationModel[];
  setNotifications: Dispatch<SetStateAction<NotificationModel[]>>;
}

export interface NotificationModel {
  notificationId: number;
  groupId: number;
  text: string;
  link: string;
  type: string;
}

interface DeleteNotificationModel {
  notificationId: number;
  groupId: number;
  type: string
}

export const Notifications: React.FC<NotificationsProps> = ({ showNotifications, notifications, setNotifications}) => {
  const [notificationShown, setNotificationShown] = useState(true);
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
    const confirmed = window.confirm("Are you sure you want to delete this notification?");
    if (confirmed) {
      try {
        const deleteNotification: DeleteNotificationModel = {
          notificationId: notification.notificationId,
          groupId: notification.groupId,
          type: notification.type
        };

        const response: Response = await fetch(`https://localhost:8443/notification`, {
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
/*
  useEffect(() => {
    const showNotification = async (): Promise<void> => {
      try {
        if ('Notification' in window && showNotifications && !notificationShown) {
          const permission: NotificationPermission = await Notification.requestPermission();
          if (permission === 'granted') {
            /*new Notification('DHBWhub', {
              body: 'Neue Benachrichtigung von DHBWhub.',
              icon: '/logo192.png',
            });
            setNotificationShown(true);
          }
        }
      } catch (error) {
        console.error('Fehler beim Anzeigen der Benachrichtigung:', error);
      }
    };

    showNotification();
  }, [showNotifications, notificationShown]);
  */
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
