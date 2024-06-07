import React from "react";
import "./CalendarEntry.css";
import {Link} from "react-router-dom";
import {Tag} from "../../../../atoms/Tag";

interface CalenderEntryProps {
  title: string;
  address: string;
  tags: string[];
  day: number;
  month: string;
  year: number;
  id: number;
}

export const CalendarEntry: React.FC<CalenderEntryProps> = (props: CalenderEntryProps) => {
  const {title, address, tags, day, month, id} = props;

  return (
      <div className="calendar-entry">
        <div className="date">
          <Link to={`/event/?id=${id}`} className="event-tag-button">
            <div className="date-background">
              <div className="day">{day}</div>
              <div className="month">{month}</div>
            </div>
          </Link>
        </div>
        <Link to={"/event/?id=" + id} className="event-link">
          <div className="event-title">{title}</div>
        </Link>
        <div className="address">{address}</div>
        {tags && tags.length > 0 && (
            <div className="event-tags">
              {tags.map((tag: string, index: number) => (tag && (
                  <Tag key={index} name={tag} index={index} isEventTag={true}/>
              )))}
            </div>
        )}
      </div>
  );
}