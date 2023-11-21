import React from "react";
import "./index.css";
import {Header} from "./components/header/Header";
import {Posts} from "./components/post/Posts";

export const Home = (): JSX.Element => {
  return (
      <div className="homepage">
        <Header></Header>
        <div className="body">
          <div className="sidebar-left">

          </div>
          <div className="middle-content">
            <Posts></Posts>
          </div>
          <div className="sidebar-right">

          </div>
        </div>
      </div>
  );
};
