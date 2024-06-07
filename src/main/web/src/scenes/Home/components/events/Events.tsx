import React, {useEffect, useMemo, useState} from "react";
import { CalendarEntry } from "./CalendarEntry";
import { Link } from "react-router-dom";
import config from "../../../../config/config";
import { EventModel } from "./models/EventModel";
import {getJWT} from "../../../../services/AuthService";
import "./Events.css";

export const Events = () => {
  const [events, setEvents] = useState<EventModel[]>();
  const sortedEvents = [...(events || [])].sort((a: EventModel, b: EventModel) => a.startDate - b.startDate);
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);

  useEffect((): void => {
    const fetchEvents = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "event/homepage-preview-events", {
          headers: headersWithJwt
        });
        if (response.ok) {
          const data = await response.json();
          setEvents(data);
        } else {
          console.log(new Error("Failed to fetch events"));
        }
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };
    fetchEvents();
  }, [headersWithJwt]);

  const months: string[] = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];

  if (sortedEvents?.length === 0) {
    return null;
  }

  return (
      <div className="events">
        <Link to={"/calendar"} className="link">
          <div className="component-headline">Next Events</div>
        </Link>
        <div className="events-layout">
          {sortedEvents.map(event => {
            const date: Date = new Date(event.startDate * 1000);
            const day: number = date.getDate();
            const monthIndex: number = date.getMonth();
            const month: string = months[monthIndex];
            const year: number = date.getFullYear();

            return (
                <div className="event" key={event.id}>
                  <CalendarEntry
                      title={event.title}
                      address={event.location}
                      day={day}
                      month={month}
                      year={year}
                      tags={event.tags}
                      id={event.id}
                  />
                </div>
            );
          })}
        </div>
      </div>
  );
};