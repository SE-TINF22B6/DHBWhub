import React from "react";
import "./index.css";
import {Header} from "./components/Header";
import ModalComponent from "./components/Modal";

export const Home = (): JSX.Element => {
  return (
      <div className="homepage">
        <Header></Header>
        <ModalComponent></ModalComponent>
      </div>
  );
};
