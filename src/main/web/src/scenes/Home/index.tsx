import React from "react";
import "./index.css";
import {Header} from "./components/header/Header";

export const Home = (): JSX.Element => {
  return (
      <div className="homepage">
        <Header></Header>
      </div>
  );
};
