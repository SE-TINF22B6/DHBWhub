import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const Calendar = (): JSX.Element => {
export const CalendarPage = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  return (
      <div className="calendar-page">
        <Header/>
        <h1 className="loading">Work in progress</h1>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};
