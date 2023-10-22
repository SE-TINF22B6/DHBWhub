import React from "react";
import "./Posts.css";
import {Post} from "./Post";

export const Posts = () => {

  return (
      <div className="posts">
        <Post
            title={"Wie man erfolgreich Leads generiert!"}
            shortDescription={"Short Description"}
            tags={["Marketing", "BWL", "Dozenten"]}
            author={"Dietmar Bohn"}
            date={"2 days ago"}
            likes={43}
            comments={453}
            imageSrc={"../assets/image-example.svg"}></Post>
      </div>
  );
}