import React from "react";
import "./CalendarEntry.css";
import {Link} from "react-router-dom";

interface CalenderEntryProps {
  title: string;
  address: string;
  tags: string[];
  day: number;
  month: string;
  id: number;
}

export const CalendarEntry: React.FC<CalenderEntryProps> = (props: CalenderEntryProps) => {
  const {
    title,
    address,
    tags,
    day,
    month,
    id,
  } = props;

  return (
      <div className="calendar-entry">
        <Link to={"/event/?id=" + id} className="event-link">
          <div className="event-title">{title}</div>
        </Link>
        <div className="address">{address}</div>
        {tags && tags.length > 0 && (
            <div className="event-tags">
              {tags.map((tag: string, index: number) => (tag && (
                  <div className="event-tag" key={index}>
                    {tag}
                  </div>
              )))}
            </div>
        )}
        <div className="date">
          <div className="date-background">
            <div className="day">{day}</div>
            <div className="month">{month}</div>
          </div>
        </div>
      </div>
  );
}