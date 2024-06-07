import React, { useEffect, useMemo, useState } from "react";
import "./index.css";
import { Header } from "../../organisms/header/Header";
import { Calendar, DateLocalizer, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import "react-big-calendar/lib/css/react-big-calendar.css";
import { Footer } from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import { useDetectAdBlock } from "adblock-detect-react";
import { usePreventScrolling } from "../../organisms/ad-block-overlay/preventScrolling";
import { C24Ad } from "../../atoms/ads/C24Ad";
import { MobileAd } from "../../atoms/ads/MobileAd";
import { MobileFooter } from "../../organisms/header/MobileFooter";
import { NavigateFunction, useNavigate } from 'react-router-dom';
import { useMediaQuery } from "@mui/system";
import { getJWT } from "../../services/AuthService";
import config from "../../config/config";
import {CalendarEvent} from "./model/CalendarEvent";

moment.updateLocale('de', {
  week: {
    dow: 1,
  },
});

const localizer: DateLocalizer = momentLocalizer(moment);

interface EndpointEvent {
  eventId: number;
  title: string;
  startDate: number;
  endDate: number;
}

const convertEndpointEventToFrontendEvent = (endpointEvent: EndpointEvent): CalendarEvent => {
  return {
    id: endpointEvent.eventId,
    title: endpointEvent.title,
    start: new Date(endpointEvent.startDate),
    end: new Date(endpointEvent.endDate),
  };
};

export const CalendarPage: React.FC = () => {
  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const navigate: NavigateFunction = useNavigate();

  const handleEventClick = (event: {
    end: string | number | Date;
    start: string | number | Date; id: number;
  }): void => {
    navigate(`/event?id=${event.id}`);
  };

  const [calendarEvents, setCalendarEvents] = useState<CalendarEvent[]>([]);

  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);

  useEffect((): void => {
    const fetchCalendarEvents = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `event/calendar-events`, {
          method: 'GET',
          headers: headersWithJwt
        });
        if (response.ok) {
          const events: EndpointEvent[] = await response.json();
          const frontendEvents: CalendarEvent[] = events.map(convertEndpointEventToFrontendEvent);
          setCalendarEvents(frontendEvents);
        } else {
          console.log(new Error("Failed to fetch calendar events"));
        }
      } catch (error) {
        console.error("Failed to fetch calendar events:", error);
      }
    };
    fetchCalendarEvents();
  }, [headersWithJwt]);

  return (
      <div className="page calendar-page">
        {adBlockDetected && <AdBlockOverlay />}
        <Header />
        <div className="calendar-content">
          <MobileAd />
          <Calendar
              className="calendar"
              onSelectEvent={handleEventClick}
              localizer={localizer}
              events={calendarEvents}
              startAccessor={(event: CalendarEvent) => new Date(event.start)}
              endAccessor={(event: CalendarEvent) => new Date(event.end)}
              style={{ height: 610 }}
              eventPropGetter={(event, start, end, isSelected) => {
                let newStyle = { backgroundColor: "var(--red)", color: 'var(--white)' };
                return { style: newStyle };
              }}
              dayPropGetter={(day: Date) => {
                let newStyle = { color: 'var(--white)', backgroundColor: 'transparent' };
                return { style: newStyle };
              }}
          />
          <C24Ad />
        </div>
        <Footer />
        {isSmartphoneSize && <MobileFooter />}
      </div>
  );
};
