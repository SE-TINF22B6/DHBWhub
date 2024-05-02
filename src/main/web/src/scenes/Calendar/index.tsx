import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import "react-big-calendar/lib/css/react-big-calendar.css";
import events from "./events";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";

moment.updateLocale('de', {
  week: {
    dow: 1,
  },
});

export const Calendar = (): JSX.Element => {
  return (
      <div className="calendar">
        <Header/>
        <div className="calendar-content">
          <div className="ad-wrapper">
            <div className="ad-info">Anzeige</div>
            <a href="https://a.check24.net/misc/click.php?pid=638229&aid=168&deep=handytarife&cat=7" target="_blank">
              <img src="https://a.check24.net/misc/view.php?pid=638229&aid=168&cat=7" width="160" height="600"
                   className="calendar-ad" alt="Ad"/>
            </a>
          </div>
          <Calendar
              className="calendar"
              localizer={localizer}
              events={events}
              startAccessor="start"
              endAccessor="end"
              style={{height: 610}}
              eventPropGetter={
                (event, start, end, isSelected) => {
                  let newStyle = {backgroundColor: "var(--red)", color: 'var(--white)'};
                  return {style: newStyle};
                }
              }
              dayPropGetter={
                (day) => {
                  let newStyle = {color: 'var(--white)', backgroundColor: 'transparent'};
                  return {style: newStyle};
                }
              }
          />
          <div className="ad-wrapper">
            <div className="ad-info">Anzeige</div>
            <a href="https://a.check24.net/misc/click.php?pid=638229&aid=338&deep=c24bank&cat=14" target="_blank">
              <img src="https://a.check24.net/misc/view.php?pid=638229&aid=338&cat=14" width="160" height="600"
                   className="calendar-ad" alt="Ad"/>
            </a>
          </div>
        </div>
        <Footer/>
      </div>
  );
};
