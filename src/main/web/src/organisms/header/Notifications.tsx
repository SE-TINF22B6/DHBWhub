import React, { useEffect, useState } from 'react';

interface NotificationsProps {
  showNotifications: boolean;
}

interface NavigatorWithBadge extends Navigator {
  setAppBadge?: (count: number) => Promise<void>;
}

export const Notifications: React.FC<NotificationsProps> = ({ showNotifications }) => {
  const [notificationShown, setNotificationShown] = useState(false);

  useEffect(() => {
    const showNotification = async (): Promise<void> => {
      try {
        if ('Notification' in window && showNotifications && !notificationShown) {
          const permission: NotificationPermission = await Notification.requestPermission();
          if (permission === 'granted') {
            new Notification('DHBWhub', {
              body: 'Neue Benachrichtigung von DHBWhub.',
              icon: '/logo192.png',
            });

            const navigatorWithBadge = navigator as NavigatorWithBadge;
            if (navigatorWithBadge.setAppBadge) {
              await navigatorWithBadge.setAppBadge(10);
            }

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
      </div>
  );
};