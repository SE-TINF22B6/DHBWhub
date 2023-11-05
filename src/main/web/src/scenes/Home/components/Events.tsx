import React from "react";
import "./Events.css";
import {CalendarEntry} from "./CalendarEntry";

export const Events = () => {
  return (
  <div className="events">
    <div className="overlap-group-wrapper">
      <div className="next-events-label-wrapper">
        <div className="next-events-label">...</div>
      </div>
    </div>
    <div className="next-events">
      <div className="overlap-3">
        <div className="next-events-label-2">Next Events</div>
        <div className="events-layout">
          <div className="event">
            <CalendarEntry
                title={"Feierabend-Treff"}
                address={"DHBW Mensa"}
                day={4} month={"OKT"}
                tags={["Party", "Freizeit"]}></CalendarEntry>
          </div>

          <div className="event-2">
            <CalendarEntry
                title={"Semester-Opening-Party mit HKA"}
                address={"Moltkestraße 30, 76133 Karlsruhe"}
                day={6} month={"OKT"}
                tags={["Party", "Freizeit"]}></CalendarEntry>
            <div className="date-2">
              <div className="overlap-4">
                <div className="day">6</div>
                <div className="month">OKT</div>
              </div>
            </div>
          </div>


          <div className="event-2">
            <CalendarEntry
                title={"StuV Karlsruhe Kneipentour"}
                address={"DHBW Haupteingang"}
                day={12} month={"OKT"}
                tags={["Party", "Freizeit"]}></CalendarEntry>
          </div>

          <div className="event-2">
            <CalendarEntry
                title={"StuV Karlsruhe Stadtrallye"}
                address={"Schlosspark"}
                day={14} month={"OKT"}
                tags={["Party", "Freizeit"]}></CalendarEntry>
          </div>

          <div className="event-2">
            <CalendarEntry
                title={"Feierabendgrillen"}
                address={"DHBW Mensa"}
                day={26} month={"OKT"}
                tags={["Beerpong", "Freizeit"]}></CalendarEntry>
          </div>

        </div>
      </div>
    </div>
  </div>
  );
};
