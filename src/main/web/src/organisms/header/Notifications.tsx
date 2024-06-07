import React, { useEffect, useState } from 'react';
import {NavigateFunction, useNavigate} from "react-router-dom";

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

  const handleClick = (link : string):void => {
    navigate(link);
    window.location.reload();
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
    const showNotification = async () => {
      try {
        if ('Notification' in window && showNotifications && !notificationShown) {
          const permission = await Notification.requestPermission();
          if (permission === 'granted') {
            /*new Notification('DHBWhub', {
              body: 'Neue Benachrichtigung von DHBWhub.',
              icon: '/logo192.png',
            });
            */
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
      <div className= "notifications">
        <span className= "notifications-title"> Notifications </span>
        <ul>
          {notifications.map((notification) => (
              <li key={notification.notificationId}>
                <a onClick={() => handleClick(notification.link)}>{notification.text}</a>
              </li>
          ))}
        </ul>
      </div>
  );
};
