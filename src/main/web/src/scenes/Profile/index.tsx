import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";

export const Profile = () => {
  return (
      <div className="profile">
        <Header/>
        <img style={{position: "relative", marginTop: "20px", marginBottom: "20px"}} className="profile-picture" alt="Profile" src={process.env.PUBLIC_URL + '/assets/profile.svg'}/>
        <Footer/>
        <MobileFooter/>
      </div>
  );
};
