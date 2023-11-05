import React from "react";
import "./index.css";
import {Header} from "./components/Header";
import {Events} from "./components/Events";
import {CreatePost} from "./components/CreatePost";
import {Posts} from "./components/Posts";
import {Infos} from "./components/Infos";
import {SavedPosts} from "./components/SavedPosts";
import {SortOptions} from "./components/SortOptions";
import {PopularTags} from "./components/PopularTags";
import ModalComponent from "./components/Modal";

export const Home = (): JSX.Element => {
  return (
      <div className="homepage">
        <Header></Header>
        <div className="body">
          <div className="sidebar-left">
            <SortOptions></SortOptions>
            <PopularTags></PopularTags>
            <SavedPosts></SavedPosts>
          </div>
          <div className="posts">
            <CreatePost></CreatePost>
            <Posts></Posts>
          </div>
          <div className="sidebar-right">
            <Events></Events>
            <Infos></Infos>
          </div>
        </div>
      </div>
  );
};
