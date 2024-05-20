import React, {useState} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {getProfilePicture} from "../../services/ProfilePictureService";

export const Profile = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  const [profilePicture, setProfilePicture] = useState(process.env.PUBLIC_URL + '/assets/profile.svg');

  const loadProfilePicture = () => {
      const username = localStorage.getItem('user');
  }

  return (
      <div className="profile">
        <Header/>
        <img style={{position: "relative", marginTop: "20px", marginBottom: "20px"}} className="profile-picture" alt="Profile" src={profilePicture}/>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};
