import React, { useEffect, useState } from "react";
import "./Events.css";
import { CalendarEntry } from "./CalendarEntry";
import { Link } from "react-router-dom";
import { dummyEvents } from "./dummyEvents";
import config from "../../../../config/config";
import { EventModel } from "./models/EventModel";

export const Events = () => {
  const [events, setEvents] = useState<EventModel[]>(dummyEvents);
  const sortedEvents = [...events].sort((a, b) => a.startDate - b.startDate);

  useEffect(() => {
    const fetchEvents = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "event/homepage-preview-events", {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
          }
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
  }, []);

  const months = [
    "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
    "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
  ];

  return (
      <div className="events">
        <Link to={"/calendar"} className="link">
          <div className="component-headline">Next Events</div>
        </Link>
        <div className="events-layout">
          {sortedEvents.map(event => {
            const date = new Date(event.startDate * 1000);
            const day = date.getDate();
            const monthIndex = date.getMonth();
            const month = months[monthIndex];
            const year = date.getFullYear();

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