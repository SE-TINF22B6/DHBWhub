import React from "react";
import "./CalendarEntry.css";

interface CalenderEntryProps {
  title: string;
  address: string;
  tags: string[];
  day: number;
  month: string;
}

export const CalendarEntry: React.FC<CalenderEntryProps> = (props: CalenderEntryProps) => {
  const {
    title,
    address,
    tags,
    day,
    month,
  } = props;

  return (
      <div className="calendar-entry">
        <div className="title">{title}</div>
        <div className="address">{address}</div>
        <div className="tags">
          <div className="tag">
            <div className="overlap-group-2">
              <div className="tag1-background"></div>
              <div className="tag1">{tags[0]}</div>
            </div>
          </div>
          <div className="overlap-wrapper">
            <div className="overlap-group-2">
              <div className="tag2-background"></div>
              <div className="tag-2">{tags[1]}</div>
            </div>
          </div>
        </div>
        <div className="date">
          <div className="overlap-4">
            <div className="day">{day}</div>
            <div className="month">{month}</div>
          </div>
        </div>
      </div>
  );
}