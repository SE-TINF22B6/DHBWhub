import React from "react";
import "./Events.css";
import {CalendarEntry} from "./CalendarEntry";
import {Link} from "react-router-dom";
import {dummyEvents} from "./dummyEvents";

export const Events = () => {
  return (
      <div className="events">
        <Link to={"/calendar"} className="link">
          <div className="component-headline">Next Events</div>
        </Link>
        <div className="events-layout">
          {dummyEvents.map(event => (
              <div className="event" key={event.id}>
                <CalendarEntry
                    title={event.title}
                    address={event.address}
                    day={event.day}
                    month={event.month}
                    year={event.year}
                    tags={event.tags}
                    id={event.id}
                />
              </div>
          ))}
        </div>
      </div>
  );
};