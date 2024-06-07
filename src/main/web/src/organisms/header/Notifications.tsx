import React, { useEffect, useState } from 'react';
import { NavigateFunction, useNavigate } from "react-router-dom";
import "./Notifications.css"; // Ensure you import the CSS for styling

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

export const Notifications: React.FC<NotificationsProps> = ({ showNotifications }) => {
  const [notificationShown, setNotificationShown] = useState(false);
  const [notifications, setNotifications] = useState<Notification[]>([]);
  let navigate: NavigateFunction = useNavigate();

  const handleClick = (link: string): void => {
    navigate(link);
    window.location.reload();
  };

  const handleRemoveNotification = (notificationId: number): void => {
    const confirmed = window.confirm("Are you sure you want to delete this notification?");
    if (confirmed) {
      const updatedNotifications = notifications.filter(notification => notification.notificationId !== notificationId);
      setNotifications(updatedNotifications);
    }
  };

  useEffect(() => {
    const fetchNotifications = async () => {
      // Replace this with your actual API call
      const fetchedNotifications: Notification[] = [
        {notificationId: 1, groupId: 1, text: 'Your Comment on “Tag des Dualen Masters” has been liked by 21 users', link: "/post/?id=9", type: ''},
        {notificationId: 2, groupId: 4, text: 'Your Post “Tag des Dualen Masters” has been liked by 21 users', link: "/post/?id=4", type: '' },
        {notificationId: 3, groupId: 9, text: 'You have 21 new follower now', link: '#', type: '' },
      ];
      setNotifications(fetchedNotifications);
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
                <button className="remove-notification" onClick={() => handleRemoveNotification(notification.notificationId)}>
                  ×
                </button>
              </li>
          ))}
        </ul>
      </div>
  );
};
